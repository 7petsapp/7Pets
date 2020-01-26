package com.noon.a7pets.Categories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.noon.a7pets.Productscategory.CatFoodActivity;
import com.noon.a7pets.Productscategory.CatSuppliesActivity;
import com.noon.a7pets.R;

public class CatsCategoryActivity extends AppCompatActivity {

    private RelativeLayout cat_food, cat_supplies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cats_category);

        cat_food = findViewById(R.id.cat_food);
        cat_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CatsCategoryActivity.this, CatFoodActivity.class));
            }
        });

        cat_supplies = findViewById(R.id.cat_supplies);
        cat_supplies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CatsCategoryActivity.this, CatSuppliesActivity.class));
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Cats");
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
