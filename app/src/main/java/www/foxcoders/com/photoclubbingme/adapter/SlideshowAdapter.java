package www.foxcoders.com.photoclubbingme.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;
import java.util.Vector;

import www.foxcoders.com.photoclubbingme.R;
import www.foxcoders.com.photoclubbingme.adapter.item.SlideShowItem;

/**
 * Created by Ray on 9/17/2017.
 */

public class SlideshowAdapter extends ArrayAdapter<SlideShowItem> {

    Vector<SlideShowItem> mItems;

    public SlideshowAdapter(Context context)
    {
        super(context, R.layout.slideshow_per_item);
        mItems = new Vector<>();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public void add(@Nullable SlideShowItem object) {
        if (object != null)
            mItems.add(object);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(getContext(), R.layout.slideshow_per_item, null);
        }
        ImageView featureImage = (ImageView) convertView.findViewById(R.id.slideshow_item_img);
        TextView title = (TextView) convertView.findViewById(R.id.slideshow_item_desc);
        TextView subtitle = (TextView) convertView.findViewById(R.id.slideshow_item_subtitle);
        TextView date = (TextView) convertView.findViewById(R.id.slideshow_item_date);
        SlideShowItem item = mItems.elementAt(position);
        featureImage.setImageResource(item.getDrawableId());
        title.setText(item.getTitle());
        subtitle.setText(item.getSubtitle());
        date.setText(item.getDateString());
        return convertView;
    }
}
