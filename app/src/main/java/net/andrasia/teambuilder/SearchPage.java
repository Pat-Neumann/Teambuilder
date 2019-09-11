package net.andrasia.teambuilder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SearchPage extends AppCompatActivity {

    Button leaveQueueBtn;
    FirebaseDatabase database;
    DatabaseReference reference;

    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);
        setupViews();
        setupListener();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("User");

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        /* Library used for the Loading Animation GIF, sjudd, 2017, https://github.com/bumptech/glide, accessed 05.09.2019 */
        ImageView loadingAnimation = findViewById(R.id.searchAnim);
        String gifURL = "https://i.imgur.com/3d2F1Ql.gif";
        Glide.with(this).load(gifURL).into(loadingAnimation);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SearchPage.this, SearchConfigurationPage.class);
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