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
import com.mycomany.entities.Coaching;
import com.mycomany.entities.RendezVous;
import com.mycompany.services.ServiceCoaching;
import com.mycompany.services.ServiceReclamation;
import java.io.IOException;


/**
 *
 * @author Maram
 */
public class ModifierCoachingForm  extends BaseForm{
     Form current;
    public ModifierCoachingForm(Resources res , Coaching r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
       // setTitle("Ajout Reclamation");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField descCoach = new TextField(r.getDescCoach() , "desc" , 20 , TextField.ANY);
        TextField dispoCoach = new TextField(r.getDispoCoach() , "dispo" , 20 , TextField.ANY);
        TextField cours = new TextField(r.getCours() , "cours" , 20 , TextField.ANY);
        TextField imgCoach= new TextField(r.getImgCoach() , "image" , 20 , TextField.ANY);


       
 
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
      
        
        
        
        
        
        descCoach.setUIID("NewsTopLine");
        dispoCoach.setUIID("NewsTopLine");
        cours.setUIID("NewsTopLine");
       imgCoach.setUIID("NewsTopLine");

        
        descCoach.setSingleLineTextArea(true);
        dispoCoach.setSingleLineTextArea(true);
        cours.setSingleLineTextArea(true);
       imgCoach.setSingleLineTextArea(true);

        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       Button uploadBtn = new Button("Upload");
       addStringValue("Image", uploadBtn);
String[] imgPath = {""};
uploadBtn.addActionListener(e -> {
    Display.getInstance().openGallery((ActionListener) (ActionEvent ev) -> {
        if (ev != null && ev.getSource() != null) {
            imgPath[0] = (String) ev.getSource();
        }
    }, Display.GALLERY_IMAGE);
});
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setDescCoach(descCoach.getText());
           r.setDispoCoach(dispoCoach.getText());
           r.setCours(cours.getText());
           r.setImgCoach(imgCoach.getText());

           
         
      
       
       //appel fonction modfier reclamation men service
       
       if(ServiceCoaching.getInstance().modifierCoaching(r)) { // if true
           new ListCoachingForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListCoachingForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(descCoach),
                createLineSeparator(),
                new FloatingHint(dispoCoach),
                createLineSeparator(),
                 new FloatingHint(cours),
                createLineSeparator(),
                 new FloatingHint(imgCoach),
                createLineSeparator(),
                //ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }

    private void addStringValue(String s, Component v) {
        
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }
    public ModifierCoachingForm(Resources theme) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    ModifierCoachingForm(Resources res, RendezVous rec) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
