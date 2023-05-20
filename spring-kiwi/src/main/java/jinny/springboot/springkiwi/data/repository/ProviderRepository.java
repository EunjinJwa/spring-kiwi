package jinny.springboot.springkiwi.data.repository;

import jinny.springboot.springkiwi.data.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {

}
