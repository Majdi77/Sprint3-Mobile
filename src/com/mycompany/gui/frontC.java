/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Coaching;
import com.mycompany.services.ServiceCoaching;
import java.util.ArrayList;

/**
 *
 * @author Maram
 */
public class frontC extends BaseForm{
    
     public static Coaching currenrProduit = null;

    Label coursLabel,descriptionLabel,dispoLabel, imageLabel;
    Button addtoCartBtn;
    Container btnsContainer;
    Resources theme = UIManager.initFirstTheme("/theme");

       

    public frontC(Resources theme) {
 super("Coaching", new BoxLayout(BoxLayout.Y_AXIS));

        addGUIs();

        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e ->showBack());
    }    

    public void refresh() {
        this.removeAll();
       addGUIs();
        this.refreshTheme();
    }

    private void addGUIs() {

       // ArrayList<Coaching> listProduits = new ArrayList<>();
               ArrayList<Coaching>listProduits = ServiceCoaching.getInstance().affichageCoaching();

        if (listProduits.size() > 0) {
            for (Coaching produit : listProduits) {
                this.add(makeProduitModel(produit));
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private Component makeProduitModel(Coaching produit) {
        ImageViewer imageIV;

        Container produitModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        produitModel.setUIID("containerRounded");

        coursLabel = new Label("cours : " + produit.getCours());
         descriptionLabel = new Label("descCoach : " + produit.getDescCoach());
         dispoLabel = new Label("descCoach : " + produit.getDispoCoach());

         

       

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");
       
       
    
        if (produit.getImgCoach() != null) {
            String url = produit.getImgCoach();
            Image image = URLImage.createToStorage(
                    EncodedImage.createFromImage(theme.getImage("2.png").fill(1100, 500), false),
                    url,
                    url,
                    URLImage.RESIZE_SCALE
            );
            imageIV = new ImageViewer(image);
        } else {
            imageIV = new ImageViewer(theme.getImage("2.png").fill(1100, 500));
        }
        imageIV.setFocusable(false);

        addtoCartBtn = new Button("Réserver votre cours");

        addtoCartBtn.setUIID("buttonDanger");
        addtoCartBtn.addActionListener(action -> {
        });

        btnsContainer.add(BorderLayout.CENTER, addtoCartBtn);

        produitModel.addAll(
                
                imageIV,
               coursLabel,
               descriptionLabel,
               dispoLabel,
              
                btnsContainer
        );

        return produitModel;
    }
    
}
