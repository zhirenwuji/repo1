package com.h3c.weixin.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewButton extends AbstractButton {
    private String type="view";
    private String url;

    public ViewButton(String name,String url) {
        this.url = url;
        this.name=name;
    }
}
