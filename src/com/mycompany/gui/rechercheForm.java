/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Coaching;
import com.mycompany.services.ServiceCoaching;
import java.util.List;
import static jdk.nashorn.internal.objects.NativeString.search;

/**
 *
 * @author Maram
 */
public class rechercheForm extends BaseForm {
     Form current;
    public  rechercheForm(Form previous) {
        current = this;
        
       
      ServiceCoaching sp = new ServiceCoaching();
      add(new InfiniteProgress());
                Display.getInstance().scheduleBackgroundTask(()-> {
                    
                    Display.getInstance().callSerially(() -> {
                           
                        removeAll();
                      setLayout(BoxLayout.y());
                     Button searchButton = new Button();
FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, "Search Icon", 4);
searchButton.setIcon(searchIcon);
       
         
       add(searchButton);

       
       
             
      

                      
                      
                      
                      
                      
                
                      
                       
        Button refreshButton = new Button();
FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_REFRESH, UIManager.getInstance().getComponentStyle("Button"));
refreshButton.setIcon(icon1);

       
       refreshButton.addActionListener(e-> new LIste2(previous).show());
        add(refreshButton);

                        List<Coaching> listerec = sp.affichageCoaching();
                       
                            
                            
                   for (Coaching c : listerec) {
    MultiButton m = new MultiButton();
    
    m.setTextLine1("Description: " + c.getDescCoach());
    m.setTextLine2("Titre: " + c.getCours());

    
    add(m);
}
                            
                            
                          
                           
                            
                         
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                     revalidate() ;   
                    });
                });
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
       
       
       
    
                        }

    
}