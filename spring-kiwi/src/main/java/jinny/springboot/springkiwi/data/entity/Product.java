package jinny.springboot.springkiwi.data.entity;

import lombok.*;

import javax.persistence.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "product")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long number;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer price;

	@Column(nullable = false)
	private Integer stock;

	@OneToOne(mappedBy = "product")
	@ToString.Exclude
	private ProductDetail productDetail;

	@ManyToOne
	@JoinColumn(name = "provider_id")
	private Provider provider;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

}
