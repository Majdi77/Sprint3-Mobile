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
import com.mycompany.entities.Transporteur;
import com.mycompany.services.ServiceTransporteur;


/**
 *
 * @author Maram
 */
public class ModifierTransporteurForm  extends BaseForm{
     Form current;
    public ModifierTransporteurForm(Resources res , Transporteur r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Transporteur");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField nom = new TextField(r.getNom() , "nom" , 20 , TextField.ANY);
        TextField description = new TextField(r.getdescription() , "description" , 20 , TextField.ANY);
        //TextField numero = new TextField(r.getnumero() , "numero" , 20 , TextField.ANY);
        //TextField prix = new TextField(r.getprix() , "prix" , 20 , TextField.ANY);
        TextField image= new TextField(r.getImage() , "image" , 20 , TextField.ANY);
        
        TextField numero = new TextField(Integer.toString(r.getnumero()), "numero", 20, TextField.ANY);
        TextField prix = new TextField(Float.toString(r.getprix()), "prix", 20, TextField.ANY);


       
 
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
      
        
        
        
        
        
        nom.setUIID("NewsTopLine");
        description.setUIID("NewsTopLine");
        numero.setUIID("NewsTopLine");
        prix.setUIID("NewsTopLine");
       image.setUIID("NewsTopLine");

        
        nom.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);
        numero.setSingleLineTextArea(true);
       prix.setSingleLineTextArea(true);
       image.setSingleLineTextArea(true);

        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setNom(nom.getText());
           r.setdescription(description.getText());
           r.setnumero(Integer.parseInt(numero.getText()));
           //r.setnumero(numero.getText());
           r.setprix(Float.parseFloat(prix.getText()));
          // r.setprix(prix.getText());
           r.setImage(image.getText());

           
         
      
       
       //appel fonction modfier reclamation men service
       
       if(ServiceTransporteur.getInstance().modifierTransporteur(r)) { // if true
           new ListTransporteurForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListTransporteurForm(res).show();
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
                new FloatingHint(description),
                createLineSeparator(),
                 new FloatingHint(numero),
                createLineSeparator(),
                new FloatingHint(prix),
                createLineSeparator(),
                 new FloatingHint(image),
                createLineSeparator(),
                //ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }

    public ModifierTransporteurForm(Resources theme) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}