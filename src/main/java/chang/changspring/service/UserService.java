package chang.changspring.service;

import chang.changspring.user.SignUp;
import chang.changspring.user.SignUpRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final SignUpRepository signUpRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(SignUpRepository signUpRepository , PasswordEncoder passwordEncoder) {
        this.signUpRepository = signUpRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public SignUp create(String username , String password, String email){
        SignUp user = new SignUp();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        this.signUpRepository.save(user);
        return user;
    }
}
