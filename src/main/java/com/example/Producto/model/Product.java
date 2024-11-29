package com.example.Producto.model;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "Producto")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id; 

    @NotNull(message = "The barcode must not be null") //It is suitable for int and float
    @JsonProperty("barCode")
    @Column (name = "codigo_barra")
    private int barCode;

    @NotNull(message = "The price must not be null")
    @JsonProperty("price")
    @Column (name = "precio")
    private float price;

    @NotBlank(message = "the content must not be null and must contain at least eight non-whitespace character ")
    @Size (min =4, max= 50, message="the content must be at most 500 characters and has at least eight character")
    @JsonProperty("name")
    @Column (name = "nombre")
    private String name;
    
    @NotNull(message = "The supplier must not be null")
    @JsonProperty("supplier")
    @ManyToOne
    @JoinColumn(name = "proveedor_id")  
    private Supplier supplier;

    @NotNull(message = "The category must not be null")
    @JsonProperty("category")
    @ManyToOne
    @JoinColumn(name = "categoria_id")  
    private Category category;


    public  Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public int getId() {
        return id; 
    }

    public void setId(int id) {
        this.id = id; 
    }

    public int getBarCode() {
        return barCode;
    }

    public void setBarCode(int barCode) {
        this.barCode = barCode;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    
    
    @Override
    public String toString() {
        return "Producto [id=" + id + ", Barcode=" + barCode + ", price=" + price + ", name=" + name
                + ", category=" + category + ", supplier=" + supplier + "]";
    }
}
