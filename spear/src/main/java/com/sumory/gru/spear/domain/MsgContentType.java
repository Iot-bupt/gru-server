package com.sumory.gru.spear.domain;

public enum MsgContentType {
    BaseMessage(0), FileMessage(1), VoiceMessage(2);

    private int value;

    MsgContentType(int v) {this.value = v;}

    public int getValue() {return this.value;}
}
