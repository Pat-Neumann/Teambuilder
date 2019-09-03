package net.andrasia.teambuilder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {

    Button mockToSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        setupViews();
        setupListener();

    }

    private void setupViews() {
        mockToSearch = findViewById(R.id.mockButtonSearch);

    }

    private void setupListener() {
        mockToSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(LoginPage.this, SearchConfigurationPage.class);
                startActivity(loginIntent);
            }
        });

    }
}