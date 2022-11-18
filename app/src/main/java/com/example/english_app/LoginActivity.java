package com.example.english_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

public class LoginActivity extends AppCompatActivity {
    private EditText user_phone, user_password;
    private MaterialButton loginBtn, registerBtn, forgetBtn;
    private CheckBox showCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_phone = findViewById(R.id.user_phone);
        user_password = findViewById(R.id.user_password);
        loginBtn = findViewById(R.id.loginBtn);
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
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });
        });

        //跳轉到註冊頁面
        registerBtn = findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(v -> {
            ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
            executor.execute(() -> {
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
            });
        });

        forgetBtn = findViewById(R.id.forgetPassword);
        forgetBtn.setOnClickListener(v -> {
            ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
            executor.execute(() -> {
                Intent intent = new Intent(this, ForgetPasswordActivity.class); //
                startActivity(intent);
            });
        });

        showCb = findViewById(R.id.show_password);
        showCb.setOnClickListener(
                view -> {
                   EditText passwordShow = findViewById(R.id.user_password);
                    if(showCb.isChecked()){
                        passwordShow.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                    else{
                        passwordShow.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
        );
    }
}