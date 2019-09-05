package net.andrasia.teambuilder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfilePage extends AppCompatActivity {

    Button editProfileBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile_page);

        setupViews();
        setupListener();
    }

    private void setupViews() {
        editProfileBackButton = findViewById(R.id.editProfilePageBackBtn);


    }

    private void setupListener() {
        editProfileBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfilePage.this, ProfilePage.class);
                startActivity(intent);
            }
        });
    }
}