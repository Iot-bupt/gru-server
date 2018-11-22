package com.sumory.gru.spear.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.corundumstudio.socketio.SocketIOClient;

import java.util.HashMap;
import java.util.Map;

public class WebrtcContext {
    private static  ThreadLocal<Map<String,Map<String,Map<String,Map<String,SocketIOClient>>>>> webrtc = new TransmittableThreadLocal<Map<String, Map<String, Map<String,Map<String,SocketIOClient>>>>>(){
            protected  Map<String,Map<String,Map<String,Object>>> initiaValue(){
                return  new HashMap<>();
            }
    };

    /**
     * 获取房间的用户
     * @param name
     * @return
     */
    public static Map<String,SocketIOClient> getRoom(String name){
        Map<String,Map<String,Map<String,Map<String,SocketIOClient>>>> adapter = webrtc.get();
        if (adapter == null){
            return  null;
        }
        Map<String, Map<String,Map<String,SocketIOClient>>> namespace = adapter.get("namespace");
        if (namespace == null){
            return  null;
        }
        Map<String,Map<String,SocketIOClient>> room = namespace.get("room");
        if(room == null){
            return null;
        }
        return room.get(name);
    }

    /**
     * 离开房间
     * @param sessionId
     */
    public static void  leaveRoom(String sessionId){
        Map<String,Map<String,Map<String,Map<String,SocketIOClient>>>> adapter = webrtc.get();
        if(adapter != null){
            Map<String,Map<String,Map<String,SocketIOClient>>> namespace = adapter.get("namespace");
            if(namespace != null){
                Map<String,Map<String,SocketIOClient>> room = namespace.get("room");
                if(room != null){
                    for (String key:room.keySet()){
                        Map<String,SocketIOClient> clients = room.get(key);
                        if(clients.containsKey(sessionId)){
                            clients.remove(sessionId);
                            //在redis移除时注意直接操作redis中的对象
                        }
                    }
                }
            }
        }
    }

    /**
     * 删除房间
     * @param name
     */
    public  static  void deleteRoom(String name){
        Map<String,Map<String,Map<String,Map<String,SocketIOClient>>>> adapter = webrtc.get();
        if (adapter != null){
            Map<String,Map<String,Map<String,SocketIOClient>>> namespace = adapter.get("namespace");
            if (namespace != null){
                Map<String,Map<String,SocketIOClient>> room = namespace.get("room");
                if(room != null){
                    room.remove(name);
                }
            }
        }
    }

    public static void joinRoom(String name,SocketIOClient socketIOClient){
        Map<String,Map<String,Map<String,Map<String,SocketIOClient>>>> adapter = webrtc.get();
        if(adapter != null){
            Map<String,Map<String,Map<String,SocketIOClient>>> namespace = adapter.get("namespace");
            if(namespace != null){
                Map<String,Map<String,SocketIOClient>> room = namespace.get("room");
                if(room != null){
                        Map<String,SocketIOClient> clients = room.get(name);
                        if(clients != null){
                            clients.put(socketIOClient.getSessionId().toString(),socketIOClient);
                        }else{
                            clients = new HashMap<>();
                            clients.put(socketIOClient.getSessionId().toString(),socketIOClient);
                            room.put(name,clients);
                        }
                }else{
                    room = new HashMap<>();
                    Map<String,SocketIOClient> clients = new HashMap<>();
                    clients.put(socketIOClient.getSessionId().toString(),socketIOClient);
                    room.put(name,clients);
                    namespace.put("room",room);
                }
            }else{
                namespace = new HashMap<>();
                Map<String,Map<String,SocketIOClient>> room = new HashMap<>();
                Map<String,SocketIOClient> clients = new HashMap<>();
                clients.put(socketIOClient.getSessionId().toString(),socketIOClient);
                room.put(name,clients);
                namespace.put("room",room);
                adapter.put("namespce",namespace);
            }
        }else{
            adapter = new HashMap<>();
            Map<String,Map<String,Map<String,SocketIOClient>>> namespace = new HashMap<>();
            Map<String,Map<String,SocketIOClient>> room = new HashMap<>();
            Map<String,SocketIOClient> clients = new HashMap<>();
            clients.put(socketIOClient.getSessionId().toString(),socketIOClient);
            room.put(name,clients);
            namespace.put("room",room);
            adapter.put("namespce",namespace);
            webrtc.set(adapter);

        }
    }

}
