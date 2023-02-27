package jinny.springboot.springkiwiapi.controller;

import jinny.springboot.springkiwiapi.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/web-api")
@RestController
public class WebController {

	@GetMapping
	public String getName() {
		return "Kassy";
	}

	@GetMapping(value = "/{variable}")
	public String getVariable(@PathVariable String variable) {
		return variable;
	}

	@GetMapping("/param")
	public String getNameWithParam(@RequestParam String name) {
		return "Hello. " + name + "!";
	}

	@PostMapping
	public ResponseEntity<MemberDto> getMember(
			@RequestBody MemberDto request,
			@RequestParam String name,
			@RequestParam String email,
			@RequestParam String organization
	) {
		System.out.println(request.getName());
		System.out.println(request.getEmail());
		System.out.println(request.getOrganization());

		MemberDto memberDto = new MemberDto();
		memberDto.setName(name);
		memberDto.setEmail(email);
		memberDto.setOrganization(organization);

		return ResponseEntity.status(HttpStatus.OK).body(memberDto);
	}

	@PostMapping(value = "/add-header")
	public ResponseEntity<MemberDto> addHeader(@RequestHeader("my-header") String header,
											   @RequestBody MemberDto memberDTO) {

		System.out.println(header);

		return ResponseEntity.status(HttpStatus.OK).body(memberDTO);
	}

	@GetMapping("/time-long")
	public String takeLongTime() throws InterruptedException {
		Thread.sleep(30000);
		return "OK";
	}


}
