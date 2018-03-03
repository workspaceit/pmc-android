package com.workspaceit.photoclubbingme.activity.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.workspaceit.photoclubbingme.ListSpacingDecoration;
import com.workspaceit.photoclubbingme.R;
import com.workspaceit.photoclubbingme.Util;
import com.workspaceit.photoclubbingme.adapter.ThumbnailAdapter;

public class ShowEventDetailsFragment extends Fragment {

    private ThumbnailAdapter thumbnailAdapter;
    private RecyclerView moreLocations;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_show_event_details, null);
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
