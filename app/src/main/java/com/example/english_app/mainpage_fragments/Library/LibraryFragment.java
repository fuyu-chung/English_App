//package com.example.english_app.mainpage_fragments.Library;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.english_app.R;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.TimeUnit;
//
//
//public class LibraryFragment extends Fragment {
//
//
//    public LibraryFragment() {
//
//    }
//
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//
//        // Required empty public constructor
//        View mView = inflater.inflate(R.layout.fragment_library, container, false);
//
//
//        RecyclerView rcvLibrary = mView.findViewById(R.id.rcv_library);
//        LibraryAdapter libraryAdapter = new LibraryAdapter(getListLibrary());
//        rcvLibrary.setAdapter(libraryAdapter);
//        rcvLibrary.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//
//
////        followerAdapter.setData(getListLibrary());
////        rcvFollower.setAdapter(followerAdapter);
////
////
////        return mView;
////    }
////    private ArrayList<LibraryModel>  getListLibrary(){
////        ExecutorService executor = Executors.newSingleThreadExecutor(); // 建立新的thread
////        executor.execute(() -> {
////            try {
////                String s1 = "jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;"; //訪問azure的db的網址
////                Connection connection = DriverManager.getConnection(s1); //建立連線
////                String query = "select Title, Author from novel";
////                PreparedStatement statement = connection.prepareStatement(query);
////                ResultSet resultSet = statement.executeQuery();
////                while (resultSet.next()) {
////                    list.add(new ReadingModel(resultSet.getString(1), resultSet.getString(2)));
////                }
////                executor.shutdown();
////            } catch (SQLException e) {
////                e.printStackTrace();
////            }
////        });
////        try {
////            boolean e = executor.awaitTermination(10, TimeUnit.SECONDS); // await會有錯誤
////            if (!e) {
////                System.out.println("time out");
////            }
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
////        return list;
//    }
//}