package com.sheryians.s2todoapp.services;

import com.sheryians.s2todoapp.model.CustomUserDetail;
import com.sheryians.s2todoapp.model.User;
import com.sheryians.s2todoapp.repositary.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("userService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByEmail(name);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found !!"));
        return optionalUser.map(CustomUserDetail::new).get();
    }
}
