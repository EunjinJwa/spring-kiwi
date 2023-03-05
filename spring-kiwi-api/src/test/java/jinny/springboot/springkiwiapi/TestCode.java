package jinny.springboot.springkiwiapi;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class TestCode {

	@Test
	public void intStreamTest() {
		IntStream.range(0, 10)
				.forEach(System.out::println);
	}
}
