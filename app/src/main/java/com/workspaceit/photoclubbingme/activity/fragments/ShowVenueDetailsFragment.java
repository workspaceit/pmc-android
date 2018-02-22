package com.workspaceit.photoclubbingme.activity.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.workspaceit.photoclubbingme.ListSpacingDecoration;
import com.workspaceit.photoclubbingme.Util;
import com.workspaceit.photoclubbingme.adapter.ThumbnailAdapter;

import com.workspaceit.photoclubbingme.R;

/**
 * Created by fadedreamz on 1/22/18.
 */

public class ShowVenueDetailsFragment extends Fragment {

    private ThumbnailAdapter thumbnailAdapter;
    private RecyclerView moreLocations;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_event_details, null);
        moreLocations = (RecyclerView) mainView.findViewById(R.id.more_locations);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        thumbnailAdapter = new ThumbnailAdapter(getActivity(),4 , Util.getDummyThumbnailImages());
        moreLocations.addItemDecoration(new ListSpacingDecoration(10));
        moreLocations.setLayoutManager(gridLayoutManager);
        moreLocations.hasFixedSize();
        moreLocations.setAdapter(thumbnailAdapter);
        return mainView;
    }

    @Override
    public void onResume() {
        //moreLocations.setVisibility(View.VISIBLE);
        super.onResume();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(150);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        moreLocations.setVisibility(View.VISIBLE);
//                    }
//                });
//            }
//        }).start();
    }

    @Override
    public void onPause() {
        super.onPause();
        //moreLocations.setVisibility(View.GONE);
    }
}
