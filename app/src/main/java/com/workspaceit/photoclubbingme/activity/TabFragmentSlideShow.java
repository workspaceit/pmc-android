package com.workspaceit.photoclubbingme.activity;

/**
 * Created by wsit on 12/19/17.
 */
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.workspaceit.photoclubbingme.R;
import com.workspaceit.photoclubbingme.VideoEnabledWebChromeClient;
import com.workspaceit.photoclubbingme.VideoEnabledWebView;
import com.workspaceit.photoclubbingme.adapter.SlideshowAdapter;
import com.workspaceit.photoclubbingme.adapter.item.SlideShowItem;


public class TabFragmentSlideShow extends Fragment implements AdapterView.OnItemClickListener {
    private VideoEnabledWebView webView;
    private VideoEnabledWebChromeClient webChromeClient;
    private ProgressDialog pd;
    private SlideshowAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.tab_fragment_slide_show,container,false);
        mAdapter = new SlideshowAdapter(getContext());
        ListView listView = (ListView) rootView.findViewById(R.id.slideshow_items);
        initItems(mAdapter);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(this);
        return rootView;
    }

    private void initItems(final SlideshowAdapter adapter) {
        adapter.add(new SlideShowItem(R.drawable.img_3, "The Annex", "\"Annex Fridays\"", "December 12, 2013"));
        adapter.add(new SlideShowItem(R.drawable.img_2, "Dance Main", "Dance Pick", "December 13, 2017"));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startActivity(new Intent(getActivity(), MainSlideshowActivity.class));
    }

    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.tab_fragment_slide_show,container,false);
         webView = (VideoEnabledWebView) rootView.findViewById(R.id.webView);
        View nonVideoLayout = (View)rootView.findViewById(R.id.nonVideoLayout); // Your own view, read class comments
        ViewGroup videoLayout = (ViewGroup)rootView.findViewById(R.id.videoLayout); // Your own view, read class comments
        //noinspection all
        View loadingView = inflater.inflate(R.layout.view_loading_video, null); // Your own view,
        pd = new ProgressDialog(getActivity(),R.style.alertStyle);
        pd.setMessage("Please wait Loading...");
        pd.show();
        webChromeClient = new VideoEnabledWebChromeClient(nonVideoLayout, videoLayout, loadingView, webView) // See all available constructors...
        {
            // Subscribe to standard events, such as onProgressChanged()...
            @Override
            public void onProgressChanged(WebView view, int progress)
            {
                Log.d("Progress",String.valueOf(progress));
            }
        };
        webChromeClient.setOnToggledFullscreen(new VideoEnabledWebChromeClient.ToggledFullscreenCallback()
        {
            @Override
            public void toggledFullscreen(boolean fullscreen)
            {
                // Your code to handle the full-screen change, for example showing and hiding the title bar. Example:
                if (fullscreen)
                {
                    WindowManager.LayoutParams attrs = getActivity().getWindow().getAttributes();
                    attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
                    attrs.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                    getActivity().getWindow().setAttributes(attrs);
                    if (android.os.Build.VERSION.SDK_INT >= 14)
                    {
                        //noinspection all
                        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
                    }
                }
                else
                {
                    WindowManager.LayoutParams attrs = getActivity().getWindow().getAttributes();
                    attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
                    attrs.flags &= ~WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                    getActivity().getWindow().setAttributes(attrs);
                    if (android.os.Build.VERSION.SDK_INT >= 14)
                    {
                        //noinspection all
                        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    }
                }

            }
        });
        webView.setWebChromeClient(webChromeClient);
        // Call private class InsideWebViewClient
        webView.setWebViewClient(new InsideWebViewClient());
        webView.loadUrl("http://192.168.1.18/pcm-dash/swiper_mobile.html");
        return rootView;
    }
    private class InsideWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            if (!pd.isShowing()) {
                pd.show();
            }

            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            System.out.println("on finish");
            if (pd.isShowing()) {
                pd.dismiss();
            }

        }
    }
*/
//    @Override
//    public void onBackPressed()
//    {
//        // Notify the VideoEnabledWebChromeClient, and handle it ourselves if it doesn't handle it
//        if (!webChromeClient.onBackPressed())
//        {
//            if (webView.canGoBack())
//            {
//                webView.goBack();
//            }
//            else
//            {
//                // Standard back button implementation (for example this could close the app)
//                super.onBackPressed();
//            }
//        }
//    }
}
