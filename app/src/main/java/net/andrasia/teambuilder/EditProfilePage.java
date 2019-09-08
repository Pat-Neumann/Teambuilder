package net.andrasia.teambuilder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EditProfilePage extends AppCompatActivity implements OnItemSelectedListener {

    Button saveLanguageBtn;
    Button saveGameBtn;
    EditText gamertag;

    String selectedLanguage;
    String selectedGame;

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
        languageSpinner.setOnItemSelectedListener(this);

        Spinner gamesSpinner = findViewById(R.id.editProfilePageGamesSpinner);
        ArrayAdapter<CharSequence> gamesSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_games_options, android.R.layout.simple_spinner_item);
        gamesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gamesSpinner.setAdapter(gamesSpinnerAdapter);
        gamesSpinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        selectedLanguage = parent.getItemAtPosition(position).toString();
        selectedGame = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }

    public void setLanguagesInDataBase() {

    }

    public void setGamesInDataBase() {

    }

}