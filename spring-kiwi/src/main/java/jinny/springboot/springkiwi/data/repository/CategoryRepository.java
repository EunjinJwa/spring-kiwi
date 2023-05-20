package jinny.springboot.springkiwi.data.repository;

import jinny.springboot.springkiwi.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
