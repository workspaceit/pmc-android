package www.foxcoders.com.photoclubbingme.adapter;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import www.foxcoders.com.photoclubbingme.R;
import www.foxcoders.com.photoclubbingme.Util;
import www.foxcoders.com.photoclubbingme.adapter.item.ThumbnailImageItem;
import www.foxcoders.com.photoclubbingme.adapter.item.ThumbnailItem;
import www.foxcoders.com.photoclubbingme.adapter.item.ThumnailImageGroupTitle;
import www.foxcoders.com.photoclubbingme.components.GridRecycleView;
import www.foxcoders.com.photoclubbingme.components.SquareImageView;

/**
 * Created by Ray on 9/15/2017.
 */

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ThumbnailItem> mData = new ArrayList<ThumbnailItem>();
    private int itemInrow;
    private LayoutInflater mInflater;
    private ThumbnailAdapter.ItemClickListener presenter;
    public boolean is_in_action_mode=false;
    private int lastPosition = -1;
    private final GridRecycleView grv;
    public final static int INITIAL_SPAN_COUNT = 16;
    public final static int STEP_SIZE = 2;
    private int spanCount = INITIAL_SPAN_COUNT / STEP_SIZE;
    private final static int FADE_DURATION = 1000;
    private boolean isExpanded = false;

    private GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
        @Override
        public int getSpanSize(int position) {
            return getCurrentSpanSize();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        CheckBox checkBox;
        SquareImageView imageView;
        TextView groupTitle;
        CardView cardView;
        View view;
        View groupTitleHbar;
        final int position;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView.findViewById(R.id.mask);
            imageView=(SquareImageView) itemView.findViewById(R.id.img_th);
            cardView=(CardView)itemView.findViewById(R.id.thumbCard);
            checkBox=(CheckBox)itemView.findViewById(R.id.checkbox);
            groupTitle = (TextView) itemView.findViewById(R.id.title);
            groupTitleHbar = itemView.findViewById(R.id.title_hbar);

            dpWidth = Util.convertPixelsToDp(displayMetrics.widthPixels, context);
            int expected = Math.round(dpWidth);
            android.view.ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.width = layoutParams.height = expected;
            imageView.setLayoutParams(layoutParams);
            //view.setLayoutParams(layoutParams);
            itemView.setOnClickListener(this);
            cardView.setLongClickable(true);
            cardView.setOnLongClickListener(this);
            cardView.setOnClickListener(this);
            position = getAdapterPosition();
        }

        @Override
        public void onClick(View v) {
            if (presenter != null) {
                presenter.onThumbnailClick(v,getAdapterPosition());
            }
            }

        @Override
        public boolean onLongClick(View v) {
            if (presenter != null)
                presenter.onThumbnailLongClick(v,getAdapterPosition());
            return true;
        }
    }

    public ThumbnailAdapter(Context context, int itemInRow , ArrayList<ThumbnailItem> data) {
        this(null, context, itemInRow, data);
    }

    public ThumbnailAdapter(@Nullable GridRecycleView grv, Context context, int itemInRow , ArrayList<ThumbnailItem> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
        presenter=(ThumbnailAdapter.ItemClickListener)context;
        this.itemInrow = itemInRow;
        this.grv = grv;
    }

    @Override
    public ThumbnailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view  = mInflater.inflate(R.layout.thumbnail_card, parent, false);
        ThumbnailAdapter.ViewHolder viewHolder = new ThumbnailAdapter.ViewHolder(view);
        viewHolder.setIsRecyclable(true);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ThumbnailAdapter.ViewHolder holder, int position) {
        setAnimation(holder.itemView, position);
        ThumbnailItem item = this.mData.get(position);
        if (item instanceof ThumbnailImageItem) {
//            Glide.with(context).load(((ThumbnailImageItem) item).getResourceId())
//                    .into(holder.imageView);
            holder.imageView.setImageResource(((ThumbnailImageItem) item).getResourceId());
        } else if (item instanceof ThumnailImageGroupTitle) {
            if (holder.groupTitle != null) {
                holder.groupTitle.setText(((ThumnailImageGroupTitle) item).getTitle());
                if (position != 0) {
                    holder.groupTitleHbar.setVisibility(View.VISIBLE);
                }
                holder.groupTitle.setVisibility(View.VISIBLE);
                holder.imageView.setVisibility(View.GONE);
            }
        }
        if(!is_in_action_mode)
        {
            if(holder.checkBox.isChecked())
            {
                holder.checkBox.setChecked(false);

            }
            holder.checkBox.setVisibility(View.GONE);
            holder.view.setVisibility(View.GONE);
        }
        else {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.view.setVisibility(View.VISIBLE);

        }

    }

    public GridLayoutManager.SpanSizeLookup getSpanSizeLookup() {
        return this.spanSizeLookup;
    }

    public void increaseSpanSize() {
        switch (spanCount) {
            case 2:
                spanCount = 4;
                break;
            case 4:
                spanCount = 8;
                break;
            case 8:
                spanCount = 16;
                break;
            default:
                spanCount = 16;
                break;
        }
    }

    public void decreaseSpanSize() {
        switch (spanCount) {
            case 16:
                spanCount = 8;
                break;
            case 8:
                spanCount = 4;
                break;
            case 4:
                spanCount = 2;
                break;
            default:
                spanCount = 2;
                break;
        }
    }

    public void setExpanded(boolean isExpanded) {
        if (grv == null) return;
        synchronized (ThumbnailAdapter.this) {
            boolean oldValue = this.isExpanded;
            this.isExpanded = isExpanded;
            if (oldValue != isExpanded) {
                if (oldValue) {
                    increaseSpanSize();
                } else {
                    decreaseSpanSize();
                }
                notifyChange(((GridLayoutManager)grv.getLayoutManager()).findFirstVisibleItemPosition(),
                        getViewItemsInRange());
            }
        }
    }

    public int getCurrentSpanSize() {
        return spanCount;
    }

    public void setCurrentSpanSize(int newSpanCount) {
        this.spanCount = newSpanCount;
    }

    public int getViewItemsInRange() {
        if (grv == null) return 0;
        int start = ((GridLayoutManager)grv.getLayoutManager()).findFirstVisibleItemPosition();
        int end = ((GridLayoutManager)grv.getLayoutManager()).findLastVisibleItemPosition();
        start = start < 0 ? 0 : start;
        end = end < 0 ? 0 : end;
        return end - start;
    }

    public void notifyChange(final int pos, final int range) {
        if (grv == null) return;
        grv.postDelayed(new Runnable() {
            @Override
            public void run() {
                notifyItemRangeChanged(pos - range > 0 ? pos - range : 0, range * 2 < getItemCount() ? range * 2 : range);
            }
        }, 100);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public ThumbnailItem getItem(int position) {
        return mData.get(position);
    }


    public interface ItemClickListener {
        void onThumbnailClick(View view, int position);
        void onThumbnailLongClick(View view,int position);
    }

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        /*if (position > lastPosition)
        {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(FADE_DURATION);
            viewToAnimate.startAnimation(anim);
            lastPosition = position;
        }*/
    }

}
