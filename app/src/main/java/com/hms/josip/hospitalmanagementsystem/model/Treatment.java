package com.hms.josip.hospitalmanagementsystem.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Josip on 21.1.2017..
 */
public class Treatment implements Serializable {

    @SerializedName("ID")
    private Integer id;

    @SerializedName("Medicine")
    private Medicine medicine;

    @SerializedName("Quantity")
    private Integer quantity;

    @SerializedName("Text")
    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
