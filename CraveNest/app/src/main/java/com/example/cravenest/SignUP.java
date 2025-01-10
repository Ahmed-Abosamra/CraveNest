package com.example.cravenest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import java.util.regex.Pattern;

public class SignUP extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private EditText emailField, passwordField, rePasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView back = findViewById(R.id.back);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(SignUP.this, MainActivity.class));
                    }
                });


        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        rePasswordField = findViewById(R.id.repassword);
        Button registerButton = findViewById(R.id.Registerbtn);

        registerButton.setOnClickListener(view -> {
            String email = emailField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();
            String rePassword = rePasswordField.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(rePassword)) {
                Toast.makeText(SignUP.this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(rePassword)) {
                Toast.makeText(SignUP.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            String userId = auth.getCurrentUser().getUid();
                            HashMap<String, String> userMap = new HashMap<>();
                            userMap.put("email", email);

                            databaseReference.child(userId).setValue(userMap)
                                    .addOnCompleteListener(dbTask -> {
                                        if (dbTask.isSuccessful()) {
                                            Toast.makeText(SignUP.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(SignUP.this, MainActivity.class));
                                            finish();
                                        } else {
                                            Toast.makeText(SignUP.this, "Database Error: " + dbTask.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            Toast.makeText(SignUP.this, "Authentication Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });


    }
}