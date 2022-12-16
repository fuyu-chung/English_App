package com.example.english_app.mainpage_fragments.Library;

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

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.LibraryViewHolder> {



    //list of vocabulary items
    public ArrayList<LibraryModel> LibraryList;
    int check_StarPosition = -1; //沒有任何星星被選擇
    private int star_click_ct = 0;

    public ProgressBar progressBar;

    public LibraryAdapter(ArrayList<LibraryModel> LibraryList) {
        this.LibraryList = LibraryList;
    }

    public class LibraryViewHolder extends RecyclerView.ViewHolder {
        public TextView nTitle, nAuthor;
        LinearLayout linearLayout;
//        private ImageButton imgBtnStar;
        //之後要補btn


        public LibraryViewHolder(@NonNull View itemView) {
            super(itemView);
            nTitle = itemView.findViewById(R.id.newsTitle);
            nAuthor = itemView.findViewById(R.id.newsDate);
            linearLayout = itemView.findViewById(R.id.news_rcv_linearlayout);

            //星星取得按下的position
//            imgBtnStar.setOnClickListener(v -> {
//                checkPosition(getAbsoluteAdapterPosition());
//                System.out.println("star click!" + getAbsoluteAdapterPosition());
////                System.out.println("item" + LibraryModel.getText());
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
    public LibraryAdapter.LibraryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_rcv_item, parent, false);
        return new LibraryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryAdapter.LibraryViewHolder holder, int position) {
        LibraryModel currentItem = LibraryList.get(position);
        holder.nTitle.setText(currentItem.getNTitle());
        holder.nAuthor.setText(currentItem.getNAuthor());
    }

    @Override
    public int getItemCount() {
        return LibraryList.size();
    }


}
