package com.isco.repository;

import com.isco.model.User;
import com.isco.model.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, JpaRepository<User, Long>{
    User findByUsername(String username);
    Page<User> findAllByAuthoritiesContaining(Collection<? extends GrantedAuthority> role, Pageable pageable);
}
