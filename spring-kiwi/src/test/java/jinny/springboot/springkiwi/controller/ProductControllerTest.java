package jinny.springboot.springkiwi.controller;

import com.google.gson.Gson;
import jinny.springboot.springkiwi.data.dto.ProductDTO;
import jinny.springboot.springkiwi.data.dto.ProductResponseDTO;
import jinny.springboot.springkiwi.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ProductService productService;

	@Test
	@DisplayName("request getProduct 테스트")
	void getProductTest() throws Exception {
	    // given
		given(productService.getProduct(12L))
				.willReturn(new ProductResponseDTO(12L, "peach", 5000, 5));
		String productId = "12";

		// when - then
		mockMvc.perform(get("/api/product/" + productId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.number").exists())
				.andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.price").exists())
				.andExpect(jsonPath("$.stock").exists())
				.andDo(print());

		verify(productService).getProduct(12L);
	}

	@Test
	@DisplayName("Product 데이터 생성 테스트")
	void createProductTest() throws Exception {
		Gson gson = new Gson();
	    // given
		ProductDTO product = new ProductDTO("kiwi", 7000, 12);

		given(productService.saveProduct(product))
				.willReturn(new ProductResponseDTO(1L, "kiwi", 7000, 12));

		//when - then
		mockMvc.perform(
						post("/api/product")
								.content(gson.toJson(product))
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.number").exists())
				.andExpect(jsonPath("$.name").value("kiwi"))
				.andExpect(jsonPath("$.price").value(7000))
				.andExpect(jsonPath("$.stock").value(12))
				.andDo(print());

		verify(productService).saveProduct(product);

	}



}