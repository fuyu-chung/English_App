package com.example.english_app.colleges.phrase;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_app.R;
import com.example.english_app.colleges.vocabulary.CheckStarClickInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PhraseUnitActivity extends AppCompatActivity implements CheckStarClickInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrase_unit);

        RecyclerView rcvPhraseUnit = findViewById(R.id.phrase_rcv);
        PhraseRcvAdapter phraseRcvAdapter = new PhraseRcvAdapter(getListPhrase(),this);
        rcvPhraseUnit.setAdapter(phraseRcvAdapter);
        rcvPhraseUnit.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ImageView backBtn = findViewById(R.id.phraseBackBtn);
        backBtn.setOnClickListener(v -> onBackPressed());
    }

    private ArrayList<PhraseRcvModel> getListPhrase() {
        ArrayList<PhraseRcvModel> list = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("Position", MODE_PRIVATE);
        int position = sharedPreferences.getInt("position", 0);
        String units;
        if (position < 9) {
            units = "Unit 0" + (position + 1);
        } else {
            units = "Unit " + (position + 1);
        }

        //unit 標題
        TextView unitText = findViewById(R.id.phrase_unit_text);
        runOnUiThread(() -> (unitText).setText(units));
        ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
        executor.execute(() -> {
            try {
                String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
                Connection connection = DriverManager.getConnection(s1); //建立連線
                int titles = sharedPreferences.getInt("title", 0);
                String query = "select Phrase, Chinese from phrase_all where Unit = ? ";
                if (titles == 0) {
                    query = "select Phrase, Chinese from phrase_all where Unit = ? ";
                }
                if (titles == 1) {
                    query = "select Phrase, Chinese from phrase_gsat where Unit = ? ";
                }
                if (titles == 2) {
                    query = "select Phrase, Chinese from phrase_ast where Unit = ? ";
                }

                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, units);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    list.add(new PhraseRcvModel(resultSet.getString(1), resultSet.getString(2)));
                }
                executor.shutdown();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        try {
            boolean e = executor.awaitTermination(20, TimeUnit.SECONDS); // await會有錯誤
            if (!e) {
                System.out.println("time out");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void onStarClicked(int position) {
        //TODO 鍾~~~~~~~~~~~~~~~~
        //voc phrase 網址方法一樣
    }
}