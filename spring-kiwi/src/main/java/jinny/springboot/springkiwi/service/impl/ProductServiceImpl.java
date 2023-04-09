package jinny.springboot.springkiwi.service.impl;

import jinny.springboot.springkiwi.data.dao.ProductDAO;
import jinny.springboot.springkiwi.data.dto.ProductDTO;
import jinny.springboot.springkiwi.data.dto.ProductResponseDTO;
import jinny.springboot.springkiwi.data.entity.Product;
import jinny.springboot.springkiwi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

	private final ProductDAO productDAO;


	@Override

	public ProductResponseDTO getProduct(Long number) {
		Product product = productDAO.selectProduct(number);

		return ProductResponseDTO.from(product);
	}

	@Override
	public ProductResponseDTO saveProduct(ProductDTO productDTO) {
		Product product = productDTO.getProductEntity();
		Product savedProduct = productDAO.insertProduct(product);

		return ProductResponseDTO.from(savedProduct);
	}

	@Override
	public ProductResponseDTO changeProductName(Long number, String name) throws Exception {
		Product updatedProduct = productDAO.updateProductName(number, name);

		return ProductResponseDTO.from(updatedProduct);
	}

	@Override
	public void deleteProduct(Long number) throws Exception {
		productDAO.deleteProduct(number);
	}

	@Override
	public List<ProductResponseDTO> getProductAll() {
		List<Product> products = productDAO.selectProducts();
		return products.stream()
				.map(ProductResponseDTO::from)
				.collect(Collectors.toList());
	}
}
