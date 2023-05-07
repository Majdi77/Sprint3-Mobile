/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.Coaching;
import com.mycomany.entities.Reclamation;
import com.mycomany.entities.RendezVous;
import com.mycomany.utils.Statics;
import static com.mycompany.services.ServiceCoaching.resultOk;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Maram
 */
public class ServiceRendezVous  {
    
       //singleton 
    public static ServiceRendezVous instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public ArrayList<RendezVous> RendezVousArrayList;
    
    
    public static ServiceRendezVous getInstance() {
        if(instance == null )
            instance = new ServiceRendezVous();
        return instance ;
    }
    
     public ServiceRendezVous() {
        req = new ConnectionRequest();
       
     
    }
     
       //ajout 
    public void ajoutRDV(RendezVous rdv) {
        
        String url =Statics.BASE_URL+"/ajoutjsonrdv?daterdv="+rdv.getDaterdv()+"&etat="
                +rdv.getEtat(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation

        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    
      public ArrayList<RendezVous> parseTasks(String jsonText){
        try {
            RendezVousArrayList=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson =
                    j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
               RendezVous f = new RendezVous();
                float id = Float.parseFloat(obj.get("id").toString());
             float etat = Float.parseFloat(obj.get("etat").toString());

                f.setId((int)id);
//                f.setDaterdv((obj.get("daterdv").toString()));
//                f.setQuantite((int)(obj.get("quantite")));
                     f.setEtat((int)etat);

                
                 String DateConverter =  obj.get("daterdv").toString().substring(obj.get("daterdv").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
                        
                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(currentTime);
                        f.setDaterdv(dateString);
           
                RendezVousArrayList.add(f);
            }


        } catch (IOException ex) {

        }
        return RendezVousArrayList;
    }

    

    
     
    
    
    //affichage
    
    public ArrayList<RendezVous>affichagerdv() {
        
        
        
   
        
        req = new ConnectionRequest();
        String url = Statics.BASE_URL+"/afficherjsonrdv?";
        //System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                RendezVousArrayList = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return RendezVousArrayList;
        
        
        
  
        
    }
    
    
    //Delete 
    public boolean deleteRendezVous(int id ) {
        
        String url = Statics.BASE_URL +"/deletejsonrdv/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
     //Update 
    public boolean modifierRendezVous(RendezVous rdv) {
        String url = Statics.BASE_URL +"/updatejsonrdv/"+rdv.getId()+"?"+"&descCoach="+rdv.getDaterdv()
                +"&dispoCoach="+rdv.getEtat()
                ;
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    
    
    
    
    ///////////////////////////////trie////////////////////////////////////
    
    

     
    
}
