package net.andrasia.teambuilder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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


public class EditProfilePage extends AppCompatActivity {

    Button saveLanguageBtn;
    Button saveGameBtn;
    EditText gamertag;

    String selectedLanguage;
    String selectedGame;

    FirebaseDatabase database;
    DatabaseReference reference;

    UserSettings user;

    private FirebaseAuth usAuth;
    private FirebaseUser currentUser;
    String userEmail;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile_page);

        setupViews();
        setupListener();
        setupFirebase();
    }



    private void setupFirebase() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("User");
        user = new UserSettings();
        usAuth = FirebaseAuth.getInstance();
        currentUser = usAuth.getCurrentUser();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EditProfilePage.this, ProfilePage.class);
        startActivity(intent);
    }

    private void setupViews() {
        setupSpinner();
        setupButtonsAndEditText();
    }


    private void setupButtonsAndEditText() {
        saveLanguageBtn = findViewById(R.id.editPPageLanguageSaveBtn);
        saveGameBtn = findViewById(R.id.editPPageGamesSaveBtn);
        gamertag = findViewById(R.id.editProfilePageGamesEditText);
    }

    private void setupListener() {
        saveLanguageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLanguagesInDataBase();
            }
        });
        saveGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGamesInDataBase();
            }
        });


    }

    private void setupSpinner() {
        Spinner languageSpinner = findViewById(R.id.editProfilePageLangaugeSpinner);
        ArrayAdapter<CharSequence> languageSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_language_options, android.R.layout.simple_spinner_item);
        languageSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(languageSpinnerAdapter);
        languageSpinner.setOnItemSelectedListener(new LanguageSpinnerClass());

        Spinner gamesSpinner = findViewById(R.id.editProfilePageGamesSpinner);
        ArrayAdapter<CharSequence> gamesSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_games_options, android.R.layout.simple_spinner_item);
        gamesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gamesSpinner.setAdapter(gamesSpinnerAdapter);
        gamesSpinner.setOnItemSelectedListener(new GamesSpinnerClass());
    }


    /* Inner classes to give the two spinner different actions with the OnItemSelectedListener */
    class LanguageSpinnerClass implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
            selectedLanguage = parent.getItemAtPosition(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    class GamesSpinnerClass implements AdapterView.OnItemSelectedListener
    {
        @Override
        public void onItemSelected(AdapterView<?>parent, View v, int position, long id)
        {
            selectedGame = parent.getItemAtPosition(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }



    public void setLanguagesInDataBase() {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getUserValues();
                userID = currentUser.getUid();
                reference.child(userID).child("Language").setValue(user.getLanguages());

                Toast.makeText(EditProfilePage.this, "Language has been added to your Profile", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void setGamesInDataBase() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getUserValues();
                userID = currentUser.getUid();
                reference.child(userID).child("Game with Gamertag").child("Game").setValue(user.getGames());
                reference.child(userID).child("Game with Gamertag").child("Gamertag").setValue(user.getGamerTag());

                Toast.makeText(EditProfilePage.this, "Game and Gamertag has been added to your Profile", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getUserValues(){
        user.setGamerTag(gamertag.getText().toString());
        user.setLanguages(selectedLanguage);
        user.setGames(selectedGame);
    }

    static String encodeUserEmail(String userEmail){
        return userEmail.replace(".", ",");
    }

}