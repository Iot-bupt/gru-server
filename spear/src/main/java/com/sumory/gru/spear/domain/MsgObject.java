package com.sumory.gru.spear.domain;

import java.sql.Blob;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.omg.CORBA.ObjectHelper;

/**
 * 传输的消息
 * 
 * <pre>
 * {
 *     type: 1, //1 广播，0 单播
 *     target: { // 给指定target
 *         id: 10, //单播时指目标用户id，广播时指群组id
 *         type: 1 //扩展字段，暂时无用
 *     },
 *     content: "字符串"//消息内容
 * }
 * 
 * </pre>
 * 
 * @author sumory.wu
 * @date 2015年3月18日 下午5:40:50
 */
public class MsgObject {

    private long fromId;//发送者id
    private int type;//类型: 1 广播，0 单播给指定target
    private int contentType;
    private Map<String, Object> target;
    private Object content;
    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public static MsgContentType BaseMessage = MsgContentType.BaseMessage;
    public static MsgContentType FileMessage = MsgContentType.FileMessage;
    public static MsgContentType VoiceMessage = MsgContentType.VoiceMessage;


    public static MsgType UNICAST = MsgType.UNICAST;
    public static MsgType BRAODCAST = MsgType.BRAODCAST;
    public static MsgType MULTICAST = MsgType.MULTICAST;

    public long getFromId() {
        return fromId;
    }

    public void setFromId(long fromId) {
        this.fromId = fromId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Map<String, Object> getTarget() {
        return target;
    }

    public void setTarget(Map<String, Object> target) {
        this.target = target;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static void main(String[] args) {
        MsgObject o = new MsgObject();
        Map<String, Object> target = new HashMap<String, Object>();
        String filename = "";
        target.put("id", 10);
        target.put("type", 1);
        o.setType(0);
        o.setContent("send");
        o.setTarget(target);
        o.setFilename(filename);

        System.out.println(o);

        System.out.println(MsgObject.BRAODCAST);
    }

}
