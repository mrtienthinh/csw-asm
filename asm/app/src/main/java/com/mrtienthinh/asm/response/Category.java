package com.mrtienthinh.asm.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("create_at")
    @Expose
    private String createAt;
    @SerializedName("update_at")
    @Expose
    private String updateAt;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("movies")
    @Expose
    private Object movies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Object getMovies() {
        return movies;
    }

    public void setMovies(Object movies) {
        this.movies = movies;
    }
}
