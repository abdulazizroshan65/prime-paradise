package com.example.prime_paradise;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    DatePickerDialog datepicker;
    EditText txtFname, txtEmail, txtDate;
    RadioGroup rdogrpGender;
    RadioButton selectedRdoBtn;
    ImageView imgCalendar;

    String name,email,dob,temp="empty";
    CharSequence gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_signup);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        txtFname = findViewById(R.id.txtFname);
        txtEmail = findViewById(R.id.txtEmail);
        rdogrpGender = (RadioGroup) findViewById(R.id.rdogrpGender);
        txtDate = (EditText) findViewById(R.id.txtDate);
        imgCalendar = (ImageView) findViewById(R.id.imgCalendar);
    }

    public void calendar(View view){
        txtDate.setInputType(InputType.TYPE_NULL);
        imgCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);
                datepicker = new DatePickerDialog(SignupActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        txtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
                datepicker.show();
            }
        });
    }

    public void Gonext(View view){
        name = txtFname.getText().toString();
        email = txtEmail.getText().toString();
        dob = txtDate.getText().toString();
        int selectedId = rdogrpGender.getCheckedRadioButtonId();
        if (selectedId>-1){
            selectedRdoBtn = (RadioButton) findViewById(selectedId);
            gender = selectedRdoBtn.getText();
            temp = "selected";
        }

        if (!name.isEmpty() && !email.isEmpty() && !dob.isEmpty() && temp=="selected") {
            Intent intent = new Intent(SignupActivity.this, NextActivity.class);
            intent.putExtra("Full Name", name);
            intent.putExtra("Email", email);
            intent.putExtra("DOB", dob);
            intent.putExtra("Gender", gender);
            startActivity(intent);
        }else {
            Toast.makeText(this,"Fields Cannot Be Empty", Toast.LENGTH_SHORT).show();
        }
    }

    public void clrFullname(View view){
        txtFname.setText(null);
    }
    public void clrEmail(View view){
        txtEmail.setText(null);
    }
}