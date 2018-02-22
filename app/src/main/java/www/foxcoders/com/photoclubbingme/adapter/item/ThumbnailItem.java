package www.foxcoders.com.photoclubbingme.adapter.item;

/**
 * Created by fadedreamz on 1/21/18.
 */

public abstract class ThumbnailItem {
    public static final int TYPE_IMAGE = 0;
    public static final int TYPE_IMAGE_GROUP_HEADING = 1;
    protected final int type;

    public ThumbnailItem(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }
}
