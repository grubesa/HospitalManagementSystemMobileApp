package com.hms.josip.hospitalmanagementsystem.activities.Receptionist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hms.josip.hospitalmanagementsystem.R;
import com.hms.josip.hospitalmanagementsystem.model.Receptionist;
import com.hms.josip.hospitalmanagementsystem.model.Record;

import java.util.ArrayList;
import java.util.List;

public class ReceptionistDetailsActivity extends AppCompatActivity {

    Receptionist receptionist;
    Intent intent;
    Bundle bundle;
    ListView receptionistLV;
    ArrayAdapter<String> adapter;
    List<String> receptionistLVItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptionist_details);

        intent = getIntent();
        bundle = intent.getExtras();
        receptionist = (Receptionist) bundle.getSerializable("receptionist");

        receptionistLV = (ListView) findViewById(R.id.receptionistLV);
        receptionistLVItems = new ArrayList<String>();
        addToListView();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, receptionistLVItems);
        receptionistLV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void addToListView() {
        receptionistLVItems.add("First name: " + receptionist.getFirstName());
        receptionistLVItems.add("Last name: " + receptionist.getLastName());
        String sex = ((receptionist.getSex().equals("0")) ? "Male" : "Female");
        receptionistLVItems.add("Sex: " + sex);
        receptionistLVItems.add("Email: " + receptionist.getEmail());
        receptionistLVItems.add("Contact number: " + receptionist.getContactNumber());
        receptionistLVItems.add("Salary: " + receptionist.getSalary().toString() + " €");
        if (!receptionist.getRecords().isEmpty()) {
            StringBuilder rooms = new StringBuilder();
            for (Record r: receptionist.getRecords()) {
                rooms.append(r.getId() + " " + r.getId() + ", ");
            }
            rooms.deleteCharAt(rooms.length()-2);
            receptionistLVItems.add("Records: " + rooms.toString());
        }
    }

    public void goBack(View view) {
        this.finish();
    }
}
