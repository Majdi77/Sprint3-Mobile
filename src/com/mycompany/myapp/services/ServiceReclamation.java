/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;

import com.codename1.ui.Form;
import java.io.IOException;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.html.DocumentInfo;
import com.mycompany.myapp.entities.Reclamation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.Calendar;



/**
 *
 * @author Majdi
 */
public class ServiceReclamation {
 public static ServiceReclamation instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<Reclamation> Reclamation=new ArrayList<>();
  //  public static final String ACCOUNT_SID = "AC099883ea0f07c67cd19e55b497fceb12";
    //public static final String AUTH_TOKEN = "0dd561ef29a8f933acf0a98205b376c0";
    //public static final String TWILIO_NUMBER = "+12766336884";
    public ServiceReclamation() {
        req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }
//ajout
    public boolean addReclamation(Reclamation t) {

      String Description = t.getDescription();
     // String Titre=t.getTitre();
    Calendar calendar = Calendar.getInstance();
    String dateSysteme = calendar.getTime().toString();
    t.setDate(dateSysteme);
      //String Status=t.getStatus();
      //String Traitement=t.getTraitement();
      
        
       
    //  String url =  "http://127.0.0.1:8000/"+ "addCategMobile/" + type + "/" + description;
String url= "http://127.0.0.1:8000/"+"addReclamationJSON/new?Titre="+t.getTitre()+"&Description="+t.getDescription();
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
//         

                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    /********** Affichage********************/
      
     public ArrayList<Reclamation> affichageReclamation()
    {

        ArrayList<Reclamation> result = new ArrayList<>();
        String url ="http://127.0.0.1:8000/"+"AllReclamations";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try 
                {
                    Map<String,Object>mapR = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> ListOfMaps = (List<Map<String,Object>>) mapR.get("root");
                    System.out.println(mapR);
                    for(Map<String, Object> obj : ListOfMaps)
                    {
                        System.out.println(obj);
                       Reclamation c = new Reclamation();
                       
                        String Description = obj.get("DescriptionR").toString();
                         float id = Float.parseFloat(obj.get("id").toString());
                         String Titre = obj.get("TitreR").toString();
                     String status = obj.get("StatusR").toString();
                     String traitement = obj.get("Traitement").toString();
                    String Date = obj.get("DateR").toString();
                     
                    //  String DateConverter =  obj.get("DateR").toString().substring(obj.get("DateR").toString().indexOf("timestamp") + 10 , obj.get("DateR").toString().lastIndexOf("}"));
                        
                      //  Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        
                       // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                       // String dateString = formatter.format(obj.get("DateR").toString());
                        
                       

                       c.setId((int)id);
                        c.setDescription(Description);
                        c.setTitre(Titre);
                        c.setStatus(status);
                        c.setTraitement(traitement);
                     c.setDate(Date);
                        
                     
                       
                        
                        result.add(c);
                        System.out.println(c.getTitre()+c.getDescription()+c.getStatus()+c.getTraitement());
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
    
    }    


    
     /*********************************************update***************************************************/    
    public boolean modifierReclamation(Reclamation t) {
        String Description = t.getDescription();
        String url= "http://127.0.0.1:8000/"+"updateReclamationJSON/id="+t.getId()+"?"+"Titre="+t.getTitre()+"&Description="+t.getDescription();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOK;
        
    }
    //////////////////////////////////delete///////////////////////////////////////
    public boolean deleteReclamation(int id ) {
        String url = "http://127.0.0.1:8000/" +"deleteReclamationJSON/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOK;
    }
    
    
   
}