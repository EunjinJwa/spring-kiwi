package jinny.springboot.springkiwi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/hello")
@RestController
public class HelloController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping()
	public String hello() {
		logger.info("request : GET Hello");

		return "Hello Kiwi!";
	}

}
