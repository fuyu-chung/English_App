package com.example.english_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ForgetPasswordActivity extends AppCompatActivity {
    private TextInputEditText user_name, user_phone, user_password, user_check, user_verify;
    private MaterialButton submitBtn, sendBtn, verifyBtn;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        user_name = findViewById(R.id.user_name);
        user_phone = findViewById(R.id.user_phone);
        user_password = findViewById(R.id.user_password);
        user_check = findViewById(R.id.user_check);
        user_verify = findViewById(R.id.user_verify);

        sendBtn = findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(v1 -> {
            ExecutorService executor1 = Executors.newSingleThreadExecutor();
            executor1.execute(() -> {
                try { //試跑try有問題就跑catch
                    boolean isCorrect1 = true;
                    String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                    Connection connection = DriverManager.getConnection(s1); //建立連線
                    String phoneText = Objects.requireNonNull((user_phone).getText()).toString();
                    String phonePattern = "^09\\d{8}$";

                    if (phoneText.isEmpty()) {
                        runOnUiThread(() -> (user_phone).setError("欄位不可為空白"));
                        isCorrect1 = false;
                    }
                    if (!phoneText.matches(phonePattern)) {
                        runOnUiThread(() -> (user_phone).setError("電話格式錯誤"));
                        isCorrect1 = false;
                    }

                    if (!isCorrect1) {
                        return;
                    }

                    String query = "select * from account where user_phone = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, phoneText); // 把?替換成phoneText，前面的數字是代表第幾褪號
                    ResultSet resultSet = statement.executeQuery(); // 把結果存在resultSet
                    if (resultSet.next()) {
                        Random r = new Random();
                        int verification＿code = r.nextInt(90000) - 10000; // 10000~99999
                        String verifyText = Objects.requireNonNull((user_verify).getText()).toString();
                        Looper.prepare();
                        Toast.makeText(this, verification＿code, Toast.LENGTH_LONG).show();
                        Looper.loop();
                        verifyBtn = findViewById(R.id.verifyBtn);
                        verifyBtn.setOnClickListener(v2 -> {
                            ExecutorService executor2 = Executors.newSingleThreadExecutor();
                            executor2.execute(() -> {
                                try {
                                    if (!verifyText.equals(verification＿code)) {
                                        runOnUiThread(() -> (user_verify).setError("驗證碼錯誤，請重新輸入！"));
                                    } else {
                                        submitBtn = findViewById(R.id.submitBtn);
                                        submitBtn.setOnClickListener(v3 -> {
                                            ExecutorService executor3 = Executors.newSingleThreadExecutor(); // 建立新的thread
                                            executor3.execute(() -> {
                                                try {//試跑try有問題就跑catch
                                                    Boolean isCorrect3 = true;
                                                    String nameText = Objects.requireNonNull((user_name).getText()).toString();
                                                    String namePattern = "^(?=.*[a-z]).{6,20}$";

                                                    String passwordText = Objects.requireNonNull((user_password).getText()).toString();
                                                    String checkText = Objects.requireNonNull((user_check).getText()).toString();
                                                    String passwordPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$";

                                                    String phoneText3 = Objects.requireNonNull(user_phone).getText().toString();

                                                    if (nameText.isEmpty()) {
                                                        runOnUiThread(() -> (user_name).setError("欄位不可為空白"));
                                                        isCorrect3 = false;
                                                    }

                                                    if (!nameText.matches(namePattern)) {
                                                        runOnUiThread(() -> (user_name).setError("名字格式錯誤"));
                                                        isCorrect3 = false;
                                                    }

                                                    if (passwordText.isEmpty()) {
                                                        runOnUiThread(() -> (user_password).setError("欄位不可為空白"));
                                                        isCorrect3 = false;
                                                    }
                                                    if (!passwordText.matches(passwordPattern)) {
                                                        runOnUiThread(() -> (user_password).setError("密碼格式錯誤"));
                                                        isCorrect3 = false;
                                                    }

                                                    if (checkText.isEmpty()) {
                                                        runOnUiThread(() -> (user_check).setError("欄位不可為空白"));
                                                        isCorrect3 = false;
                                                    }
                                                    if (!checkText.matches(passwordPattern)) {
                                                        runOnUiThread(() -> (user_check).setError("密碼格式錯誤"));
                                                        isCorrect3 = false;
                                                    }

                                                    if (!passwordText.equals(checkText)) {
                                                        runOnUiThread(() -> (user_check).setError("密碼輸入不相同"));
                                                        isCorrect3 = false;
                                                    }

                                                    if (!isCorrect3) {
                                                        return;
                                                    }

                                                    String salt1 = Encryption.generatedSalt();
                                                    String salt2 = Encryption.generatedSalt();
                                                    passwordText = Encryption.sha1(salt1 + Encryption.md5(salt2 + passwordText));
                                                    String s3 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                                                    Connection connection3 = DriverManager.getConnection(s3); //建立連線
                                                    String query3 = "select * from account where user_phone = ? and user_name = ?";
                                                    PreparedStatement statement3 = connection3.prepareStatement(query3);
                                                    statement3.setString(1, phoneText3); // 把?替換成phoneText，前面的數字是代表第幾褪號
                                                    statement3.setString(2, nameText);
                                                    ResultSet resultSet3 = statement3.executeQuery(); // 把結果存在resultSet
                                                    if (resultSet3.next()) {
                                                        query3 = "UPDATE account SET user_password = ?, user_salt1 = ?, user_salt2 = ? where user_phone = ? and user_name = ?";
                                                        statement3 = connection3.prepareStatement(query3);
                                                        statement3.setString(1, passwordText); // 把?替換成phoneText，前面的數字是代表第幾褪號
                                                        statement3.setString(2, salt1);
                                                        statement3.setString(3, salt2);
                                                        statement3.setString(4, phoneText3);
                                                        statement3.setString(5, nameText);
                                                        resultSet3 = statement3.executeQuery();
                                                        if (resultSet3.next()) {
                                                            System.out.println("Successful");
                                                            Intent intent = new Intent(this, LoginActivity.class);
                                                            startActivity(intent);
                                                            Looper.prepare();
                                                            Toast.makeText(this, "密碼更改成功", Toast.LENGTH_LONG).show();
                                                            Looper.loop();
                                                        }
                                                    } else {
                                                        runOnUiThread(() -> (user_name).setError("使用者名字輸入錯誤"));
                                                    }
                                                } catch (SQLException e) {
                                                    System.out.println(e.getMessage());
                                                }
                                            });
                                        });
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            });
                        });
                    } else {
                        runOnUiThread(() -> (user_phone).setError("使用者不存在"));
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });
        });
    }
}