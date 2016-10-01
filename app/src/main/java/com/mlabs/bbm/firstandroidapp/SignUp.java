package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by Guitarista on 9/17/2016.
 */
public class SignUp extends AppCompatActivity{
    EditText editEmail;
    EditText editPassSU;
    EditText editPassCon;
    EditText editUsername;
    EditText editFirstname;
    EditText editLastname;
    Button btnRegister;
    DatabaseAdapter loginDatabaseAdapter;

    boolean pwvalidate(String password) {
        if(password.length() > 7) {
            return true;
        } else {
            return false;
        }
    }
    //------------------------------------------------------

    //------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        //commit
        loginDatabaseAdapter = new DatabaseAdapter(this);
        loginDatabaseAdapter = loginDatabaseAdapter.open();

        final EditText editFirstname = (EditText)findViewById(R.id.editFirstname);
        final EditText editLastname = (EditText)findViewById(R.id.editLastname);
        final EditText editUsername = (EditText)findViewById(R.id.editUsername);
        final EditText editEmail = (EditText)findViewById(R.id.editEmail);
        final EditText editPassSU = (EditText)findViewById(R.id.editPassSU);
        final EditText editPassCon = (EditText)findViewById(R.id.editPassCon);
        final Button btnRegister = (Button)findViewById(R.id.btnRegister);
        editUsername.requestFocus();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                /**
                 * for user name validation ONLY + password
                 */
                //if (Pattern.compile("([a-zA-Z0-9]+_?)+").matcher(editEmail.getText()).matches() && editPassSU.length()>=8 && editPassCon.length()>=8 && editPassSU == editPassCon)
                String emailInput = editEmail.getText().toString();
                String passwordInput = editPassSU.getText().toString();
                String passwordInputVerify = editPassCon.getText().toString();
                String userInput = editUsername.getText().toString();
                String firstInput = editFirstname.getText().toString();
                String lastInput = editLastname.getText().toString();


                boolean checkEmail;
                boolean checkUser;
                checkUser = loginDatabaseAdapter.getUsernameEntry(editUsername.getText().toString());
                checkEmail =loginDatabaseAdapter.getEmailEntry(editEmail.getText().toString());

                //-------------------------
                //if (Pattern.compile("^\\w+.*\\w*@[a-zA-Z_]+?\\.[0-9a-zA-Z]{2,}$").matcher(editUser.getText()).matches() && editPass.length()>=6)
                if(emailInput.equals("")||passwordInput.equals("")||passwordInputVerify.equals("")||userInput.equals("")||firstInput.equals("")||lastInput.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please fill out all the field.", Toast.LENGTH_LONG).show();
                    return;
                }
                //if(emailValidator(editTextEmail.getText())==false){
                if(Pattern.compile("^\\w+.*\\w*@[a-zA-Z_]+?\\.[0-9a-zA-Z]{2,}(\\.[a-z]{2})??").matcher(editEmail.getText()).matches()==false){
                    Toast.makeText(getApplicationContext(), "Invalid Email address.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(Pattern.compile("[a-zA-Z]+").matcher(editFirstname.getText()).matches()==false &&
                        Pattern.compile("[a-zA-Z]+").matcher(editLastname.getText()).matches()==false)
                {
                    Toast.makeText(getApplicationContext(), "Invalid First or Last name input", Toast.LENGTH_LONG).show();
                    return;
                }

                if(!passwordInput.equals(passwordInputVerify))
                {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }


                else {
                    if(Pattern.compile("^\\w+.*\\w*@[a-zA-Z_]+?\\.[0-9a-zA-Z]{2,}(\\.[a-z]{2})??").matcher(editEmail.getText()).matches()==true &&
                            Pattern.compile("([a-zA-Z0-9]+_?)+").matcher(editUsername.getText()).matches()==true &&
                            pwvalidate(editPassSU.getText().toString())==true && checkUser==true && checkEmail==true)
                    {
                        loginDatabaseAdapter.insertEntry(emailInput, passwordInput, userInput, firstInput, lastInput);
                        Toast.makeText(getApplicationContext(), "Agent Successfully Activated.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else if (Pattern.compile("^\\w+.*\\w*@[a-zA-Z_]+?\\.[0-9a-zA-Z]{2,}(\\.[a-z]{2})??").matcher(editEmail.getText()).matches()==true &&
                            Pattern.compile("([a-zA-Z0-9]+_?)+").matcher(editUsername.getText()).matches()==true &&
                            checkEmail == true && checkUser == true &&
                            pwvalidate(editPassSU.getText().toString())==false)
                    {
                        Toast.makeText(getApplicationContext(), "Password must be at least 8 character.", Toast.LENGTH_LONG).show();
                        return;
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Email or Username already taken", Toast.LENGTH_LONG).show();
                        return;
                    }

                }
            }
        });
    }
    //---------------------------------------------------------------------------------------------
    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDatabaseAdapter.close();
    }
}