package net.andrasia.teambuilder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SearchConfigurationPage extends AppCompatActivity {

    Button profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_configuration_page);

        setupViews();
        setupListener();
    }

    private void setupViews() {
        profileButton = findViewById(R.id.searchConfigProfileButton);


    }

    private void setupListener() {
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchConfigurationPage.this, ProfilePage.class);
                startActivity(intent);
            }
        });
    }
}