package com.hms.josip.hospitalmanagementsystem.activities.Room;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hms.josip.hospitalmanagementsystem.R;
import com.hms.josip.hospitalmanagementsystem.model.Person;
import com.hms.josip.hospitalmanagementsystem.model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomDetailsActivity extends AppCompatActivity {

    Room room;
    Intent intent;
    Bundle bundle;
    ListView roomLV;
    ArrayAdapter<String> adapter;
    List<String> roomLVItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);

        intent = getIntent();
        bundle = intent.getExtras();
        room = (Room) bundle.getSerializable("room");

        roomLV = (ListView) findViewById(R.id.roomLV);
        roomLVItems = new ArrayList<String>();
        addToListView();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, roomLVItems);
        roomLV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void addToListView() {
        roomLVItems.add("Room number: " + room.getId());
        roomLVItems.add("Room type: " + room.getRoomType());
        if (!room.getPatients().isEmpty()) {
            StringBuilder attends = new StringBuilder();
            for (Person p: room.getPatients()) {
                attends.append(p.getFirstName() + " " + p.getLastName() + ", ");
            }
            attends.deleteCharAt(attends.length()-2);
            roomLVItems.add("Attends: " + attends.toString());
        }
    }

    public void goBack(View view) {
        this.finish();
    }
}
