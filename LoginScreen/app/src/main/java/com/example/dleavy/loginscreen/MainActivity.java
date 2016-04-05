package com.example.dleavy.loginscreen;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // Declare variables to grab the text entered by the user
    EditText unametext,passtext;
    Button loginbtn;

    // Declare variable for the textview that will hold the attempts
    TextView attempts;

    // Declare variables to hold the amount of attempts allowed and the correct username and password
    int counter = 3;
    String entereduname, enteredpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link the loginbtn variable to the button1 on the layout page
        loginbtn = (Button) findViewById(R.id.button1);

        /* Link the unametext and passtext variables to the edittext boxes on the layout
        where the user enters their credentials */
        unametext = (EditText)findViewById(R.id.EditText1);
        passtext = (EditText)findViewById(R.id.EditText2);

        //Link the attempts variable to the attemptstextview on the layout page
        attempts = (TextView)findViewById(R.id.attemptsTextView);



        // Call the onClick method when the loginbtn is clicked
        onClick(loginbtn);
    }

        public void onClick(View view)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Declare intent variable to null and set the correct login credentials
                Intent intent = null;
                String uname = "david";
                String pass = "pass";

                // Set strings to the values the user has entered
                String entereduname = unametext.getText().toString()  ;
                String enteredpass = passtext.getText().toString();

                // Compare the correct credentials to the credentals the user has inputted
                if( entereduname.equals(uname) && enteredpass.equals(pass)   )
                {

                    // If credentials are correct display message and direct user to qub website
                    Toast.makeText(getApplicationContext(), "You have succesfully logged in",
                            Toast.LENGTH_SHORT).show();

                    intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://www.qub.ac.uk"));



                }
                else
                {
                    // If credentials are incorrect display message and lower counter by 1
                    Toast.makeText(getApplicationContext(), "Incorrect Username or Password",
                            Toast.LENGTH_SHORT).show();

                    counter--;

                    // Set the counter textview to the new counter value
                    attempts.setText(Integer.toString(counter));

                   /* Switch statement that checks the attempts left and changes the colour
                    from green, orange and red */
                   switch (counter){
                        case 3:
                            attempts.setTextColor(Color.parseColor("#458B00"));break;
                        case 2:
                            attempts.setTextColor(Color.parseColor("#FFA500"));break;
                        case 1:
                            attempts.setTextColor(Color.parseColor("#FF0000"));break;


                    }

                    // If counter is 0 disable loginbtn
                    if (counter == 0){
                        loginbtn.setEnabled(false);
                    }


                }

                /* Checks if the intent is not null which happens when the credentals are correct
                and starts the activity to direct the user to qub website */
                if (intent != null) {
                    startActivity(intent);
                }

        }

    });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
