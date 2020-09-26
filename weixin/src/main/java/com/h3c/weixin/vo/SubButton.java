package com.h3c.weixin.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
public class SubButton extends AbstractButton {
    private List<AbstractButton> sub_button=new ArrayList<>();
    public SubButton(String name){
        this.name=name;
    }
}
