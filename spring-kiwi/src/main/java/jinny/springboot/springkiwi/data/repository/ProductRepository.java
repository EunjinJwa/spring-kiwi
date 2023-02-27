package jinny.springboot.springkiwi.data.repository;

import jinny.springboot.springkiwi.data.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {


}
