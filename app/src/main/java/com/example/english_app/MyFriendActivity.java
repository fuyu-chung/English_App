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
import java.util.List;
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

//        if (savedInstanceState == null) {
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            FollowerFragment fragment = new FollowerFragment();
//            transaction.replace(R.id.rcv_follower, fragment);
//            transaction.commit();
//        }

        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        int id = sharedPreferences.getInt("user_id", 0);
        TextInputEditText friend_ID = findViewById(R.id.friend_ID);

        ImageButton searchBtn = findViewById(R.id.search_id_btn);
        searchBtn.setOnClickListener(v -> {
            ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
            executor.execute(() -> {
                try {
                    String IDText = Objects.requireNonNull((friend_ID).getText()).toString();
                    int IDValue = Integer.parseInt(IDText);
                    String idPattern = "[0-9]{5}";
                    boolean isCorrect = true;

                    if (!IDText.matches(idPattern)) {
                        runOnUiThread(() -> (friend_ID).setError("ID格式錯誤"));
                        runOnUiThread(() -> (friend_ID).setText(""));
                        isCorrect = false;
                    }

                    if (id == IDValue) {
                        runOnUiThread(() -> (friend_ID).setError("請勿輸入自己的ID"));
                        runOnUiThread(() -> (friend_ID).setText(""));
                        isCorrect = false;
                    }

                    if (!isCorrect) {
                        return;
                    }

                    String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                    Connection connection = DriverManager.getConnection(s1); //建立連線
                    String query = "select * from friends where user_id = ? AND friend_id = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, id);
                    statement.setInt(2, IDValue);
                    ResultSet resultSet = statement.executeQuery(); // 把結果存在resultSet
                    if (!resultSet.next()) {
                        int resultSet2;
                        query = "select user_name from account where user_id = ?";
                        statement = connection.prepareStatement(query);
                        statement.setInt(1, IDValue);
                        resultSet = statement.executeQuery();
                        if (resultSet.next()) {
//                            sharedPreferences.edit().putInt("friend_id", IDValue).apply();
                            sharedPreferences.edit().putString("friend_name", resultSet.getString(1)).apply();
//                            User u = new User(IDValue, resultSet.getString(1));
                            query = "insert into friends values (?, ?);";
                            statement = connection.prepareStatement((query));
                            statement.setInt(1, id);
                            statement.setInt(2, IDValue);
                            resultSet2 = statement.executeUpdate();
                            if (resultSet2 != 0) {
//                                String friend_name = u.getUserName();
                                String friend_name = sharedPreferences.getString("friend_name", "");
                                Looper.prepare();
                                Toast.makeText(this, "開始追蹤 " + friend_name, Toast.LENGTH_LONG).show();
                                Looper.loop();
                                (friend_ID).setText("");
                            }
                        } else {
                            runOnUiThread(() -> (friend_ID).setError("請輸入正確的ID"));
                            runOnUiThread(() -> (friend_ID).setText(""));
                        }
                    } else {
                        runOnUiThread(() -> (friend_ID).setError("好友已存在"));
                        runOnUiThread(() -> (friend_ID).setText(""));
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        });
    }

    @Override
    public void onStart() {
        super.onStart();
//        getlist();
    }
}