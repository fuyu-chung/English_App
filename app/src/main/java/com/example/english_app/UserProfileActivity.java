package com.example.english_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class UserProfileActivity extends AppCompatActivity {

    //show user's info
    TextView userName, userID, userPhone, userBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        /*------------------------------- userINFOs hooks -------------------------------*/
        userName = findViewById(R.id.user_name);
        userID = findViewById(R.id.user_id);
        userPhone = findViewById(R.id.user_phone);
        userBirthday = findViewById(R.id.user_birthday);

        //call readDataFromSharedPreferences function
        readDataFromSharedPreferences();
    }

    //get userINFOs from loginActivity byP
    private void readDataFromSharedPreferences() {

        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        String name = sharedPreferences.getString("user_name","");
        userName.setText(name);

        String id = sharedPreferences.getString("user_id","");
        userID.setText(id);

        String phone = sharedPreferences.getString("user_phone","");
        userPhone.setText(phone);

        String birthday = sharedPreferences.getString("user_birthday","");
        userBirthday.setText(birthday);

    }
}