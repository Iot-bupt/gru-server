package com.sumory.gru.spear.domain;

public enum MsgContentType {
    TEXT(0), PICUTURE(1), AUDIO(2), VIDIO(3);

    private int value;

    MsgContentType(int v) {this.value = v;}

    public int getValue() {return this.value;}
}
