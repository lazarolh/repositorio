package com.example.Producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Producto.model.Category;

@Repository

public interface CategoryRepositori extends JpaRepository<Category, Integer> {

}
