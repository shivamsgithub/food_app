package com.example.foodapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryRealLIst {
    @SerializedName("categories")
    @Expose
    private List<CategoryRealData> categories;
    private final static long serialVersionUID = 5125273832255075206L;

    public List<CategoryRealData> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryRealData> categories) {
        this.categories = categories;
    }
}
