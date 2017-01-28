package com.hms.josip.hospitalmanagementsystem.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Josip on 16.1.2017..
 */
public class Nurse extends Employee implements Serializable {

    @SerializedName("Governs")
    private List<Room> rooms;

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
