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
import com.mycomany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Maram
 */
public class ServiceCoaching {
       //singleton 
    public static ServiceCoaching instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public ArrayList<Coaching> CoachingArrayList;
    
    
    public static ServiceCoaching getInstance() {
        if(instance == null )
            instance = new ServiceCoaching();
        return instance ;
    }
    
    
    
    public ServiceCoaching() {
        req = new ConnectionRequest();
       
     
    }
    
       //ajout 
    public void ajoutCoaching(Coaching coaching) {
        
        String url =Statics.BASE_URL+"/ajoutjson?descCoach="+coaching.getDescCoach()+"&dispoCoach="+coaching.getDispoCoach()+"&cours="+coaching.getCours()+"&imgCoach="+coaching.getImgCoach(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    public ArrayList<Coaching> parseTasks(String jsonText){
        try {
            CoachingArrayList=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson =
                    j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
               Coaching f = new Coaching();
                float id = Float.parseFloat(obj.get("id").toString());
                f.setId((int)id);
                f.setDescCoach((obj.get("descCoach").toString()));
//                f.setQuantite((int)(obj.get("quantite")));
                f.setDispoCoach((obj.get("dispoCoach").toString()));
                f.setCours((obj.get("cours").toString()));
                f.setImgCoach((obj.get("imgCoach").toString()));
                //f.setNumero((int)(obj.get("Numero")));
                CoachingArrayList.add(f);
            }


        } catch (IOException ex) {

        }
        return CoachingArrayList;
    }

    

    
     
    
    
    //affichage
    
    public ArrayList<Coaching>affichageCoaching() {
        
        
        
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
                CoachingArrayList = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return CoachingArrayList;
        
        
        
    /*    ArrayList<Coaching> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/afficherjson?";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapCoaching = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapCoaching.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Coaching c  = new Coaching();
                        System.out.println(obj);
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String descCoach = obj.get("descCoach").toString();
                        
                        String dispoCoach = obj.get("dispoCoach").toString();
                        String cours = obj.get("cours").toString();
                         String imgCoach = obj.get("imgCoach").toString();


                        
                        c.setId((int)id);
                        c.setDescCoach(descCoach);
                        c.setDispoCoach(dispoCoach);
                        c.setCours(cours);
                        c.setImgCoach(imgCoach);

              
                        
                        //insert data into ArrayList result
                        result.add(c);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        */
        
    }
    
       //Delete 
    public boolean deleteCoaching(int id ) {
        
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
    public boolean modifierCoaching(Coaching coaching) {
        String url = Statics.BASE_URL +"/updatejson/"+coaching.getId()+"?"+"&descCoach="+coaching.getDescCoach()+"&dispoCoach="+coaching.getDispoCoach()+"&cours="
                +coaching.getCours()+"&imgCoach="+coaching.getImgCoach();
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
    
    
    public ArrayList<Coaching> parseTrie(String jsonText){
        try {
            CoachingArrayList=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson =
                    j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
               Coaching f = new Coaching();
                float id = Float.parseFloat(obj.get("id").toString());
                f.setId((int)id);
                f.setDescCoach((obj.get("descCoach").toString()));
//                f.setQuantite((int)(obj.get("quantite")));
                f.setDispoCoach((obj.get("dispoCoach").toString()));
                f.setCours((obj.get("cours").toString()));
                f.setImgCoach((obj.get("imgCoach").toString()));
                //f.setNumero((int)(obj.get("Numero")));
                CoachingArrayList.add(f);
            }


        } catch (IOException ex) {

        }
        return CoachingArrayList;
    }
     public ArrayList<Coaching>trieCoaching() {
        
        
        
        req = new ConnectionRequest();
        String url = Statics.BASE_URL+"/triejson";
        //System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CoachingArrayList = parseTrie(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return CoachingArrayList;
        
        
        
   
        
    }
    
     
     
}
