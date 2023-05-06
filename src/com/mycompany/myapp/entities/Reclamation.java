/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Majdi
 */
public class Reclamation {
      String Description;
    String Titre;
    String Status;
    String Traitement;
    String Date;
    
    
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reclamation() {
    }
public Reclamation(int id,String Description, String Titre) {
        this.Description = Description;
        this.Titre = Titre;


        
        this.id = id;
    }
public Reclamation(int id,String Description, String Titre, String Status, String Traitement, String Date) {
        this.Description = Description;
        this.Titre = Titre;
        this.Status = Status;
        this.Traitement = Traitement;
       

        
        this.id = id;
    }
     public Reclamation(String Description, String Titre) {
        this.Description = Description;
        this.Titre = Titre;
        
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public Object get(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int setId(String string, String id, int i, int ANY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getStatus() {
        return Status;
    }

    public String getTraitement() {
        return Traitement;
    }

    public String getDate() {
        return Date;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setTraitement(String Traitement) {
        this.Traitement = Traitement;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
    
    
}
