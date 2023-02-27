package jinny.springboot.springkiwi.controller;

import jinny.springboot.springkiwi.data.dto.MemberDTO;
import jinny.springboot.springkiwi.service.RestMemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest-template")
public class RestTemplateController {

	private final RestMemberService restMemberService;

	public RestTemplateController(RestMemberService restMemberService) {
		this.restMemberService = restMemberService;
	}

	@GetMapping
	public String getName() {
		return restMemberService.getName();
	}

	@GetMapping("/path-variable")
	public String getNameWithPathVariable(){
		return restMemberService.getNameWithPathVariable();
	}

	@GetMapping("/parameter")
	public String getNameWithParameter(){
		return restMemberService.getNameWithParameter();
	}

	@PostMapping
	public ResponseEntity<MemberDTO> postDto(){
		return restMemberService.postWithParamAndBody();
	}

	@PostMapping("/header")
	public ResponseEntity<MemberDTO> postWithHeader(){
		return restMemberService.postWithHeader();
	}

	@GetMapping("/time-long/1")
	public String timeLong1() {
		return restMemberService.callLongTimeWithRestBean();
	}

	@GetMapping("/time-long/2")
	public String timeLong2() {
		return restMemberService.callLongTimeWithNewRest();
	}
}
