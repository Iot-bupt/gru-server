package com.sumory.gru.spear.webrtc.service.impl;



import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.sumory.gru.spear.context.ResourceContext;
import com.sumory.gru.spear.context.RoomContext;
import com.sumory.gru.spear.context.WebrtcContext;
import com.sumory.gru.spear.webrtc.service.WebrtcService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("webrtcService")
public class WebrtcServiceImpl implements WebrtcService {
    @Override
    public void join(String name, AckRequest ackRequest,SocketIOClient ioClient) {
        if (name instanceof  String){
            int max = 10;
            Map<String,SocketIOClient> room = WebrtcContext.getRoom(name);
            if (room != null && room.size() > max) {
                ackRequest.sendAckData("full");
                return;
            }
            List<String> rlist = RoomContext.getRoom(ioClient.getSessionId().toString());
            if(rlist!= null && rlist.size() > 0){
                WebrtcContext.deleteRoom(name);
            }else {
                WebrtcContext.leaveRoom(ioClient.getSessionId().toString());
            }
            ackRequest.sendAckData(null,describeroom(name));
            WebrtcContext.joinRoom(name,ioClient);
        }
    }

    @Override
    public Map<String, Map<String, Map<String, Object>>> describeroom(String name) {
        Map<String,SocketIOClient> room = WebrtcContext.getRoom(name);
        Map<String,Map<String,Map<String,Object>>> result = new HashMap<>();
        Map<String,Map<String,Object>> clients = new HashMap<>();
        if(room != null){
            for (String key:room.keySet()){
                if(ResourceContext.getResource(key) != null){
                    clients.put(key,ResourceContext.getResource(key));
                }
            }
        }
        result.put("clients",clients);
        return result;
    }
}
