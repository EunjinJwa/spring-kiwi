package jinny.springboot.springkiwi.data.repository;

import jinny.springboot.springkiwi.data.entity.Category;
import jinny.springboot.springkiwi.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryRepositoryTest {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Test
	public void relationshipCategoryTest() {
		Category category = new Category();
		category.setCode("A");
		category.setName("Code1");

		categoryRepository.save(category);

		Product product = new Product();
		product.setName("Book");
		product.setPrice(3000);
		product.setStock(20);
		product.setCategory(category);

		productRepository.save(product);


		Category savedCategory = categoryRepository.findById(category.getId()).get();
		System.out.println(savedCategory);
		System.out.println(savedCategory.getProducts());


	}


}
