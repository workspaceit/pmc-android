package com.workspaceit.photoclubbingme.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.workspaceit.photoclubbingme.R;

/**
 * Created by Ray on 9/16/2017.
 */

public class SendRecycleAdapter extends RecyclerView.Adapter<SendRecycleAdapter.ViewHolder>{
    private String[] mData = new String[0];
    private LayoutInflater mInflater;
    boolean is_in_action_mode=false;


    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        ImageView imageView;
        CardView cardView;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView.findViewById(R.id.mask);
            imageView=(ImageView)itemView.findViewById(R.id.img_th);
            cardView=(CardView)itemView.findViewById(R.id.thumbCard);
            checkBox=(CheckBox)itemView.findViewById(R.id.checkbox);
            cardView.setLongClickable(true);

        }
    }

    public SendRecycleAdapter(Context context, String[] data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;

    }

    @Override
    public SendRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.send_card, parent, false);
        SendRecycleAdapter.ViewHolder viewHolder = new SendRecycleAdapter.ViewHolder(view);
        viewHolder.setIsRecyclable(true);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SendRecycleAdapter.ViewHolder holder, int position) {

        holder.imageView.setImageResource(R.drawable.flower);
        if(!is_in_action_mode)
        {
            holder.checkBox.setVisibility(View.GONE);
            holder.view.setVisibility(View.GONE);
        }
        else {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.view.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }



}
