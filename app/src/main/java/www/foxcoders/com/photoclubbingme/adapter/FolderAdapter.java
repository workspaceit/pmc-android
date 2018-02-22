package www.foxcoders.com.photoclubbingme.adapter;

import android.content.Context;
import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import www.foxcoders.com.photoclubbingme.R;

/**
 * Created by Ray on 9/15/2017.
 */

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) mClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    private ArrayList<Integer> mData = new ArrayList<Integer>();
    private LayoutInflater mInflater;
    private EventAdapter.ItemClickListener mClickListener;
    private View view;
    private final static int FADE_DURATION = 1000;

    public FolderAdapter(Context context, ArrayList<Integer> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public FolderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = mInflater.inflate(R.layout.image_text_card, parent, false);
        FolderAdapter.ViewHolder viewHolder = new FolderAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FolderAdapter.ViewHolder holder, int position) {
        ImageView imageView = (ImageView) view.findViewById(R.id.roundedImage2);
        imageView.setImageResource(mData.get(position));
        setAnimation(holder.itemView);

    }


    @Override
    public int getItemCount() {
        Log.d("COUNT", String.valueOf(mData.size()));
        return mData.size();

    }

    // allows clicks events to be caught
    public void setClickListener(EventAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onFolderClick(View view, int position);
    }

    private void setAnimation(View viewToAnimate) {
//        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        anim.setDuration(FADE_DURATION);
//        viewToAnimate.startAnimation(anim);

    }
}
