package com.Contact.Management.Models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String name;

    @Column(unique = true)
    private String email;

    private String password;
    
    private String role;
    
    private boolean enable;
    
    private String imageUrl;
    
    @Column(length = 500)
    private String about;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Contact> contacts = new ArrayList<>();

    // Default constructor
    public User() {
    }

    // Getters and setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    // @Override
    // public String toString() {
    //     return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
    //             + ", enable=" + enable + ", imageUrl=" + imageUrl + ", about=" + about + ", contacts=" + contacts
    //             + ", getId()=" + getId() + ", getName()=" + getName() + ", getEmail()=" + getEmail()
    //             + ", getPassword()=" + getPassword() + ", getRole()=" + getRole() + ", isEnable()=" + isEnable()
    //             + ", getImageUrl()=" + getImageUrl() + ", getAbout()=" + getAbout() + ", getContacts()=" + getContacts()
    //             + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
    //             + "]";
    // }

    
}
