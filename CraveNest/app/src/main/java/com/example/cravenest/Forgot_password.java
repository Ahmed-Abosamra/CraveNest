package com.example.cravenest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Forgot_password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView back;
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(Forgot_password.this,MainActivity.class);
                startActivity(intent);

            }
        });

        TextView emailField = findViewById(R.id.email);
        String emailPattern = "^[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+$";

        Button send  = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // what the send will do ?????

                if (emailField.getText().toString().isEmpty()){
                    Toast.makeText(Forgot_password.this, "Email is required", Toast.LENGTH_SHORT).show();
                }
                else if(!(emailField.getText().toString().matches(emailPattern))){
                    Toast.makeText(Forgot_password.this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Forgot_password.this, "The code was sent successfully", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }
}