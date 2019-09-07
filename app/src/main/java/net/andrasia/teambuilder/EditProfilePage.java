package net.andrasia.teambuilder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfilePage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile_page);

        setupViews();
        setupListener();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EditProfilePage.this, ProfilePage.class);
        startActivity(intent);
    }

    private void setupViews() {

    }

    private void setupListener() {
    }
}