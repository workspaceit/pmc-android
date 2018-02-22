package www.foxcoders.com.photoclubbingme.adapter.item;

/**
 * Created by fadedreamz on 1/21/18.
 */

public class ThumbnailImageItem extends ThumbnailItem {
    private final int resourceId;

    public ThumbnailImageItem(int resourceId) {
        super(ThumbnailItem.TYPE_IMAGE);
        this.resourceId = resourceId;
    }

    public int getResourceId() {
        return this.resourceId;
    }
}
