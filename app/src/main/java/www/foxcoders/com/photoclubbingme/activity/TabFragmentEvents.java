package www.foxcoders.com.photoclubbingme.activity;

/**
 * Created by wsit on 12/19/17.
 */

import android.animation.Animator;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import www.foxcoders.com.photoclubbingme.ListSpacingDecoration;
import www.foxcoders.com.photoclubbingme.R;
import www.foxcoders.com.photoclubbingme.Util;
import www.foxcoders.com.photoclubbingme.adapter.EventAdapter;
import www.foxcoders.com.photoclubbingme.adapter.ThumbnailAdapter;
import www.foxcoders.com.photoclubbingme.adapter.item.ThumnailImageGroupTitle;

import static www.foxcoders.com.photoclubbingme.SharedPref.B_MORE_EVENT_SHOWING;


public class TabFragmentEvents extends  Fragment{
    RecyclerView recentEvents,moreEvents;
    ThumbnailAdapter thumbnailAdapter;
    EventAdapter eventAdapter;
    final ArrayList<Integer> itemsimg = new ArrayList<Integer>();
    TextView eventName;
    TextView tvMoreLocations;
    private boolean isMoreEventAnimating = false;
    private boolean isMoreEventShowing = true;
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
        View rootView = inflater.inflate(R.layout.tab_fragment_events,container,false);
        recentEvents = (RecyclerView) rootView.findViewById(R.id.recent_events);
        moreEvents = (RecyclerView) rootView.findViewById(R.id.more_events);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        eventAdapter = new EventAdapter(getActivity(), itemsimg, EventAdapter.EVENT_TYPE_EVENT);
        recentEvents.setLayoutManager(linearLayoutManager);
        recentEvents.addItemDecoration(new ListSpacingDecoration(getContext(), R.dimen.folder));
        recentEvents.setAdapter(eventAdapter);
        eventAdapter.setClickListener(new EventAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), FolderActivity.class));
            }
        });


        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        thumbnailAdapter = new ThumbnailAdapter(getActivity(),4 , Util.getDummyThumbnailImagesWithGroupings());
        moreEvents.addItemDecoration(new ListSpacingDecoration(10));
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (thumbnailAdapter.getItem(position) instanceof ThumnailImageGroupTitle) ? 4 : 1;
            }
        });
        moreEvents.setLayoutManager(gridLayoutManager);
        moreEvents.hasFixedSize();
        moreEvents.setAdapter(thumbnailAdapter);

        tvMoreLocations = (TextView) rootView.findViewById(R.id.textView13);
        tvMoreLocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synchronized (TabFragmentEvents.this) {
                    if (isMoreEventAnimating) return;
                    isMoreEventAnimating = true;
                }
                float toValue = 0.0f;
                Character lastChar = tvMoreLocations.getText().charAt(tvMoreLocations.getText().length() - 1);
                if (lastChar == '\u25b4') {
                    toValue = 0.0f;
                } else {
                    toValue = -1.5f * moreEvents.getHeight();
                }
                ObjectAnimator animator = ObjectAnimator.ofFloat(moreEvents, "translationY", toValue);
                animator.setDuration(500);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        synchronized (TabFragmentEvents.this) {
                            isMoreEventAnimating = false;
                        }
                        Character lastChar = tvMoreLocations.getText().charAt(tvMoreLocations.getText().length() - 1);
                        if (lastChar == '\u25b4') {
                            tvMoreLocations.setText("More Events(25) \u25be");
                            synchronized (TabFragmentEvents.this) {
                                isMoreEventShowing = true;
                            }
                        } else {
                            tvMoreLocations.setText("More Events(25) \u25b4");
                            synchronized (TabFragmentEvents.this) {
                                isMoreEventShowing = false;
                            }
                            moreEvents.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        Character lastChar = tvMoreLocations.getText().charAt(tvMoreLocations.getText().length() - 1);
                        if (lastChar == '\u25b4') {
                            moreEvents.setVisibility(View.VISIBLE);
                        }
                    }
                });
                animator.start();
            }
        });

        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        isMoreEventShowing = sharedPreferences.getBoolean(B_MORE_EVENT_SHOWING, true);

        if (!isMoreEventShowing) {
            moreEvents.setTranslationY(-1.5f * Util.convertPixelsToDp(10000, getContext()));
            tvMoreLocations.setText("More Events(25) \u25b4");
            moreEvents.setVisibility(View.GONE);
        }

        eventName = (TextView) rootView.findViewById(R.id.event_name);
        eventName.setOnClickListener((View.OnClickListener) getActivity());

        TextView tapForMore = (TextView) rootView.findViewById(R.id.city_count);
        tapForMore.setOnClickListener((View.OnClickListener) getActivity());

        return rootView;
    }

    @Override
    public void onDestroy() {
        SharedPreferences.Editor prefEditor = getActivity().getPreferences(Context.MODE_PRIVATE).edit();
        prefEditor.putBoolean(B_MORE_EVENT_SHOWING, isMoreEventShowing);
        prefEditor.apply();
        super.onDestroy();
    }
}
