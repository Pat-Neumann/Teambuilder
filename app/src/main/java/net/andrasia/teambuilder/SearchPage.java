package net.andrasia.teambuilder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class SearchPage extends AppCompatActivity {

    Button leaveQueueBtn;
    FirebaseDatabase database;
    DatabaseReference reference;

    FirebaseAuth auth;
    FirebaseUser user;

    UserSettings userPreferences;
    String userPrefGame;
    String userPrefLang;

    ArrayList<UserDB> entries = new ArrayList<>();
    ArrayList<String> team = new ArrayList<>();

    ValueEventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.search_page);
        setupFirebase();
        setupViews();
        setupListener();
        setupQueueSystem();

        /* Library used for the Loading Animation GIF, sjudd, 2017, https://github.com/bumptech/glide, accessed 05.09.2019 */
        ImageView loadingAnimation = findViewById(R.id.searchAnim);
        String gifURL = "https://i.imgur.com/3d2F1Ql.gif";
        Glide.with(this).load(gifURL).into(loadingAnimation);

    }


    private void setupFirebase() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
    }

    private void setupQueueSystem() {

        getCurrentUserPreferences();
        getAllUserPreferences();
    }

    private void getCurrentUserPreferences() {
        userPreferences = new UserSettings();
        getUserValues();
        userPrefGame = userPreferences.getGames();
        userPrefLang = userPreferences.getLanguages();

    }

    private void getUserValues() {
        userPreferences.setGamerTag(SearchConfigurationPage.getCurrentGame());
        userPreferences.setLanguages(SearchConfigurationPage.getCurrentlanguage());
        userPreferences.setGames(SearchConfigurationPage.getCurrentGame());
    }


    private void getAllUserPreferences() {
        listener = reference.child("Users").addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              //  Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                Toast.makeText(SearchPage.this, "Currently in queue :" + dataSnapshot.getChildrenCount(), Toast.LENGTH_SHORT).show();
                UserDB entry;
                entries.clear();
                for (DataSnapshot item : dataSnapshot.getChildren()) {

                    String game, language, gamertag;
                    game = (String) item.child("Language").getValue();
                    language = (String) item.child("Game").getValue();
                    gamertag = (String) item.child("Gamertag").getValue();
                    entry = new UserDB(game, language, gamertag);
                    entries.add(entry);

                }
                readThroughData();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void readThroughData() {
        checkForLeague();
        checkForOverwatch();
        checkForApexLegends();
        checkForCSGO();

    }


    private void checkForLeague() {
        Log.d("MYBAD", String.valueOf(entries.size()));
        if (userPrefGame.equals("League of Legends")) {
            int players = 1;
            int entriesLength = entries.size();
        /*    for (int i = 0; i < entriesLength; i++) {
               // Log.d("MYBAD", String.valueOf(entries.get(0).getDBGamertag()));
                UserDB checkUser;
                checkUser = entries.get(i);
                if (checkUser.getDBLanguage().equals(userPreferences.getLanguages()) && players < 2) {
                    team.add(checkUser.getDBgame());

                    players++;

                }
            } */
        }
    }

    private void checkForOverwatch() {
    }

    private void checkForApexLegends() {
    }

    private void checkForCSGO() {
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SearchPage.this, SearchConfigurationPage.class);
        removeUserFromDatabase();

        finish();
        startActivity(intent);
    }

    private void setupViews() {
        leaveQueueBtn = findViewById(R.id.searchPageCancelBtn);

    }

    private void setupListener() {
        leaveQueueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("Users").removeEventListener(listener);
                removeUserFromDatabase();
                Intent intent = new Intent(SearchPage.this, SearchConfigurationPage.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void removeUserFromDatabase() {
        reference.child("Users").child(user.getUid()).removeValue();

    }


}