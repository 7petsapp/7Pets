package com.noon.a7pets.Categories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.noon.a7pets.Productscategory.OthersFoodActivity;
import com.noon.a7pets.Productscategory.OthersSuppliesActivity;
import com.noon.a7pets.R;

public class OthersCategoryActivity extends AppCompatActivity {

    private RelativeLayout others_food, others_supplies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_category);

        others_food = findViewById(R.id.others_food);
        others_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OthersCategoryActivity.this, OthersFoodActivity.class));
            }
        });

        others_supplies = findViewById(R.id.others_supplies);
        others_supplies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OthersCategoryActivity.this, OthersSuppliesActivity.class));
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Others");
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
