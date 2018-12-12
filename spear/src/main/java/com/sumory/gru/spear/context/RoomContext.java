package com.sumory.gru.spear.context;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * webrtc 创建的房间
 */
public class RoomContext {
    private  static  ThreadLocal<Map<String,List<String>>> room = new TransmittableThreadLocal<Map<String,List<String>>>(){
        protected  Map<String,List<String>> initiaValue(){
            return new HashMap<>();
        }
    };
    public  static  List<String> getRoom(String sessionId){
        Map<String,List<String>> rmap = room.get();
        if(rmap == null){
            return  null;
        }
        return  rmap.get(sessionId);
    }
    public static void setRoom(String userId,String roomname){
        Map<String,List<String>> rmap = room.get();
        if(rmap == null){
            rmap = new HashMap<>();
            List<String> rlist = new ArrayList<>();
            rlist = new ArrayList<>();
            rlist.add(userId);
            rmap.put(roomname,rlist);
            room.set(rmap);
        }else{
            List<String> rlist = rmap.get(roomname);
            if(rlist == null){
                rlist = new ArrayList<>();
                rlist.add(userId);
                rmap.put(roomname,rlist);
            }else {
                if(!rlist.contains(userId)){
                    rlist.add(userId);
                }
            }
        }
     }

    /**
     * 离开房间
     * @param userId
     */
     public  static  void leaveRoom(String userId){
         Map<String,List<String>> rmap = room.get();
         if(rmap !=null){
             for (String name : rmap.keySet()){
                 List<String> room = rmap.get(name);
                 if(room.contains(userId)){
                     room.remove(userId);
                     if(room.size() == 0){
                         rmap.remove(name);
                     }
                 }
             }
         }

     }
}
