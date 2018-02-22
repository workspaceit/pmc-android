package www.foxcoders.com.photoclubbingme.activity.fragments;

import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableStringBuilder;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import java.util.Vector;

import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;
import www.foxcoders.com.photoclubbingme.R;

/**
 * Created by fadedreamz on 1/31/18.
 */

public class LocationTabFragmentHolder extends Fragment {

    public static final int USE_FRAGMENT_LOCATION = 0;
    public static final int USE_FRAGMENT_VENUE = 1;

    private View rootView = null;

    private Fragment getFragment(int fragId) {
        switch (fragId) {
            case USE_FRAGMENT_LOCATION:
                return new TabFragmentLocations();
            case USE_FRAGMENT_VENUE:
                return new ShowVenueDetailsFragment();
            default:
                return new TabFragmentLocations();
        }
    }

    public void showFragment(final int fragId, boolean addTobackstack) {
        showFragment(fragId, addTobackstack, true);
    }

    public void showFragment(final int fragId, boolean addTobackstack, boolean withAnimation) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (withAnimation) {
            transaction.setCustomAnimations(R.anim.anim_slidein_from_right, R.anim.anim_slideout_to_right, R.anim.anim_slidein_from_right, R.anim.anim_slideout_to_right);
        }
        if (addTobackstack) {
            transaction.add(R.id.location_fragment_holder, getFragment(fragId), null);
            transaction.addToBackStack(null);
        } else {
            transaction.replace(R.id.location_fragment_holder, getFragment(fragId), null);
        }
        transaction.commit();
    }

    public void popFragment() {
        if (getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_fragment_location_holder, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        showFragment(USE_FRAGMENT_LOCATION, false, false);
        super.onResume();
    }
}
