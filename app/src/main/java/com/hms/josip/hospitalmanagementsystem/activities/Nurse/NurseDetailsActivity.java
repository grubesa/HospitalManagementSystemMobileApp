package com.hms.josip.hospitalmanagementsystem.activities.Nurse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hms.josip.hospitalmanagementsystem.R;
import com.hms.josip.hospitalmanagementsystem.model.Nurse;
import com.hms.josip.hospitalmanagementsystem.model.Person;
import com.hms.josip.hospitalmanagementsystem.model.Room;

import java.util.ArrayList;
import java.util.List;

public class NurseDetailsActivity extends AppCompatActivity {

    Nurse nurse;
    Intent intent;
    Bundle bundle;
    ListView nurseLV;
    ArrayAdapter<String> adapter;
    List<String> nurseLVItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_details);

        intent = getIntent();
        bundle = intent.getExtras();
        nurse = (Nurse) bundle.getSerializable("nurse");

        nurseLV = (ListView) findViewById(R.id.nurseLV);
        nurseLVItems = new ArrayList<String>();
        addToListView();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, nurseLVItems);
        nurseLV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void addToListView() {
        nurseLVItems.add("First name: " + nurse.getFirstName());
        nurseLVItems.add("Last name: " + nurse.getLastName());
        String sex = ((nurse.getSex().equals("0")) ? "Male" : "Female");
        nurseLVItems.add("Sex: " + sex);
        nurseLVItems.add("Email: " + nurse.getEmail());
        nurseLVItems.add("Contact number: " + nurse.getContactNumber());
        nurseLVItems.add("Salary: " + nurse.getSalary().toString() + " €");
        if (!nurse.getRooms().isEmpty()) {
            StringBuilder rooms = new StringBuilder();
            for (Room r: nurse.getRooms()) {
                rooms.append(r.getId() + " " + r.getId() + ", ");
            }
            rooms.deleteCharAt(rooms.length()-2);
            nurseLVItems.add("Rooms: " + rooms.toString());
        }
    }

    public void goBack(View view) {
        this.finish();
    }
}
