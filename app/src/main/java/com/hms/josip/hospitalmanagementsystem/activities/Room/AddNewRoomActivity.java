package com.hms.josip.hospitalmanagementsystem.activities.Room;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.hms.josip.hospitalmanagementsystem.Config;
import com.hms.josip.hospitalmanagementsystem.R;
import com.hms.josip.hospitalmanagementsystem.model.Room;
import com.hms.josip.hospitalmanagementsystem.service.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddNewRoomActivity extends AppCompatActivity {

    Room room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_room);

        room = new Room();
    }

    public void submitNewRoom(View view) {
        addNewRoom();

        Toast.makeText(getBaseContext(), "New room added :)", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    private void addNewRoom() {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(new Config().getURL()).
                            addConverterFactory(GsonConverterFactory.create())
                    .build();
            Api service = retrofit.create(Api.class);
            Call<Room> call = service.addNewRoom(room);
            call.enqueue(new Callback<Room>() {
                @Override
                public void onResponse(Call<Room> call, Response<Room> response) {
                    Log.d("Success", response.toString());
                }

                @Override
                public void onFailure(Call<Room> call, Throwable t) {
                    Log.d("onFailure", t.toString());
                }
            });
        } catch (Exception e) {
            Log.d("onResponse", "There is an error");
            e.printStackTrace();
        }
    }

    public void onRoomTypeClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.one:
                if (checked)
                    room.setRoomType("One");
                break;
            case R.id.two:
                if (checked)
                    room.setRoomType("Two");
                break;
            case R.id.multi:
                if (checked)
                    room.setRoomType("Multi");
                break;
        }
    }

    public void goBack(View view) {
        this.finish();
    }
}
