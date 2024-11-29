package com.example.Producto.Controller;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RestController;
import com.example.Producto.model.Category;
//import com.example.Producto.model.Product;
import com.example.Producto.service.CategoryService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.media.ArraySchema;
// import io.swagger.v3.oas.annotations.media.Content;
// import io.swagger.v3.oas.annotations.media.Schema;
// import io.swagger.v3.oas.annotations.responses.ApiResponse;
// import io.swagger.v3.oas.annotations.tags.Tag;

@Controller

public class CategoryControllerGQL {
	@Autowired
	private CategoryService service;
	@QueryMapping
	public List<Category> getAll() {
		return service.getAll();
	}

	@QueryMapping
	public Category  getById(@Argument Integer id) {
		try {
		Category categoria = service.getById(id);
		return categoria;
		}catch (NoSuchElementException e){
			return null;	}
	}
	//@QueryMapping
	@MutationMapping

	public Category register(@Argument Category categoria) {
		
		return service.save(categoria);	
	}

//@QueryMapping
@MutationMapping
    public Category update(@Argument Category categoria, @Argument Integer id) {
			return  service.save(categoria); 
    } 

	@MutationMapping
	public Boolean delete(@Argument Integer id) {
		
		service.delete(id);
		return true;
}
}
