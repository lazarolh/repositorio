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

import com.example.Producto.model.Product;
//import com.example.Producto.model.Producto;
import com.example.Producto.model.Supplier;
import com.example.Producto.service.SupplierService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/Suppliers")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
@Tag(name = "Supplier", description =  "Provides methods to manage the supplier")
public class SupplierController {
    
	@Autowired
	private SupplierService service;
	
	@Operation(summary= "Get all Supplier")
	@ApiResponse(responseCode = "200", description = "Suppliers found", content ={
			@Content(mediaType= "application/json", array= @ArraySchema(schema = @Schema(implementation = Product.class))) })	
	@GetMapping
	public List<Supplier> getAll() {
		return service.getAll();
	}

	@Operation(summary= "Get Supplier by ID")
	@ApiResponse(responseCode = "200", description = "Suppliers found", content ={
			@Content(mediaType= "application/json", array= @ArraySchema(schema = @Schema(implementation = Product.class))) })	
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		try{
		Supplier proveedor = service.getById(id);
		return new ResponseEntity<Supplier>(proveedor, HttpStatus.OK);
		}catch (NoSuchElementException e){
			return new ResponseEntity<String >("supplier not found",HttpStatus.NOT_FOUND);
		}	
	}

	@Operation(summary= "Register Supplier")
	@ApiResponse(responseCode = "200", description = "Register Supplier", content ={
			@Content(mediaType= "application/json", array= @ArraySchema(schema = @Schema(implementation = Product.class))) })	
	@PostMapping
	public ResponseEntity<String> registrar(@RequestBody Supplier proveedor) {
		try {
			service.save(proveedor);
			return new ResponseEntity<>("Saved supplier", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error saving supplier", HttpStatus.BAD_REQUEST);
		}
	}


@Operation(summary= "Update Supplier by ID")
@ApiResponse(responseCode = "200", description = "Suppliers found", content ={
		@Content(mediaType= "application/json", array= @ArraySchema(schema = @Schema(implementation = Product.class))) })	
    @PutMapping("{id}")
    public ResponseEntity<String> update(@RequestBody Supplier proveedor, @PathVariable Integer id) {
        try {
            Supplier existingProveedor = service.getById(id); 
            proveedor.setId(existingProveedor.getId()); 
            service.save(proveedor); 
            return new ResponseEntity<>("Updated supplier", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("supplier not found", HttpStatus.NOT_FOUND);
        }
    } 

	@Operation(summary= "Delete Supplier by ID ")
	@ApiResponse(responseCode = "200", description = "Delete Supplier by ID ", content ={
			@Content(mediaType= "application/json", array= @ArraySchema(schema = @Schema(implementation = Product.class))) })	
			@DeleteMapping("{id}")
			public ResponseEntity<?> delete(@PathVariable Integer id) {
				try {
					service.getById(id);
					service.delete(id);
					return new ResponseEntity<>("Deleted supplier", HttpStatus.OK);
				} catch (NoSuchElementException e) {
					return new ResponseEntity<>("Supplier not found", HttpStatus.NOT_FOUND);
				} catch (Exception e) {
					return new ResponseEntity<>("Error deleting supplier: " + e.getMessage(), HttpStatus.BAD_REQUEST);
				}
			}
			
}
