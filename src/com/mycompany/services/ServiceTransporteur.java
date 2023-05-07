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
import com.mycompany.entities.Transporteur;
import com.mycompony.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 21692
 */
public class ServiceTransporteur {
    
    
     //singleton 
    public static ServiceTransporteur instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    public ArrayList<Transporteur>TransporteurArrayList;
    
    public static ServiceTransporteur getInstance() {
        if(instance == null )
            instance = new ServiceTransporteur();
        return instance ;
    }
    
    public ServiceTransporteur() {
        req = new ConnectionRequest();
        
    }
    
     
    //ajout 
    public void ajoutTransporteur(Transporteur Transporteur) {
        
        String url =Statics.BASE_URL+"/ajoutTransporteurjson?nom="+Transporteur.getNom()+"&description="+Transporteur.getdescription()+"&prix="+Transporteur.getprix()+"&numero="+Transporteur.getnumero()+"&image="+Transporteur.getImage(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    public ArrayList<Transporteur> parseTasks(String jsonText){
        try {
            TransporteurArrayList=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson =
                    j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
               Transporteur f = new Transporteur();
                float id = Float.parseFloat(obj.get("id").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                float numero = Float.parseFloat(obj.get("numero").toString());
                f.setId((int)id);
                f.setNom((obj.get("nom").toString()));
                f.setdescription((obj.get("description").toString()));
                f.setnumero((int)numero);
                f.setprix((int)prix);
               // f.setnumero(obj.get("numero"));
             // f.setprix(((float)obj.get("prix")));
                
                 //               f.setnumero((int)obj.get("numero"));

//                f.setImage((obj.get("imgage").toString()));
                //f.setNumero((int)(obj.get("Numero")));
                TransporteurArrayList.add(f);
            }


        } catch (IOException ex) {

        }
        return TransporteurArrayList;
    }

    
     
    
    
    //affichage
    
    public ArrayList<Transporteur>affichageTransporteurs() {
        
        
        
      //    ArrayList<Coaching> result = new ArrayList<>();
        
     //   String url = Statics.BASE_URL+"/afficherjson?";
     //   req.setUrl(url);
        
        req = new ConnectionRequest();
        String url = Statics.BASE_URL+"/affichertransporteurjson";
        //System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              TransporteurArrayList = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return TransporteurArrayList;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //affichage
    /*
    public ArrayList<Transporteur>affichageTransporteurs() {
        ArrayList<Transporteur> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/afficherjson";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapTransporteurs = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapTransporteurs.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Transporteur re = new Transporteur();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String nom = obj.get("nom").toString();
                        
                        String description = obj.get("description").toString();
                        float prix = Float.parseFloat(obj.get("prix").toString());
                        int numero = Integer.parseInt(obj.get("numero").toString());
                        
                        re.setId((int)id);
                        re.setNom(nom);
                        re.setdescription(description);
                        re.setprix((float)prix);
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
    public boolean deleteTransporteur(int id ) {
        //String url = Statics.BASE_URL +"/deletejson?id="+id;
        String url = Statics.BASE_URL +"/deleteTransporteurjson/"+id;
        
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
    public boolean modifierTransporteur(Transporteur Transporteur) {
        String url = Statics.BASE_URL +"/updateTransporteurjson/"+Transporteur.getId()+"?"+"&nom="+Transporteur.getNom()+"&description="+Transporteur.getdescription()+"&prix="+Transporteur.getprix()+"&numero="+Transporteur.getnumero();
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
