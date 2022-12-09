package com.example.english_app.mainpage_fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.english_app.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LoungeFragment extends Fragment {
    private Socket clientSocket;
    private TextView TextView01;
    private EditText EditTextMsg;

    public LoungeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 從資源檔裡取得位址後強制轉型成文字方塊

        ExecutorService executor1 = Executors.newSingleThreadExecutor(); // 建立新的thread
        executor1.execute(() -> {
            try {
                clientSocket = new Socket("20.243.200.205", 1098);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        ExecutorService executor2 = Executors.newSingleThreadExecutor(); // 建立新的thread
        executor2.execute(() -> {
            try {
                while (clientSocket == null) {
                    TimeUnit.MILLISECONDS.sleep(300);
                }
                BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                while (clientSocket.isConnected()) {
                    String inputMsg = br.readLine();
                    //TODO
                    if (inputMsg == null) {
                        clientSocket.close();
                        break;
                    }
                    getActivity().runOnUiThread(() -> TextView01.append(inputMsg + '\n'));
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lounge, container, false);
        TextView01 = view.findViewById(R.id.TextView01);
        EditTextMsg = view.findViewById(R.id.EditTextMsg);

        ImageButton sendBtn = view.findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(v -> {
            ExecutorService executor3 = Executors.newSingleThreadExecutor(); // 建立新的thread
            executor3.execute(() -> {
                try {
                    while (clientSocket == null) {
                        TimeUnit.MILLISECONDS.sleep(300);
                    }
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    if (clientSocket.isConnected()) {
                        String msg = (EditTextMsg).getText().toString();
                        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("User", MODE_PRIVATE);
                        String name = sharedPreferences.getString("user_name", "");
                        String phone = sharedPreferences.getString("user_phone", "");
                        pw.println(phone + name + " : " + msg);
                        pw.flush();
                        getActivity().runOnUiThread(() -> (EditTextMsg).getText().clear());
                    }

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        clientSocket = new Socket();
    }
}