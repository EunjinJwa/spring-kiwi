package jinny.springboot.apple.junit;

import jinny.springboot.apple.junit.model.Passenger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Import(TestBean.class)
public class PassengerRegisterationTest {

    @Autowired
    private Passenger passenger;

    @Test
    public void passengerImportTest() {
        assertTrue(passenger.isRegistered());
    }
}
