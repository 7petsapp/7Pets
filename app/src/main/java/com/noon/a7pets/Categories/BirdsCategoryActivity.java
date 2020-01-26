package com.noon.a7pets.Categories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.noon.a7pets.Productscategory.BirdFoodActivity;
import com.noon.a7pets.Productscategory.BirdSuppliesActivity;
import com.noon.a7pets.Productscategory.BuyBirdsActivity;
import com.noon.a7pets.R;

public class BirdsCategoryActivity extends AppCompatActivity {

    private RelativeLayout buy_bird, bird_food, bird_supplies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birds_category);

        buy_bird = findViewById(R.id.buy_bird);
        buy_bird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BirdsCategoryActivity.this, BuyBirdsActivity.class));
            }
        });

        bird_food = findViewById(R.id.bird_food);
        bird_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BirdsCategoryActivity.this, BirdFoodActivity.class));
            }
        });

        bird_supplies = findViewById(R.id.bird_supplies);
        bird_supplies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BirdsCategoryActivity.this, BirdSuppliesActivity.class));
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Birds");
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
