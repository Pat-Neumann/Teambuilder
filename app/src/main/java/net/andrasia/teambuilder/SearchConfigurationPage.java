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

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SearchConfigurationPage extends AppCompatActivity {


    Button userLogout;
    Button searchTeamBtn;
    EditText gamertag;

    String selectedLanguage;
    String selectedGame;

    Spinner languageSpinner;
    Spinner gamesSpinner;


    FirebaseDatabase database;
    DatabaseReference reference;

    UserSettings user;

    private FirebaseAuth usAuth;
    private FirebaseUser currentUser;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_configuration_page);

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

    }

    private void setupViews() {
        setupSpinner();
        setupButtonsAndEditText();
    }


    private void setupButtonsAndEditText() {

        userLogout = findViewById(R.id.searchConfiglogoutBtn);
        searchTeamBtn = findViewById(R.id.searchButton);
        gamertag = findViewById(R.id.editProfilePageGamesEditText);
    }

    private void setupListener() {
        userLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(SearchConfigurationPage.this, LandingPage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Log out successful", Toast.LENGTH_LONG).show();
            }
        });
        searchTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (languageSpinner.getSelectedItem().toString().equals("") || gamesSpinner.getSelectedItem().toString().equals("") || gamertag.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please add gamertag!", Toast.LENGTH_LONG).show();
                } else {
                    setDataInDataBase();
                    Intent intent = new Intent(SearchConfigurationPage.this, SearchPage.class);
                    startActivity(intent);
                }
            }
        });


    }


    public void setDataInDataBase() {
        getUserValues();
        userID = currentUser.getUid();

        reference.child(userID).child("Language").setValue(user.getLanguages());
        reference.child(userID).child("Game with Gamertag").child("Game").setValue(user.getGames());
        reference.child(userID).child("Game with Gamertag").child("Gamertag").setValue(user.getGamerTag());

    }

    private void setupSpinner() {
        languageSpinner = findViewById(R.id.searchConfigLangSpinner);
        ArrayAdapter<CharSequence> languageSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_language_options, android.R.layout.simple_spinner_item);
        languageSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(languageSpinnerAdapter);
        languageSpinner.setOnItemSelectedListener(new LanguageSpinnerClass());

        gamesSpinner = findViewById(R.id.searchConfigGameSpinner);
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

    class GamesSpinnerClass implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
            selectedGame = parent.getItemAtPosition(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    private void getUserValues() {
        user.setGamerTag(gamertag.getText().toString());
        user.setLanguages(selectedLanguage);
        user.setGames(selectedGame);
    }

}