package com.dateapp.dateapp.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);
    List<User> findAllByUserInfo_GenderIdentity(String genderIdentity);
}
