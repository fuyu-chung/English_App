package com.example.english_app.user_basic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.english_app.Encryption;
import com.example.english_app.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText user_name, user_phone, user_birthday, user_password, user_check;
    private CheckBox showCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user_name = findViewById(R.id.user_name);
        user_phone = findViewById(R.id.user_phone);
        user_birthday = findViewById(R.id.user_birthday);
        user_password = findViewById(R.id.user_password);
        user_check = findViewById(R.id.user_check);
        showCb = findViewById(R.id.show_password);

        MaterialButton enrollBtn = findViewById(R.id.enrollBtn);
        enrollBtn.setOnClickListener(v -> {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                try { //試跑try有問題就跑catch
                    boolean isCorrect = true;
                    String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                    Connection connection = DriverManager.getConnection(s1); //建立連線

                    String nameText = Objects.requireNonNull((user_name).getText()).toString();
                    String namePattern = "^(?=.*[a-z]).{6,20}$";

                    String phoneText = Objects.requireNonNull((user_phone).getText()).toString();
                    String phonePattern = "^09\\d{8}$";

                    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.TAIWAN);
                    String birthdayText = Objects.requireNonNull((user_birthday).getText()).toString();
                    String birthdayPattern = "^(((?:19|20)[0-9]{2})(0?[1-9]|1[012])(0?[1-9]|[12][0-9]|3[01]))$";

                    String passwordText = Objects.requireNonNull((user_password).getText()).toString();
                    String checkText = Objects.requireNonNull((user_check).getText()).toString();
                    String passwordPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$";

                    String query = "select * from account where user_phone = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, phoneText);

                    if(nameText.isEmpty() ) {
                        runOnUiThread(() -> (user_name).setError("欄位不可為空白"));
                        isCorrect = false;
                    }

                    if (!nameText.matches(namePattern)) {
                        runOnUiThread(() -> (user_name).setError("名字格式錯誤"));
                        isCorrect = false;
                    }

                    if(phoneText.isEmpty()) {
                        runOnUiThread(() -> (user_phone).setError("欄位不可為空白"));
                        isCorrect = false;
                    }
                    if (!phoneText.matches(phonePattern)) {
                        runOnUiThread(() -> (user_phone).setError("電話格式錯誤"));
                        isCorrect = false;
                    }

                    if(birthdayText.isEmpty()) {
                        runOnUiThread(() -> (user_birthday).setError("欄位不可為空白"));
                        isCorrect = false;
                    }
                    if(!birthdayText.matches(birthdayPattern)){
                        runOnUiThread(() -> (user_birthday).setError("生日格式錯誤"));
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

                    if(!passwordText.equals(checkText)){
                        runOnUiThread(() -> (user_check).setError("密碼輸入不相同"));
                        isCorrect = false;
                    }

                    if (!isCorrect) {
                        return;
                    }

                    ResultSet resultSet = statement.executeQuery();
                    if(resultSet.next()){
                        runOnUiThread(() -> (user_phone).setError("使用者電話已存在"));
                        isCorrect = false;
                    }

                    if (!isCorrect) {
                        return;
                    }

                    String salt1 = Encryption.generatedSalt();
                    String salt2 = Encryption.generatedSalt();

                    passwordText = Encryption.sha1(salt1+ Encryption.md5(salt2 + passwordText));
                    System.out.println(passwordText);
//                    query = "select count(*) from account";
//                    statement = connection.prepareStatement(query);
//                    resultSet = statement.executeQuery();
//                    resultSet.next();
//                    int number = resultSet.getInt(1);
                    Random r = new Random();
                    int number = r.nextInt(100000);
                    System.out.println(number);
                    query = "insert into account values (?, ?, ?, ?, ?, ?, ?);";
                    // 1: user_id, 2: user_name, 3: user_phone, 4: user_birthday, 5:user_password_hash, 6: user_password_salt
                    statement = connection.prepareStatement(query);
                    statement.setInt(1, (((number + 1) * 19379 + 62327) % 89989) + 10000);
                    statement.setString(2, nameText);
                    statement.setString(3, phoneText);
                    statement.setDate(4, new java.sql.Date(Objects.requireNonNull(dateFormat.parse(birthdayText)).getTime()));
                    statement.setString(5, passwordText);
                    statement.setString(6, salt1);
                    statement.setString(7, salt2);
                    statement.executeUpdate();
                    System.out.println("Successful");
                    Intent mainIntent = new Intent(this, LoginActivity.class);
                    startActivity(mainIntent);
                    Looper.prepare();
                    Toast.makeText(this, "註冊成功", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
                catch (SQLException e) {
                    System.out.println("sql failed");
                    System.out.println(e.getMessage());
                }
                catch (ParseException e){
                    System.out.println("parse failed");
                    System.out.println(e.getMessage());
                }
            });
        });

        showCb = findViewById(R.id.show_password);
        showCb.setOnClickListener(
                view -> {
                    if(showCb.isChecked()){
                        (user_password).setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        (user_check).setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                    else{
                        (user_password).setTransformationMethod(PasswordTransformationMethod.getInstance());
                        (user_check).setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
        );
    }
}