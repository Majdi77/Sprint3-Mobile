/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author 21692
 */
public class Produit {
    
    private int id,quantite;
    private String nom,description;
    private float prix;
    private String image;

    public Produit(int id, int quantite, String nom, String description, float prix, String image) {
        this.id = id;
        this.quantite = quantite;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.image = image;
    }

  
    public Produit(int quantite, String nom, String description, float prix,String image) {
        this.quantite = quantite;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.image=image;
    }

    public Produit() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

 
    
    
    
    
}
