package com.e.c2cjprtechnosoft.activitis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.c2cjprtechnosoft.Activities.NavigationActivity;
import com.e.c2cjprtechnosoft.R;
import com.e.c2cjprtechnosoft.Retrofit.RestClient;
import com.e.c2cjprtechnosoft.Utils.Utils;
import com.e.c2cjprtechnosoft.model.udateUser.UpdateUserRequest;
import com.e.c2cjprtechnosoft.model.udateUser.UpdateUserResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateUserActivity extends AppCompatActivity {


    @BindView(R.id.edit_email)
    EditText editTextEmail;

    @BindView(R.id.edit_username)
    EditText editTextUsername;

    @BindView(R.id.edit_phone)
    EditText editTextPhone;

    @BindView(R.id.btn_update)
    Button btnUpdate;

    String email, name, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        ButterKnife.bind(this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });


    }

    private void update() {
        email = editTextEmail.getText().toString().trim();
        phone = editTextPhone.getText().toString().trim();
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

        if (TextUtils.isEmpty(phone)) {

            editTextPhone.setError(getString(R.string.invalid_email));

            return;
        } else {
            if (phone.length() < 10) {
                editTextPhone.setError(getString(R.string.valid_phone));
                return;
            }
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
                        Toast.makeText(UpdateUserActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdateUserActivity.this, NavigationActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }

                @Override
                public void onFailure(Call<UpdateUserResponse> call, Throwable t) {
                    Toast.makeText(UpdateUserActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    Utils.dismissProgressDialog();
                }
            });


        } else {
            Toast.makeText(this, "Internet Connection Failed", Toast.LENGTH_SHORT).show();
            Utils.dismissProgressDialog();
        }


    }
}
