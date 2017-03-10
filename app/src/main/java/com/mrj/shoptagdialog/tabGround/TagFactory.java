package com.mrj.shoptagdialog.tabGround;
/**
 * Author: mrj
 * github:https://github.com/jj3341332
 * blog:http://blog.csdn.net/jj3341332
 * Create Date: 2017/3/8 9:33
 */
public abstract class TagFactory <T>{
    public enum ClickStatus{
        BAN,CLICK,UNCLICK
    }
    public abstract ClickStatus onColorTagClick(int position);
    public abstract ClickStatus onSizeTagClick(int position);
    public abstract T getClickObject();
}
