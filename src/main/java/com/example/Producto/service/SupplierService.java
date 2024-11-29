package com.example.Producto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Producto.model.Supplier;
import com.example.Producto.repository.SupplierRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional

public class SupplierService {
    @Autowired
    private SupplierRepository repo;

    public List<Supplier> getAll() {
        return repo.findAll();
    
    }
    public void save(Supplier Proveedor){
        repo.save(Proveedor);
    }
public Supplier getById (Integer id){
    return repo.findById(id).get();
    //(idProducto).get();

}
public void delete(Integer id){
repo.deleteById(id);
}

}
