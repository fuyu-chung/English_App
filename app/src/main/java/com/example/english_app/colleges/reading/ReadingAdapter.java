package com.example.english_app.colleges.reading;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_app.R;
import com.example.english_app.colleges.vocabulary.DRcvInterface.LoadMore;

import java.util.ArrayList;

public class ReadingAdapter extends RecyclerView.Adapter<ReadingAdapter.ReadingViewHolder> {

//    LoadMore loadMore;
//    boolean isLoading;
//    Activity activity;
//    int visibleThreshold = 10;
//    int lastVisibileItem;

//    public ReadingAdapter(RecyclerView recyclerView, Activity activity, ArrayList<ReadingModel> readingList) {
//        this.activity = activity;
//        ReadingList = readingList;
//
//        final LinearLayoutManager linearLayoutManager =(LinearLayoutManager) recyclerView.getLayoutManager();
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if(!isLoading && )
//            }
//        });
//    }

    //list of vocabulary items
    public ArrayList<ReadingModel> ReadingList;
    int check_StarPosition = -1; //沒有任何星星被選擇
    private int star_click_ct = 0;

    public ProgressBar progressBar;

    public ReadingAdapter(ArrayList<ReadingModel> ReadingList) {
        this.ReadingList = ReadingList;
    }

    public class ReadingViewHolder extends RecyclerView.ViewHolder {
        public TextView newsTitle, newsDate;
        LinearLayout linearLayout;
//        private ImageButton imgBtnStar;
        //之後要補btn


        public ReadingViewHolder(@NonNull View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            newsDate = itemView.findViewById(R.id.newsDate);
            linearLayout = itemView.findViewById(R.id.news_rcv_linearlayout);
            progressBar = itemView.findViewById(R.id.progress_bar_news);

            //星星取得按下的position
//            imgBtnStar.setOnClickListener(v -> {
//                checkPosition(getAbsoluteAdapterPosition());
//                System.out.println("star click!" + getAbsoluteAdapterPosition());
////                System.out.println("item" + ReadingModel.getText());
//            });

        }

        private void checkPosition(int adapterPosition) {
            if (adapterPosition == RecyclerView.NO_POSITION) {
                return;
            }
            notifyItemChanged(check_StarPosition);
            check_StarPosition = adapterPosition;
            notifyItemChanged(check_StarPosition);
        }
    }

    @NonNull
    @Override
    public ReadingAdapter.ReadingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_rcv_item, parent, false);
        return new ReadingAdapter.ReadingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadingAdapter.ReadingViewHolder holder, int position) {
        ReadingModel currentItem = ReadingList.get(position);
        holder.newsTitle.setText(currentItem.getNewsTitle());
        holder.newsDate.setText(currentItem.getNewsDate());
    }

    @Override
    public int getItemCount() {
        return ReadingList.size();
    }


}
