package net.andrasia.teambuilder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;

public class SearchPage extends AppCompatActivity {

    Button leaveQueueBtn;
    FirebaseDatabase database;
    DatabaseReference reference;

    FirebaseAuth auth;
    FirebaseUser user;

    User userPreferences;

    HashMap<String, User> waitingOthers = new HashMap<>();

    ChildEventListener listener;
    private Random rgen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rgen = new Random();

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
        userPreferences = (User) getIntent().getSerializableExtra("user");
        getAllUserPreferences();
    }

    private void toastUsersInQueue() {
        Toast.makeText(SearchPage.this, "Currently in queue :" + waitingOthers.size(), Toast.LENGTH_SHORT).show();
    }

    private void getAllUserPreferences() {
        listener = reference.child("Users").addChildEventListener( new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String prevChildKey) {
                User user = dataSnapshot.getValue(User.class);

                if(userPreferences.isCompatible(user)){
                    waitingOthers.put(dataSnapshot.getKey(), user);
                    Log.d("Queue", ((String) dataSnapshot.child("gamertag").getValue()) + " is now waiting");
                    toastUsersInQueue();

                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                User removed = waitingOthers.remove(dataSnapshot.getKey());
                //Log.d("Queue",  removed.getGamertag() + " is not waiting anymore");
                toastUsersInQueue();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private int getUsersLeftForFullGame() {
        int left = userPreferences.getGame().getRequiredPlayerCount() - (waitingOthers.size() + 1);
        return (left > 0) ? left : 0;
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
        reference.child("Teams").child("Team_" + user.getUid()).removeValue();
    }


}