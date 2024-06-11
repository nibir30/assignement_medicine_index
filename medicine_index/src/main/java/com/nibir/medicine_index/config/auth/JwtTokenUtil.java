package com.nibir.medicine_index.config.auth;

import com.nibir.medicine_index.config.auth.dto.JwtUserData;
import com.nibir.medicine_index.config.auth.dto.SecurityUser;
import com.nibir.medicine_index.exception.UnauthorizedUserException;
import com.nibir.medicine_index.model.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    // public static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60;
    // public static final long JWT_TOKEN_VALIDITY = (365 * 24) * 60 * 60;
    public static final long JWT_TOKEN_SHORTLY = 60;
    public static final int TOKEN_EXPIRE_TIME_11PM = 10; // 23
    public static final String USER_ID = "userId";
    public static final String USER_TYPE = "userType";
    public static final String USER_STATUS = "userStatus";
    public static final String USER_ROLE = "userrRole";
    private static final Long serialVersionUID = -2550185165626007488L;
    @Value("${jwt.secret}")
    private String secret;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public JwtUserData getJwtUserDataFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        JwtUserData jwtUserData;
        if (claims.get(USER_ID) != null) {
            jwtUserData = new JwtUserData();
            jwtUserData.setUserId(claims.get(USER_ID) != null ? claims.get(USER_ID).toString() : null);
            jwtUserData.setUserStatus(claims.get(USER_STATUS) != null ? (claims.get(USER_STATUS).toString()) : null);
            jwtUserData.setUserType(claims.get(USER_TYPE) != null ? claims.get(USER_TYPE).toString() : null);
            jwtUserData.setUserRole(claims.get(USER_ROLE) != null ? claims.get(USER_ROLE).toString() : null);
        } else {
            throw new UnauthorizedUserException("Invalid JWT token", 99);
        }
        return jwtUserData;
    }

    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//    return Jwts.parserBuilder().
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Boolean ignoreTokenExpiration(String token) {
        // here you specify tokens, for that the expiration is ignored
        return false;
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userType", "type-1");

        return doGenerateToken(claims, userDetails.getUsername());
    }

    public String generateToken(UserModel securityUserModel) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(USER_ID, securityUserModel.getUserId());
        claims.put(USER_STATUS, securityUserModel.getUserStatus());

        return doGenerateToken(claims, securityUserModel.getUserId().toString());
    }
   /* public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(USER_NAME, userName);
        // claims.put(TELLER_ID, securityUser.getTellerId());
        return doGenerateToken(claims, userName);
    }*/

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(getTokenExpireDate()).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Date getTokenExpireDate() {
        Date expireDate;

        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);

//        if (currentHour > 22) { // current date is after 10pm expire date will be next day 11:59PM
//            Calendar now = Calendar.getInstance();
//            now.add(Calendar.DAY_OF_YEAR, 90); // Add 90 days to the current date
//
//            calendar.set(Calendar.YEAR, now.get(Calendar.YEAR));
//            calendar.set(Calendar.MONTH, now.get(Calendar.MONTH));
//            calendar.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH));
//        }
        //        nibir
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_YEAR, 90); // Add 90 days to the current date

        calendar.set(Calendar.YEAR, now.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, now.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH));
        //        nibir done
        calendar.set(Calendar.HOUR_OF_DAY, TOKEN_EXPIRE_TIME_11PM);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        expireDate = calendar.getTime();
        return expireDate;
    }

    private String doGenerateIssueAccessToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_SHORTLY * 1000)).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean canTokenBeRefreshed(String token) {
        return (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = getUsernameFromToken(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }

    public Boolean validateToken(JwtUserData jwtUserData, String token, SecurityUser userDetails) {
        return (jwtUserData != null && jwtUserData.getUserId().equals(userDetails.getUserId())
                && !isTokenExpired(token));
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
