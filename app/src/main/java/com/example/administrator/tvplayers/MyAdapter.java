package com.example.administrator.tvplayers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @创建人 Administrator
 * <p>
 * Created by Administrator on 2017/3/20 0020.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
private Context mContext;
    private List<String> mDatas;
    private MyItemClickListener mItemClickListener;
    private Object mItemLongClickListener;

    public MyAdapter(Context mContext, List<String> mDatas){
        this.mContext=mContext;
        this.mDatas=mDatas;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_layout, parent,false);
        MyViewHolder holder = new MyViewHolder(itemView,mItemClickListener);
//        return vh;
//        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
//                mContext).inflate(R.layout.item_recycler_layout, parent,
//                false));
        return holder;
    }
    public void setOnItemClickListener(MyItemClickListener listener){
        this.mItemClickListener = listener;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));


    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        private TextView tv;
        private  MyItemClickListener mListener;


        public MyViewHolder(View view,MyItemClickListener listener)
        {
            super(view);
            this.mListener = listener;
            view.setOnClickListener(this);
            tv = (TextView) view.findViewById(R.id.item_tv);

        }

        @Override
        public void onClick(View v) {
            if (mListener !=null){
                mListener.onItemClick(v,getPosition());

            }

        }
    }

}
