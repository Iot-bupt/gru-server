package com.sumory.gru.spear.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.sumory.gru.spear.domain.Client;
import com.sumory.gru.spear.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * webrtc登陆用户
 */
public class UserContext {
    private  static  ThreadLocal<Map<String,User>> userMap = new TransmittableThreadLocal<Map<String,User>>(){
        protected  Map<String,User> initiaValue(){
            return new HashMap<>();
        }
    };
    public static  void setUser(User user){
        Map<String,User> usrmap = userMap.get();
        if(usrmap == null){
            usrmap = new HashMap<>();
            usrmap.put(user.getId()+"",user);
            userMap.set(usrmap);
        }else{
            if(!usrmap.containsKey(user.getId()+"")){
                usrmap.put(user.getId()+"",user);
            }
        }
    }
    public  static  User getUser(String userId){
        Map<String,User> usrmap = userMap.get();
        if(usrmap == null){
            return  null;
        }
        return usrmap.get(userId);
    }

    public  static  void removeUser(String userId){
        Map<String,User> usrmap = userMap.get();
        if(usrmap != null && usrmap.containsKey(userId)){
           usrmap.remove(userId);
        }

    }

    /**
     * 通过sessionId 获取userId
     * @param sessionId
     * @return
     */
    public static String getUserIdBySessionId(String sessionId){
        Map<String,User> usrmap = userMap.get();
        if(usrmap == null){
            return  null;
        }
        for (String key : usrmap.keySet()){
            User user = usrmap.get(key);
            ConcurrentLinkedQueue<Client> clients = user.getClients();
            for (Client client:clients){
                if (sessionId.equals(client.getUuid())){
                    return user.getId()+"";
                }
            }
        }
        return "";
    }
}
