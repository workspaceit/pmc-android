package www.foxcoders.com.photoclubbingme.activity.fragments;

/**
 * Created by wsit on 12/19/17.
 */

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import www.foxcoders.com.photoclubbingme.ListSpacingDecoration;
import www.foxcoders.com.photoclubbingme.R;
import www.foxcoders.com.photoclubbingme.Util;
import www.foxcoders.com.photoclubbingme.activity.FolderActivity;
import www.foxcoders.com.photoclubbingme.adapter.EventAdapter;
import www.foxcoders.com.photoclubbingme.adapter.FolderAdapter;
import www.foxcoders.com.photoclubbingme.adapter.ThumbnailAdapter;
import www.foxcoders.com.photoclubbingme.adapter.item.ThumbnailImageItem;
import www.foxcoders.com.photoclubbingme.adapter.item.ThumbnailItem;

public class TabFragmentLocations extends Fragment {
    RecyclerView recentLocations,moreLocations;
    ThumbnailAdapter thumbnailAdapter;
    EventAdapter eventAdapter;
    final ArrayList<Integer> itemsimg = new ArrayList<Integer>();
    ArrayList<ThumbnailItem> imgItems;
    TextView locationName;
    private TextView tvMoreLocations;

    private boolean isMoreLocationsAnimating = false;
    private boolean isMoreLocationShowing = true;
    private static final String MORE_LOCATION_SHOWING_STRING = "more_location_showing";

    private void initDummyThumnailImages() {
        imgItems = Util.getDummyThumbnailImages();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        itemsimg.add(R.drawable.img_1);
        itemsimg.add(R.drawable.img_2);
        itemsimg.add(R.drawable.img_3);
        itemsimg.add(R.drawable.img_4);
        itemsimg.add(R.drawable.img_5);
        itemsimg.add(R.drawable.img_6);
        itemsimg.add(R.drawable.img_1);
        itemsimg.add(R.drawable.img_2);
        itemsimg.add(R.drawable.img_3);
        itemsimg.add(R.drawable.img_4);
        itemsimg.add(R.drawable.img_5);
        itemsimg.add(R.drawable.img_6);
        itemsimg.add(R.drawable.img_1);
        itemsimg.add(R.drawable.img_2);
        itemsimg.add(R.drawable.img_3);
        itemsimg.add(R.drawable.img_4);
        itemsimg.add(R.drawable.img_5);
        itemsimg.add(R.drawable.img_6);
        itemsimg.add(R.drawable.img_1);
        itemsimg.add(R.drawable.img_2);
        itemsimg.add(R.drawable.img_3);
        itemsimg.add(R.drawable.img_4);
        itemsimg.add(R.drawable.img_5);
        itemsimg.add(R.drawable.img_6);
        itemsimg.add(R.drawable.img_2);

        initDummyThumnailImages();

        View rootView = inflater.inflate(R.layout.tab_fragment_locations, container, false);
        recentLocations = (RecyclerView) rootView.findViewById(R.id.recent_locations);
        moreLocations = (RecyclerView) rootView.findViewById(R.id.more_locations);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        eventAdapter = new EventAdapter(getActivity(), itemsimg, EventAdapter.EVENT_TYPE_LOCATION);
        recentLocations.setLayoutManager(linearLayoutManager);
        recentLocations.addItemDecoration(new ListSpacingDecoration(getContext(), R.dimen.folder));
        recentLocations.setAdapter(eventAdapter);
        eventAdapter.setClickListener(new EventAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), FolderActivity.class));
            }
        });


        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        thumbnailAdapter = new ThumbnailAdapter(getActivity(),4 , imgItems);
        moreLocations.addItemDecoration(new ListSpacingDecoration(10));
        moreLocations.setLayoutManager(gridLayoutManager);
        moreLocations.hasFixedSize();
        moreLocations.setAdapter(thumbnailAdapter);

        tvMoreLocations = (TextView) rootView.findViewById(R.id.textView13);
        tvMoreLocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synchronized (TabFragmentLocations.this) {
                    if (isMoreLocationsAnimating) return;
                    isMoreLocationsAnimating = true;
                }
                float toValue = 0.0f;
                Character lastChar = tvMoreLocations.getText().charAt(tvMoreLocations.getText().length() - 1);
                if (lastChar == '\u25b4') {
                    toValue = 0.0f;
                } else {
                    toValue = -1.5f * moreLocations.getHeight();
                }
                ObjectAnimator animator = ObjectAnimator.ofFloat(moreLocations, "translationY", toValue);
                animator.setDuration(500);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        synchronized (TabFragmentLocations.this) {
                            isMoreLocationsAnimating = false;
                        }
                        Character lastChar = tvMoreLocations.getText().charAt(tvMoreLocations.getText().length() - 1);
                        if (lastChar == '\u25b4') {
                            tvMoreLocations.setText("More Locations(25) \u25be");
                            synchronized (TabFragmentLocations.this) {
                                isMoreLocationShowing = true;
                            }
                        } else {
                            tvMoreLocations.setText("More Locations(25) \u25b4");
                            synchronized (TabFragmentLocations.this) {
                                isMoreLocationShowing = false;
                            }
                            moreLocations.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        Character lastChar = tvMoreLocations.getText().charAt(tvMoreLocations.getText().length() - 1);
                        if (lastChar == '\u25b4') {
                            moreLocations.setVisibility(View.VISIBLE);
                        }
                    }
                });
                animator.start();
            }
        });

        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        isMoreLocationShowing = sharedPreferences.getBoolean(MORE_LOCATION_SHOWING_STRING, true);

        if (!isMoreLocationShowing) {
            moreLocations.setTranslationY(-1.5f * Util.convertPixelsToDp(5000, getContext()));
            tvMoreLocations.setText("More Locations(25) \u25b4");
            moreLocations.setVisibility(View.GONE);
        }

        locationName = (TextView) rootView.findViewById(R.id.loc_name);
        locationName.setOnClickListener((View.OnClickListener) getActivity());
        return rootView;
    }

    @Override
    public void onDestroy() {
        SharedPreferences.Editor prefEditor = getActivity().getPreferences(Context.MODE_PRIVATE).edit();
        prefEditor.putBoolean(MORE_LOCATION_SHOWING_STRING, isMoreLocationShowing);
        prefEditor.apply();
        super.onDestroy();
    }
}
