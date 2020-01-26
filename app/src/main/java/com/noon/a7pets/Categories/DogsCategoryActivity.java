package com.noon.a7pets.Categories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.noon.a7pets.Productscategory.DogSuppliesActivity;
import com.noon.a7pets.Productscategory.DogsFoodActivity;
import com.noon.a7pets.R;

public class DogsCategoryActivity extends AppCompatActivity {

    private RelativeLayout dog_food, dog_supplies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs_category);

        dog_food = findViewById(R.id.dog_food);
        dog_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DogsCategoryActivity.this, DogsFoodActivity.class));
            }
        });

        dog_supplies = findViewById(R.id.dog_supplies);
        dog_supplies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DogsCategoryActivity.this, DogSuppliesActivity.class));
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Dogs");
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
