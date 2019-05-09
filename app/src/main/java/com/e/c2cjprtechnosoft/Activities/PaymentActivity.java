package com.e.c2cjprtechnosoft.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.e.c2cjprtechnosoft.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentActivity extends AppCompatActivity {

    @BindView(R.id.linear_cash)
    LinearLayout linearLayoutCash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        linearLayoutCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PaymentActivity.this,WalletActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home) {

            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
