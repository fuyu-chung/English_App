package com.example.english_app.user_dorm;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.english_app.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserProfileActivity extends AppCompatActivity {

    //show user's info
    TextView userName, userID, userPhone, userBirthday, user_password, user_friend, user_achv;
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
        user_password = findViewById(R.id.user_password);
        user_friend = findViewById(R.id.user_friend);
        user_achv = findViewById(R.id.user_achv);

        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        int id = sharedPreferences.getInt("user_id", 0);
        String ids = Integer.toString(id);
        runOnUiThread(() -> (userID).setText(ids));

        int achv = sharedPreferences.getInt("total", 0);
        int k = 0;
        int level;
        int balance;
        while (achv > 250 * (k * k + k)) {
            k ++;
        }
        level = k;
        balance = (250 * (k * k + k)) - achv;
        String levels = "level " + level;
        String balances = (balance) + " / " + (500 * k);
        runOnUiThread(() -> (user_achv).setText(levels));

        ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
        executor.execute(() -> {
            try {//試跑try有問題就跑catch
                String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                Connection connection = DriverManager.getConnection(s1); //建立連線
                String query1 = "select count(*) from friends where user_id = ?";
                String query2 = "select count(*) from friends where friend_id = ?";
                PreparedStatement statement1 = connection.prepareStatement(query1);
                PreparedStatement statement2 = connection.prepareStatement(query2);
                statement1.setInt(1, id);
                ResultSet resultSet1 = statement1.executeQuery();

                int following, follower;
                String followings, followers;

                if (resultSet1.next()){
                    following = resultSet1.getInt(1);
                }
                else {
                    following = 0;
                }
                followings = Integer.toString(following);

                statement2.setInt(1, id);
                ResultSet resultSet2 = statement2.executeQuery(); // 把結果存在resultSet
                if (resultSet2.next()){
                    follower = resultSet2.getInt(1);
                }
                else {
                    follower = 0;
                }
                followers = Integer.toString(follower);

                String friends = followers + " / " + followings;
                runOnUiThread(() -> (user_friend).setText(friends));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        int imageNum = sharedPreferences.getInt("image", 0);
        if (imageNum == 1) {
            image.setImageResource(R.drawable.pic_penguin);
        }
        if (imageNum == 2) {
            image.setImageResource(R.drawable.pic_dino);
        }
        if (imageNum == 3) {
            image.setImageResource(R.drawable.pic_duck);
        }
        if (imageNum == 4) {
            image.setImageResource(R.drawable.pic_fox);
        }
        if (imageNum == 5) {
            image.setImageResource(R.drawable.pic_hedge);
        }

        String name = sharedPreferences.getString("user_name", "");
        runOnUiThread(() -> (userName).setText(name));

        String phone = sharedPreferences.getString("user_phone", "");
        runOnUiThread(() -> (userPhone).setText(phone));

        String birthday = sharedPreferences.getString("user_birthday", "");
        runOnUiThread(() -> (userBirthday).setText(birthday));

        int password_length = sharedPreferences.getInt("password_length", 0);
        String password = "";
        for (int i = 0; i < password_length; i++) {
            password += '*';
        }
        String finalPassword = password;
        runOnUiThread(() -> (user_password).setText(finalPassword));

    }
}