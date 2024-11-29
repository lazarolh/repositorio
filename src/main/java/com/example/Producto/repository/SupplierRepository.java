package com.example.Producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Producto.model.Supplier;

@Repository


public interface SupplierRepository   extends JpaRepository<Supplier, Integer>{

}
