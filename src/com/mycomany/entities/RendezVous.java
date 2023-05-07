/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author Maram
 */
public class RendezVous {
    
        private int id;
    private String daterdv;
    private int etat;

    
     public RendezVous() {
    }
    
    public RendezVous(int id, String daterdv, int etat) {
        this.id = id;
        this.daterdv = daterdv;
        this.etat = etat;
    }

    public RendezVous(String daterdv, int etat) {
        this.daterdv = daterdv;
        this.etat = etat;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDaterdv() {
        return daterdv;
    }

    public void setDaterdv(String daterdv) {
        this.daterdv = daterdv;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

   
    
    
}
