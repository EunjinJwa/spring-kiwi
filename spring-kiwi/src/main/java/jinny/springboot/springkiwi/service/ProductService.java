package jinny.springboot.springkiwi.service;

import jinny.springboot.springkiwi.data.dto.ProductDTO;
import jinny.springboot.springkiwi.data.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {

	ProductResponseDTO getProduct(Long number);

	ProductResponseDTO saveProduct(ProductDTO productDTO);

	ProductResponseDTO changeProductName(Long number, String name) throws Exception;

	void deleteProduct(Long number) throws Exception;

	List<ProductResponseDTO> getProductAll();
}
