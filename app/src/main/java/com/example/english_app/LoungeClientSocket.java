package com.example.english_app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LoungeClientSocket extends Activity {

    private Socket clientSocket;    // 客戶端socket

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lounge_client_socket);

        // 從資源檔裡取得位址後強制轉型成文字方塊
        TextView TextView01 = findViewById(R.id.TextView01);
        TextView TextView02 = findViewById(R.id.TextView02);
        EditText EditText02 = findViewById(R.id.EditText02);
        MaterialButton sendBtn = findViewById(R.id.sendBtn);

        ExecutorService executor1 = Executors.newSingleThreadExecutor(); // 建立新的thread
        executor1.execute(() -> {
            try {
                clientSocket = new Socket("20.243.200.205", 1098);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        String name = sharedPreferences.getString("user_name", "");
        TextView02.setText(name);
        String phone = sharedPreferences.getString("user_phone", "");

        ExecutorService executor2 = Executors.newSingleThreadExecutor(); // 建立新的thread
        executor2.execute(() -> {
            try {
                while (clientSocket == null) {
                    TimeUnit.MILLISECONDS.sleep(300);
                }
                BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                while (clientSocket.isConnected()) {
                    String inputMsg = br.readLine();
                    if(inputMsg == null){
                        clientSocket.close();
                        break;
                    }
                    runOnUiThread(() -> {
                        TextView01.append(inputMsg + '\n');
                    });
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        sendBtn.setOnClickListener(v -> {
            ExecutorService executor3 = Executors.newSingleThreadExecutor(); // 建立新的thread
            executor3.execute(() -> {
                try {
                    while (clientSocket == null) {
                        TimeUnit.MILLISECONDS.sleep(300);
                    }
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    if (clientSocket.isConnected()) {
                        String msg = EditText02.getText().toString();
                        pw.println("0928560277" + name + " : " + msg);
                        pw.flush();
                    }

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });

    }
}