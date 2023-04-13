package chang.changspring.service;

import chang.changspring.user.SignUp;
import chang.changspring.user.SignUpRepository;
import chang.changspring.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {

    private final SignUpRepository signUpRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SignUp> _signUp = this.signUpRepository.findByUsername(username);

        if (_signUp.isEmpty()) {
            throw new UsernameNotFoundException("해당 사용자는 데이터베이스에 없습니다.");
        }

        SignUp signUp = _signUp.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }

        return new User(signUp.getUsername(), signUp.getPassword(), authorities);
    }
}