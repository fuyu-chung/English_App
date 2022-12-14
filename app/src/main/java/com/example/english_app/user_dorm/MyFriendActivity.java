package com.example.english_app.user_dorm;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.english_app.R;
import com.example.english_app.VPAdapter;
import com.example.english_app.user_dorm.friend_fragments.FollowerFragment;
import com.example.english_app.user_dorm.friend_fragments.FollowingFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    private FloatingActionButton floatingActionButton;

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

        floatingActionButton = findViewById(R.id.fab_friendHint);
        floatingActionButton.setOnClickListener(v -> {
            showAlertDialog();
        });


        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        int id = sharedPreferences.getInt("user_id", 0);
        TextInputEditText friend_ID = findViewById(R.id.friend_ID);

        ImageButton searchBtn = findViewById(R.id.search_id_btn);
        searchBtn.setOnClickListener(v -> {
            ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
            executor.execute(() -> {
                try {
                    String IDText = Objects.requireNonNull((friend_ID).getText()).toString();
                    String idPattern = "[0-9]{5}";
                    boolean isCorrect = true;

//                    if(IDText.isEmpty()) {
//                        runOnUiThread(() -> (friend_ID).setError("欄位不可為空白"));
//                        isCorrect = false;
//                    }

                    if (!IDText.matches(idPattern)) {
                        runOnUiThread(() -> (friend_ID).setError("ID格式錯誤"));
                        runOnUiThread(() -> (friend_ID).setText(""));
                        isCorrect = false;
                    }

                    if (!isCorrect) {
                        return;
                    }

                    boolean isCorrect1 = true;

                    int IDValue = Integer.parseInt(IDText);
                    if (id == IDValue) {
                        runOnUiThread(() -> (friend_ID).setError("請勿輸入自己的ID"));
                        runOnUiThread(() -> (friend_ID).setText(""));
                        isCorrect1 = false;
                    }

                    if (!isCorrect1) {
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
                            int resultSet2 = statement.executeUpdate();
                            if (resultSet2 != 0) {
//                                String friend_name = u.getUserName();
                                String friend_name = sharedPreferences.getString("friend_name", "");
                                (friend_ID).setText("");
                                Looper.prepare();
                                Toast.makeText(this, "開始追蹤 " + friend_name, Toast.LENGTH_LONG).show();
                                Looper.loop();
                            }
                        } else {
                            runOnUiThread(() -> (friend_ID).setError("請輸入正確的ID"));
                            runOnUiThread(() -> (friend_ID).setText(""));
                        }
                    } else {
                        runOnUiThread(() -> (friend_ID).setError("好友已存在"));
                        runOnUiThread(() -> (friend_ID).setText(""));
                    }
                    executor.shutdown();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        });
    }

    private void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(this).inflate(
                R.layout.friend_hint_dialog, findViewById(R.id.layoutHintDialog)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.dialogTextTitle)).setText("注意事項!!!");
        ((TextView) view.findViewById(R.id.dailogText)).setText(String.format("*可去「個人資料」查看個人ID\n*加完好友後務必按返回鍵，再度進來此頁面，以更新粉絲/追蹤者\n*可輸入「89216」即可追蹤Principal帳號，測試功能喔！"));
        ((Button) view.findViewById(R.id.backComBtn)).setText("我了解了!");
        ((ImageView) view.findViewById(R.id.megaPhoneImg)).setImageResource(R.drawable.ic_megaphone);

        final AlertDialog alertDialog = builder.create();
        builder.setCancelable(false);
        //取消
        view.findViewById(R.id.backComBtn).setOnClickListener(v2 -> {
            alertDialog.cancel();
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }
}