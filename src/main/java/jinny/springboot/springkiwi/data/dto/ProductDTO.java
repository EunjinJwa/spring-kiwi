package jinny.springboot.springkiwi.data.dto;

import jinny.springboot.springkiwi.data.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
public class ProductDTO {

	private String name;
	private int price;
	private int stock;

	public Product getProductEntity() {
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setStock(stock);
		product.setCreatedAt(LocalDateTime.now());
		product.setUpdatedAt(LocalDateTime.now());
		return product;
	}
}
