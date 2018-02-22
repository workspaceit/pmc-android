package com.workspaceit.photoclubbingme.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import com.workspaceit.photoclubbingme.R;

/**
 * Created by Ray on 9/15/2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    public static final int EVENT_TYPE_EVENT = 1;
    public static final int EVENT_TYPE_LOCATION = 2;

    private ArrayList<Integer> mData = new ArrayList<Integer>();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private View view;
    private final static int FADE_DURATION = 1000;
    private final int type;



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RoundedImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView=(RoundedImageView)itemView.findViewById(R.id.roundedImage1);
        }

        @Override
        public void onClick(View v) {

            if (mClickListener != null) {
                setClickListener(mClickListener);
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }


    public EventAdapter(Context context, ArrayList<Integer> data, int type) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = mInflater.inflate(R.layout.event_card, parent, false);
        RoundedImageView imageView=(RoundedImageView)view.findViewById(R.id.roundedImage1);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder holder, int position) {
        ImageView imageView = (ImageView) view.findViewById(R.id.roundedImage1);
        TextView textView = (TextView)view.findViewById(R.id.tvDate);
        imageView.setImageResource(mData.get(position));
        if (type == EVENT_TYPE_EVENT) {
            textView.setText("(10) Photos");
        } else if (type == EVENT_TYPE_LOCATION) {
            textView.setText("(10) Events");
        } else {
            textView.setText("");
        }
        setAnimation(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
    private void setAnimation(View viewToAnimate) {
        /*ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        viewToAnimate.startAnimation(anim);
*/
    }

}
