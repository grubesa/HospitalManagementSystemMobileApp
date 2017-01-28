package com.hms.josip.hospitalmanagementsystem.activities.Patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hms.josip.hospitalmanagementsystem.R;
import com.hms.josip.hospitalmanagementsystem.model.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientDetailsActivity extends AppCompatActivity {

    Patient patient;
    Intent intent;
    Bundle bundle;
    ListView patientLV;
    ArrayAdapter<String> adapter;
    List<String> patientLVItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        intent = getIntent();
        bundle = intent.getExtras();
        patient = (Patient) bundle.getSerializable("patient");

        patientLV = (ListView) findViewById(R.id.patientLV);
        patientLVItems = new ArrayList<String>();
        addToListView();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, patientLVItems);
        patientLV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void addToListView() {
        patientLVItems.add("First name: " + patient.getFirstName());
        patientLVItems.add("Last name: " + patient.getLastName());
        String sex = ((patient.getSex().equals("0")) ? "Male" : "Female");
        patientLVItems.add("Sex: " + sex);
        patientLVItems.add("Contact number: " + patient.getContactNumber());
    }

    public void goBack(View view) {
        this.finish();
    }
}
