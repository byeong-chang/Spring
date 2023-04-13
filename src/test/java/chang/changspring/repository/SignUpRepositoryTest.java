package chang.changspring.repository;

import chang.changspring.user.SignUp;
import chang.changspring.user.SignUpRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest // Test 에만 사용되는 코드라고 명시하는 것.
public class SignUpRepositoryTest {

    @Autowired // 의존성 주입 DI
    private SignUpRepository signUpRepository;

    @Test
    void testJpa(){
        SignUp user1= new SignUp();
        user1.setUsername("user1");
        user1.setPassword("user1user1");
        user1.setEmail("user1@user1.com");
        this.signUpRepository.save(user1);

        SignUp user2= new SignUp();
        user2.setUsername("user2");
        user2.setPassword("user2user2");
        user2.setEmail("user2@user2.com");
        this.signUpRepository.save(user2);
    }

    @Test
    void testJpa2(){
        List<SignUp> all = this.signUpRepository.findAll(); // 모든 유저를 긁어와서 로그인이 맞는지 확인하는데 사용할 수 있다.
        Assertions.assertEquals(2, all.size()); //두개의 인자값이 같은지 확인

        SignUp user = all.get(0); // username,password,email
        Assertions.assertEquals("user1",user.getUsername());
    }

    @Test
    void testJpa3(){
        Optional<SignUp> up = this.signUpRepository.findById(1L); //findById로 id를 기준으로 user를 찾을 수 있다.
                            //Optional은 null을 처리할 때 유용한 클래스이다
        if(up.isPresent()){ //그래서 null일 때도 작동하는 클래스이기 때문에 null이 아닌경우에만 실행하도록 if문을 사용했다.
            //get을 쓰기 위해서 한번 벗겨주는 과정이 필요하다.
            SignUp user = up.get();
            Assertions.assertEquals("user1",user.getUsername());
        }
    }

    @Test
    void testJpa4(){
        Optional<SignUp> user = this.signUpRepository.findByUsername("user2");
        if (user.isPresent()){
            SignUp option_user = user.get();
            //Assertions.assertEquals("user2@user2.com",user.getEmail());
        }
    }

    @Test
    void testJpa5(){
        SignUp user = this.signUpRepository.findByUsernameAndEmail("user1","user1@user1.com");
        Assertions.assertEquals("user1@user1.com",user.getEmail());
    }

    @Test
    void testJpa6(){
        List<SignUp> userList = this.signUpRepository.findByEmailLike("%com");
        SignUp user = userList.get(1);
        Assertions.assertEquals("user2@user2.com",user.getEmail());
    }

    @Test
    void testJpa7(){
        Optional<SignUp> user = this.signUpRepository.findById(7L);
        Assertions.assertTrue(user.isPresent());
        SignUp u = user.get();
        u.setEmail("lol@lol.com");
        this.signUpRepository.save(u);
        System.out.println(u.getEmail());
    }

    @Test
    void testJpa8(){
        Optional<SignUp> user = this.signUpRepository.findById(2L);
        Assertions.assertTrue(user.isPresent());
        SignUp u = user.get();
        this.signUpRepository.delete(u);//user2데이터를 의미
    }
}
