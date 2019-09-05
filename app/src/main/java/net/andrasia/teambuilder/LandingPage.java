package net.andrasia.teambuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LandingPage extends AppCompatActivity  {

    Button login;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        setupViews();
        setupListener();

    }

    private void setupViews() {
        login = findViewById(R.id.landingPagelogIn);
        register = findViewById(R.id.landingPageRegister);

    }

    private void setupListener() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(LandingPage.this, LoginPage.class);
                startActivity(loginIntent);

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LandingPage.this, RegisterPage.class);
                startActivity(registerIntent);
            }
        });

    }

}
