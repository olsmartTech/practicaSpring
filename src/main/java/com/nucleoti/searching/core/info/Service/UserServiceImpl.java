package com.nucleoti.searching.core.info.Service;

import com.nucleoti.searching.core.info.model.User;
import com.nucleoti.searching.core.info.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User findUserById(Integer id) {
        return userRepository.findUserById(id);
    }

}
