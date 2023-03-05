package jinny.springboot.springkiwi.service;

import jinny.springboot.springkiwi.data.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class WebClientMemberService implements RequestMemberService {

	private final WebClient webClient;


	@Override
	public String getName() {
		return webClient.get()
				.uri("/api/v1/web-api")
				.retrieve()
				.bodyToMono(String.class)
				.block();
	}

	@Override
	public String getNameWithPathVariable() {
		ResponseEntity<String> responseEntity = webClient.get()
				.uri("/api/v1/web-api/{name}", "WebClient Kassy")
				.retrieve()
				.toEntity(String.class)
				.block();

		return responseEntity.getBody();
	}

	@Override
	public String getNameWithParameter() {
		return webClient.get()
				.uri(uriBuilder -> uriBuilder
						.path("/api/v1/web-api")
						.queryParam("name", "WebClient queryParam Kassy!")
						.build()
				).exchangeToMono(clientResponse -> {
					if (clientResponse != null && clientResponse.statusCode().equals(HttpStatus.OK)) {
						return clientResponse.bodyToMono(String.class);
					} else {
						return clientResponse.createException().flatMap(Mono::error);
					}
				})
				.block();
	}

	@Override
	public ResponseEntity<MemberDTO> postWithParamAndBody() {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setName("kassy");
		memberDTO.setEmail("kassy@gmail.com");
		memberDTO.setOrganization("org");

		ResponseEntity<MemberDTO> responseEntity = webClient.post()
				.uri(uriBuilder -> uriBuilder
						.path("/api/v1/web-api")
						.queryParam("name", "q kassy")
						.queryParam("email", "q kassy@gmail.com")
						.build())
				.bodyValue(memberDTO)
				.retrieve()
				.toEntity(MemberDTO.class)
				.block();
		return responseEntity;
	}

	@Override
	public ResponseEntity<MemberDTO> postWithHeader() {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setName("kassy");
		memberDTO.setEmail("kassy@gmail.com");
		memberDTO.setOrganization("org");

		ResponseEntity<MemberDTO> responseEntity = webClient.post()
				.uri("/api/v1/web-api/add-header")
				.header("my-header", "Kassy-Kiwi")
				.bodyValue(memberDTO)
				.retrieve()
				.toEntity(MemberDTO.class)
				.block();
		return responseEntity;
	}

	@Override
	public String callLongTime(int second) {
		webClient.get()
				.uri("/api/v1/web-api/time-long/{second}", second)
				.retrieve()
				.bodyToMono(String.class)
				.subscribe(this::printResult);		// 비동기 콜백 함수

		return "비동기 요청";
	}

	private void printResult(String result) {
		System.out.println(result);
	}
}
