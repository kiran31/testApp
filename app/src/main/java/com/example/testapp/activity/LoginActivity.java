package com.example.testapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.testapp.MainActivity;
import com.example.testapp.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    EditText mobileNo;
    Button btnOtp;
    private CountryCodePicker ccp;
    String checker = "", phoneNumber="";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mobileNo = findViewById(R.id.phoneText);
        btnOtp = findViewById(R.id.btnOtp);
        progressBar = findViewById(R.id.progressBar);

        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(mobileNo);

        btnOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mobileNo.getText().toString().trim().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please Enter Mobile No.", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    phoneNumber = ccp.getFullNumberWithPlus();
                    if (!phoneNumber.equals("")){
                        progressBar.setVisibility(View.VISIBLE);
                        btnOtp.setVisibility(View.GONE);
                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                phoneNumber,
                                60,
                                TimeUnit.SECONDS,
                                LoginActivity.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        progressBar.setVisibility(View.GONE);
                                        btnOtp.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        progressBar.setVisibility(View.GONE);
                                        btnOtp.setVisibility(View.VISIBLE);
                                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        progressBar.setVisibility(View.GONE);
                                        btnOtp.setVisibility(View.VISIBLE);

                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.putExtra("mobileNo", mobileNo.getText().toString());
                                        intent.putExtra("mobileccp", phoneNumber);
                                        intent.putExtra("verificationId", verificationId);
                                        startActivity(intent);
                                    }
                                });

                    }
                }

            }
        });
    }
}