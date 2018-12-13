package com.sumory.gru.spear.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.sumory.gru.spear.domain.Client;
import com.sumory.gru.spear.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * webrtc登陆用户
 */
public class UserContext {
    private  static  Map<String,User> userMap = new HashMap<>();

    public static  void setUser(User user){
        //Map<String,User> usrmap = userMap.get();
        if(userMap == null){
            userMap = new HashMap<>();
            userMap.put(user.getId()+"",user);
          //  userMap.set(usrmap);
        }else{
            if(!userMap.containsKey(user.getId()+"")){
                userMap.put(user.getId()+"",user);
            }
        }
    }
    public  static  User getUser(String userId){
        //Map<String,User> usrmap = userMap.get();
        if(userMap == null){
            return  null;
        }
        return userMap.get(userId);
    }

    public  static  void removeUser(String userId){
        //Map<String,User> usrmap = userMap.get();
        if(userMap != null && userMap.containsKey(userId)){
            userMap.remove(userId);
        }

    }

    /**
     * 通过sessionId 获取userId
     * @param sessionId
     * @return
     */
    public static String getUserIdBySessionId(String sessionId){
        //Map<String,User> usrmap = userMap.get();
        if(userMap == null){
            return  null;
        }
        for (String key : userMap.keySet()){
            User user = userMap.get(key);
            ConcurrentLinkedQueue<Client> clients = user.getClients();
            for (Client client:clients){
                if (sessionId.equals(client.getUuid().toString())){
                    return user.getId()+"";
                }
            }
        }
        return "";
    }
}
