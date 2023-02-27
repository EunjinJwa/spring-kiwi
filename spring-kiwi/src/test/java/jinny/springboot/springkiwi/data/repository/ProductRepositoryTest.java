package jinny.springboot.springkiwi.data.repository;

import jinny.springboot.springkiwi.data.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)		// Replace.NONE : 애플리케이션에서 사용하는 데이터베이스로 사용 가
public class ProductRepositoryTest {

	@Autowired
	ProductRepository productRepository;

	@Test
	@DisplayName("")
	void saveTest() {
	    // given
		Product product = Product.builder()
				.name("kiwi")
				.price(7000)
				.stock(12)
				.build();

	    //when
		Product saveProduct = productRepository.save(product);

	    //then
		Assertions.assertThat(saveProduct.getName()).isEqualTo(product.getName());
		Assertions.assertThat(saveProduct.getPrice()).isEqualTo(product.getPrice());
	}


}
