package com.nibir.medicine_index.config.auth;

import com.nibir.medicine_index.config.auth.dto.JwtUserData;
import com.nibir.medicine_index.config.auth.dto.SecurityUserDetails;
import com.nibir.medicine_index.constants.ErrorStatusCode;
import com.nibir.medicine_index.exception.ErrorDetails;
import com.nibir.medicine_index.exception.UnauthorizedUserException;
import com.nibir.medicine_index.service.UserProfileDetailsService;
import com.nibir.medicine_index.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserProfileDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
        try {
            return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String requestTokenHeader = request.getHeader("Authorization");
        String url = request.getRequestURL().toString();

        String jwtToken = null;
        JwtUserData jwtUserData = null;
        if (requestTokenHeader == null) {
            requestTokenHeader = request.getHeader("token");
        }
        // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ") && requestTokenHeader.length() > 7) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                jwtUserData = jwtTokenUtil.getJwtUserDataFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                throw new UnauthorizedUserException("UNABLE TO GET JWT TOKEN", 400);
            } catch (ExpiredJwtException e) {
                writeCustomUnAuthorizedJwtExpireException(request, response);
//                throw new UnauthorizedUserException("JWT TOKEN HAS EXPIRED", 400);
            }
        } else if (!StringUtils.containsWordsAfterRemoving(url, SecurityConfig.AUTH_WHITELIST, "/**")) {
            writeCustomUnAuthorizedException(request, response);
        }

        //Once we get the token validate it.
        if (jwtUserData != null
                && SecurityContextHolder.getContext().getAuthentication() == null) {

            SecurityUserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtUserData.getUserId());
            if (userDetails.getUserId() != null && jwtTokenUtil.validateToken(jwtUserData, jwtToken, userDetails)) {
                // if token is valid configure Spring Security to manually set authentication
                if ("N".equals(userDetails.getUserStatus())) {
                    request.setAttribute("CUSTOM-FILTER-ERROR", ErrorStatusCode.USER_NOT_ACTIVE.getValue());
                    throw new UnauthorizedUserException("USER NOT ACTIVE YET ", 99);
                } else if (userDetails.getUserStatus() != null) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // After setting the Authentication in the context, we specify
                    // that the current user is authenticated. So it passes the Spring Security Configurations successfully.
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } else {
                    request.setAttribute("CUSTOM-FILTER-ERROR", ErrorStatusCode.INVALID_USER.getValue());
                    throw new UnauthorizedUserException("INVALID USER ", 99);
                }
            } else {
                writeCustomUnAuthorizedException(request, response);
            }
        }
        chain.doFilter(request, response);
    }


    private void writeCustomUnAuthorizedException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("CUSTOM-FILTER-ERROR", ErrorStatusCode.JWT_TOKEN_EXPIRED.getValue());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        OutputStream responseStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        ErrorDetails customErrorData = new ErrorDetails("INVALID JWT TOKEN",
                "98");
        mapper.writeValue(responseStream, customErrorData);
        responseStream.flush();
    }

    private void writeCustomUnAuthorizedJwtExpireException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("CUSTOM-FILTER-ERROR", ErrorStatusCode.JWT_TOKEN_EXPIRED.getValue());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        OutputStream responseStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        ErrorDetails customErrorData = new ErrorDetails("YOUR TOKEN HAS EXPIRED, PLEASE LOGIN AGAIN",
                "90");
        mapper.writeValue(responseStream, customErrorData);
        responseStream.flush();
    }

}
