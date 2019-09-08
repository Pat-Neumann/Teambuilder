package net.andrasia.teambuilder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SearchConfigurationPage extends AppCompatActivity {

    Button profileButton;
    Button startQueue;
    Button userLogout;

    private FirebaseAuth loAuth;
    private FirebaseUser loUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_configuration_page);

        setupViews();
        setupLogOut();
        setupListener();
        setupLogOutListener();

    }


    @Override
    public void onBackPressed() {

    }

    private void setupViews() {
        profileButton = findViewById(R.id.searchConfigProfileButton);
        startQueue = findViewById(R.id.searchButton);
        userLogout = findViewById(R.id.searchConfiglogoutBtn);

    }

    private void setupListener() {
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchConfigurationPage.this, ProfilePage.class);
                startActivity(intent);
            }
        });
        startQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchConfigurationPage.this, SearchPage.class);
                startActivity(intent);
            }
        });

    }

    private void setupLogOut() {
        loAuth = FirebaseAuth.getInstance();
        loUser = loAuth.getCurrentUser();
    }

    private void setupLogOutListener() {
        userLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(SearchConfigurationPage.this, LandingPage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Log out successful", Toast.LENGTH_LONG).show();
            }
        });
    }


}