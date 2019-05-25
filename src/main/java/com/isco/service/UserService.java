package com.isco.service;


import com.isco.model.User;
import com.isco.model.UserRole;
import com.isco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }

    public void getUserFromMapAndSave(@Valid Map<String, String> userMap) throws IllegalAccessException {
        User user = getUserFromMap(userMap);
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setRoles(Collections.singleton(UserRole.USER));
        userRepository.save(user);
    }

    public Page<User> getAllUsers(int page, int size){
        Page<User> users = userRepository.findAllByAuthoritiesContaining(Collections.singleton(UserRole.USER),  PageRequest.of(page, size));
        return users;
    }


    private User getUserFromMap(Map<String, String> userMap) throws IllegalAccessException {
        String username = userMap.get("username");
        String email = userMap.get("email");
        String password = userMap.get("password");
        String passwordConfirm = userMap.get("passwordConfirm");
        if (password.equals(passwordConfirm)){
            return new User(username, email, password, Collections.singleton(UserRole.USER));
        }
        return null;
    }


}
