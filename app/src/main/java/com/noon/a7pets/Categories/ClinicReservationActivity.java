package com.noon.a7pets.Categories;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.noon.a7pets.Fragments.DatePickerFragment;
import com.noon.a7pets.R;
import com.noon.a7pets.SpinnersListeners.ReasonAppointmentSpinnerListener;
import com.noon.a7pets.models.ClinicReservation;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ClinicReservationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public static int REASON_OF_APPOINTMENT = 0;
    public static int APPOINTMENT_TIME = 0;
    private Spinner spinnerReasonOfAppointment, spinnerAppointmentTime;
    private EditText appointmentDate;
    private Button buttonSubmitAppointment;
    private String userId;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar_reservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_reservation);

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        progressBar_reservation = findViewById(R.id.progressBar_reservation);

        appointmentDate = findViewById(R.id.appointmentDate);
        appointmentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        buttonSubmitAppointment = findViewById(R.id.buttonSubmitAppointment);
        buttonSubmitAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });

        spinnerReasonOfAppointment = findViewById(R.id.spinnerReasonOfAppointment);
        spinnerAppointmentTime = findViewById(R.id.spinnerAppointmentTime);

        reasonOfAppointmentSpinner();
        AppointmentTimeSpinner();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Clinic Reservation");
    }

    private void reasonOfAppointmentSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.reason_of_appointment, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerReasonOfAppointment.setAdapter(adapter);
        spinnerReasonOfAppointment.setOnItemSelectedListener(new ReasonAppointmentSpinnerListener());
    }

    private void AppointmentTimeSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.appointment_time, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAppointmentTime.setAdapter(adapter);
        spinnerAppointmentTime.setOnItemSelectedListener(new ReasonAppointmentSpinnerListener());
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String chosenDateString = dateFormat.format(c.getTime());
        appointmentDate.setText(chosenDateString);
        appointmentDate.setError(null);
    }

    private void submitForm() {
        final String reasonOfAppointment = spinnerReasonOfAppointment.getSelectedItem().toString();
        final String appointmentTime = spinnerAppointmentTime.getSelectedItem().toString();
        final String appointmentdate = appointmentDate.getText().toString().trim();
        if (spinnerReasonOfAppointment.getSelectedItem().toString().equals("Reason of Appointment")) {
            Toast.makeText(getApplicationContext(), "Choose a reason of the Appointment", Toast.LENGTH_LONG).show();
            return;
        }
        if (appointmentdate.isEmpty()) {
            appointmentDate.setError("Enter Appointment's date");
            appointmentDate.requestFocus();
            return;
        }
        if (spinnerAppointmentTime.getSelectedItem().toString().equals("Appointment Time")) {
            Toast.makeText(getApplicationContext(), "Choose Appointment time", Toast.LENGTH_LONG).show();
            return;
        }
        buttonSubmitAppointment.setVisibility(View.INVISIBLE);
        progressBar_reservation.setVisibility(View.VISIBLE);
        DatabaseReference appointmentsDb = FirebaseDatabase.getInstance().getReference().child("appointments").child(userId);
        ClinicReservation clinicReservation = new ClinicReservation(reasonOfAppointment, appointmentdate, appointmentTime);
        appointmentsDb.setValue(clinicReservation).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ClinicReservationActivity.this, "Appointment has ben reserved scuccessfully", Toast.LENGTH_LONG).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ClinicReservationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                buttonSubmitAppointment.setVisibility(View.VISIBLE);
                progressBar_reservation.setVisibility(View.INVISIBLE);
            }
        });
//        appointmentsDb.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                if (dataSnapshot.exists()) {
//                    ClinicReservation reservation = dataSnapshot.getValue(ClinicReservation.class);
//                    if (reservation.getDate().equals(appointmentdate) && reservation.getTime().equals(appointmentTime)) {
//                        Toast.makeText(ClinicReservationActivity.this, "Appointment time is not available, please choose another time", Toast.LENGTH_LONG).show();
//                        buttonSubmitAppointment.setVisibility(View.VISIBLE);
//                    } else {
//                        appointmentsDb.setValue(clinicReservation).addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Toast.makeText(ClinicReservationActivity.this, "Appointment has ben reserved scuccessfully", Toast.LENGTH_LONG).show();
//                                finish();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(ClinicReservationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
//                                buttonSubmitAppointment.setVisibility(View.VISIBLE);
//                            }
//                        });
//                    }
//                }
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //To support reverse transition when user clicks the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
