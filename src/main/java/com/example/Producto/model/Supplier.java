package com.example.Producto.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Proveedor")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 
    
    @NotBlank(message = "the content must not be null and must contain at least eight non-whitespace character ")
    @Size (min =3, max= 50, message="the content must be at most 500 characters and has at least eight character")
    @JsonProperty("name")
    @Column(name = "nombre")
    private String name;

    @NotBlank(message = "the content must not be null and must contain at least eight non-whitespace character ")
    @Size (min =4, max= 50, message="the content must be at most 500 characters and has at least eight character")
    @JsonProperty("phone")
    @Column(name = "telefono")
    private String phone;

    @NotBlank(message = "the content must not be null and must contain at least eight non-whitespace character ")
    @Size (min =4, max= 50, message="the content must be at most 500 characters and has at least eight character")
    @JsonProperty("mail")
    @Column(name = "correo")
    private String  mail;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }


    @Override
    public String toString() {
        return "Proveedor [id=" + id + ", name=" + name + ", phone=" + phone + ", mail=" + mail + "]";
    }

}