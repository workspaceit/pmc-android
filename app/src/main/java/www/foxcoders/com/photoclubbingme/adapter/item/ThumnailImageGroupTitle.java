package www.foxcoders.com.photoclubbingme.adapter.item;

/**
 * Created by fadedreamz on 1/21/18.
 */

public class ThumnailImageGroupTitle extends ThumbnailItem {
    private final String title;
    public ThumnailImageGroupTitle(String title) {
        super(ThumbnailItem.TYPE_IMAGE_GROUP_HEADING);
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
