package com.example.english_app;

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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText user_name, user_phone, user_birthday, user_password, user_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user_name = findViewById(R.id.user_name);
        user_phone = findViewById(R.id.user_phone);
        user_birthday = findViewById(R.id.user_birthday);
        user_password = findViewById(R.id.user_password);
        user_check = findViewById(R.id.user_check);
        MaterialButton enrollBtn = findViewById(R.id.enrollBtn);
        enrollBtn.setOnClickListener(v -> {
            ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
           executor.execute(() -> {
                try { //試跑try有問題就跑catch
                    String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                    Connection connection = DriverManager.getConnection(s1); //建立連線
                    //String query = "insert into account (user_name, user_phone, user_birthday, user_password) values (?, ?, ?, ?);";
                    String query = "select * from account where user_phone = ?";
                    String phoneText = Objects.requireNonNull((user_phone).getText()).toString();
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, phoneText);
                    ResultSet resultSet = statement.executeQuery();
                    if(resultSet.next()){
                        System.out.println("phone failed");
                        Looper.prepare();
                        Toast.makeText(this, "使用者已存在", Toast.LENGTH_SHORT).show();
                        Toast.makeText(this, "請輸入其他電話或前往登入", Toast.LENGTH_LONG).show();
                        Looper.loop();
                        return;
                    }


                    String passwordText = Objects.requireNonNull((user_password).getText()).toString();
                    String checkText = Objects.requireNonNull((user_check).getText()).toString();

                    if(!passwordText.equals(checkText)){
                        System.out.println("password failed");
                        Looper.prepare();
                        Toast.makeText(this, "輸入的密碼不相同，請重新輸入", Toast.LENGTH_LONG).show();
                        Looper.loop();
                        return;
                    }

                    query = "select count(*) from account";
                    statement = connection.prepareStatement(query);
                    resultSet = statement.executeQuery();
                    resultSet.next();
                    int number = resultSet.getInt(1);
                    query = "insert into account values (?, ?, ?, ?, ?);";
                    String nameText = Objects.requireNonNull((user_name).getText()).toString();
                    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.TAIWAN);
                    String birthdayText = Objects.requireNonNull((user_birthday).getText()).toString();
                    statement = connection.prepareStatement(query);
                    statement.setInt(1, (((number + 1) * 19379 + 62327) % 89989) + 10000);
                    statement.setString(2, nameText);
                    statement.setString(3, phoneText);
                    statement.setDate(4, new java.sql.Date(Objects.requireNonNull(dateFormat.parse(birthdayText)).getTime()));
                    statement.setString(5, passwordText);
                    statement.execute();
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
    }
}
