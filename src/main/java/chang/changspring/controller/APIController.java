package chang.changspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIController {

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name_value) {   //단순 값 반환
        return "안녕하세요? 반환된 너의 이름은...?" + name_value;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloAPI(@RequestParam("name") String name_value){
        Hello hello = new Hello();
        hello.setName(name_value);
        return hello;
    }
    static class Hello{
        private String name;
        public void setName(String name) {
            this.name = name;
        }
    }
}
