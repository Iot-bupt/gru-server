package com.sumory.gru.spear.context;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * webrtc 创建的房间
 */
public class RoomContext {
    private  static  Map<String,List<String>> room = new HashMap<>();
    public  static  List<String> getRoom(String sessionId){
       // Map<String,List<String>> rmap = room.get();
        if(room == null){
            return  null;
        }
        return  room.get(sessionId);
    }
    public static void setRoom(String userId,String roomname){
       // Map<String,List<String>> rmap = room.get();
        if(room == null){
            room = new HashMap<>();
            List<String> rlist = new ArrayList<>();
            rlist = new ArrayList<>();
            rlist.add(userId);
            room.put(roomname,rlist);
           // room.set(rmap);
        }else{
            List<String> rlist = room.get(roomname);
            if(rlist == null){
                rlist = new ArrayList<>();
                rlist.add(userId);
                room.put(roomname,rlist);
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
         //Map<String,List<String>> rmap = room.get();
         if(room !=null){
             for (String name : room.keySet()){
                 List<String> roomlist= room.get(name);
                 if(roomlist.contains(userId)){
                     roomlist.remove(userId);
                     if(roomlist.size() == 0){
                         room.remove(name);
                     }
                 }
             }
         }

     }

    /**
     *
     * @param userId
     * @return
     */

     public static String getRoomNameByUserId(String userId){
         if(room!=null){
             for (String key:room.keySet()){
                 if(room.get(key).contains(userId)){
                     return key;
                 }
             }
         }
         return "";
     }
}
