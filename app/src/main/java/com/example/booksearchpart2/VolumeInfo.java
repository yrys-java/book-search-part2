package com.example.booksearchpart2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class VolumeInfo implements Serializable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("authors")
    @Expose
    private List<String> authors = null;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("publishedDate")
    @Expose
    private String publishedDate;
    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("imageLinks")
    @Expose
    public ImageLinks imageLinks;

    @SerializedName("previewLink")
    public String previewLink = "";

    @SerializedName("pageCount")
    @Expose
    private String pageCount;
    @SerializedName("averageRating")
    @Expose
    public String averageRating;
    @SerializedName("infoLink")
    @Expose
    public String infoLink;

    public String getInfoLink() {
        return infoLink;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public String getPageCount() {
        return pageCount;
    }


    public String getPreviewLink() {
        return previewLink;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }


    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getDescription() {
        return description;
    }


}
