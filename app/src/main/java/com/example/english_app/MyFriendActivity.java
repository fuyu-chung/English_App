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
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
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
    int idValue, IDValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friend);
        TabLayout friendTabLayout = findViewById(R.id.friend_tablayout);
        ViewPager friendViewpager = findViewById(R.id.friend_viewpager);
        AppBarLayout mAppBarLayout = findViewById(R.id.friend_collapsing_appbar);
        CollapsingToolbarLayout mToolBar = findViewById(R.id.friend_collapsing_toolbar);

        friendTabLayout.setupWithViewPager(friendViewpager);
        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new FollowerFragment(), "粉絲");
        vpAdapter.addFragment(new FollowingFragment(), "追蹤中");
        friendViewpager.setAdapter(vpAdapter);

        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        int id = sharedPreferences.getInt("user_id", 0);
        TextInputEditText friend_ID = findViewById(R.id.friend_ID);
        String IDText = Objects.requireNonNull((friend_ID).getText()).toString();
        try {
            IDValue = Integer.parseInt(IDText);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        ImageButton searchBtn = findViewById(R.id.search_id_btn);
        searchBtn.setOnClickListener(v -> {
            ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
            executor.execute(() -> {
                try {
                    System.out.println("我的" + id);
                    System.out.println("好友的" + IDText);
                    System.out.println(IDValue);
                    String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                    Connection connection = DriverManager.getConnection(s1); //建立連線
                    String query = "select user_name from account where user_id = ? AND user_id <> ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, IDValue);
                    statement.setInt(2, id);
                    ResultSet resultSet = statement.executeQuery(); // 把結果存在resultSet
                    if (resultSet.next()){
                        sharedPreferences.edit().putInt("friend_id", IDValue).apply();
                        sharedPreferences.edit().putString("friend_name", resultSet.getString(1)).apply();
                        query = "insert into friends values (?, ?);";
                        statement = connection.prepareStatement((query));
                        statement.setInt(1,id);
                        statement.setInt(2, IDValue);
                        boolean resultSet2 = statement.execute();
                            if (resultSet2){
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
                            runOnUiThread(() -> (friend_ID).setError("欄位不可為空白"));
                            isCorrect = false;
                        }
//                        if (!IDText.matches(idPattern)) {
//                            runOnUiThread(() -> (EditTextID).setError("ID格式錯誤"));
//                            runOnUiThread(() -> (EditTextID).setText(""));
//                            isCorrect = false;
//                        }
                        else{
                            runOnUiThread(() -> (friend_ID).setError("請輸入正確的ID"));
                            runOnUiThread(() -> (friend_ID).setText(""));
                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        });
    }




}