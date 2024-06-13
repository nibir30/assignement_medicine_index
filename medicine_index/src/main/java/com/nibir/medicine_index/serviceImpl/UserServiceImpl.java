package com.nibir.medicine_index.serviceImpl;

import com.nibir.medicine_index.config.auth.JwtTokenUtil;
import com.nibir.medicine_index.data.ReqData.LoginReqData;
import com.nibir.medicine_index.data.ReqData.UserRegisterReqData;
import com.nibir.medicine_index.data.ResData.LoginInfo;
import com.nibir.medicine_index.data.ResData.LoginResData;
import com.nibir.medicine_index.data.ResData.RegistrationResData;
import com.nibir.medicine_index.data.ResData.core.JwtResponseData;
import com.nibir.medicine_index.model.RoleModel;
import com.nibir.medicine_index.model.UserModel;
import com.nibir.medicine_index.repository.RoleRepository;
import com.nibir.medicine_index.repository.UserRepository;
import com.nibir.medicine_index.service.UserService;
import com.nibir.medicine_index.util.IdGenerator;
import com.nibir.medicine_index.util.ResponseUtils;
import com.nibir.medicine_index.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public ResponseEntity<?> getAllUser() {
        List<UserModel> userModels = userRepository.findAll();
        List<RegistrationResData> usersRes = new ArrayList<>();
        for (UserModel userModel : userModels) {
            RegistrationResData registrationResData = new RegistrationResData();
            BeanUtils.copyProperties(userModel, registrationResData);
            List<String> userroles = new ArrayList<>();
            for (RoleModel roleModel : userModel.getRoleModels()) {
                userroles.add(roleModel.getRole_name());
            }
            registrationResData.setRoleNames(userroles);

            usersRes.add(registrationResData);
        }
        return ResponseUtils.dataSuccess("All users", usersRes);
    }

    @Override
    public ResponseEntity<?> findUserByEmail(String email) {
        UserModel userModel = userRepository.findByUserId(email);
        if (userModel == null) {
            return ResponseUtils.validationError("User not found!");
        }
        RegistrationResData registrationResData = new RegistrationResData();

        BeanUtils.copyProperties(userModel, registrationResData);
        return ResponseUtils.dataSuccess("Successfully got user data", registrationResData);
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateProfile(RegistrationResData registrationResData) {
        try {
            UserModel userModel = userRepository.findByUserId(registrationResData.getUserId());
            if (registrationResData.getAddress() != null) {
                userModel.setAddress(registrationResData.getAddress());
            }

            if (registrationResData.getPhoneNo() != null) {
                userModel.setPhoneNo(registrationResData.getPhoneNo());
            }
            userRepository.save(userModel);
            return ResponseUtils.dataSuccess("Successfully updated profile", registrationResData);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("Could not update profile", e.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> saveUser(UserRegisterReqData userRegisterReqData) {
        if (!StringUtils.hasText(userRegisterReqData.getUserId())) {
            return ResponseUtils.validationError("Invalid user ID");
        } else if (!StringUtils.hasText(userRegisterReqData.getFirstName())) {
            return ResponseUtils.validationError("Invalid First Name");
        } else if (!StringUtils.hasText(userRegisterReqData.getLastName())) {
            return ResponseUtils.validationError("Invalid Last Name");
        } else if (!StringUtils.hasText(userRegisterReqData.getPassword())) {
            return ResponseUtils.validationError("Invalid password");
        }

        try {
            if (userRepository.findByUserId(userRegisterReqData.getUserId()) != null) {
                return ResponseUtils.validationError("User already exists!");
            }
            String hashedPassword = passwordEncoder.encode(userRegisterReqData.getPassword());
            Long id = IdGenerator.generateId();
            List<RoleModel> roleModels = new ArrayList<>();
            for (Long roleId : userRegisterReqData.getRoleIds()) {
                Optional<RoleModel> nullableRoleModel = roleRepository.findById(roleId);
                if (nullableRoleModel.isEmpty()) {
                    return ResponseUtils.validationError("Role does not exist");
                }
                roleModels.add(nullableRoleModel.get());
            }

            UserModel newUserModel = new UserModel().builder()
                    .firstName(userRegisterReqData.getFirstName())
                    .lastName(userRegisterReqData.getLastName())
                    .lastName(userRegisterReqData.getFirstName() + " " +userRegisterReqData.getLastName())
                    .imageId(userRegisterReqData.getImageId())
                    .password(hashedPassword)
                    .id(id)
                    .userId(userRegisterReqData.getUserId())
                    .phoneNo(userRegisterReqData.getPhoneNo())
                    .insertTime(LocalDateTime.now())
                    .userStatus("Y")
                    .roleModels(roleModels)
                    .address(userRegisterReqData.getAddress())
                    .email(userRegisterReqData.getUserId())
                    .passwordExpiryDate(LocalDateTime.now().plusDays(182))
                    .build();
            UserModel savedUserModel = userRepository.save(newUserModel);
            RegistrationResData registrationResData = new RegistrationResData();
            BeanUtils.copyProperties(savedUserModel, registrationResData);
            List<String> userRoles = new ArrayList<>();

            for (RoleModel roleModel : savedUserModel.getRoleModels()) {
                userRoles.add(roleModel.getRole_name());
            }
            registrationResData.setRoleNames(userRoles);
            return ResponseUtils.dataSuccess("User created successfully", registrationResData);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseUtils.exceptionError("Unable to create user", ex.getMessage());
        }

    }

    @Override
    @Transactional
    public ResponseEntity<?> login(LoginReqData loginReqData) {
        try {
            if (!org.springframework.util.StringUtils.hasText(loginReqData.getUserId()) || !org.springframework.util.StringUtils.hasText(loginReqData.getPassword())) {
                return ResponseUtils.validationError("Invalid user ID or password!");
            }

            final UserModel securityUserModel = userRepository.findByUserId(loginReqData.getUserId().trim());
            if (null == securityUserModel) {
                return ResponseUtils.validationError("User doesn't exist!");
            }

            if (!passwordEncoder.matches(loginReqData.getPassword().trim(), securityUserModel.getPassword())) {
                return ResponseUtils.validationError("Wrong password!");
            }

            LocalDateTime passwordExpiryDate = securityUserModel.getPasswordExpiryDate();

            if (!securityUserModel.getUserStatus().equals("Y")) {
                return ResponseUtils.validationError("User doesn't exist!");
            }
            if (passwordExpiryDate.isBefore(LocalDateTime.now())) {
                return ResponseUtils.validationError("Password has expired!");
            }

            List<String> roleNames = new ArrayList<>();

            for (RoleModel roleModel : securityUserModel.getRoleModels()) {
                roleNames.add(roleModel.getRole_name());
            }

            final String token = jwtTokenUtil.generateToken(securityUserModel);
            JwtResponseData jwtResponseData = new JwtResponseData("Bearer", token);

            LoginResData loginResData = new LoginResData();

            LoginInfo loginInfo = new LoginInfo();

            BeanUtils.copyProperties(securityUserModel, loginInfo);
            loginInfo.setRoleNames(roleNames);

            loginResData.setUserInfo(loginInfo);
            loginResData.setTokenInfo(jwtResponseData);

            return ResponseUtils.dataSuccess("User Login successful", loginResData);

        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("Login failed", e.getMessage());
        }
    }
}
