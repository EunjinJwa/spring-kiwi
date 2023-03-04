package jinny.springboot.springkiwi.service;

import jinny.springboot.springkiwi.data.dto.MemberDTO;
import org.springframework.http.ResponseEntity;

public interface RequestMemberService {

	public String getName();

	public String getNameWithPathVariable();

	public String getNameWithParameter();

	public ResponseEntity<MemberDTO> postWithParamAndBody();

	public ResponseEntity<MemberDTO> postWithHeader();

	public String callLongTime(int second);

}
