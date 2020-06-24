package com.challenge.service.serviceImpl;
import com.challenge.model.User;
import com.challenge.repository.UserRepository;
import com.challenge.service.UserService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByUsername(login);
    }

    public User save(User user,String action){
        if(userRepository.existsByUsername(user.getUsername()) && action.equals("POST")){
            throw new IllegalArgumentException("username já existe na base");
        }
        user.setUsername(user.getUsername().toLowerCase());
        return this.userRepository.save(user);
    }

    @Override
    public Page<User> findAll(Predicate predicate, Pageable pageable) {
        Page<User> logPage = userRepository.findAll(predicate,pageable);
        return logPage;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
