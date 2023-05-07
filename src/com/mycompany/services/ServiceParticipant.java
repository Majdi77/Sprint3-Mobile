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
import com.mycomany.entities.Participant;
import com.mycomany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 21692
 */
public class ServiceParticipant {
    

     //singleton 
    public static ServiceParticipant instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    public ArrayList<Participant>ParticipantArrayList;
    
    public static ServiceParticipant getInstance() {
        if(instance == null )
            instance = new ServiceParticipant();
        return instance ;
    }
    
    public ServiceParticipant() {
        req = new ConnectionRequest();
        
    }
    
     
    //ajout 
    public void ajoutParticipant(Participant Participant) {
        
        String url =Statics.BASE_URL+"/ajoutjson?nom="+Participant.getNom()+"&prenom="+Participant.getPrenom()+"&email="+Participant.getEmail()+"&age="+Participant.getAge()+"&tel="+Participant.getTel();
// aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    public ArrayList<Participant> parseTasks(String jsonText){
        try {
            ParticipantArrayList=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson =
                    j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
               Participant f = new Participant();
                float id = Float.parseFloat(obj.get("id").toString());
                float age = Float.parseFloat(obj.get("age").toString());
                float tel = Float.parseFloat(obj.get("tel").toString());
                f.setId((int)id);
                f.setNom((obj.get("nom").toString()));
                f.setPrenom((obj.get("prenom").toString()));
                f.setEmail((obj.get("email").toString()));
                f.setTel((int)tel);
                f.setAge((int)age);
               // f.setnumero(obj.get("numero"));
             // f.setprixStandard(((float)obj.get("prixStandard")));
                
                 //               f.setnumero((int)obj.get("numero"));

//                f.setImage((obj.get("imgage").toString()));
                //f.setNumero((int)(obj.get("Numero")));
                ParticipantArrayList.add(f);
            }


        } catch (IOException ex) {

        }
        return ParticipantArrayList;
    }


    public Participant affichageDernierParticipant() {
    Participant dernierParticipant = null;
    
    ArrayList<Participant> ParticipantArrayList = affichageParticipants();
    if (!ParticipantArrayList.isEmpty()) {
        // récupérer le dernier participant
        dernierParticipant = ParticipantArrayList.get(ParticipantArrayList.size() - 1);
    }
    
    return dernierParticipant;
}

    
     
    
    
    //affichage
    
    public ArrayList<Participant>affichageParticipants() {
        
        
        
      //    ArrayList<Coaching> result = new ArrayList<>();
        
     //   String url = Statics.BASE_URL+"/afficherjson?";
     //   req.setUrl(url);
        
        req = new ConnectionRequest();
        String url = Statics.BASE_URL+"/afficherjson?";
        //System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              ParticipantArrayList = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ParticipantArrayList;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //affichage
    /*
    public ArrayList<Participant>affichageParticipants() {
        ArrayList<Participant> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/afficherjson";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapParticipants = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapParticipants.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Participant re = new Participant();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String nom = obj.get("nom").toString();
                        
                        String description = obj.get("description").toString();
                        float prixStandard = Float.parseFloat(obj.get("prixStandard").toString());
                        int numero = Integer.parseInt(obj.get("numero").toString());
                        
                        re.setId((int)id);
                        re.setNom(nom);
                        re.setdescription(description);
                        re.setprixStandard((float)prixStandard);
                        re.setnumero((int)numero);
                        
                        //insert data into ArrayList result
                        result.add(re);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
    
    */
    }
     //Delete 
    public boolean deleteParticipant(int id ) {
        //String url = Statics.BASE_URL +"/deletejson?id="+id;
        String url = Statics.BASE_URL +"/deletejson/"+id;
        
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
    public boolean modifierParticipant(Participant Participant) {
        String url = Statics.BASE_URL +"/updatejson/"+Participant.getId()+"?"+"&nom="+Participant.getNom()+"&prenom="+Participant.getPrenom()+"&Email="+Participant.getEmail()+"&Age="+Participant.getAge()+"&tel="+Participant.getTel();
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
    

    
    
    
  

    
    
    
    
}
