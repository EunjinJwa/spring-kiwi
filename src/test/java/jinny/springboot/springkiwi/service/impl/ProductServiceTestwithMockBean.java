package jinny.springboot.springkiwi.service.impl;

import jinny.springboot.springkiwi.data.dao.ProductDAO;
import jinny.springboot.springkiwi.data.dto.ProductDTO;
import jinny.springboot.springkiwi.data.dto.ProductResponseDTO;
import jinny.springboot.springkiwi.data.entity.Product;
import jinny.springboot.springkiwi.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class ProductServiceTestwithMockBean {

	private ProductService productService;
	@MockBean
	private ProductDAO productDAO;		// Spring에 mock객체를 등록해서 주입함.


	@BeforeEach
	public void setUpTest() {
		productService = new ProductServiceImpl(productDAO);
	}

	@Test
	@DisplayName("mockito를 사용한 getProduct 테스트")
	void getProductTest() {
	    // given
		Product givenProduct = Product.builder()
				.number(12L)
				.name("kiwi")
				.price(7000)
				.stock(12)
				.build();

		Mockito.when(productDAO.selectProduct(12L))
				.thenReturn(givenProduct);

	    //when
		ProductResponseDTO productResponseDTO = productService.getProduct(12L);

		//then
		Assertions.assertThat(productResponseDTO.getName()).isEqualTo(givenProduct.getName());
		Assertions.assertThat(productResponseDTO.getPrice()).isEqualTo(givenProduct.getPrice());
		Assertions.assertThat(productResponseDTO.getStock()).isEqualTo(givenProduct.getStock());

		verify(productDAO).selectProduct(12L);
	}

	@Test
	@DisplayName("mokito save product Test")
	void saveProductTest() {
	    // given
		ProductDTO product = new ProductDTO("kiwi", 7000, 12);

		Mockito.when(productDAO.insertProduct(any(Product.class)))
				.then(returnsFirstArg());

		//when
		ProductResponseDTO saveProduct = productService.saveProduct(product);

		//then
		Assertions.assertThat(saveProduct.getName()).isEqualTo(product.getName());
		Assertions.assertThat(saveProduct.getPrice()).isEqualTo(product.getPrice());
	}

	






}