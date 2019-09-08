package net.andrasia.teambuilder;

import android.app.Application;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/* This Class checks if a user is logged in, if not he will be directed to the landing page to register or log in */

public class CheckAuthentication extends Application {

    private FirebaseAuth preRunAuth;
    private FirebaseUser preRunUser;


    @Override
    public void onCreate() {
        super.onCreate();

        getAuthenticationInstances();
        checkCurrentSession();
    }


    private void getAuthenticationInstances() {
        preRunAuth = FirebaseAuth.getInstance();
        preRunUser = preRunAuth.getCurrentUser();
    }

    private void checkCurrentSession() {
        if(preRunUser != null){
            Intent intent=new Intent(CheckAuthentication.this,SearchConfigurationPage.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
