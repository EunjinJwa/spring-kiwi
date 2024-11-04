package jinny.springboot.apple;

import jinny.springboot.apple.junit.bean.FlightBuilder;
import jinny.springboot.apple.junit.model.Country;
import jinny.springboot.apple.junit.model.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.Map;

@SpringBootApplication
@Import(FlightBuilder.class)
public class SpringbootAppleApplication {

	@Autowired
	private Map<String, Country> countriesMap;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAppleApplication.class, args);
	}

	@Bean
	CommandLineRunner configureRepository(CountryRepository countryRepository) {
		return args -> {
			countryRepository.saveAll(countriesMap.values());
		};
	}
}
