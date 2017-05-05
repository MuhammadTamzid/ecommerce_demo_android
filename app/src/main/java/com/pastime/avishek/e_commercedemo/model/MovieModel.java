package com.pastime.avishek.e_commercedemo.model;

import java.io.Serializable;

/**
 * Created by Avishek on 4/18/17.
 */

public class MovieModel implements Serializable {
    private String name;
    private boolean isWished;
    private String imageUrl;

    public MovieModel(String name, boolean isWished, String imageUrl) {
        this.name = name;
        this.isWished = isWished;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWished() {
        return isWished;
    }

    public void setWished(boolean wished) {
        this.isWished = wished;
    }
}
