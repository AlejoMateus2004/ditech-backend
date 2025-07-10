package com.backend_user_service.backend.repository;

import com.backend_user_service.backend.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long>{
}
