/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Participant;
import com.mycompany.services.ServiceParticipant;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author 21692
 */
public class AjoutParticipantBack extends BaseForm  {
     Form current;
    public AjoutParticipantBack(Resources res ) {
    super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Participant");
        getContentPane().setScrollVisible(false);
        
        
        tb.addSearchCommand(e ->  {
            
            
        });
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        addTab(swipe,s1, res.getImage("t-shirt.PNG"),"","",res);
        
        //
        
         swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Mes Participants", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Statistique", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Ajouter", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
            ListParticipantForm ListParticipantForm = new ListParticipantForm(res);
            ListParticipantForm.show();
               
            refreshTheme();
        });
        liste.addActionListener((e) -> {
    // Ajouter l'action à effectuer lorsque le bouton "liste" est cliqué
    // Par exemple, ouvrir un formulaire de statistiques
    StatistiquePieForm StatistiqueFormPie = new StatistiquePieForm(res);
    StatistiqueFormPie.show();

    refreshTheme();
});

partage.addActionListener((e) -> {
    // Ajouter l'action à effectuer lorsque le bouton "partage" est cliqué
    // Par exemple, ouvrir un formulaire d'ajout de participants
    AjoutParticipantBack ajouterParticipantForm = new AjoutParticipantBack(res);
    ajouterParticipantForm.show();

    refreshTheme();
});
add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        
        //
        
      
        TextField nom = new TextField("", "Participant nom !");
        nom.setUIID("TextFieldBlack");
        addStringValue("nom",nom);
        
           TextField prenom = new TextField("", "Participant prenom !");
        prenom.setUIID("TextFieldBlack");
        addStringValue("prenom",prenom);
        
           TextField email = new TextField("", "Participant email !");
        email.setUIID("TextFieldBlack");
        addStringValue("email",email);
        
        
        TextField age = new TextField("", "age participant !");
        age.setUIID("TextFieldBlack");
        addStringValue("age",age);
        
        TextField tel = new TextField("", "tel Participant !");
        tel.setUIID("TextFieldBlack");
        addStringValue("tel",tel);
        
        
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);
        
        
        //onclick button event 

        btnAjouter.addActionListener((e) -> {
            
            
            try {
                
                if(nom.getText().equals("") ||prenom.getText().equals("") || email.getText().equals("") || age.getText().equals("")|| tel.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                ///zedna hethyy controle de saisie aal nom
                else if (containsDigit(nom.getText().toString())){
        Dialog.show("Erreur", "Le champ nom ne doit pas contenir de chiffres.", "OK", null);
        
    }
      else if (containsDigit(prenom.getText().toString())){
        Dialog.show("Erreur", "Le champ prenom ne doit pas contenir de chiffres.", "OK", null);
    }
                               //controle de saisie aal numero
                               else if  (tel.getText().length() != 8) {
    Dialog.show("Erreur", "Le numéro de téléphone doit contenir exactement 8 chiffres.", "OK", null);
}
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
              
                    //njibo iduser men session (current user)
                  Participant r = new Participant(
     0,// or any valid integer value for the "id" parameter
    Integer.parseInt(age.getText()),
    String.valueOf(nom.getText()),
    String.valueOf(prenom.getText()),
    String.valueOf(email.getText()),
    Integer.parseInt(tel.getText())
);

                   
                   
               

                    
                    System.out.println("data  reclamation == "+r);
                    
                    
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    ServiceParticipant.getInstance().ajoutParticipant(r);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    
                    //ba3d ajout net3adaw lel ListREclamationForm
                  
                        //ba3d ajout net3adaw lel ListREclamationForm
                    new ListParticipantForm(res).show();
                    
                    refreshTheme();//Actualisation
                            
                }
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
            
            
            
            
            
        });
        
        
    }

    private void addStringValue(String s, Component v) {
        
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }

    private void addTab(Tabs swipe, Label spacer , Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
        if(image.getHeight() < size) {
            image = image.scaledHeight(size);
        }
        
        
        
        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2 ) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overLay = new Label("","ImageOverlay");
        
        
        Container page1 = 
                LayeredLayout.encloseIn(
                imageScale,
                        overLay,
                        BorderLayout.south(
                        BoxLayout.encloseY(
                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                        )
                    )
                );
        
        swipe.addTab("",res.getImage("t-shirt.PNG"), page1);
        
        
        
        
    }
    
    
    
    public void bindButtonSelection(Button btn , Label l ) {
        
        btn.addActionListener(e-> {
        if(btn.isSelected()) {
            updateArrowPosition(btn,l);
        }
    });
    }

    private void updateArrowPosition(Button btn, Label l) {
        
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth()  / 2  - l.getWidth() / 2 );
        l.getParent().repaint();
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

