package jinny.springboot.springkiwi.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product_detail")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductDetail extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@OneToOne(optional = false)	// not null
	@JoinColumn(name = "product_number")	// product_number 라는 컬럼 이름으로 참조키가 저장됨
	private Product product;
}
