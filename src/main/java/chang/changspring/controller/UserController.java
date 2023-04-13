package chang.changspring.controller;

import chang.changspring.service.UserService;
import chang.changspring.user.UserCreateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor //의존성 주입 3가지 방법중에서 현업에서 가장 많이 쓰는 방법.
public class UserController {

    private final UserService userService;

    @GetMapping("/signup") // /user/signup으로 접근할 때 이용되는 메소드
    public String signup(UserCreateForm userCreateForm){
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "signup_form";
        }
        if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())){
            bindingResult.rejectValue("password2", "passwordIncorrect"
                    ,"password1과 password2가 동일하지 않습니다.");
        }

        try {
            userService.create(userCreateForm.getUsername(), userCreateForm.getPassword1(), userCreateForm.getEmail());
        }catch(DataIntegrityViolationException e){
            e.printStackTrace();
            bindingResult.reject("signupFailed","이미 가입되어 있는 사용자입니다.");
            return "signup_form";
        }catch(Exception e){
            e.printStackTrace();
            bindingResult.reject("signupFailed" ,e.getMessage());
            return "signup_form";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
     return "login_form";
    }
    //로그인 post는 Spring SecurityConfig에서 설정한 formLogin을 넣어주었기 때문에 SpringSecurity에서 자동으로 만들어준다.
}
