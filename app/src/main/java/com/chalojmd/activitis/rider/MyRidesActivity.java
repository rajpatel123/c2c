package com.chalojmd.activitis.rider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.chalojmd.Adapter.AllMyRideAdapter;
import com.chalojmd.R;
import com.chalojmd.Retrofit.ApiClient;
import com.chalojmd.Retrofit.ApiInterface;
import com.chalojmd.Retrofit.RestClient;
import com.chalojmd.Utils.Utils;
import com.chalojmd.model.BestSelling;
import com.chalojmd.model.TestResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyRidesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<TestResponse> itemList1;


    private CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rides);
        cardView=findViewById(R.id.card_view);
        recyclerView = findViewById(R.id.recyclerview);
        allMyRides();
       /* cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyRidesActivity.this, RideDetailsActivity.class);
                startActivity(intent);
            }
        });*/
        if (getSupportActionBar() != null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home) {

            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public  void allMyRides(){

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        Call<TestResponse> call = apiService.getAllRides();
        call.enqueue(new Callback<TestResponse>() {
            @Override
            public void onResponse(Call<TestResponse> call, Response<TestResponse> response) {

                List<BestSelling> bestSellingList = response.body().getBestSelling();
                recyclerView.setAdapter(new AllMyRideAdapter(bestSellingList, R.layout.recyler_my_rides, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<TestResponse> call, Throwable t) {
                Toast.makeText(MyRidesActivity.this, "failure", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

