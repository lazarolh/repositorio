package com.example.Producto.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Producto.model.Category;
import com.example.Producto.repository.CategoryRepositori;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepositori repo;

    public List<Category> getAll() {
        return repo.findAll();
    
    }
    public Category save(Category categoria){
         return repo.save(categoria);
    }
public Category getById (Integer id){
    return repo.findById(id).get();
    //(idProducto).get();

}
public  void delete(Integer id){
 repo.deleteById(id);
}


}



