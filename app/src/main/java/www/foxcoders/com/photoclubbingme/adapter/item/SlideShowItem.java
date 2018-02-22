package www.foxcoders.com.photoclubbingme.adapter.item;

import android.graphics.drawable.Drawable;

/**
 * Created by fadedreamz on 1/14/18.
 */

public class SlideShowItem {
    private final Integer mImgId;
    private final String mTitle;
    private final String mSubtitle;
    private final String mDate;

    public SlideShowItem(Integer imgId, String title, String subtitle, String date) {
        this.mImgId = imgId;
        this.mTitle = title;
        this.mSubtitle = subtitle;
        this.mDate = date;
    }

    public Integer getDrawableId() {
        return this.mImgId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public String getDateString() {
        return this.mDate;
    }
}
