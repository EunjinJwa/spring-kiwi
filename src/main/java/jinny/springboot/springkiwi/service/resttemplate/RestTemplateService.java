package jinny.springboot.springkiwi.service.resttemplate;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {

	@Bean
	public RestTemplate restTemplate() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

		HttpClient client = HttpClientBuilder.create()
				.setMaxConnTotal(500)
				.setMaxConnPerRoute(500)
				.build();

		CloseableHttpClient httpClient = HttpClients.custom()
				.setMaxConnTotal(500)
				.setMaxConnPerRoute(500)
				.build();

		factory.setHttpClient(httpClient);
		factory.setConnectTimeout(2000);
		factory.setReadTimeout(5000);
		RestTemplate restTemplate = new RestTemplate(factory);

		return restTemplate;

	}



}
