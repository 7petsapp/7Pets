package com.noon.a7pets.SpinnersListeners;

import android.view.View;
import android.widget.AdapterView;

import com.noon.a7pets.Categories.ClinicReservationActivity;

public class AppointmentTimeSpinnerListener implements AdapterView.OnItemSelectedListener{

    public static String AppointmentTime;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getItemAtPosition(position).equals("Appointment Time")){
            //do nothing
            ClinicReservationActivity.APPOINTMENT_TIME = 1;
        } else {
            ClinicReservationActivity.APPOINTMENT_TIME = 0;
            AppointmentTime = parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
