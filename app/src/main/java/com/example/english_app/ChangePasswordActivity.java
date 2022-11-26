package com.example.english_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChangePasswordActivity extends AppCompatActivity {
    private TextInputEditText user_old_password, user_password, user_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        user_old_password = findViewById(R.id.user_old_password);
        user_password = findViewById(R.id.user_password);
        user_check = findViewById(R.id.user_check);

        MaterialButton submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(v -> {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                try {
                    boolean isCorrect = true;
                    String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                    Connection connection = DriverManager.getConnection(s1); //建立連線
                    String passwordPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
                    SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
                    String password = sharedPreferences.getString("user_password", " ");
                    String phone = sharedPreferences.getString("user_phone", " ");
                    String old_salt1 = sharedPreferences.getString("user_salt1", "");
                    String old_salt2 = sharedPreferences.getString("user_salt2", "");
                    String oldPasswordText = Objects.requireNonNull((user_old_password).getText()).toString();
                    oldPasswordText = Encryption.sha1(old_salt1 + Encryption.md5(old_salt2 + oldPasswordText));

                    assert oldPasswordText != null;
                    if (oldPasswordText.isEmpty()) {
                        runOnUiThread(() -> (user_old_password).setError("欄位不可為空白"));
                        isCorrect = false;
                    }
                    if (!oldPasswordText.matches(passwordPattern)) {
                        runOnUiThread(() -> (user_old_password).setError("密碼格式錯誤"));
                        isCorrect = false;
                    }

                    if (!isCorrect) {
                        return;
                    }

                    if (password.equals(oldPasswordText)) {
                        String passwordText = Objects.requireNonNull((user_password).getText()).toString();
                        String checkText = Objects.requireNonNull((user_check).getText()).toString();
                        if (passwordText.isEmpty()) {
                            runOnUiThread(() -> (user_password).setError("欄位不可為空白"));
                            isCorrect = false;
                        }
                        if (!passwordText.matches(passwordPattern)) {
                            runOnUiThread(() -> (user_password).setError("密碼格式錯誤"));
                            isCorrect = false;
                        }

                        if (checkText.isEmpty()) {
                            runOnUiThread(() -> (user_check).setError("欄位不可為空白"));
                            isCorrect = false;
                        }
                        if (!checkText.matches(passwordPattern)) {
                            runOnUiThread(() -> (user_check).setError("密碼格式錯誤"));
                            isCorrect = false;
                        }

                        if (!passwordText.equals(checkText)) {
                            runOnUiThread(() -> (user_check).setError("密碼輸入不相同"));
                            isCorrect = false;
                        }

                        if (!isCorrect) {
                            return;
                        }

                        String salt1 = Encryption.generatedSalt();
                        String salt2 = Encryption.generatedSalt();
                        passwordText = Encryption.sha1(salt1 + Encryption.md5(salt2 + passwordText));
                        String query = "UPDATE account SET user_password = ?, user_salt1 = ?, user_salt2 = ? where user_phone = ?";
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setString(1, passwordText);
                        statement.setString(2, salt1);
                        statement.setString(3, salt2);
                        statement.setString(4, phone);
                        boolean resultSet = statement.execute();

                        if (resultSet) {
                            sharedPreferences.edit().putString("user_password", passwordText).apply();
                            sharedPreferences.edit().putString("user_salt1", salt1).apply();
                            sharedPreferences.edit().putString("user_salt2", salt2).apply();
                        }
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });
        });


//        MaterialButton backBtn = findViewById(R.id.back);
//        backBtn.setOnClickListener(v -> {
//            ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
//            executor.execute(() -> {
//                Intent intent = new Intent(this, LoginActivity.class);
//                startActivity(intent);
//            });
//        });
    }


}