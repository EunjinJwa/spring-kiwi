package jinny.springboot.springkiwi.data.repository;

import jinny.springboot.springkiwi.data.entity.Product;
import jinny.springboot.springkiwi.data.entity.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProviderRepositoryTest {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProviderRepository providerRepository;


	@Test
	void relationshipProviderTest() {
		Provider provider = new Provider();
		provider.setName("공장A");

		providerRepository.save(provider);

		Product product = new Product();
		product.setName("Book");
		product.setPrice(3000);
		product.setStock(20);
		product.setProvider(provider);

		productRepository.save(product);

		Product savedProduct = productRepository.findById(product.getNumber()).get();
		System.out.println(savedProduct);
		System.out.println(savedProduct.getProvider());
	}

	@Test
	void relationshipProviderTest2() {
		Provider provider = new Provider();
		provider.setName("공장A");

		providerRepository.save(provider);

		Product product = new Product();
		product.setName("Book");
		product.setPrice(3000);
		product.setStock(20);
		product.setProvider(provider);

		productRepository.save(product);

		Product product2 = new Product();
		product2.setName("Note");
		product2.setPrice(3000);
		product2.setStock(20);
		product2.setProvider(provider);

		productRepository.save(product2);

		Provider savedProvider = providerRepository.findById(provider.getId()).get();
		System.out.println(savedProvider);
		System.out.println(savedProvider.getProducts());


	}
}
