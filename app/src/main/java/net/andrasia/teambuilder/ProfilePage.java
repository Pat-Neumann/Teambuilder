package net.andrasia.teambuilder;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfilePage extends AppCompatActivity {


    Button profilePageSettingsButton;

    private FirebaseDatabase database;
    private DatabaseReference reference;
    FirebaseAuth usAuth;
    FirebaseUser currentUser;
    String userID;

    TextView gamertag;
    TextView language;
    TextView game;

    ImageView gameLogo;

    String gameL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        setupFirebase();
        setupViews();
        setTextViews();
        setupListener();
        setupProfileLogo();

    }


    private void setTextViews() {
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                gamertag.setText(dataSnapshot.child(userID).child("Game with Gamertag").child("Gamertag").getValue().toString());
                language.setText(dataSnapshot.child(userID).child("Language").getValue().toString());
                game.setText(dataSnapshot.child(userID).child("Game with Gamertag").child("Game").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProfilePage.this, SearchConfigurationPage.class);
        startActivity(intent);
    }

    private void setupFirebase() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("User");
        usAuth = FirebaseAuth.getInstance();
        currentUser = usAuth.getCurrentUser();
        userID = currentUser.getUid();
    }

    private void setupViews() {
        profilePageSettingsButton = findViewById(R.id.pPageSettingsButton);
        gamertag = findViewById(R.id.pPageGamertagsData);
        language = findViewById(R.id.pPageLanguageData);
        game = findViewById(R.id.pPageGamesData);
        gameLogo = findViewById(R.id.gameLogo);

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

    private void setupProfileLogo() {
            gameL = game.getText().toString();
            if (gameL == "League of Legends") {
                gameLogo.setImageResource(R.drawable.game_lol_logo);
            }
            if (gameL == "Overwatch") {
                gameLogo.setImageResource(R.drawable.game_overwatch_logo);
            }
            if (gameL == "Overwatch") {
                gameLogo.setImageResource(R.drawable.game_overwatch_logo);
            }
    }

}