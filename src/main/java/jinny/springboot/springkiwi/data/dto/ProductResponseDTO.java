package jinny.springboot.springkiwi.data.dto;

import jinny.springboot.springkiwi.data.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductResponseDTO {

	private Long number;
	private String name;
	private int price;
	private int stock;

	public static ProductResponseDTO from(Product product) {
		return new ProductResponseDTO(product.getNumber(), product.getName(), product.getPrice(), product.getStock());
	}
}
