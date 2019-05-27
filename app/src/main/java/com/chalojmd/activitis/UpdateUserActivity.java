package com.chalojmd.activitis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chalojmd.Utils.C2CPref;
import com.chalojmd.Utils.Constants;
import com.chalojmd.activitis.NavigationActivity;
import com.chalojmd.R;
import com.chalojmd.Retrofit.RestClient;
import com.chalojmd.Utils.Utils;
import com.chalojmd.model.udateUser.UpdateUserRequest;
import com.chalojmd.model.udateUser.UpdateUserResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateUserActivity extends AppCompatActivity {


    @BindView(R.id.email)
    EditText editTextEmail;

    @BindView(R.id.name)
    EditText editTextUsername;


    @BindView(R.id.updateDetails)
    Button btnUpdate;

    String email, name, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        ButterKnife.bind(this);


        if (getIntent().hasExtra("mobile")){
            phone=getIntent().getStringExtra("mobile");
        }
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });


    }

    private void update() {
        email = editTextEmail.getText().toString().trim();
        name = editTextUsername.getText().toString().trim();


        if (TextUtils.isEmpty(name.trim()) || name.length() == 0) {
            editTextUsername.setError(getString(R.string.invalid_username));
            Utils.displayToast(getApplicationContext(), getString(R.string.invalid_username));
            return;
        }
        if (TextUtils.isEmpty(email.trim()) || email.length() == 0) {
            editTextEmail.setError(getString(R.string.invalid_email));
            Utils.displayToast(getApplicationContext(), getString(R.string.invalid_email));
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError(getString(R.string.invalid_email));
            Utils.displayToast(getApplicationContext(), getString(R.string.invalid_email));
            return;
        }


        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setEmail(email);
        updateUserRequest.setMobile(phone);
        updateUserRequest.setUsername(name);

        if (Utils.isInternetConnected(this)) {
            Utils.showProgressDialog(this);
            RestClient.updateUser(updateUserRequest, new Callback<UpdateUserResponse>() {
                @Override
                public void onResponse(Call<UpdateUserResponse> call, Response<UpdateUserResponse> response) {
                    if (response.body() != null) {
                        Utils.dismissProgressDialog();
                        C2CPref.putString(UpdateUserActivity.this, Constants.NAME,response.body().getUsername());
                        C2CPref.putString(UpdateUserActivity.this, Constants.EMAILID,response.body().getEmail());
                        C2CPref.putBoolean(UpdateUserActivity.this, Constants.IS_LOOGED_IN,true);
                        Intent intent = new Intent(UpdateUserActivity.this, NavigationActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }

                @Override
                public void onFailure(Call<UpdateUserResponse> call, Throwable t) {
                    Toast.makeText(UpdateUserActivity.this, "Something went wrong, lease try again!", Toast.LENGTH_SHORT).show();
                    Utils.dismissProgressDialog();
                }
            });


        } else {
            Toast.makeText(this, "Internet Connection Failed", Toast.LENGTH_SHORT).show();
            Utils.dismissProgressDialog();
        }


    }
}
