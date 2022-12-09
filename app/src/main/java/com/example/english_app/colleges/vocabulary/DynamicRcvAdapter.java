package com.example.english_app.colleges.vocabulary;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_app.R;

import java.util.ArrayList;

public class DynamicRcvAdapter extends RecyclerView.Adapter<DynamicRcvAdapter.DynamicRcvHolder>{

    public ArrayList<DynamicRcvModel> dynamicRcvModels;

    public DynamicRcvAdapter(ArrayList<DynamicRcvModel> dynamicRcvModels){
        this.dynamicRcvModels = dynamicRcvModels;
    }

    public class DynamicRcvHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        ConstraintLayout constraintLayout;


        public DynamicRcvHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.voc_rcv_unitText);
            constraintLayout = itemView.findViewById(R.id.voc_unit_layout);
        }
    }

    @NonNull
    @Override
    public DynamicRcvAdapter.DynamicRcvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_rcv_item, parent, false);
        DynamicRcvHolder dynamicRcvHolder = new DynamicRcvHolder(view);
        return dynamicRcvHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull DynamicRcvAdapter.DynamicRcvHolder holder, int position) {
        DynamicRcvModel currentItem = dynamicRcvModels.get(position);
        holder.textView.setText(currentItem.getUnitText());
        holder.textView.setTextColor(Color.parseColor(currentItem.getColor()));//改變顏色
    }

    @Override
    public int getItemCount() {
        return dynamicRcvModels.size();
    }


}

//class LoadingViewHolder extends RecyclerView.ViewHolder{
//
//    public ProgressBar progressBar;
//
//    public LoadingViewHolder(@NonNull View itemView) {
//        super(itemView);
//        progressBar = itemView.findViewById(R.id.progress_bar);
//    }
//}
//
//class ItemViewHolder extends RecyclerView.ViewHolder{
//    public TextView unitText;
//
//    public ItemViewHolder(@NonNull View itemView) {
//        super(itemView);
//        unitText = itemView.findViewById(R.id.voc_rcv_unitText);
//    }
//}
//public class DynamicRcvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
//
//    private final  int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;
//    LoadMore loadMore;
//    boolean isLoading;
//    Activity activity;
//    List<DynamicRcvModel> unitItems;
//    int visibleThreshold = 5;
//    int lastVisibleItem, totalItemCount;
//
//    public DynamicRcvAdapter(RecyclerView recyclerView, Activity activity, List<DynamicRcvModel> unitItems) {
//        this.activity = activity;
//        this.unitItems = unitItems;
//
//        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                totalItemCount = linearLayoutManager.getItemCount();
//                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
//                if (!isLoading && totalItemCount <= (lastVisibleItem+visibleThreshold)){
//                    if(loadMore != null)
//                        loadMore.onLoadMore();
//                    isLoading = true;
//                }
//
//            }
//        });
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return unitItems.get(position) == null ? VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
//    }
//
//    public void setLoadMore(LoadMore loadMore){
//        this.loadMore = loadMore;
//    }
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        if(viewType == VIEW_TYPE_ITEM){
//            View view = LayoutInflater.from(activity).inflate(R.layout.dynamic_rcv_item,parent, false);
//            return new LoadingViewHolder(view);
//        }
//        else if(viewType == VIEW_TYPE_LOADING){
//            View view = LayoutInflater.from(activity).inflate(R.layout.dynamic_rcv_progress_bar,parent, false);
//            return new LoadingViewHolder(view);
//        }
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//
//        if(holder instanceof ItemViewHolder){
//            DynamicRcvModel item = unitItems.get(position);
//            ItemViewHolder viewHolder = (ItemViewHolder) holder;
//            viewHolder.unitText.setText(item.getUnitText());
//        }
//        else if(holder instanceof LoadingViewHolder){
//            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
//        }
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return unitItems.size();
//    }
//
//    public  void setLoaded(){
//        isLoading = false;
//    }
//}
