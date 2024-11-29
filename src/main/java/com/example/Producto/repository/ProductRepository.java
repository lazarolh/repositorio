package com.example.Producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Producto.model.Product;

@Repository

public interface ProductRepository extends JpaRepository<Product, Integer>
{
    
}
   
