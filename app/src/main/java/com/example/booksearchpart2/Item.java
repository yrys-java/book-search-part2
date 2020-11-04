package com.example.booksearchpart2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Item implements Serializable {
    @SerializedName("volumeInfo")
    @Expose
    private VolumeInfo volumeInfo;

    public String getmLink() {
        return mLink;
    }

    @SerializedName("previewLink")
    @Expose
    private String mLink;

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

}
