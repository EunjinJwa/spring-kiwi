package jinny.springboot.springkiwi.controller;

import jinny.springboot.springkiwi.controller.base.BaseController;
import jinny.springboot.springkiwi.data.dto.ProductDTO;
import jinny.springboot.springkiwi.data.dto.ProductNameDTO;
import jinny.springboot.springkiwi.data.dto.ProductResponseDTO;
import jinny.springboot.springkiwi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/product")
@RestController
public class ProductController {

	private final ProductService productService;

	@GetMapping(value = "/{number}")
	public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long number) {
		ProductResponseDTO productResponseDTO = productService.getProduct(number);

		return ResponseEntity.ok(productResponseDTO);
	}

	@PostMapping
	public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductDTO productDTO) {
		ProductResponseDTO productResponseDTO = productService.saveProduct(productDTO);

		return ResponseEntity.ok(productResponseDTO);
	}

	@PutMapping
	public ResponseEntity<ProductResponseDTO> changeProductName(@RequestBody ProductNameDTO productNameDTO) throws Exception {
		ProductResponseDTO productResponseDTO = productService.changeProductName(productNameDTO.getNumber(), productNameDTO.getName());

		return ResponseEntity.ok(productResponseDTO);
	}

	@DeleteMapping("/{number}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long number) throws Exception {
		productService.deleteProduct(number);

		return ResponseEntity.ok("Deleted.");
	}
}
