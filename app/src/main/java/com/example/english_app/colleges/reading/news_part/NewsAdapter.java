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


    //list of vocabulary items
    public ArrayList<NewsModel> NewsList;
    int check_position = -1; //沒有任何星星被選擇
    private int star_click_ct = 0;

    public ProgressBar progressBar;
    CheckWhatNewsClickedInterface checkWhatNewsClickedInterface;

    public NewsAdapter(ArrayList<NewsModel> NewsList, CheckWhatNewsClickedInterface checkWhatNewsClickedInterface) {
        this.NewsList = NewsList;
        this.checkWhatNewsClickedInterface = checkWhatNewsClickedInterface;
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

            linearLayout.setOnClickListener(v -> checkPosition(getAbsoluteAdapterPosition()));


        }

        private void checkPosition(int adapterPosition) {
            if (adapterPosition == RecyclerView.NO_POSITION) {
                return;
            }
            notifyItemChanged(check_position);
            check_position = adapterPosition;
            notifyItemChanged(check_position);
            checkWhatNewsClickedInterface.onNewsTitleClicked(check_position);
            check_position = -1;//初始化
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
