package com.sumory.gru.spear.context;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.HashMap;
import java.util.Map;

public class ResourceContext {
    private static ThreadLocal<Map<String,Map<String,Object>>> resource = new TransmittableThreadLocal<Map<String,Map<String,Object>>>(){
        protected  Map<String,Map<String,Object>> initiaValue(){
            return new HashMap<>();
        }
    };
    public static Map<String,Object> getResource(String sessionId){
        Map<String,Map<String,Object>> remap = resource.get();
        if(remap == null){
            return null;
        }
        return remap.get(sessionId);
    }
    public static  void setResource(String sessionId,Map<String,Object> rmap){
        Map<String,Map<String,Object>> remap = resource.get();
        if (remap == null){
            remap = new HashMap<>();
            resource.set(remap);
        }
        remap.put(sessionId,rmap);
    }
}
