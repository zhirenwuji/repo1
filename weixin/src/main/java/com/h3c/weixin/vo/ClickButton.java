package com.h3c.weixin.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClickButton extends AbstractButton {
    private String type="click";
    private String key;

    public ClickButton(String name,String key) {
        this.key = key;
        this.name=name;
    }
}
