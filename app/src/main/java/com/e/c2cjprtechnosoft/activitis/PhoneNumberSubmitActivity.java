package com.e.c2cjprtechnosoft.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.e.c2cjprtechnosoft.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhoneNumberSubmitActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.next_button)
    Button nextBtn;
    @BindView(R.id.linear_five)
    LinearLayout linearLayout;
    @BindView(R.id.edit_number)
    EditText editNumber;
    @BindView(R.id.back_button)
    ImageView backImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number_submit);
        ButterKnife.bind(this);
        nextBtn.setOnClickListener(this);
        editNumber.setOnClickListener(this);
        backImage.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_button:
                Toast.makeText(PhoneNumberSubmitActivity.this, "Submit", Toast.LENGTH_SHORT).show();
                checkValidation();
                break;

            case R.id.edit_number:
                linearLayout.setVisibility(View.GONE);
                break;
            case R.id.back_button:
                finish();
                break;
        }
    }

    private void checkValidation() {
        Intent intent = new Intent(PhoneNumberSubmitActivity.this, VerifyOtpActivity.class);
        startActivity(intent);
        finish();
    }
}
