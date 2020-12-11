package Springapi.springapi.service;

import Springapi.springapi.repository.UserRepository;
import Springapi.springapi.entity.UserModel;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        UserModel login = userRepository.findByLogin(string);
        if (login == null) {
            throw new UsernameNotFoundException("user not found");
        }

        return new MyLogin(login);
    }

}
