package com.example.Producto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Producto.model.Product;
import com.example.Producto.repository.ProductRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository repo;
 
    public List<Product> getAll() {
        return repo.findAll();
    
    }

 public void save(Product producto) {
    try {
        repo.save(producto);
    } catch (Exception e) {
        throw new RuntimeException("Error saving product", e);
    }
}

public Product getById (Integer id){
    return repo.findById(id).get();
    //(idProducto).get();

}
public void delete(Integer id){
repo.deleteById(id);
}


}
