package jinny.springboot.springkiwi.data.dao;

import jinny.springboot.springkiwi.data.entity.Product;

import java.util.Optional;

public interface ProductDAO {

	Product insertProduct(Product product);

	Product selectProduct(Long number);

	Product updateProductName(Long number, String name) throws Exception;

	void deleteProduct(Long number) throws Exception;
}
