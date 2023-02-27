package jinny.springboot.springkiwi.config.annotation;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TelephoneValidator.class)
public @interface Telephone {

	String message() default "전화번호 형식이 지정된 길이보다 짧습니다.";		// 유효성 검사 실패시 메세지
	Class[] groups() default {};		// 유효성 검사를 사용하는 그룹
	Class[] payload() default {};		// 사용자가 추가 정보를 위해 전달하는 값

}
