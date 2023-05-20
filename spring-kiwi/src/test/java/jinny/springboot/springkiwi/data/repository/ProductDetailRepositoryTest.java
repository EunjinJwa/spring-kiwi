package jinny.springboot.springkiwi.data.repository;

import jinny.springboot.springkiwi.data.entity.Product;
import jinny.springboot.springkiwi.data.entity.ProductDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductDetailRepositoryTest {

	@Autowired
	ProductDetailRepository productDetailRepository;

	@Autowired
	ProductRepository productRepository;

	@Test
	public void saveAndReadTest1() {
		Product product = new Product();
		product.setName("스프링부트 JPA");
		product.setPrice(300);
		product.setStock(20);

		productRepository.save(product);

		ProductDetail productDetail = new ProductDetail();
		productDetail.setProduct(product);
		productDetail.setDescription("스프링부트 JPA 디테일 북");

		productDetailRepository.save(productDetail);

		ProductDetail savedProductDetail = productDetailRepository.findById(productDetail.getId()).get();
		System.out.println(savedProductDetail);
		System.out.println(savedProductDetail.getProduct());
	}


}
