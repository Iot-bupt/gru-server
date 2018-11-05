package com.sumory.gru.spear.message;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

// 字符串消息
public class Message extends BaseMessage {
    private long fromId;//来自谁
    private int msgType;//类型: 1 广播，0 单播给指定target
    private int MsgContentType;

    public int getMsgContentType() {
        return MsgContentType;
    }

    public void setMsgContentType(int msgContentType) {
        MsgContentType = msgContentType;
    }

    private Map<String, Object> target;
    private Object content;

    public Message(long id, long fromId, int msgType, Map<String, Object> target,
                   Object content) {
        super(id);
        this.createTime = System.currentTimeMillis();

        this.fromId = fromId;
        this.msgType = msgType;
        this.target = target;
        this.content = content;
    }

    public long getFromId() {
        return fromId;
    }

    public void setFromId(long fromId) {
        this.fromId = fromId;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
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
        return JSONObject.toJSONString(this);
    }
}
