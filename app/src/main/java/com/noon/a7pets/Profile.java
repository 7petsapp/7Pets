package com.noon.a7pets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikepenz.crossfadedrawerlayout.view.CrossfadeDrawerLayout;
import com.mikepenz.materialdrawer.Drawer;
import com.noon.a7pets.models.User;
import com.noon.a7pets.networksync.CheckInternetConnection;
import com.noon.a7pets.usersession.UserSession;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.appcompat.widget.Toolbar;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    private Drawer result;
    private CrossfadeDrawerLayout crossfadeDrawerLayout = null;
    private TextView tvemail, tvphone;

    private TextView namebutton;
    private CircleImageView primage;
    private TextView updateDetails;
    private LinearLayout addressview;
    private String userId;
    private FirebaseAuth mAuth;


    //to get user session data
    private UserSession session;
    private HashMap<String, String> user;
    private String name, email, photo, mobile;
    private SliderLayout sliderShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        initialize();

        //check Internet Connection
        new CheckInternetConnection(this).checkConnection();

        //retrieve session values and display on listviews
        getValues();

        //ImageSLider
        inflateImageSlider();

    }

    private void initialize() {

        addressview = findViewById(R.id.addressview);
        primage = findViewById(R.id.profilepic);
        tvemail = findViewById(R.id.emailview);
        tvphone = findViewById(R.id.mobileview);
        namebutton = findViewById(R.id.name_button);
        updateDetails = findViewById(R.id.updatedetails);

        updateDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //     startActivity(new Intent(Profile.this,UpdateData.class));
                finish();
            }
        });

        addressview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //     startActivity(new Intent(Profile.this,Wishlist.class));
            }
        });
    }


    private void getValues() {

//        [\9+

        DatabaseReference usersDb = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
        usersDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    User user = dataSnapshot.getValue(User.class);
                    tvemail.setText(user.getEmail());
                    tvphone.setText(user.getMobile());
                    namebutton.setText(user.getName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Picasso.with(Profile.this).load(photo).into(primage);


    }

    private void inflateImageSlider() {

        // Using Image Slider -----------------------------------------------------------------------
        sliderShow = findViewById(R.id.slider);

        //populating Image slider
        ArrayList<String> sliderImages = new ArrayList<>();
        sliderImages.add("http://uploads.printland.in/fnf/online2017/home_republic_day.jpg");
        sliderImages.add("http://uploads.printland.in/fnf/online2017/calender-homepage-29-dec.jpg");
        sliderImages.add("http://uploads.printland.in/fnf/online2017/notebook-homepage-05-dec.jpg");
        sliderImages.add("http://uploads.printland.in/fnf/online2017/home-vtds.jpg");

        for (String s : sliderImages) {
            DefaultSliderView sliderView = new DefaultSliderView(this);
            sliderView.image(s);
            sliderShow.addSlider(sliderView);
        }

        sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void viewCart(View view) {
        startActivity(new Intent(Profile.this, Cart.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //check Internet Connection
        new CheckInternetConnection(this).checkConnection();
    }

    public void Notifications(View view) {
        //startActivity(new Intent(Profile.this,NotificationActivity.class));
        finish();
    }

    @Override
    protected void onStop() {
        sliderShow.stopAutoCycle();
        super.onStop();

    }
}
