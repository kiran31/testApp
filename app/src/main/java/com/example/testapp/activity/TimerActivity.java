package com.example.testapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testapp.R;

public class TimerActivity extends AppCompatActivity {
    int time = 30;
    TextView textTimer;
    TextView textCount;
    EditText edtAbout;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        textTimer = (TextView)findViewById(R.id.timer);
        textCount = (TextView)findViewById(R.id.txtCount);
        edtAbout = findViewById(R.id.edtAbout);
        btnSubmit = findViewById(R.id.btnSubmit);
        edtAbout.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                int length = edtAbout.length();
                String convert = String.valueOf(length);
                textCount.setText(convert);
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                textTimer.setText("0:" + checkDigit(time));
                time--;
            }

            public void onFinish() {
                edtAbout.setEnabled(false);
                btnSubmit.setVisibility(View.VISIBLE);
                textTimer.setText("Submit the Answer");
            }

        }.start();
    }

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }
}