package net.andrasia.teambuilder;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterPage extends AppCompatActivity {

    Button registerBackBtn;
    Button register;
    EditText eMailEdit;
    EditText passwordEdit;
    EditText passwordConfirmEdit;
    private FirebaseAuth mAuth;
    private static final String TAG = "RegisterPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        setupViews();
        setupListener();
        mAuth = FirebaseAuth.getInstance();

    }

    private void setupViews() {
        registerBackBtn = findViewById(R.id.registerPageBackBt);
        register = findViewById(R.id.registerPageRegisterButton);
        eMailEdit = findViewById(R.id.registerPageEmailEdit);
        passwordEdit = findViewById(R.id.registerPagePasswordEdit);
        passwordConfirmEdit = findViewById(R.id.registerPageConfirmPWEdit);

    }

    private void setupListener() {
        registerBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterPage.this, LandingPage.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                registerNewUser();
            }
        });
    }

    private void registerNewUser() {
        String email, password;
        email = eMailEdit.getText().toString();
        if(passwordEdit.getText().toString().equals(passwordConfirmEdit.getText().toString())){
            password = passwordEdit.getText().toString();
        } else {
            password = "";
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(RegisterPage.this, LoginPage.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}