package com.hms.josip.hospitalmanagementsystem.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Josip on 15.1.2017..
 */
public class Person implements Serializable {

    @SerializedName("ID")
    private Integer id;

    @SerializedName("ContactNumber")
    private String contactNumber;

    @SerializedName("Sex")
    private Integer sex;

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName("LastName")
    private String lastName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getSex() {
        if (sex == 0){
            return "Male";
        }
        else {
            return "Female";
        }
    }

    public void setSex(String sex) {
        if (sex.equals("Male")){
            this.sex = 0;
        }
        else {
            this.sex = 1;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
