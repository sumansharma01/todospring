package com.suman.demo.repository;

import com.suman.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<UserEntity,Long> {
}
