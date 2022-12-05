package com.example.english_app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.english_app.friend_fragments.FollowerFragment;
import com.example.english_app.friend_fragments.FollowingFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyFriendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friend);
        TabLayout friendTabLayout = findViewById(R.id.friend_tablayout);
        ViewPager friendViewpager = findViewById(R.id.friend_viewpager);
        friendTabLayout.setupWithViewPager(friendViewpager);
        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new FollowerFragment(), "粉絲");
        vpAdapter.addFragment(new FollowingFragment(), "追蹤中");
        friendViewpager.setAdapter(vpAdapter);

        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        String id = sharedPreferences.getString("user_id", "");
        int idValue = Integer.parseInt(id);
        TextInputEditText EditTextID = findViewById(R.id.EditTextID);
        String IDText = Objects.requireNonNull(EditTextID.getText()).toString();
        int IDValue = Integer.parseInt(IDText);
        ImageButton searchBtn = findViewById(R.id.search_id_btn);
        searchBtn.setOnClickListener(v -> {
            ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
            executor.execute(() -> {
                try {
                    String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                    Connection connection = DriverManager.getConnection(s1); //建立連線
                    String query = "select * from account where user_id = ? AND user_id <> ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, IDValue);
                    statement.setInt(2, idValue);
                    ResultSet resultSet = statement.executeQuery(); // 把結果存在resultSet
                    if (resultSet.next()){
                        query = "insert into friends values (?, ?);";
                        statement = connection.prepareStatement((query));
                        statement.setInt(1,idValue);
                        statement.setInt(2, IDValue);
                        boolean resultSet2 = statement.execute();
                        if (resultSet2) {
                            query = "select user_name from account where user_id = ?";
                            statement = connection.prepareStatement(query);
                            statement.setInt(1, IDValue);
                            resultSet = statement.executeQuery();
                            sharedPreferences.edit().putInt("friend_id", IDValue).apply();
                            sharedPreferences.edit().putString("friend_name", resultSet.getString(1)).apply();

                            String friend_name = sharedPreferences.getString("friend_name", "");
                            Looper.prepare();
                            Toast.makeText(this, "開始追蹤 " + friend_name, Toast.LENGTH_LONG).show();
                            Looper.loop();
                        }
                    }

                    else{
                        String idPattern = "^\\d{5}$";
                        boolean isCorrect = true;

                        if (IDText.isEmpty()) {
                            runOnUiThread(() -> (EditTextID).setError("欄位不可為空白"));
                            isCorrect = false;
                        }
                        if (!IDText.matches(idPattern)) {
                            runOnUiThread(() -> (EditTextID).setError("ID格式錯誤"));
                            runOnUiThread(() -> (EditTextID).setText(""));
                            isCorrect = false;
                        }

                        if (!isCorrect) {
                            return;
                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        });
    }




}