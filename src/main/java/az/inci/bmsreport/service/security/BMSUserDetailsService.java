package az.inci.bmsreport.service.security;

import az.inci.bmsreport.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BMSUserDetailsService implements UserDetailsService {

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = service.getById(userId);
        if (user.getUserId() == null)
            throw new UsernameNotFoundException("İstifadəçi tapılmadı!");
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (user.isAdmin())
            authorities.add((new SimpleGrantedAuthority("ADMIN")));

        return new org.springframework.security.core.userdetails
                .User(user.getUserId(), user.getPassword(), authorities);
    }
}
