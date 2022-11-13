package com.example.english_app;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textfield.TextInputEditText;

import com.google.android.material.button.MaterialButton;
import android.widget.EditText;
import android.widget.TextView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Register extends AppCompatActivity {
    private EditText user_name, user_phone, user_birthday, user_password, user_check;
    private MaterialButton enrollbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user_name = findViewById(R.id.user_name);
        user_phone = findViewById(R.id.user_phone);
        user_birthday = findViewById(R.id.user_birthday);
        user_password = findViewById(R.id.user_password);
        user_check = findViewById(R.id.user_check);
        enrollbtn = findViewById(R.id.enrollbtn);
//        enrollbtn.setOnClickListener(v -> {
//            ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
//            executor.execute(() -> {
//                try { //試跑try有問題就跑catch
//                    String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
//                    Connection connection = DriverManager.getConnection(s1); //建立連線
//                    String query = "insert into account (user_phone) values (?);";
////String query2 = "select user_phone from account";
//                    PreparedStatement statement = connection.prepareStatement(query);
////PreparedStatement statement2 = connection.prepareStatement(query2);
//                    String phoneText = (user_phone).getText().toString();
//                    statement.setString(1, phoneText); // 把?替換成phoneText，前面的數字是代表第幾褪號
//                    ResultSet phone_resultSet = statement.executeQuery(); // 把結果存在resultset

// ResultSet new_result = statement2.executeQuery();
// query = "insert into account (user_name) values (?);";
// statement = connection.prepareStatement(query);
// String nameText = (user_name).getText().toString();
// statement.setString(1, nameText);
// ResultSet name_resultset = statement.executeQuery();
//
// query = "insert into account (user_birthady) values (?);";
// statement = connection.prepareStatement(query);
// String bithdayText = (user_birthday).getText().toString();
// statement.setString(1, bithdayText);
// ResultSet birthday_resultset = statement.executeQuery();
//
//
// if (phone_resultSet.next()) {
// query = "select * from account where user_phone = ? and user_password = ?";
// statement = connection.prepareStatement(query);
// String passwordText = (user_password).getText().toString();
// statement.setString(1, phoneText); // 把?替換成newphoneText，前面的數字是代表第幾褪號
// statement.setString(2, passwordText);
// ResultSet Resultset = statement.executeQuery();
// if (Resultset.next()) {
// Intent mainIntent = new Intent(Register.this, Login.class);
// startActivity(mainIntent);
// Looper.prepare();
// Toast.makeText(this, "登入成功", Toast.LENGTH_SHORT).show();
// Looper.loop();
//
// }
// else {
// Looper.prepare();
// Toast.makeText(this, "使用者密碼錯誤！", Toast.LENGTH_LONG).show();
// Looper.loop();
// }
// }
// else {
// Looper.prepare();
// Toast.makeText(this, "使用者不存在", Toast.LENGTH_SHORT).show();
// Toast.makeText(this, "請重新輸入或前往註冊", Toast.LENGTH_LONG).show();
// Looper.loop();
// }
//                }
//                catch (Exception e) {
//                    System.out.println(e.getMessage());
//                }
//            });
//        });
    }
}