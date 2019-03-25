package com.e.c2cjprtechnosoft.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import com.e.c2cjprtechnosoft.R;

import java.util.ArrayList;
import java.util.Arrays;

public class PickupSearchActivity extends AppCompatActivity {

    ImageView imageViewBack;
    ListView listViewData;
    ArrayAdapter<String> arrayAdapter;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_search);
        imageViewBack=findViewById(R.id.back);
        listViewData=findViewById(R.id.listview);
        searchView=findViewById(R.id.search_view);


        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.addAll(Arrays.asList(getResources().getStringArray(R.array.array_country)));
        arrayAdapter=new ArrayAdapter<String>(PickupSearchActivity.this,android.R.layout.simple_expandable_list_item_1,arrayList);
        listViewData.setAdapter(arrayAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
