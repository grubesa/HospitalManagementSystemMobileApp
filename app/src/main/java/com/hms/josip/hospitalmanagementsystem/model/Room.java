package com.hms.josip.hospitalmanagementsystem.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Josip on 16.1.2017..
 */
public class Room implements Serializable {

    @SerializedName("ID")
    private Integer id;

    @SerializedName("RoomType")
    private Integer roomType;

    @SerializedName("AssignedPatients")
    private List<Patient> patients;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomType() {
        switch (roomType) {
            case 0:
                return "One";
            case 1:
                return "Two";
            case 2:
                return "Multi";
        }

        return "Don't have type yet.";
    }

    public void setRoomType(String roomType) {
        switch (roomType) {
            case "One":
                this.roomType = 0;
            case "Two":
                this.roomType = 1;
            case "Multi":
                this.roomType = 2;
        }
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
