package chang.changspring.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// 테이블에 데이터를 CRUD(Create(insert), Read(Select), Update, Delete) 연산하는 로직 역할
public interface SignUpRepository extends JpaRepository<SignUp,Long> {//JPA repository를 상속 받아야 한다.

    Optional<SignUp> findByUsername(String username);
    SignUp findByUsernameAndEmail(String username , String email);
    List<SignUp> findByEmailLike(String email);
}
