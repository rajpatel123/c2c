package com.chalojmd.activitis.rider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.chalojmd.R;
import com.chalojmd.Retrofit.RestClient;
import com.chalojmd.Utils.C2CPref;
import com.chalojmd.model.registration.RegistrationRequest;
import com.chalojmd.model.registration.RegistrationResponse;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationFormActivity extends AppCompatActivity {
    private EditText userEmail_id, user_Name, user_DateOfBirth, calenderDate;
    private RadioButton radioButtonMale, radioButtonFemale, userIspooler, userIsRider;
    private ImageView calenderImage,backbtnregistration;
    private Calendar c;
    private Button submitButton;
    private DatePickerDialog dp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        calenderImage = findViewById(R.id.calenderPicker);
        backbtnregistration=findViewById(R.id.backbtn_registration);
        calenderDate = findViewById(R.id.calender_dob);
        userIspooler = findViewById(R.id.radio_pooler);
        userIsRider = findViewById(R.id.radio_Rider);
        radioButtonMale = findViewById(R.id.radio_Male);
        radioButtonFemale = findViewById(R.id.radio_female);
        userEmail_id = findViewById(R.id.user_email);
        user_Name = findViewById(R.id.user_name);
        submitButton = findViewById(R.id.submitButton);

        if (radioButtonMale.isChecked()) {
            radioButtonMale.setText("Male");

        } else if (radioButtonFemale.isChecked())
            radioButtonFemale.setText("Female");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndRegistrationForm();
            }
        });

        calenderImage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);
                dp = new DatePickerDialog(RegistrationFormActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        calenderDate.setText(year + "/" + (month + 1) + "/" + day);

                    }
                }, day, year, month);
                dp.show();


            }
        });

        backbtnregistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(RegistrationFormActivity.this,OtpVerifyActivity.class );
                startActivity(i);
            }
        });
    }

    public void onRadioButtonClicked(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        switch (v.getId()) {

            case R.id.radio_Male:
                if (checked)
                    break;

            case R.id.radio_female:
                if (checked)
                    break;
        }


    }

    public void validateAndRegistrationForm() {
        String name_User = user_Name.getText().toString().trim();
        String email_User  = userEmail_id.getText().toString().trim();



        String userId = C2CPref.getString(getApplicationContext(), "user_id");
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setEmail(email_User);
        registrationRequest.setName(name_User);
        registrationRequest.setDob("1995/10/24");
        registrationRequest.setGender("Male");
        registrationRequest.setId(userId);
        registrationRequest.setRole("pooler");

        RestClient.registerNewUser(registrationRequest, new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                if(response != null){
                    if(response.body().getStatus() == true){
                        Intent i = new Intent(RegistrationFormActivity.this,DriverLicenseActivity.class);
                        startActivity(i);

                        Toast.makeText(RegistrationFormActivity.this, "successfully", Toast.LENGTH_SHORT).show();
                    }else if(response.body().getStatus() == false){

                        Toast.makeText(RegistrationFormActivity.this, "failed", Toast.LENGTH_SHORT).show();
                    }


                }


            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                Toast.makeText(RegistrationFormActivity.this, "failed", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
