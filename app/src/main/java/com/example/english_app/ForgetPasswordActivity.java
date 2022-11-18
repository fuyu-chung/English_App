package com.example.english_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.text.Editable;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
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
    private MaterialButton submitBtn, sendBtn, verifyBtn, backBtn;
    private boolean continues = false;
    private String str;

    @RequiresApi(api = Build.VERSION_CODES.N)
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
        sendBtn.setOnClickListener(v -> {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                try { //試跑try有問題就跑catch
                    boolean isCorrect = true;
                    String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                    Connection connection = DriverManager.getConnection(s1); //建立連線
                    String phoneText = Objects.requireNonNull((user_phone).getText()).toString();
                    String phonePattern = "^09\\d{8}$";

                    if (phoneText.isEmpty()) {
                        runOnUiThread(() -> (user_phone).setError("欄位不可為空白"));
                        isCorrect = false;
                    }
                    if (!phoneText.matches(phonePattern)) {
                        runOnUiThread(() -> (user_phone).setError("電話格式錯誤"));
                        isCorrect = false;
                    }

                    if (!isCorrect) {
                        return;
                    }

                    String query = "select * from account where user_phone = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, phoneText); // 把?替換成phoneText，前面的數字是代表第幾褪號
                    ResultSet resultSet = statement.executeQuery(); // 把結果存在resultSet
                    String verifyText = (user_verify).getText().toString();
                    String verify_code = generated_verify_code();
                    str = verify_code;
                    if (resultSet.next()) {
                        System.out.println(verify_code);
                        System.out.println(verifyText);
                        Looper.prepare();
                        Toast.makeText(this, verify_code, Toast.LENGTH_LONG).show();
                        Looper.loop();

                    } else {
                        runOnUiThread(() -> (user_phone).setError("使用者不存在"));
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });
        });

        verifyBtn = findViewById(R.id.verifyBtn);
        verifyBtn.setOnClickListener(v -> {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                String verifyText = (user_verify).getText().toString();
                try {
                    if (verifyText.equals(str)) {
                        System.out.println("code correct");
                        Looper.prepare();
                        Toast.makeText(this, "驗證成功", Toast.LENGTH_LONG).show();
                        Toast toast = Toast.makeText(this, "驗證成功", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        LinearLayout linearLayout = (LinearLayout) toast.getView();
                        TextView messageTextView = (TextView) linearLayout.getChildAt(0);
                        messageTextView.setTextSize(200);
                        toast.show();
                        Looper.loop();
                        continues = true;

                    } else {
                        runOnUiThread(() -> (user_verify).setError("驗證碼錯誤，請重新輸入！"));
                        System.out.println("code wrong");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
        });

        submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(v -> {
            ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
            executor.execute(() -> {
                try {//試跑try有問題就跑catch
                    Boolean isCorrect = true;
                    String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                    Connection connection = DriverManager.getConnection(s1); //建立連線

                    String phoneText = Objects.requireNonNull((user_phone).getText()).toString();
                    String phonePattern = "^09\\d{8}$";

                    String nameText = Objects.requireNonNull((user_name).getText()).toString();
                    String namePattern = "^(?=.*[a-z]).{6,20}$";

                    String passwordText = Objects.requireNonNull((user_password).getText()).toString();
                    String checkText = Objects.requireNonNull((user_check).getText()).toString();
                    String passwordPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$";

                    if (phoneText.isEmpty()) {
                        runOnUiThread(() -> (user_phone).setError("欄位不可為空白"));
                        isCorrect = false;
                    }
                    if (!phoneText.matches(phonePattern)) {
                        runOnUiThread(() -> (user_phone).setError("電話格式錯誤"));
                        isCorrect = false;
                    }
                    if (nameText.isEmpty()) {
                        runOnUiThread(() -> (user_name).setError("欄位不可為空白"));
                        isCorrect = false;
                    }

                    if (!nameText.matches(namePattern)) {
                        runOnUiThread(() -> (user_name).setError("名字格式錯誤"));
                        isCorrect = false;
                    }

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

                    String query = "select * from account where user_phone = ? and user_name = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, phoneText); // 把?替換成phoneText，前面的數字是代表第幾褪號
                    statement.setString(2, nameText);
                    ResultSet resultSet = statement.executeQuery(); // 把結果存在resultSet

                    String salt1 = Encryption.generatedSalt();
                    String salt2 = Encryption.generatedSalt();
                    passwordText = Encryption.sha1(salt1 + Encryption.md5(salt2 + passwordText));
                    if (resultSet.next()) {
                        query = "UPDATE account SET user_password = ?, user_salt1 = ?, user_salt2 = ? where user_phone = ? and user_name = ?";
                        statement = connection.prepareStatement(query);
                        statement.setString(1, passwordText); // 把?替換成phoneText，前面的數字是代表第幾褪號
                        statement.setString(2, salt1);
                        statement.setString(3, salt2);
                        statement.setString(4, phoneText);
                        statement.setString(5, nameText);
                        statement.execute();
                        System.out.println("Successful");
                        Intent intent = new Intent(this, LoginActivity.class);
                        startActivity(intent);
                        Looper.prepare();
                        Toast.makeText(this, "密碼更改成功", Toast.LENGTH_LONG).show();
                        Looper.loop();

                    } else {
                        System.out.println("user name error");
                        runOnUiThread(() -> (user_name).setError("使用者名字輸入錯誤"));
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });
        });

        backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(v -> {
            ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
            executor.execute(() -> {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            });
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String generated_verify_code() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 4;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        //System.out.println(generatedString);
        return generatedString;
    }
}