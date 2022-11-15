package com.example.english_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {
    private EditText user_phone, user_password;
    private MaterialButton loginbtn, registerbtn;
//    private boolean hide =true;
//    private ImageView eye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_phone = findViewById(R.id.user_phone);
        user_password = findViewById(R.id.user_password);
//        eye = findViewById(R.id.eye);
//        eye.setImageResource(R.drawable.close);
        loginbtn = findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(v -> {
            ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
            executor.execute(() -> {
                try { //試跑try有問題就跑catch
                    String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                    Connection connection = DriverManager.getConnection(s1); //建立連線
                    String query = "select * from account where user_phone = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    
                    String phoneText = (user_phone).getText().toString();
                    statement.setString(1, phoneText); // 把?替換成phoneText，前面的數字是代表第幾褪號
                    ResultSet resultSet = statement.executeQuery(); // 把結果存在resultset
                    if (resultSet.next()) {
                        query = "select * from account where user_phone = ? and user_password = ?";
                        statement = connection.prepareStatement(query);
                        String passwordText = (user_password).getText().toString();
                        passwordText = sha1("kD0a1" + md5("xA4" + passwordText) + "f4A");
                        statement.setString(1, phoneText); // 把?替換成newphoneText，前面的數字是代表第幾褪號
                        statement.setString(2, passwordText);
                        ResultSet resultSet1 = statement.executeQuery();
                        if (resultSet1.next()) {
                            Intent mainIntent = new Intent(this, MainPageActivity.class);
                            startActivity(mainIntent);
                            Looper.prepare();
                            Toast.makeText(this, "登入成功", Toast.LENGTH_LONG).show();
                            Looper.loop();
                        }
                        else {
                            runOnUiThread(() -> (user_password).setError("密碼錯誤"));
//                            Looper.prepare();
//                            Toast.makeText(this, "使用者密碼錯誤！", Toast.LENGTH_LONG).show();
//                            Looper.loop();
                        }
                    }
                    else {
                        runOnUiThread(() -> (user_phone).setError("使用者不存在"));
//                        Looper.prepare();
//                        Toast.makeText(this, "使用者不存在", Toast.LENGTH_SHORT).show();
//                        Toast.makeText(this, "請重新輸入或前往註冊", Toast.LENGTH_LONG).show();
//                        Looper.loop();
                    }
                }
                catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });
        });

        //跳轉到註冊頁面
        registerbtn = findViewById(R.id.registerbtn);
        registerbtn.setOnClickListener(v -> {
            ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
            executor.execute(() -> {
                Intent registerIntent = new Intent(this, RegisterActivity.class);
                startActivity(registerIntent);
            });
        });
    }


    public static String sha1(String clearString) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(clearString.getBytes("UTF-8"));
            byte[] bytes = messageDigest.digest();
            StringBuilder buffer = new StringBuilder();
            for (byte b : bytes) {
                buffer.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return buffer.toString();
        }
        catch (Exception ignored) {
            ignored.printStackTrace();
            return null;
        }
    }

    public static String md5(String s) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}