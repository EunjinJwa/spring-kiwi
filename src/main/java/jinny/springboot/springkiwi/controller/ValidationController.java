package jinny.springboot.springkiwi.controller;

import jinny.springboot.springkiwi.data.dto.ValidRequestDTO;
import jinny.springboot.springkiwi.data.dto.ValidatedRequestDTO;
import jinny.springboot.springkiwi.data.group.ValidationGroup1;
import jinny.springboot.springkiwi.data.group.ValidationGroup2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api/validation")
@RestController
public class ValidationController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping("/valid")
	public ResponseEntity<String> checkValidationByValid(
			@Valid @RequestBody ValidRequestDTO requestDTO) {

		logger.info(requestDTO.toString());
		return ResponseEntity.ok(requestDTO.toString());
	}

	@PostMapping("/validated")
	public ResponseEntity<String> checkValidationByValidated(
			@Validated @RequestBody ValidatedRequestDTO validatedRequestDTO
	) {
		logger.info(validatedRequestDTO.toString());
		return ResponseEntity.ok(validatedRequestDTO.toString());
	}

	@PostMapping("/validated/group1")
	public ResponseEntity<String> checkValidationByValidatedForGroup1(
			@Validated(ValidationGroup1.class) @RequestBody ValidatedRequestDTO validatedRequestDTO
	) {
		logger.info(validatedRequestDTO.toString());
		return ResponseEntity.ok(validatedRequestDTO.toString());
	}

	@PostMapping("/validated/group2")
	public ResponseEntity<String> checkValidationByValidatedForGroup2(
			@Validated(ValidationGroup2.class) @RequestBody ValidatedRequestDTO validatedRequestDTO
	) {
		logger.info(validatedRequestDTO.toString());
		return ResponseEntity.ok(validatedRequestDTO.toString());
	}

	@PostMapping("/validated/all-group")
	public ResponseEntity<String> checkValidationByValidatedAllGroup(
			@Validated({ValidationGroup1.class, ValidationGroup2.class}) @RequestBody ValidatedRequestDTO validatedRequestDTO
	) {
		logger.info(validatedRequestDTO.toString());
		return ResponseEntity.ok(validatedRequestDTO.toString());
	}
}
