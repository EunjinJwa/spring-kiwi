package jinny.springboot.springkiwi.data.dao;

import jinny.springboot.springkiwi.data.entity.Product;
import jinny.springboot.springkiwi.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ProductDAOImpl implements ProductDAO {

	private final ProductRepository productRepository;

	@Override
	public Product insertProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product selectProduct(Long number) {
		Optional<Product> selectedProduct = productRepository.findById(number);
		return selectedProduct.orElse(null);
	}

	@Override
	public Product updateProductName(Long number, String name) throws Exception {

		Optional<Product> selectedProduct = productRepository.findById(number);

		Product updatedProduct;
		if (selectedProduct.isPresent()) {
			Product product = selectedProduct.get();
			product.setName(name);
			product.setUpdatedAt(LocalDateTime.now());

			updatedProduct = productRepository.save(product);
		} else {
			throw new Exception();
		}
		return updatedProduct;
	}

	@Override
	public void deleteProduct(Long number) throws Exception {
		Optional<Product> selectedProduct = productRepository.findById(number);

		if (selectedProduct.isPresent()) {
			productRepository.delete(selectedProduct.get());
		} else {
			throw new Exception();
		}
	}
}
