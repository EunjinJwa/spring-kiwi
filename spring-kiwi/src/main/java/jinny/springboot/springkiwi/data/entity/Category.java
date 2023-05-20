package jinny.springboot.springkiwi.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String code;

	private String name;

	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER
	)
	@ToString.Exclude
	private List<Product> products = new ArrayList<>();

}
