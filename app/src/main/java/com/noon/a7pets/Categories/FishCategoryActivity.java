package com.noon.a7pets.Categories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.noon.a7pets.Productscategory.BuyFishActivity;
import com.noon.a7pets.Productscategory.FishFoodActivity;
import com.noon.a7pets.Productscategory.FishSuppliesActivity;
import com.noon.a7pets.R;

public class FishCategoryActivity extends AppCompatActivity {

    private RelativeLayout buy_fish, fish_food, fish_supplies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_category);

        buy_fish = findViewById(R.id.buy_fish);
        buy_fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FishCategoryActivity.this, BuyFishActivity.class));
            }
        });
        fish_food = findViewById(R.id.fish_food);
        fish_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FishCategoryActivity.this, FishFoodActivity.class));
            }
        });
        fish_supplies = findViewById(R.id.fish_supplies);
        fish_supplies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FishCategoryActivity.this, FishSuppliesActivity.class));
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Fish");
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
