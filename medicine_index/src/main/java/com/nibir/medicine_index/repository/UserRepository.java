package com.nibir.medicine_index.repository;

import com.nibir.medicine_index.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUserId(String userId);

    List<UserModel> findByBloodGroup(String bloodGroup);
}
