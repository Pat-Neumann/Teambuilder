package net.andrasia.teambuilder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterPage extends AppCompatActivity {

    Button registerBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        setupViews();
        setupListener();

    }

    private void setupViews() {
       registerBackBtn = findViewById(R.id.registerPageBackBt);
        /*    register = findViewById(R.id.landingPageRegister); */

    }

    private void setupListener() {
        registerBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterPage.this, LandingPage.class);
                startActivity(intent);

            }
        });
     /*
     add button to register
         register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LandingPage.this, RegisterPage.class);
                startActivity(registerIntent);
            }
        });
  */
    }

}