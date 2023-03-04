package jinny.springboot.springkiwi.service;

import jinny.springboot.springkiwi.data.dto.MemberDTO;
import jinny.springboot.springkiwi.service.resttemplate.RestTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
//@Service
public class RestMemberService implements RequestMemberService {

	private final RestTemplate restTemplate;

	public String getName() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8082")
				.path("/api/v1/web-api")
				.encode()
				.build()
				.toUri();

		System.out.println("restTemplate: " + restTemplate);
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

		return responseEntity.getBody();
	}

	public String getNameWithPathVariable() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8082")
				.path("/api/v1/web-api/{name}")
				.encode()
				.build()
				.expand("Rest Kassy")
				.toUri();

		System.out.println("restTemplate: " + restTemplate);
		RestTemplate restTemplateNew = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplateNew.getForEntity(uri, String.class);

		return responseEntity.getBody();
	}

	public String getNameWithParameter() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8082")
				.path("/api/v1/web-api")
				.queryParam("name", "Kassy")
				.encode()
				.build()
				.toUri();

		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

		return responseEntity.getBody();
	}

	public ResponseEntity<MemberDTO> postWithParamAndBody() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8082")
				.path("/api/v1/web-api")
				.queryParam("name", "q Kassy")
				.queryParam("email", "q kassy@gmail.com")
				.queryParam("organization", "q org")
				.encode()
				.build()
				.toUri();

		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setName("kassy");
		memberDTO.setEmail("kassy@gmail.com");
		memberDTO.setOrganization("org");

		System.out.println("restTemplate: " + restTemplate);
		ResponseEntity<MemberDTO> responseEntity = restTemplate.postForEntity(uri, memberDTO, MemberDTO.class);

		return responseEntity;
	}

	public ResponseEntity<MemberDTO> postWithHeader() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8082")
				.path("/api/v1/web-api/add-header")
				.encode()
				.build()
				.toUri();

		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setName("kassy");
		memberDTO.setEmail("kassy@gmail.com");
		memberDTO.setOrganization("org");

		RequestEntity<MemberDTO> requestEntity = RequestEntity.post(uri)
				.header("my-header", "Kassy-Kiwi")
				.body(memberDTO);

		System.out.println("restTemplate: " + restTemplate);
		ResponseEntity<MemberDTO> responseEntity = restTemplate.exchange(requestEntity, MemberDTO.class);

		return responseEntity;
	}

	public String callLongTime(int second) {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8082")
				.path("/api/v1/web-api/time-long/{second}")
				.encode()
				.build()
				.expand(second)
				.toUri();

		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

		return responseEntity.getBody();
	}

}
