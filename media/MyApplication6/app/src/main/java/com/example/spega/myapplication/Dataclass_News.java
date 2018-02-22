package com.example.spega.myapplication;

/**
 * Created by Admin on 2/13/2018.
 */

public class Dataclass_News {
    String title;
    String desc;
    String image;

    public Dataclass_News(String title) {
        this.title = title;
    }

    public Dataclass_News(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public Dataclass_News(String title, String desc, String image) {
        this.title = title;
        this.desc = desc;
        this.image = image;
    }

    public Dataclass_News() {

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
