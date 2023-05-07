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
import com.mycomany.entities.Produit;
import com.mycomany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 21692
 */
public class ServiceProduit {
    
    
     //singleton 
    public static ServiceProduit instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceProduit getInstance() {
        if(instance == null )
            instance = new ServiceProduit();
        return instance ;
    }
    
    public ServiceProduit() {
        req = new ConnectionRequest();
        
    }
    
     
    //ajout 
    public void ajoutProduit(Produit produit) {
        
        String url =Statics.BASE_URL+"/ajoutjson?nom="+produit.getNom()+"&description="+produit.getDescription()+"&prix="+produit.getPrix()+"&quantite="+produit.getQuantite()+"&image="+produit.getImage(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    
    
    //affichage
    
    public ArrayList<Produit>affichageProduits() {
        ArrayList<Produit> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/afficherjson";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapProduits = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapProduits.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Produit re = new Produit();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String nom = obj.get("nom").toString();
                        
                        String description = obj.get("description").toString();
                        float prix = Float.parseFloat(obj.get("prix").toString());
                        //int quantite = Integer.parseInt(obj.get("quantite").toString());
                        float q = Float.parseFloat(obj.get("quantite").toString());
                        re.setId((int)id);
                        re.setNom(nom);
                        re.setDescription(description);
                        re.setPrix((float)prix);
                        re.setQuantite((int)q);
                        
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
    
    
     //Delete 
    public boolean deleteProduit(int id ) {
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
    public boolean modifierProduit(Produit produit) {
        String url = Statics.BASE_URL +"/updatejson/"+produit.getId()+"?nom="+produit.getNom()+"&description="+produit.getDescription()+"&prix="+produit.getPrix()+"&quantite="+produit.getQuantite();
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
