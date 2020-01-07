package com.example.customlistview;

public class CustomItem {

    String id, name, img;

    public CustomItem(String _id, String _name, String _img) {
        id = _id;
        name = _name;
        img = _img;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return img;
    }

}