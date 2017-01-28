package com.hms.josip.hospitalmanagementsystem.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Josip on 21.1.2017..
 */
public class Medicine implements Serializable {

    @SerializedName("ID")
    private Integer id;

    @SerializedName("Name")
    private String name;

    @SerializedName("Price")
    private Float price;

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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
