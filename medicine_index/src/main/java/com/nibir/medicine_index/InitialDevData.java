package com.nibir.medicine_index;

import com.nibir.medicine_index.model.LookupMediaType;
import com.nibir.medicine_index.model.RoleModel;
import com.nibir.medicine_index.model.UserModel;
import com.nibir.medicine_index.repository.LookupMediaTypeRepository;
import com.nibir.medicine_index.repository.RoleRepository;
import com.nibir.medicine_index.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
//@Profile("dev")
public class InitialDevData implements ApplicationRunner {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final LookupMediaTypeRepository lookupMediaTypeRepository;

    @Override
    public void run(ApplicationArguments args) {
        log.info(".... init application ...");
        List<RoleModel> roleModels = roleRepository.findAll();
        if (roleModels.isEmpty()) {
            RoleModel adminModel = RoleModel.builder()
                    .role_id(1L)
                    .active_yn("Y")
                    .role_name("ADMIN")
                    .build();
            RoleModel userModel = RoleModel.builder()
                    .role_id(2L)
                    .active_yn("Y")
                    .role_name("USER")
                    .build();
            roleRepository.save(adminModel);
            roleRepository.save(userModel);
        }
//
        List<UserModel> userModels = userRepository.findAll();
        if (userModels.isEmpty()) {
            Optional<RoleModel> roleModel = roleRepository.findById(1L);
            List<RoleModel> userRoleModels = new ArrayList<RoleModel>();
            userRoleModels.add(roleModel.get());
            UserModel adminUser = UserModel.builder()
                    .id(24021514114563204L)
                    .userId("admin@test.com")
                    .email("admin@test.com")
                    .firstName("Super")
                    .lastName("Admin")
                    .insertTime(LocalDateTime.now())
                    .userStatus("Y")
                    .address("Dhaka")
                    .phoneNo("01701005793")
                    .password(passwordEncoder.encode("123456"))
                    .roleModels(userRoleModels)
                    .passwordExpiryDate(LocalDateTime.now().plusDays(1000))
                    .build();
            userRepository.save(adminUser);

            Optional<RoleModel> userRoleModel = roleRepository.findById(2L);
            List<RoleModel> userRoleModels2 = new ArrayList<RoleModel>();
            userRoleModels2.add(userRoleModel.get());
            UserModel publicUser = UserModel.builder()
                    .id(24021514114563205L)
                    .userId("user@test.com")
                    .email("user@test.com")
                    .firstName("Nibir")
                    .lastName("Rahman")
                    .insertTime(LocalDateTime.now())
                    .userStatus("Y")
                    .address("Dhaka")
                    .phoneNo("01701002233")
                    .password(passwordEncoder.encode("123456"))
                    .roleModels(userRoleModels2)
                    .passwordExpiryDate(LocalDateTime.now().plusDays(1000))
                    .build();
            userRepository.save(publicUser);

        }
        List<LookupMediaType> lookupMediaTypes = lookupMediaTypeRepository.findAll();
        if (lookupMediaTypes.isEmpty()) {
            List<LookupMediaType> lookupMediaTypeList = new ArrayList<>();
            LookupMediaType image = LookupMediaType.builder()
                    .mediaTypeId(1L)
                    .mediaTypeName("image")
                    .maximumSize(2000L)
                    .build();
            lookupMediaTypeList.add(image);
            LookupMediaType audio = LookupMediaType.builder()
                    .mediaTypeId(2L)
                    .mediaTypeName("audio")
                    .maximumSize(2000L)
                    .build();
            lookupMediaTypeList.add(audio);
            LookupMediaType video = LookupMediaType.builder()
                    .mediaTypeId(3L)
                    .mediaTypeName("video")
                    .maximumSize(5000L)
                    .build();
            lookupMediaTypeList.add(video);
            LookupMediaType pdf = LookupMediaType.builder()
                    .mediaTypeId(4L)
                    .mediaTypeName("pdf")
                    .maximumSize(2000L)
                    .build();
            lookupMediaTypeList.add(pdf);
            LookupMediaType txt = LookupMediaType.builder()
                    .mediaTypeId(5L)
                    .mediaTypeName("txt")
                    .maximumSize(600L)
                    .build();
            lookupMediaTypeList.add(txt);
            lookupMediaTypeRepository.saveAll(lookupMediaTypeList);
        }
    }
}




