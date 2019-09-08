package net.andrasia.teambuilder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProfilePage extends AppCompatActivity {


    Button profilePageSettingsButton;
    ArrayList Languages;
    ArrayList Games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        setupViews();
        setupListener();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProfilePage.this, SearchConfigurationPage.class);
        startActivity(intent);
    }

    private void setupViews() {
        profilePageSettingsButton = findViewById(R.id.pPageSettingsButton);

    }

    private void setupListener() {
        profilePageSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfilePage.this, EditProfilePage.class);
                startActivity(intent);
            }
        });
    }


}