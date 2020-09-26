package com.h3c.weixin.vo;

public class CustomButton extends AbstractButton {
    private String type;
    private String key;

    public CustomButton(String name,String type, String key) {
        this.name=name;
        this.type = type;
        this.key = key;
    }
}
