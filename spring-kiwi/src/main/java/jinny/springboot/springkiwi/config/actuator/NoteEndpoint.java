package jinny.springboot.springkiwi.config.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "note")
public class NoteEndpoint {

	private Map<String, Object> noteContent = new HashMap<String, Object>();

	/**
	 * GET /actuator/note
	 */
	@ReadOperation
	public Map<String, Object> getNote() {
		return noteContent;
	}

	/**
	 * POST /actuator/note
	 * {key: "", value: ""}
	 */
	@WriteOperation		// POST
	public Map<String, Object> writeNote(String key, Object value) {
		noteContent.put(key, value);
		return noteContent;
	}

	/**
	 * queryString
	 * DELETE /actuator/note?key={key}
	 */
	@DeleteMapping		// DELETE
	public Map<String, Object> deleteNote(String key) {
		noteContent.remove(key);
		return noteContent;
	}

}
