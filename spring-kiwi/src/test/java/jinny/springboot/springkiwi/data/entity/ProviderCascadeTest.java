package jinny.springboot.springkiwi.data.entity;

import jinny.springboot.springkiwi.data.repository.ProductRepository;
import jinny.springboot.springkiwi.data.repository.ProviderRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@SpringBootTest
class ProviderCascadeTest {

	@Autowired
	ProviderRepository providerRepository;

	@Autowired
	ProductRepository productRepository;

	@Test
	@DisplayName("provider 저장으로 영속성 전이를 통해 product 데이터도 함께 생성된다.")
	void cascadeTest() {
		Provider provider = saveProvider("공급업체 A");

		Product product1 = saveProduct("상품1", 1000, 1000);
		Product product2 = saveProduct("상품2", 500, 15);
		Product product3 = saveProduct("상품3", 780, 33);

//		// 연관관계 설정
		product1.setProvider(provider);
		product2.setProvider(provider);
		product3.setProvider(provider);

		provider.setProducts(Arrays.asList(product1, product2, product3));

		providerRepository.save(provider);
	}

	@Test
	@Transactional
	void orphanRemovalTest() {
		Provider provider = saveProvider("공급업체 A");

		Product product1 = saveProduct("상품1", 1000, 1000);
		Product product2 = saveProduct("상품2", 500, 15);
		Product product3 = saveProduct("상품3", 780, 33);

		//		// 연관관계 설정
		product1.setProvider(provider);
		product2.setProvider(provider);
		product3.setProvider(provider);

		provider.getProducts().addAll(Lists.newArrayList(product1, product2, product3));

		providerRepository.saveAndFlush(provider);

		providerRepository.findAll().forEach(System.out::println);
		productRepository.findAll().forEach(System.out::println);

		// provider 삭제
		Provider foundProvider = providerRepository.findById(1L).get();
		foundProvider.getProducts().remove(0);

		providerRepository.findAll().forEach(System.out::println);
		productRepository.findAll().forEach(System.out::println);
	}




	private Provider saveProvider(String name) {
		Provider provider = new Provider();
		provider.setName(name);

		return provider;
	}

	private Product saveProduct(String name, int price, int stock) {
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setStock(stock);

		return product;
	}

}
