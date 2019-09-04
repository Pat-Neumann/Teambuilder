package net.andrasia.teambuilder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {

    Button mockToSearch;
    Button loginPageBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        setupViews();
        setupListener();

    }

    private void setupViews() {
        mockToSearch = findViewById(R.id.mockButtonSearch);
        loginPageBackBtn = findViewById(R.id.loginPageBackBtn);

    }

    private void setupListener() {
        mockToSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(LoginPage.this, SearchConfigurationPage.class);
                finish();
                startActivity(loginIntent);
            }
        });
        loginPageBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, LandingPage.class);
                startActivity(intent);
            }
        });
    }
}