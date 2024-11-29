package com.example.Producto.Controller;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.Producto.model.Category;
import com.example.Producto.model.Product;
import com.example.Producto.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
@Tag(name = "Category", description =  "Provides methods for managing Category")

public class CategoryController {
	@Autowired
	private CategoryService service;


	@Operation(summary= "Get all Category")
	@ApiResponse(responseCode = "200", description = "Categories found", content ={
			@Content(mediaType= "application/json", array= @ArraySchema(schema = @Schema(implementation = Product.class))) })	
	@GetMapping
	public List<Category> getAll() {
		return service.getAll();
	}

	@Operation(summary= "Get categories by ID")
	@ApiResponse(responseCode = "200", description = "category found", content ={
			@Content(mediaType= "application/json", array= @ArraySchema(schema = @Schema(implementation = Product.class))) })	
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		try {
		Category categoria = service.getById(id);
		return new ResponseEntity<Category>(categoria, HttpStatus.OK);
		}catch (NoSuchElementException e){
			return new ResponseEntity<String >("Category not found",HttpStatus.NOT_FOUND);	}
	}

	@Operation(summary= "Register category")
	@ApiResponse(responseCode = "200", description = "Register category", content ={
			@Content(mediaType= "application/json", array= @ArraySchema(schema = @Schema(implementation = Product.class))) })	
	@PostMapping
	public ResponseEntity<?> register(@RequestBody Category categoria) {
		try {
		service.save(categoria);
		return new ResponseEntity<String>("Saved record", HttpStatus.OK);
	} catch (Exception e){
			return new ResponseEntity< >("Error saving category" ,HttpStatus.BAD_REQUEST);
		}	
	}

	@Operation(summary= "Update category by ID")
	@ApiResponse(responseCode = "200", description = "Update category by ID", content ={
			@Content(mediaType= "application/json", array= @ArraySchema(schema = @Schema(implementation = Product.class))) })	
 @PutMapping("{id}")
    public ResponseEntity<String> update(@RequestBody Category categoria, @PathVariable Integer id) {
        try {
            Category existingCategoria = service.getById(id); 
            categoria.setId(existingCategoria.getId()); 
            service.save(categoria); 
            return new ResponseEntity<>("Updated record", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        }
    } 

	@Operation(summary= "Delete category by ID")
	@ApiResponse(responseCode = "200", description = "Delete category by ID", content ={
			@Content(mediaType= "application/json", array= @ArraySchema(schema = @Schema(implementation = Product.class))) })
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try{
		service.delete(id);
		return new ResponseEntity<String>("Deleted Category", HttpStatus.OK);
	}  catch (NoSuchElementException e) {
            return new ResponseEntity<String>("Category not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error deleting Category", HttpStatus.BAD_REQUEST);
        }


}
}
