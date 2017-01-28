package com.hms.josip.hospitalmanagementsystem.activities.Patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hms.josip.hospitalmanagementsystem.Config;
import com.hms.josip.hospitalmanagementsystem.R;
import com.hms.josip.hospitalmanagementsystem.model.Patient;
import com.hms.josip.hospitalmanagementsystem.service.Api;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PatientActivity extends AppCompatActivity {

    List<Patient> patients;
    ListView listOfPatients;
    ArrayAdapter<String> adapter;
    boolean deleteState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        patients = new ArrayList<Patient>();
        listOfPatients = (ListView) findViewById(R.id.listOfPatients);
        adapter = new ArrayAdapter<>(this, R.layout.check_list, R.id.code);
        listOfPatients.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listOfPatients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Patient doc = patients.get(position);
                personDetails(doc);
            }
        });

        getAllPatients();
    }

    public void personDetails(Patient patient) {
        Intent intent = new Intent(this, PatientDetailsActivity.class);
        Bundle bundle = new Bundle();

        bundle.putSerializable("patient", patient);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void addNewPatient() {
        Intent intent = new Intent(this, AddNewPatientActivity.class);
        startActivity(intent);
    }

    public void onDeleteClick(View view) {
        if (deleteState){
            List<Patient> deletePatients = new ArrayList<Patient>();
            ListView listview = (ListView) findViewById(R.id.listOfPatients);
            for (int i=0; i<adapter.getCount(); i++) {
                RelativeLayout relativeLayout = (RelativeLayout)adapter.getView(i, listview.getChildAt(i), listview);
                CheckBox checkBox = (CheckBox) relativeLayout.findViewById(R.id.checkBox);
                if (checkBox.isChecked()) {
                    deletePatients.add(patients.get(i));
                }
            }
            deletePatients(deletePatients);
        }
        deleteStateVisibility(View.INVISIBLE);
        refresh();
    }
    private void refresh() {
        adapter.clear();
        getAllPatients();
    }

    private void deletePatients(List<Patient> patients) {
        for (Patient patient : patients) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(new Config().getURL()).
                                addConverterFactory(GsonConverterFactory.create())
                        .build();
                Api service = retrofit.create(Api.class);
                Call<ResponseBody> call = service.deletePatient(patient.getId());
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

    private void getAllPatients() {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(new Config().getURL()).
                            addConverterFactory(GsonConverterFactory.create())
                    .build();
            Api service = retrofit.create(Api.class);
            Call<List<Patient>> call = service.listOfPatients();
            call.enqueue(new Callback<List<Patient>>() {
                @Override
                public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
                    patients = response.body();
                    for (Patient doc : patients) {
                        adapter.add(doc.getFirstName() + " " + doc.getLastName());
                    }
                }

                @Override
                public void onFailure(Call<List<Patient>> call, Throwable t) {
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
        ListView listview = (ListView) findViewById(R.id.listOfPatients);
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
            addNewPatient();
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
