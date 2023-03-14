package jinny.springboot.springkiwi.data.repository;

import jinny.springboot.springkiwi.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	User getByUid(String uid);
}
