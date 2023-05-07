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
public class Coaching {
    
     private int id;
    private String descCoach;
    private String dispoCoach;
    private String cours;
    private String imgCoach;
    
     private int iduser;


    public Coaching(int id, String descCoach, String dispoCoach, String cours, String imgCoach) {
        this.id = id;
        this.descCoach = descCoach;
        this.dispoCoach = dispoCoach;
        this.cours = cours;
        this.imgCoach = imgCoach;
    }

   

    public Coaching() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescCoach() {
        return descCoach;
    }

    public void setDescCoach(String descCoach) {
        this.descCoach = descCoach;
    }

    public String getDispoCoach() {
        return dispoCoach;
    }

    public void setDispoCoach(String dispoCoach) {
        this.dispoCoach = dispoCoach;
    }

    public String getCours() {
        return cours;
    }

    public void setCours(String cours) {
        this.cours = cours;
    }

    public String getImgCoach() {
        return imgCoach;
    }

    public void setImgCoach(String imgCoach) {
        this.imgCoach = imgCoach;
    }

    public Coaching(String descCoach, String dispoCoach, String cours,String imgCoach) {
        this.descCoach = descCoach;
        this.dispoCoach = dispoCoach;
        this.cours = cours;
     this.imgCoach = imgCoach;

        
    }
    
    



}
