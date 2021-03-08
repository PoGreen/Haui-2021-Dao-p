package com.haui.dao.repositories;


import com.haui.dao.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);
}
