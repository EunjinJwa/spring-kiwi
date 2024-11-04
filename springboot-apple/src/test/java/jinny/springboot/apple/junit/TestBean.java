package jinny.springboot.apple.junit;

import jinny.springboot.apple.junit.model.Passenger;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestBean {

    @Bean
    Passenger createPassenger() {
        Passenger kassy = new Passenger("Kassy");
        kassy.setIsRegistered(true);

        return kassy;
    }

}
