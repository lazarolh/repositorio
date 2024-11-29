package com.example.Producto.Controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.Producto.model.Product;
import com.example.Producto.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/products") 
	@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
			RequestMethod.PUT }) 
	@Tag(name = "Product", description =  "Provides methods for managing Product")
	public class ProductController {


@Autowired
private ProductService service; 
	@Operation(summary= "Get all products")
	@ApiResponse(responseCode = "200", description = "Get all products", content = {
		@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Product.class)))
	})
	
	@GetMapping
	public List<Product> getAll() {
		return service.getAll();
	}
	
	@Operation(summary= "Get by ID  products")
	@ApiResponse(responseCode = "200", description = "Get Product by ID", content = {
		@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Product.class)))
	})
	
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		try { 
		Product producto = service.getById(id);
		return new ResponseEntity<Product>(producto, HttpStatus.OK); 
		}catch (NoSuchElementException e){
			return new ResponseEntity<String >("Product not found",HttpStatus.NOT_FOUND);	}
	}

	@Operation(summary= "Register product")
	@ApiResponse(responseCode = "200", description = "Register product", content ={
			@Content(mediaType= "application/json", array= @ArraySchema(schema = @Schema(implementation = Product.class))) })
	@PostMapping
	public ResponseEntity<?> registrar(@RequestBody Product producto) {
		try {
		service.save(producto);
		return new ResponseEntity<String>("Saved Product", HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity< >("Error saving product" ,HttpStatus.BAD_REQUEST);
		}	
	}

	@Operation(summary= "Update Product by ID")
	@ApiResponse(responseCode = "200", description = "Update Product by ID", content ={
			@Content(mediaType= "application/json", array= @ArraySchema(schema = @Schema(implementation = Product.class))) })
	@PutMapping("{id}")
    public ResponseEntity<String> update(@RequestBody Product producto, @PathVariable Integer id) {
        try {
            Product existingProducto = service.getById(id); 
            producto.setId(existingProducto.getId()); 
            service.save(producto); 
            return new ResponseEntity<>("Updated Product", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>("Error updating product",HttpStatus.BAD_REQUEST);
        }
    } 
	@Operation(summary= "Delete product by ID")
	@ApiResponse(responseCode = "200", description = "Delete product by ID", content ={
	@Content(mediaType= "application/json", array= @ArraySchema(schema = @Schema(implementation = Product.class))) })
	@DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            service.delete(id);
            return new ResponseEntity<String>("Product disposed correctly", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<String>("Product not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error deleting product", HttpStatus.BAD_REQUEST);
        }
    }	
	


}
