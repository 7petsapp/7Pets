package com.noon.a7pets.Productscategory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.noon.a7pets.ProductsAdapter;
import com.noon.a7pets.R;
import com.noon.a7pets.models.GenericProductModel;

import java.util.ArrayList;

public class BuyFishActivity extends AppCompatActivity {

    private RecyclerView fish_recycler_view;
    private StaggeredGridLayoutManager mLayoutManager;
    private ProductsAdapter productsAdapter;
    private DatabaseReference fishReference;
    private ArrayList<GenericProductModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_fish);

        fish_recycler_view = findViewById(R.id.fish_recycler_view);

        if (fish_recycler_view != null) {
            //to enable optimization of recyclerview
            fish_recycler_view.setHasFixedSize(true);
        }

        list = new ArrayList<GenericProductModel>();

        //using staggered grid pattern in recyclerview
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        fish_recycler_view.setLayoutManager(mLayoutManager);
        fish_recycler_view.setHasFixedSize(true);
        fetch();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Buy Fish");
    }

    private void fetch() {

        fishReference = FirebaseDatabase.getInstance().getReference().child("fish").child("buy fish");

        fishReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                if (dataSnapshot.exists()) {
                    GenericProductModel g = dataSnapshot.getValue(GenericProductModel.class);
                    list.add(g);
                }
                productsAdapter = new ProductsAdapter(BuyFishActivity.this, list);
                fish_recycler_view.setAdapter(productsAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                productsAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
