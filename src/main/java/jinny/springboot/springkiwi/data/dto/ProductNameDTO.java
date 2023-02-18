package jinny.springboot.springkiwi.data.dto;

import jinny.springboot.springkiwi.data.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
public class ProductNameDTO {

	private Long number;
	private String name;

}
