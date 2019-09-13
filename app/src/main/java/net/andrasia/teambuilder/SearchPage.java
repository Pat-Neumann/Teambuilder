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
    int playerCount;

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
        reference = database.getReference("User");
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
    }

    private void setupQueueSystem() {
        getCurrentUserPreferences();
        getAllUserPreferences();
        readThroughData();
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

    private static class UserDB {
        public String game, language, gamertag;

        public UserDB(String game, String language, String gamertag) {
            this.game = game;
            this.language = language;
            this.gamertag = gamertag;
        }

        public String getDBgame() {
            return game;
        }

        public String getDBLanguage() {
            return language;
        }

        public String getDBGamertag() {
            return gamertag;
        }
    }


    private void getAllUserPreferences() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                Toast.makeText(SearchPage.this, "Currently in queue :" + dataSnapshot.getChildrenCount(), Toast.LENGTH_SHORT).show();
                while (items.hasNext()) {
                        DataSnapshot item = items.next();
                        String game, language, gamertag;
                        game = item.child("Language").getValue().toString();
                        language = item.child("Game").getValue().toString();
                        gamertag = item.child("Gamertag").getValue().toString();
                        UserDB entry = new UserDB(game, language, gamertag);
                        entries.add(entry);
                }
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
        if (userPrefGame.equals("League of Legends")) {
            playerCount = 1;
            Log.d("PLSWORK",String.valueOf(entries.size()));
            int entriesLength = entries.size();
           /* while (playerCount < 2) {
                for (int i = 0; i < entriesLength; i++) {
                    UserDB checkUser;
                    checkUser = entries.get(i);
                    if (checkUser.getDBLanguage().equals(userPreferences.getLanguages())) {
                        team.add(checkUser.getDBgame());
                        playerCount++;
                        Log.d("MYBAD", checkUser.getDBgame());
                    }
                }
            }*/
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
                removeUserFromDatabase();
                Intent intent = new Intent(SearchPage.this, SearchConfigurationPage.class);
                finish();
                startActivity(intent);
            }
        });
    }

    private void removeUserFromDatabase() {
        reference.child(user.getUid()).removeValue();
    }


}