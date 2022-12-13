package com.example.english_app.user_dorm;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.english_app.R;

public class UserProfileActivity extends AppCompatActivity {

    //show user's info
    TextView userName, userID, userPhone, userBirthday;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        /*------------------------------- userINFOs hooks -------------------------------*/
        userName = findViewById(R.id.user_name);
        userID = findViewById(R.id.user_id);
        userPhone = findViewById(R.id.user_phone);
        userBirthday = findViewById(R.id.user_birthday);
        image = findViewById(R.id.user_photo);

        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        int imageNum = sharedPreferences.getInt("image", 0);
        if (imageNum == 1){
            image.setImageResource(R.drawable.pic_penguin);
        }
        if (imageNum == 2){
            image.setImageResource(R.drawable.pic_penguin);
        }
        if (imageNum == 3){
            image.setImageResource(R.drawable.pic_penguin);
        }
        if (imageNum == 4){
            image.setImageResource(R.drawable.pic_penguin);
        }
        if (imageNum == 5){
            image.setImageResource(R.drawable.pic_penguin);
        }

        String name = sharedPreferences.getString("user_name","");
        runOnUiThread(() -> (userName).setText(name));

        int id = sharedPreferences.getInt("user_id",0);
        String ids = Integer.toString(id);
        runOnUiThread(() -> (userID).setText(ids));

        String phone = sharedPreferences.getString("user_phone","");
        runOnUiThread(() -> (userPhone).setText(phone));

        String birthday = sharedPreferences.getString("user_birthday","");
        runOnUiThread(() -> (userBirthday).setText(birthday));
    }
}