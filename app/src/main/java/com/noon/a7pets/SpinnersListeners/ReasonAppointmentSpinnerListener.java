package com.noon.a7pets.SpinnersListeners;

import android.view.View;
import android.widget.AdapterView;

import com.noon.a7pets.Categories.ClinicReservationActivity;

public class ReasonAppointmentSpinnerListener implements AdapterView.OnItemSelectedListener{

    public static String ReasonOfAppointment;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getItemAtPosition(position).equals("Reason of Appointment")){
            //do nothing
            ClinicReservationActivity.REASON_OF_APPOINTMENT = 1;
        } else {
            ClinicReservationActivity.REASON_OF_APPOINTMENT = 0;
            ReasonOfAppointment = parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
