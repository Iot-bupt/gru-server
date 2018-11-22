package com.sumory.gru.spear.context;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static void setRoom(String sessionId,String roomname){
        Map<String,List<String>> rmap = room.get();
        if(rmap == null){
            rmap = new HashMap<>();
            room.set(rmap);
        }
        List<String> rlist = rmap.get(sessionId);
        if(rlist == null){
            rlist = new ArrayList<>();
            rlist.add(roomname);
            rmap.put(sessionId,rlist);
        }
        rlist.add(roomname);
     }
}
