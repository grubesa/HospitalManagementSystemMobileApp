package com.hms.josip.hospitalmanagementsystem;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.test.mock.MockContext;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.hms.josip.hospitalmanagementsystem.activities.Doctor.AddNewDoctorActivity;
import com.hms.josip.hospitalmanagementsystem.model.Doctor;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DoctorTest extends AppCompatActivity {

    @Mock
    RadioButton radioButton;

    @Mock
    Doctor doctorMock;

    @Test
    public void sexRadioButtonTest() {
        AddNewDoctorActivity addNewDoctorActivity = new AddNewDoctorActivity();
        Doctor doctor = new Doctor();
        addNewDoctorActivity.setDoctor(doctor);

        when(radioButton.isChecked()).thenReturn(true);
        when(radioButton.getId()).thenReturn(R.id.male);

        addNewDoctorActivity.onSexClicked(radioButton);

        Assert.assertEquals(addNewDoctorActivity.getDoctor().getSex(), "Male");
    }

    @Test
    public void statusRadioButtonTest() {
        AddNewDoctorActivity addNewDoctorActivity = new AddNewDoctorActivity();
        Doctor doctor = new Doctor();
        addNewDoctorActivity.setDoctor(doctor);

        when(radioButton.isChecked()).thenReturn(true);
        when(radioButton.getId()).thenReturn(R.id.visiting);

        addNewDoctorActivity.onStatusClicked(radioButton);

        Assert.assertEquals(addNewDoctorActivity.getDoctor().getStatus(), "Visiting");
    }

}
