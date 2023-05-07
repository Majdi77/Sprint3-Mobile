/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author 21692
 */
public class Transporteur {
    
    private int id,numero;
    private String nom,description;
    private float prix;
    private String image;

    public Transporteur(int id, int numero, String nom, String description, float prix, String image) {
        this.id = id;
        this.numero = numero;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.image = image;
    }

    public Transporteur(int numero, String nom, String description, float prix, String image) {
        this.numero = numero;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.image = image;
    }

    public Transporteur(int numero, String nom, String description, float prix) {
        this.numero = numero;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
    }

    public Transporteur() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getnumero() {
        return numero;
    }

    public void setnumero(int numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public float getprix() {
        return prix;
    }

    public void setprix(float prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

 
    
    
    
    
}
