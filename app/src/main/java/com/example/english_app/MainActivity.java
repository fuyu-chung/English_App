package com.example.english_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text = findViewById(R.id.textView);
        TextView errorText = findViewById(R.id.textView2);
        Button show = findViewById(R.id.button);
        show.setOnClickListener(view -> { //點之後會執行下面的
            new Thread(()->{
                try { //試跑try有問題就跑catch
                    String records = "", error="";
                    String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                    Connection connection = DriverManager.getConnection(s1); //建立連線
                    Statement statement = connection.createStatement(); // 跑出一個結果
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM account order by user_id"); // 括號裡面寫我要的table，引號裡面是sql
                    while (resultSet.next()) { // 當還有row的時候
                        records += resultSet.getString(1) + "  " + resultSet.getString(2) + " " + resultSet.getString(3) + "\n"; // attribute從1開始，超過也會掰
                    }
                    String finalRecords = records;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            text.setText(finalRecords); // 文字設定成records內容
                        }
                    });
                }

                catch(SQLException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            errorText.setText(e.getMessage());
                        }
                    });
                }
            }).start();
        });
    }
}