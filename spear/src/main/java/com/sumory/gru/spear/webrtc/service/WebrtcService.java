package com.sumory.gru.spear.webrtc.service;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;

import java.util.Map;

public interface WebrtcService {
    public  void join (String name, AckRequest ackRequest,SocketIOClient ioClient);
    public void join(String name, AckRequest ackRequest,String userId);
    public Map<String,Map<String,Map<String,Object>>> describeroom(String name);
}
