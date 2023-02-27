package jinny.springboot.springkiwi.data.dto;

import jinny.springboot.springkiwi.config.annotation.Telephone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter @Setter
@AllArgsConstructor
@Builder
public class ValidRequestDTO {

	// @Null : Null 값만 허용
	// @NotNull : Null을 허용하지 않음. "", " "는 허용
	// @NotEmpty : null, ""을 허용하지 않음. " "는 허용
	// @NotBlank : null, "", " " 모두 허용하지 않음
	@NotBlank
	String name;
	@Email
	String email;
	@Telephone
	String phoneNumber;
	// DecimalMin(value = "$numberString") : $numberString 이상의 값을 허용
	// DecimalMax(value = "$numberString") : $numberString 이하의 값을 허용
	// @Min(value = $number) : $number 이상의 값을 허용
	// @Max(value = $number) : $number 이하의 값을 허용
	@Min(value = 20) @Max(value = 40)
	int age;
	@Size(min = 0, max = 40)
	String description;
	// @Positive : 양수를 허용
	// @PositiveOrZero : 0을 포함한 양수를 허용
	// @Negative : 음수를 허용
	// @NegativeOrZero : 0을 포함한 음수를 허용
	@Positive
	int count;
	@AssertTrue
	boolean booleanCheck;
}
