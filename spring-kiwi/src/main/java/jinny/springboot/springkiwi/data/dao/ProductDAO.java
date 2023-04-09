package jinny.springboot.springkiwi.data.dao;

import jinny.springboot.springkiwi.data.entity.Product;

import java.util.List;

public interface ProductDAO {

	Product insertProduct(Product product);

	Product selectProduct(Long number);

	List<Product> selectProducts();

	Product updateProductName(Long number, String name) throws Exception;

	void deleteProduct(Long number) throws Exception;
}
