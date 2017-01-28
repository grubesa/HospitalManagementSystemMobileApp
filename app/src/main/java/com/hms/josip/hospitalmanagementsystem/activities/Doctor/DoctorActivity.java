package com.hms.josip.hospitalmanagementsystem.activities.Doctor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hms.josip.hospitalmanagementsystem.R;
import com.hms.josip.hospitalmanagementsystem.model.Doctor;
import com.hms.josip.hospitalmanagementsystem.service.Api;
import com.hms.josip.hospitalmanagementsystem.Config;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DoctorActivity extends AppCompatActivity {

    List<Doctor> doctors;
    ListView listOfDoctors;
    ArrayAdapter<String> adapter;
    boolean deleteState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        doctors = new ArrayList<Doctor>();
        listOfDoctors = (ListView) findViewById(R.id.listOfDoctors);
        adapter = new ArrayAdapter<>(this, R.layout.check_list, R.id.code);
        listOfDoctors.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listOfDoctors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Doctor doc = doctors.get(position);
                personDetails(doc);
            }
        });

        getAllDoctors();
    }

    public void personDetails(Doctor doctor) {
        Intent intent = new Intent(this, DoctorDetailsActivity.class);
        Bundle bundle = new Bundle();

        bundle.putSerializable("doctor", doctor);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void addNewDoctor() {
        Intent intent = new Intent(this, AddNewDoctorActivity.class);
        startActivity(intent);
    }

    public void onDeleteClick(View view) {
        if (deleteState){
            List<Doctor> deleteDoctors = new ArrayList<Doctor>();
            ListView listview = (ListView) findViewById(R.id.listOfDoctors);
            for (int i=0; i<adapter.getCount(); i++) {
                RelativeLayout relativeLayout = (RelativeLayout)adapter.getView(i, listview.getChildAt(i), listview);
                CheckBox checkBox = (CheckBox) relativeLayout.findViewById(R.id.checkBox);
                if (checkBox.isChecked()) {
                    deleteDoctors.add(doctors.get(i));
                }
            }
            deleteDoctors(deleteDoctors);
        }
        deleteStateVisibility(View.INVISIBLE);
        refresh();
    }
    private void refresh() {
        adapter.clear();
        getAllDoctors();
    }

    private void deleteDoctors(List<Doctor> doctors) {
        for (Doctor doctor : doctors) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(new Config().getURL()).
                                addConverterFactory(GsonConverterFactory.create())
                        .build();
                Api service = retrofit.create(Api.class);
                Call<ResponseBody> call = service.deleteDoctor(doctor.getId());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("onSuccess", response.toString());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("onFailure", t.toString());
                    }
                });
            } catch (Exception e) {
                Log.d("onResponse", "There is an error");
                e.printStackTrace();
            }
        }
    }

    private void getAllDoctors() {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(new Config().getURL()).
                            addConverterFactory(GsonConverterFactory.create())
                    .build();
            Api service = retrofit.create(Api.class);
            Call<List<Doctor>> call = service.listOfDoctors();
            call.enqueue(new Callback<List<Doctor>>() {
                @Override
                public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                    doctors = response.body();
                    for (Doctor doc : doctors) {
                        adapter.add(doc.getFirstName() + " " + doc.getLastName());
                    }
                }

                @Override
                public void onFailure(Call<List<Doctor>> call, Throwable t) {
                    Log.d("onFailure", t.toString());
                }
            });
        } catch (Exception e) {
            Log.d("onResponse", "There is an error");
            e.printStackTrace();
        }
    }

    public void goBack(View view) {
        if (deleteState) {
            deleteStateVisibility(View.INVISIBLE);
            deleteState = false;
        }else {
            this.finish();
        }
    }

    private void deleteStateVisibility(int visibility) {
        ListView listview = (ListView) findViewById(R.id.listOfDoctors);
        for (int i=0; i<adapter.getCount(); i++) {
            RelativeLayout relativeLayout = (RelativeLayout)adapter.getView(i, listview.getChildAt(i), listview);
            CheckBox checkBox = (CheckBox) relativeLayout.findViewById(R.id.checkBox);
            checkBox.setVisibility(visibility);
        }
        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        ImageView imageViewDeleteButton = (ImageView) findViewById(R.id.deleteButtonImageView);
        deleteButton.setVisibility(visibility);
        imageViewDeleteButton.setVisibility(visibility);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add) {
            addNewDoctor();
            return true;
        }
        if (id == R.id.delete) {
            deleteStateVisibility(View.VISIBLE);
            deleteState = true;
            return true;
        }
        if (id == R.id.refresh) {
            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
