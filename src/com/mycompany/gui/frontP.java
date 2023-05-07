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
import com.mycompany.entities.Produit;
import com.mycompany.services.ServiceProduit;
import java.util.ArrayList;

/**
 *
 * @author Maram
 */
public class frontP extends BaseForm {

    public static Produit currenrProduit = null;

    Label coursLabel, imageLabel;
    Button addtoCartBtn;
    Container btnsContainer;
    Resources theme = UIManager.initFirstTheme("/theme");

    public frontP(Resources theme) {
        super("Produit", new BoxLayout(BoxLayout.Y_AXIS));

        addGUIs();

        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> showBack());
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        this.refreshTheme();
    }

    private void addGUIs() {

        // ArrayList<Coaching> listProduits = new ArrayList<>();
        ArrayList<Produit> listProduits = ServiceProduit.getInstance().affichageProduits();

        if (listProduits.size() > 0) {
            for (Produit produit : listProduits) {
                this.add(makeProduitModel(produit));
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private Component makeProduitModel(Produit produit) {
        ImageViewer imageIV;

        Container produitModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        produitModel.setUIID("containerRounded");

        coursLabel = new Label("nom : " + produit.getNom());
     //   coursLabel = new Label("description : " + produit.getDescription());
       // coursLabel = new Label("prix : " + produit.getPrix());

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        if (produit.getImage() != null) {
            String url = produit.getImage();
            Image image = URLImage.createToStorage(
                    EncodedImage.createFromImage(theme.getImage("").fill(1100, 500), false),
                    url,
                    url,
                    URLImage.RESIZE_SCALE
            );
            imageIV = new ImageViewer(image);
        } else {
            imageIV = new ImageViewer(theme.getImage("t-shirt.jpg").fill(1100, 500));
        }
        imageIV.setFocusable(false);

        addtoCartBtn = new Button("Add to Cart");
        addtoCartBtn.setUIID("buttonDanger");
        addtoCartBtn.addActionListener(action -> {
        });

        btnsContainer.add(BorderLayout.CENTER, addtoCartBtn);

        produitModel.addAll(
                imageIV,
                coursLabel,
                btnsContainer
        );

        return produitModel;
    }

}
