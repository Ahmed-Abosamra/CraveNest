package com.example.cravenest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        auth = FirebaseAuth.getInstance();
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Button login = findViewById(R.id.loginbtn);
        Button signup = findViewById(R.id.Signup);

        login.setOnClickListener(view -> {
            String email = username.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            auth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                            SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("username", email);
                            editor.putString("email", email);
                            editor.apply();

                            startActivity(new Intent(MainActivity.this, Home.class));
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Login Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        signup.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, SignUP.class)));

        TextView forgotpass = findViewById(R.id.forgotpass);
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Forgot_password.class));
            }
        });

    }
}