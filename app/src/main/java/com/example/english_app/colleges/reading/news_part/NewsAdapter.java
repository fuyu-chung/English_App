package com.example.english_app.colleges.reading.news_part;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_app.R;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

//    LoadMore loadMore;
//    boolean isLoading;
//    Activity activity;
//    int visibleThreshold = 10;
//    int lastVisibileItem;

//    public ReadingAdapter(RecyclerView recyclerView, Activity activity, ArrayList<ReadingModel> NewsList) {
//        this.activity = activity;
//        NewsList = NewsList;
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
    public ArrayList<NewsModel> NewsList;
    int check_StarPosition = -1; //沒有任何星星被選擇
    private int star_click_ct = 0;

    public ProgressBar progressBar;

    public NewsAdapter(ArrayList<NewsModel> NewsList) {
        this.NewsList = NewsList;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        public TextView newsTitle, newsDate;
        LinearLayout linearLayout;
//        private ImageButton imgBtnStar;
        //之後要補btn


        public NewsViewHolder(@NonNull View itemView) {
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
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_rcv_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {
        NewsModel currentItem = NewsList.get(position);
        holder.newsTitle.setText(currentItem.getNewsTitle());
        holder.newsDate.setText(currentItem.getNewsDate());
    }

    @Override
    public int getItemCount() {
        return NewsList.size();
    }


}
