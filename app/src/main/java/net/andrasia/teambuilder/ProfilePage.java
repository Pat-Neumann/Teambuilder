package net.andrasia.teambuilder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ProfilePage extends AppCompatActivity {

    Button profilePageBackButton;
    Button profilePageSettingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        setupViews();
        setupListener();
    }

    private void setupViews() {
        profilePageBackButton = findViewById(R.id.pPageBackButton);
        profilePageSettingsButton = findViewById(R.id.pPageSettingsButton);


    }

    private void setupListener() {
        profilePageBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfilePage.this, SearchConfigurationPage.class);
                finish();
                startActivity(intent);
            }
        });
        profilePageSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfilePage.this, EditProfilePage.class);
                finish();
                startActivity(intent);
            }
        });
    }
}