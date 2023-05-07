/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Produit;
import com.mycompany.services.ServiceProduit;

import java.io.IOException;


/**
 *
 * @author Maram
 */
public class ModifierProduitForm  extends BaseForm{
     Form current;
    public ModifierProduitForm(Resources res , Produit r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
       // setTitle("Ajout Reclamation");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField nom = new TextField(r.getNom() , "desc" , 20 , TextField.ANY);
        TextField description = new TextField(r.getDescription() , "dispo" , 20 , TextField.ANY);
TextField prix = new TextField(Float.toString(r.getPrix()), "prix", 20, TextField.NUMERIC);
TextField quantite = new TextField(Integer.toString(r.getQuantite()), "quantite", 20, TextField.NUMERIC);

       TextField image= new TextField(r.getImage() , "image" , 20 , TextField.ANY);


       
 
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
      
        
        
        
        
        
        nom.setUIID("NewsTopLine");
        description.setUIID("NewsTopLine");
        prix.setUIID("NewsTopLine");
        quantite.setUIID("NewsTopLine");
        image.setUIID("NewsTopLine");

        nom.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);
        prix.setSingleLineTextArea(true);
        quantite.setSingleLineTextArea(true);
        image.setSingleLineTextArea(true);

        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");

        //Event onclick btnModifer
       

        btnModifier.addPointerPressedListener(l -> {

            r.setNom(nom.getText());
            r.setDescription(description.getText());
            r.setDescription(description.getText());
            r.setPrix(Float.parseFloat(prix.getText()));
            r.setQuantite(Integer.parseInt(quantite.getText()));

            //appel fonction modfier reclamation men service
            if (ServiceProduit.getInstance().modifierProduit(r)) { // if true
                new ListProduitForm(res).show();
            }
        });
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e -> {
            new ListProduitForm(res).show();
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
                new FloatingHint(prix),
                createLineSeparator(),
                new FloatingHint(quantite),
                createLineSeparator(),
                new FloatingHint(image),
                createLineSeparator(),
                btnModifier,
                btnAnnuler
        );

        add(content);
        show();

    }

    public ModifierProduitForm(Resources theme) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}