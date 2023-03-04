package jinny.springboot.springkiwi.service;

import org.apache.http.HttpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WebClientService {

	@Bean
	public WebClient webClient() {

		WebClient webClient = WebClient.builder()
				.baseUrl("http://localhost:8082")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();

		return webClient;
	}

}
