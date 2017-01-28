package com.hms.josip.hospitalmanagementsystem.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.hms.josip.hospitalmanagementsystem.JsonHelper;
import com.hms.josip.hospitalmanagementsystem.NetDateTimeAdapter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Josip on 15.1.2017..
 */
public class Patient extends Person implements Serializable {

    @SerializedName("Admitted")
    private String admitted;

    @SerializedName("Discharged")
    private String discharged;

    @SerializedName("Address")
    private String address;

    public Date getAdmitted() {
        JsonHelper jsonHelper = new JsonHelper();

        return jsonHelper.DeserializeDate(admitted);
    }

    public void setAdmitted(String admitted) {
        this.admitted = admitted;
    }

    public Date getDischarged() {
        JsonHelper jsonHelper = new JsonHelper();

        return jsonHelper.DeserializeDate(discharged);
    }

    public void setDischarged(String discharged) {
        this.discharged = discharged;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
