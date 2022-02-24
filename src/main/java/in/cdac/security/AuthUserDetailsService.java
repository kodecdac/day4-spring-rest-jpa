package in.cdac.security;


import in.cdac.model.UserModel;
import in.cdac.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    UserModelRepository userModelRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserModel userModel =  userModelRepository.findByUsername(s);
        if(userModel == null) {
            throw new UsernameNotFoundException("Username not found!");
        }

        User user = new User(userModel.getUsername(), userModel.getPassword(), new ArrayList<>());
        return user;
    }
}
