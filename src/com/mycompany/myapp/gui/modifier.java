/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
/**
 *
 * @author HP
 */

public class modifier extends Form {
     
    public modifier(Form previous, Reclamation c) {
        setTitle("Modifier Categorie");
        setLayout(BoxLayout.y());
           
       
        
        //TextField ID = new TextField(Integer.toString(c.getId()), "ID", 20, TextField.ANY);
        TextField tDescription = new TextField(c.getDescription(),"DescriptionR");
        TextField tTitre = new TextField(c.getTitre(),"TitreR");
;
        
        Button btnValider = new Button("Edit ");
          
         
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tDescription.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                   try {
                       //float id = Float.parseFloat(ID.getText().toString());
                        Reclamation t;
                        t = new Reclamation(c.getId(),tDescription.getText().toString(),tTitre.getText().toString());
                        if( ServiceReclamation.getInstance().modifierReclamation(t))
                        {
                           Dialog.show("Success","Congrats!!",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
           }
        });
        
        addAll(/*ID,*/tDescription,tTitre,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
             
    }
    
}
