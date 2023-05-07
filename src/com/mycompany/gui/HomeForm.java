/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.util.List;


/**
 *
 * @author Sondes
 */
public class HomeForm extends BaseForm {

    private static final char ADD_ICON_NAME = FontImage.MATERIAL_ADD_CIRCLE_OUTLINE;
    private static final char LIST_ICON_NAME = FontImage.MATERIAL_LIST_ALT;
    private static final String ADD_BUTTON_TEXT = "Ajouter Reclamation";
    private static final String LIST_BUTTON_TEXT = "Afficher Les Reclamations";

    public HomeForm() {
          super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
        setTitle("Menu");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));// Obtenir le style par défaut pour les boutons
Style buttonStyle = UIManager.getInstance().getComponentStyle("Button");

// Personnaliser le style des boutons Ajouter et Liste
buttonStyle.setFgColor(0xFFFFFF);
buttonStyle.setBgTransparency(0);
buttonStyle.setPaddingTop(10);
buttonStyle.setPaddingBottom(10);
buttonStyle.setPaddingLeft(20);
buttonStyle.setPaddingRight(20);

// Appliquer les styles personnalisés aux boutons






        

        Button addButton = new Button("");
        addButton.setIcon(FontImage.createMaterial(ADD_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
        addButton.setText(ADD_BUTTON_TEXT);
        addButton.addActionListener(e -> new AddReclamationForm(this).show());

        Button listButton = new Button("");
        listButton.setIcon(FontImage.createMaterial(LIST_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
        listButton.setText(LIST_BUTTON_TEXT);
        listButton.addActionListener(e -> new ListReclamation(this).show());

        addAll(addButton, listButton);
       
    }
   
}





     


