package com.example.english_app.user_basic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.english_app.MainPageActivity;
import com.example.english_app.R;
import com.example.english_app.TestActivity;
import com.example.english_app.user_dorm.collections.MyCollectionMainActivity;
import com.google.android.material.button.MaterialButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {
    private EditText user_phone, user_password;
    private CheckBox showCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_phone = findViewById(R.id.user_phone);
        user_password = findViewById(R.id.user_password);
        MaterialButton loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(v -> {
            ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
            executor.execute(() -> {
                try { //試跑try有問題就跑catch
                    String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                    Connection connection = DriverManager.getConnection(s1); //建立連線
                    String query = "select * from account where user_phone = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    String phoneText = (user_phone).getText().toString();
                    statement.setString(1, phoneText); // 把?替換成phoneText，前面的數字是代表第幾褪號
                    ResultSet resultSet = statement.executeQuery(); // 把結果存在resultSet
                    if (resultSet.next()) {
                        String salt1 = resultSet.getString(6);
                        String salt2 = resultSet.getString(7);
                        String passwordText = (user_password).getText().toString();
                        passwordText = Encryption.sha1(salt1 + Encryption.md5(salt2 + passwordText));
                        query = "select * from account where user_phone = ? and user_password = ?";
                        statement = connection.prepareStatement(query);
                        statement.setString(1, phoneText);
                        statement.setString(2, passwordText);
                        resultSet = statement.executeQuery();
                        if (resultSet.next()) {
                            SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
                            sharedPreferences.edit().putInt("user_id", resultSet.getInt(1)).apply();
                            sharedPreferences.edit().putString("user_name", resultSet.getString(2)).apply();
                            sharedPreferences.edit().putString("user_phone", resultSet.getString(3)).apply();
                            sharedPreferences.edit().putString("user_birthday", resultSet.getString(4)).apply();
                            sharedPreferences.edit().putString("user_password", resultSet.getString(5)).apply();
                            sharedPreferences.edit().putString("user_salt1", resultSet.getString(6)).apply();
                            sharedPreferences.edit().putString("user_salt2", resultSet.getString(7)).apply();

                            Intent intent = new Intent(this, MainPageActivity.class);
                            startActivity(intent);
                            Looper.prepare();
                            Toast.makeText(this, "登入成功", Toast.LENGTH_LONG).show();
                            Looper.loop();

                        } else {
                            runOnUiThread(() -> (user_password).setError("密碼錯誤"));
//                            Looper.prepare();
//                            Toast.makeText(this, "使用者密碼錯誤！", Toast.LENGTH_LONG).show();
//                            Looper.loop();
                        }
                    } else {
                        runOnUiThread(() -> (user_phone).setError("使用者不存在"));
//                        Looper.prepare();
//                        Toast.makeText(this, "使用者不存在", Toast.LENGTH_SHORT).show();
//                        Toast.makeText(this, "請重新輸入或前往註冊", Toast.LENGTH_LONG).show();
//                        Looper.loop();
                    }
                    executor.shutdown();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });
        });

        //跳轉到註冊頁面
        MaterialButton registerBtn = findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(v -> {
            ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
            executor.execute(() -> {
                Intent intent = new Intent(this, MyCollectionMainActivity.class);
                startActivity(intent);
                executor.shutdown();
            });
        });

        MaterialButton forgetBtn = findViewById(R.id.forgetPassword);
        forgetBtn.setOnClickListener(v -> {
            ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
            executor.execute(() -> {
                Intent intent = new Intent(this, ForgetPasswordActivity.class); //
                startActivity(intent);
                executor.shutdown();
            });
        });

        showCb = findViewById(R.id.show_password);
        showCb.setOnClickListener(
                view -> {
                    if(showCb.isChecked()){
                        (user_password).setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                    else{
                        (user_password).setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
        );
    }
}