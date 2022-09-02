package com.example.brainnews.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.brainnews.R;
import com.example.brainnews.model.Article;
import com.example.brainnews.utils.OnRecyclerViewItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



public class MainArticleAdapter  extends RecyclerView.Adapter<MainArticleAdapter.ViewHolder> {

    private List<Article> articleArrayList = new ArrayList<>();
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    @Override
    public MainArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new MainArticleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainArticleAdapter.ViewHolder viewHolder, int position) {
        final Article articleModel = articleArrayList.get(position);
        if (!TextUtils.isEmpty(articleModel.getTitle())) {
            viewHolder.titleText.setText(articleModel.getTitle());
        }
        if (!TextUtils.isEmpty(articleModel.getAuthor())){
            viewHolder.author.setText(articleModel.getAuthor());
        }
        if(!TextUtils.isEmpty(articleModel.getPublishedAt())){
            viewHolder.publish.setText(articleModel.getPublishedAt());
        }
        if (!TextUtils.isEmpty(articleModel.getUrlToImage())){
            Picasso.get().load(articleModel.getUrlToImage()).into(viewHolder.image);
        }else {
            viewHolder.image.setImageResource(R.drawable.noimage);
        }
        viewHolder.artilceAdapterParentLinear.setTag(articleModel);
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

    public void setResults(List<Article> results){
        this.articleArrayList = results;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleText;
        private ImageView image;
        private TextView author;
        private TextView publish;
        private RelativeLayout artilceAdapterParentLinear;

        ViewHolder(View view) {
            super(view);
            titleText = view.findViewById(R.id.judul_berita);
            image = view.findViewById(R.id.gambar_berita);
            author = view.findViewById(R.id.publisher_berita);
            publish = view.findViewById(R.id.tanggal);
            artilceAdapterParentLinear = view.findViewById(R.id.relative_layout);
            artilceAdapterParentLinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onRecyclerViewItemClickListener != null) {
                        onRecyclerViewItemClickListener.onItemClick(getAdapterPosition(), view);
                    }
                }
            });
        }
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}
