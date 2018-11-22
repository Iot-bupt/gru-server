package com.sumory.gru.spear.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.corundumstudio.socketio.SocketIOClient;

import java.util.HashMap;
import java.util.Map;

public class ConnectedContext {
    private static  ThreadLocal<Map<String,SocketIOClient>> connected = new TransmittableThreadLocal<Map<String, SocketIOClient>>(){
        protected Map<String,SocketIOClient> initiaValue(){
            return new HashMap<>();
        }
    };
public  static SocketIOClient getContext(String sessionId){
    Map<String,SocketIOClient> conmap = connected.get();
    if (conmap == null){
        return  null;
    }
    return  conmap.get(sessionId);
}

public  static  void addContext(SocketIOClient socketIOClient){
    Map<String,SocketIOClient> conmap = connected.get();
    if(conmap == null){
        conmap = new HashMap<>();
        connected.set(conmap);
    }
    if(!conmap.containsKey(socketIOClient.getSessionId())){
        conmap.put(socketIOClient.getSessionId().toString(),socketIOClient);
    }
}
}
