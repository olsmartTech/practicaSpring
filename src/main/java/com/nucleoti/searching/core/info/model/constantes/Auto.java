package com.nucleoti.searching.core.info.model.constantes;

import com.nucleoti.searching.core.info.firebase.core.FirebaseObject;

public class Auto implements FirebaseObject {
    private String color;
    private String desc;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
