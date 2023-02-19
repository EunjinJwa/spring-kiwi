package jinny.springboot.springkiwi.data.repository;

import jinny.springboot.springkiwi.data.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTestByH2 {

	@Autowired
	private ProductRepository productRepository;

	@Test
	@DisplayName("save test")
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

	@Test
	@DisplayName("get product test with h2")
	void getProductTest() {
	    // given
		Product product = Product.builder()
				.name("kiwi")
				.price(7000)
				.stock(12)
				.build();

		Product saveProduct = productRepository.save(product);

		//when
		Product findProduct = productRepository.findById(saveProduct.getNumber()).orElse(null);

	    //then
		assert findProduct != null;
		Assertions.assertThat(saveProduct.getNumber()).isEqualTo(findProduct.getNumber());
		Assertions.assertThat(saveProduct.getName()).isEqualTo(findProduct.getName());
	}


}
