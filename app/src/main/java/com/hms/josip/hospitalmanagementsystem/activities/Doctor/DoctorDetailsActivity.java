package com.hms.josip.hospitalmanagementsystem.activities.Doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hms.josip.hospitalmanagementsystem.R;
import com.hms.josip.hospitalmanagementsystem.model.Doctor;
import com.hms.josip.hospitalmanagementsystem.model.Patient;
import com.hms.josip.hospitalmanagementsystem.model.Person;

import java.util.ArrayList;
import java.util.List;

public class DoctorDetailsActivity extends AppCompatActivity {

    Doctor doctor;
    Intent intent;
    Bundle bundle;
    ListView doctorLV;
    ArrayAdapter<String> adapter;
    List<String> doctorLVItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        intent = getIntent();
        bundle = intent.getExtras();
        doctor = (Doctor) bundle.getSerializable("doctor");

        doctorLV = (ListView) findViewById(R.id.doctorLV);
        doctorLVItems = new ArrayList<String>();
        addToListView();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, doctorLVItems);
        doctorLV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void addToListView() {
        doctorLVItems.add("First name: " + doctor.getFirstName());
        doctorLVItems.add("Last name: " + doctor.getLastName());
        String sex = ((doctor.getSex().equals("0")) ? "Male" : "Female");
        doctorLVItems.add("Sex: " + sex);
        doctorLVItems.add("Email: " + doctor.getEmail());
        doctorLVItems.add("Contact number: " + doctor.getContactNumber());
        doctorLVItems.add("Salary: " + doctor.getSalary().toString() + " €");
        doctorLVItems.add("Status: " + doctor.getStatus().toString());
        if (!doctor.getAttends().isEmpty()) {
            StringBuilder attends = new StringBuilder();
            for (Person p: doctor.getAttends()) {
                attends.append(p.getFirstName() + " " + p.getLastName() + ", ");
            }
            attends.deleteCharAt(attends.length()-2);
            doctorLVItems.add("Attends: " + attends.toString());
        }
    }

    public void goBack(View view) {
        this.finish();
    }

}
