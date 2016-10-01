package com.mlabs.bbm.firstandroidapp;

//import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;//text view declaration of pwd_show
import android.text.InputType;//class for show password

public class MainActivity extends AppCompatActivity {

    private EditText editUser;
    private EditText editPass;
    private Button pwd_show; //Declaration for show password variable
    private TextView signup;
    //Boolean onTouch = true; //For ShowPassword
    DatabaseAdapter loginDatabaseAdapter;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //commit
        editUser = (EditText) findViewById(R.id.editUser);
        editUser.requestFocus();
        editPass = (EditText) findViewById(R.id.editPass);
        loginDatabaseAdapter = new DatabaseAdapter(this);
        loginDatabaseAdapter = loginDatabaseAdapter.open();
        pwd_show = (Button)findViewById(R.id.pwd_show); //another declaration for pwd_show
        //below is the actual code of pwd_show based on STACK OVERFLOW
        pwd_show.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionevent){
                switch ( motionevent.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        editPass.setInputType(InputType.TYPE_CLASS_TEXT);
                        editPass.setSelection(editPass.length());
                        break;
                    case MotionEvent.ACTION_UP:
                        editPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        editPass.setSelection(editPass.length());
                        break;
                }
                return true;
            }
        });
        //end of pwd_show -------------------------------------------------------

        //start of signup code once click
        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this,SignUp.class);
                startActivity(i);
            }
        });
        //end of signup code once click

        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * for user name validation ONLY + password
                 *
                 if (Pattern.compile("([a-zA-Z0-9]+_?)+").matcher(editUser.getText()).matches() && editPass.length()>=6)
                 {
                 Intent i = new Intent(MainActivity.this,Display.class);
                 startActivity(i);
                 Toast.makeText(getBaseContext(), "Welcome back Agent!",Toast.LENGTH_SHORT).show();
                 Toast.makeText(getBaseContext(), "Transmission's jammed. Proximity Coverage Only",Toast.LENGTH_LONG).show();
                 }
                 /**
                 * for email validation ONLY + password
                 */
                /**
                 if (Pattern.compile("^\\w+.*\\w*@[a-zA-Z_]+?\\.[0-9a-zA-Z]{2,}$").matcher(editUser.getText()).matches() && editPass.length()>=6){
                 Intent i = new Intent(MainActivity.this,Display.class);
                 startActivity(i);
                 Toast.makeText(getBaseContext(), "Welcome back Agent!",Toast.LENGTH_SHORT).show();
                 }
                 else{
                 Toast.makeText(getBaseContext(),"Email or Password is Incorrect",Toast.LENGTH_SHORT).show();
                 }
                 */
                String email = editUser.getText().toString();
                //String userName = editUser.getText().toString();
                String pw = editPass.getText().toString();
                String strpw = loginDatabaseAdapter.getSinlgeEntry(email);//User
                String strpw1 = loginDatabaseAdapter.getSinlgeEntryEmail(email);//email

                if (pw.equals(strpw))
                {
                    Toast.makeText(MainActivity.this, "Welcome Back Agent!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), Display.class);
                    startActivity(i);
                }
                else if (pw.equals(strpw1))
                {
                    Toast.makeText(MainActivity.this, "Welcome Back Agent!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), Display.class);
                    startActivity(i);
                }

                else {
                    if (email.equals("") && pw.equals(""))
                    {
                        Toast.makeText(getApplicationContext(), "Please fill out all the field.", Toast.LENGTH_LONG).show();
                        return;
                    }
                    else if(email.equals("") || pw.equals(""))
                    {
                        Toast.makeText(getApplicationContext(), "Please fill out the field.", Toast.LENGTH_LONG).show();
                        return;
                    }
                    Toast.makeText(MainActivity.this, "Incorrect Email/Username or Password.", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    //Exit automatically code
    //@Override
    //protected void onPause() {
    //    super.onPause();
    //    finish();
    //}
    //end of exit code --------------
    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDatabaseAdapter.close();
    }


}

