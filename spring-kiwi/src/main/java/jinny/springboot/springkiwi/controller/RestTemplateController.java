package jinny.springboot.springkiwi.controller;

import jinny.springboot.springkiwi.data.dto.MemberDTO;
import jinny.springboot.springkiwi.service.RequestMemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest-template")
public class RestTemplateController {

	private final RequestMemberService requestMemberService;

	public RestTemplateController(RequestMemberService requestMemberService) {
		this.requestMemberService = requestMemberService;
	}

	@GetMapping
	public String getName() {
		return requestMemberService.getName();
	}

	@GetMapping("/path-variable")
	public String getNameWithPathVariable(){
		return requestMemberService.getNameWithPathVariable();
	}

	@GetMapping("/parameter")
	public String getNameWithParameter(){
		return requestMemberService.getNameWithParameter();
	}

	@PostMapping
	public ResponseEntity<MemberDTO> postDto(){
		return requestMemberService.postWithParamAndBody();
	}

	@PostMapping("/header")
	public ResponseEntity<MemberDTO> postWithHeader(){
		return requestMemberService.postWithHeader();
	}

	@GetMapping("/time-long/{second}")
	public String timeLong(@PathVariable Integer second) {
		return requestMemberService.callLongTime(second);
	}
}
