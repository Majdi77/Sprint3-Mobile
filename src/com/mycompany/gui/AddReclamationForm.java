/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Reclamation;

import com.mycompany.services.ServiceReclamation;



import java.util.Map;


/**
 *
 * @author Majdi
 */
public class AddReclamationForm extends Form {
    

      
  
    public AddReclamationForm(Form previous) {
        
        
                  super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        //Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
        setTitle("Ajoute une nouvelle reclamation");
        setLayout(BoxLayout.y());
        String accountSid="AC099883ea0f07c67cd19e55b497fceb12";
        String authToken="7cd54f265b4727ce40c3c978b0181069";
        String fromPhone="+12766336884";
        String toPhone="+21692524435";
        String message="ah";
        
        // Create input fields and button
        TextField tfTitre = new TextField("", "Titre");

   






        TextField tfDescription = new TextField("", "Description");
       // TextField tfStatus = new TextField("", "Status");
       // TextField tfTraitement= new TextField("", "Traitement");
        Button btnAdd = new Button("Add");
    
       
        // Add action listener to button
        btnAdd.addActionListener(evt -> {
            if (tfTitre.getText().length() == 0 || tfDescription.getText().length() == 0 ) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            }
            else if (containsDigit(tfTitre.getText().toString())){
        Dialog.show("Erreur", "Le champ titre ne doit pas contenir de chiffres.", "OK", null);
    } 
            else if (tfDescription.getText().length() < 5){
                Dialog.show("Erreur", "il faut plus que 5 caractére .", "OK", null);
            }
            else {
                try {
                    // Create new Reclamation object and add it to the server
                    Reclamation Reclamation = new Reclamation(tfDescription.getText(), tfTitre.getText());
                    if (ServiceReclamation.getInstance().addReclamation(Reclamation)) {
                        Dialog.show("Success", "Congrats", new Command("OK"));
                        new ListReclamation(previous).show();
                      //  sendSms(accountSid, authToken, fromPhone, toPhone, message);
                    } else {
                        Dialog.show("Error", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("Error", "Invalid input", new Command("OK"));
                }
            }
        });
        
        // Add input fields and button to the form
        addAll(tfTitre, tfDescription, btnAdd);
        
        // Add back button to the toolbar
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack());

    }
     public static boolean containsDigit(String s){
        //tow new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         for (char c : s.toCharArray()) {
        if (Character.isDigit(c)) {
            return true;
        }
    }
    return false;
    }
    
   


}
   

    

