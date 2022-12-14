package com.example.english_app.mainpage_fragments.Library;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_app.R;

import java.util.ArrayList;

public class NovelRcvAdapter extends RecyclerView.Adapter<NovelRcvAdapter.NovelRcvViewHolder> {


    public ArrayList<NovelRcvModel> novelList;
    int check_position = -1; //沒有任何被選擇
    private int star_click_ct = 0;

    CheckWhatNovelClickInterface checkWhatNovelClickInterface;

    public NovelRcvAdapter(ArrayList<NovelRcvModel> novelList, CheckWhatNovelClickInterface checkWhatNovelClickInterface) {

        this.novelList = novelList;
        this.checkWhatNovelClickInterface = checkWhatNovelClickInterface;
    }

    public class NovelRcvViewHolder extends RecyclerView.ViewHolder {
        public TextView novelTitle, novelAuthor;
        LinearLayout linearLayout;
        private ImageButton imgBtnStar;
        //之後要補btn


        public NovelRcvViewHolder(@NonNull View itemView) {
            super(itemView);
            novelTitle = itemView.findViewById(R.id.novel_title);
            novelAuthor = itemView.findViewById(R.id.novel_author);
            linearLayout = itemView.findViewById(R.id.novel_rcv_linearlayout);

            linearLayout.setOnClickListener(v -> checkPosition(getAbsoluteAdapterPosition()));


        }

        private void checkPosition(int adapterPosition) {
            if (adapterPosition == RecyclerView.NO_POSITION) {
                return;
            }
            notifyItemChanged(check_position);
            check_position = adapterPosition;
            notifyItemChanged(check_position);
            checkWhatNovelClickInterface.onNovelTitleClicked(check_position);
            check_position = -1;//初始化
        }
    }

    @NonNull
    @Override
    public NovelRcvAdapter.NovelRcvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.novel_rcv_item, parent, false);
        return new NovelRcvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NovelRcvAdapter.NovelRcvViewHolder holder, int position) {
        NovelRcvModel currentItem = novelList.get(position);
        holder.novelTitle.setText(currentItem.getNovelTitle());
        holder.novelAuthor.setText(currentItem.getNovelAuthor());
    }

    @Override
    public int getItemCount() {
        return novelList.size();
    }


}
