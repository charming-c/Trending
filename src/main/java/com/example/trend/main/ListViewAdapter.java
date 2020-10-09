package com.example.trend.main;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trend.R;
import com.example.trend.bean.ListBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.InnerHolder> {
    private List<ListBean> mData;
    private OnItemClickListener mOnItemClickListener;
    private static int isOpen=-1;

    public ListViewAdapter(List<ListBean> list){
        this.mData=list;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.trending_item,parent,false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final InnerHolder holder, final int position) {

        if(mData!=null) holder.setData(mData.get(position));

        if(isOpen==position){
            holder.mLinearLayout.setVisibility(View.VISIBLE);
            holder.Content.setMaxLines(5);
        }else {
            holder.mLinearLayout.setVisibility(View.GONE);
            holder.Content.setMaxLines(1);
        }


        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(position);
                    if(isOpen==position){
                        isOpen=-1;
                        notifyItemChanged(position);
                    }else{
                        int oldisOpen=isOpen;
                        isOpen=position;
                        notifyItemChanged(oldisOpen);
                        notifyItemChanged(isOpen);
                    }

                }
            });
        }

    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        //设置一个监听器，其实就是设置一个回调的接口
        this.mOnItemClickListener=listener;

    }


    public interface OnItemClickListener{
        void onItemClick(int position);
    }


    @Override
    public int getItemCount() {
        if(mData!=null){
            return mData.size();
        }
        else return 0;    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView icon;
        private ImageView mLanguageColor;
        private TextView forks;
        private TextView stars;
        private TextView language;
        private TextView url;
        public LinearLayout mLinearLayout;
        private TextView Name;
        private TextView Content;
        private String color;
        private Uri uri;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            mLinearLayout=itemView.findViewById(R.id.childLayout);
            Name=itemView.findViewById(R.id.user_name);
            Content=itemView.findViewById(R.id.itemcontent);
            url=itemView.findViewById(R.id.url);
            language=itemView.findViewById(R.id.programLanguage);
            stars=itemView.findViewById(R.id.starNum);
            forks=itemView.findViewById(R.id.forkNum);
            mLanguageColor=itemView.findViewById(R.id.programLanguageColor);
            icon=itemView.findViewById(R.id.icon);
        }
        public void setData(ListBean data){
            Name.setText(data.getName());
            Content.setText(data.getDescription());
            url.setText(data.getUrl());
            if(data.getLanguage()!=null)
            language.setText(data.getLanguage());
            else language.setText("--");
            stars.setText((String.valueOf(data.getStars())));
            forks.setText(String.valueOf(data.getForks()));

            color=data.getLanguageColor();
            if(color!=null){
                Drawable drawable=new ColorDrawable(Color.parseColor(color));
                mLanguageColor.setImageDrawable(drawable);
            }
            if (color==null)
                mLanguageColor.setImageDrawable(new ColorDrawable(Color.parseColor("#525252")));

            Log.d("1","3"+color);
            uri=Uri.parse(data.getAvatar());
            icon.setImageURI(uri);
        }
    }
}
