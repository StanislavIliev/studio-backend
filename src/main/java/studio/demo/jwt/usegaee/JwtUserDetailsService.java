package studio.demo.jwt.usegaee;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService {
    public UserDetails loadUserByUsername(String username) {
        return null;
    }
}
