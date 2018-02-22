package www.foxcoders.com.photoclubbingme.adapter;

/**
 * @brief tab page adapter
 * @author fadedreamz@gmail.com
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.SpannableStringBuilder;
import android.util.SparseArray;

import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;
import www.foxcoders.com.photoclubbingme.activity.TabFragmentEvents;
import www.foxcoders.com.photoclubbingme.activity.fragments.LocationTabFragmentHolder;
import www.foxcoders.com.photoclubbingme.activity.fragments.TabFragmentLocations;
import www.foxcoders.com.photoclubbingme.activity.TabFragmentSlideShow;
import www.foxcoders.com.photoclubbingme.activity.fragments.SlideShowDetails;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private final FragmentManager fragmentManager;
    private final SparseArray<Fragment> fragments;

    public static final int SHOW_VENUE_PAGE = 3;

    /**
     * @brief initializes the fragments
     */
    private void initPages() {
        fragments.put(0, new LocationTabFragmentHolder());
        fragments.put(1, new TabFragmentEvents());
        fragments.put(2, new TabFragmentSlideShow());
    }

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragmentManager = fm;
        this.fragments = new SparseArray<>();
        initPages();
    }

    @Override
    public Fragment getItem(int position) {
        // sanity check
        if ((position >= fragments.size()) || position < 0)
            return null;
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence text = "";
        switch (position) {
            case 0:
                text = "Locations";
                break;
            case 1:
                text = "Events";
                break;
            case 2:
                text = "SlideShows";
                break;
                default:
        }
        return text;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}