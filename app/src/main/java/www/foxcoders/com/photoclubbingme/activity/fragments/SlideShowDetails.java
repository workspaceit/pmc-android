package www.foxcoders.com.photoclubbingme.activity.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.foxcoders.com.photoclubbingme.ListSpacingDecoration;
import www.foxcoders.com.photoclubbingme.R;
import www.foxcoders.com.photoclubbingme.Util;
import www.foxcoders.com.photoclubbingme.adapter.ThumbnailAdapter;

/**
 * Created by fadedreamz on 1/22/18.
 */

public class SlideShowDetails extends Fragment {

    private ThumbnailAdapter thumbnailAdapter;
    private RecyclerView moreLocations;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.activity_slideshow_rc_view, null);
        moreLocations = (RecyclerView) mainView.findViewById(R.id.more_locations);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        thumbnailAdapter = new ThumbnailAdapter(getActivity(),4 , Util.getDummyThumbnailImages());
        moreLocations.addItemDecoration(new ListSpacingDecoration(10));
        moreLocations.setLayoutManager(gridLayoutManager);
        moreLocations.hasFixedSize();
        moreLocations.setAdapter(thumbnailAdapter);
        return mainView;
    }
}
