package chang.changspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("name" , "민병창");
        model.addAttribute("age",25);

        return "practice/hello"; //해당하는 page로 모델 값을 보내라 라는 뜻이다.
    }
    @GetMapping("helloMvc")
    public String helloMvc(@RequestParam("name") String name_value, Model model){
        model.addAttribute("name",name_value);
        return "hello-template";
    }
    @GetMapping("ageName")
    public String ageName(@RequestParam("name") String name_val ,@RequestParam("age") int age_val ,Model model){
        model.addAttribute("name", name_val);
        model.addAttribute("age", age_val);
        return "ageName-template";
    }
    @GetMapping("form-test")
    public String formTest(){
        return "form-test";
    }
    @GetMapping("form-test-get")
    public String formTestGet(@RequestParam("title") String title_value,
                              @RequestParam("content") String content_value, Model model){
        model.addAttribute("title", title_value);
        model.addAttribute("content",content_value);
        return "form-test-get";
    }
    @GetMapping("form-test2")
    public String formTest2(){
        return "form-test2";
    }
    @PostMapping("form-test-post")
    public String formTestPost(@RequestParam("username") String username,
                              @RequestParam("password") String password, Model model){
        model.addAttribute("username", username);
        model.addAttribute("password",password);
        return "form-test-post";
    }
    @GetMapping("/")
    public String root(){
        return "index";
    }

}
