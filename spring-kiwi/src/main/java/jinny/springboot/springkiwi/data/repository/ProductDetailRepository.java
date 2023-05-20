package jinny.springboot.springkiwi.data.repository;

import jinny.springboot.springkiwi.data.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
}
