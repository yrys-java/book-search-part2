package com.example.booksearchpart2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookResponse implements Serializable {

    @SerializedName("items")
    @Expose
    private ArrayList<Item> items = null;

    public ArrayList<Item> getItems() {
        return items;
    }
}

