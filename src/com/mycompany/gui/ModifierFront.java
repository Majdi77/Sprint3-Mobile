/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Participant;
import com.mycompany.services.ServiceParticipant;


/**
 *
 * @author Maram
 */
public class ModifierFront  extends BaseForm{
     Form current;
    public ModifierFront(Resources res , Participant r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Participant");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField nom = new TextField(r.getNom() , "nom" , 20 , TextField.ANY);
        TextField prenom = new TextField(r.getPrenom() , "prenom" , 20 , TextField.ANY);
        TextField email = new TextField(r.getEmail() , "email" , 20 , TextField.ANY);
        //TextField numero = new TextField(r.getnumero() , "numero" , 20 , TextField.ANY);
        //TextField prixStandard = new TextField(r.getprixStandard() , "prixStandard" , 20 , TextField.ANY);
        TextField tel = new TextField(Integer.toString(r.getTel()), "tel", 20, TextField.ANY);
          TextField age = new TextField(Integer.toString(r.getAge()), "age", 20, TextField.ANY);
       

       
 
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
      
        
        
        
        
        
        nom.setUIID("NewsTopLine");
        prenom.setUIID("NewsTopLine");
        email.setUIID("NewsTopLine");
        tel.setUIID("NewsTopLine");
        age.setUIID("NewsTopLine");
 

        
        nom.setSingleLineTextArea(true);
               prenom.setSingleLineTextArea(true);
        email.setSingleLineTextArea(true);
        tel.setSingleLineTextArea(true);
       age.setSingleLineTextArea(true);
   

        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setNom(nom.getText());
              r.setPrenom(prenom.getText());
           r.setEmail(email.getText());
           r.setTel(Integer.parseInt(tel.getText()));
           //r.setnumero(numero.getText());
              r.setAge(Integer.parseInt(age.getText()));
          // r.setprixStandard(prixStandard.getText());
       

           
         
      
       
       //appel fonction modfier reclamation men service
       
       if(ServiceParticipant.getInstance().modifierParticipant(r)) { // if true
           new ListFront(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListParticipantForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(nom),
                createLineSeparator(),
                  new FloatingHint(prenom),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                 new FloatingHint(tel),
                createLineSeparator(),
                new FloatingHint(age),
                createLineSeparator(),
                
                //ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }

    public ModifierFront(Resources theme) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}