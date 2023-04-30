package com.example.prime_paradise;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText txtUsername,txtPassword;
    TextView txtShowHide;
    RadioGroup rdogrpAccType;
    RadioButton selectedRdoBtn, rbAdmin, rbMember;
    DB_Opera database;

    String accType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        database = new DB_Opera(this);

        txtShowHide = findViewById(R.id.txtShowh);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        rdogrpAccType = (RadioGroup) findViewById(R.id.rdogrpAccType);
        rbAdmin = (RadioButton) findViewById(R.id.rbAdmin);
        rbMember = (RadioButton) findViewById(R.id.rbMember);
    }

    public void Login(View view){
        int selectedId = rdogrpAccType.getCheckedRadioButtonId();
        if (selectedId>-1){
            selectedRdoBtn = (RadioButton) findViewById(selectedId);
            accType = selectedRdoBtn.getText().toString();
        }

        try{
            if (!txtUsername.getText().toString().isEmpty() && !txtPassword.getText().toString().isEmpty() && accType!=null){
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                if (accType.equals("Admin")){
                    Admin a = new Admin();
                    a.setUname(username);
                    a = database.searchAdmin(a);
                    try {
                        if (a != null){
                            if (a.getPass().equals(password)){
                                Intent intent = new Intent(LoginActivity.this,AdminActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(this,"Incorrect Username or Password",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }catch (Exception ex){
                        Toast.makeText(this,"Admin Record Not Found",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    User u = new User();
                    u.setUsername(username);
                    u = database.searchUser(u);
                    try {
                        if (u != null){
                            if (u.getPassword().equals(password)){
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                intent.putExtra("Username",username);
                                startActivity(intent);
                            }else{
                                Toast.makeText(this,"Incorrect Username or Password",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }catch (Exception ex){
                        Toast.makeText(this,"User Record Not Found",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }catch (Exception ex){
            Toast.makeText(this,"User Type Not Selected",Toast.LENGTH_SHORT).show();
        }
    }

    public void SignUp(View view){
        Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
        startActivity(intent);
    }

    public void ShowHide(View view){
        if(txtShowHide.getText().toString().equals("Show")){
            txtPassword.setTransformationMethod(null);
            txtShowHide.setText("Hide");
        } else{
            txtPassword.setTransformationMethod(new PasswordTransformationMethod());
            txtShowHide.setText("Show");
        }
    }

    public void open(View view){
        Intent intent = new Intent(LoginActivity.this,AdminActivity.class);
        startActivity(intent);
    }
}
