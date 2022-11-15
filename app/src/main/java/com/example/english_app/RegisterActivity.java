package com.example.english_app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText user_name, user_phone, user_birthday, user_password, user_check;

    @RequiresApi(api = Build.VERSION_CODES.O)
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
                    System.out.println(passwordText);
                    passwordText = sha1("kD0a1" + md5("xA4" + passwordText) + "f4A");
                    System.out.println(passwordText);
                    query = "select count(*) from account";
                    statement = connection.prepareStatement(query);
                    resultSet = statement.executeQuery();
                    resultSet.next();
                    int number = resultSet.getInt(1);
                    query = "insert into account values (?, ?, ?, ?, ?);";
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
//    public static String hashPassword(String password) throws NoSuchAlgorithmException {
//        MessageDigest md = MessageDigest.getInstance("SHA-512");
//        md.reset();
//        md.update(password.getBytes());
//        byte[] mdArray = md.digest();
//        StringBuilder sb = new StringBuilder(mdArray.length * 2);
//        for(byte b : mdArray) {
//            int v = b & 0xff;
//            if(v < 16)
//                sb.append('0');
//            sb.append(Integer.toHexString(v));
//        }
//        return sb.toString();
//    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public static String getSalt(String passwordText) throws NoSuchAlgorithmException {
//        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
//        byte[] salt = new byte[32];
//        sr.nextBytes(salt);
//        return Base64.getEncoder().encodeToString(salt);
//
//    }
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