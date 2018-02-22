package www.foxcoders.com.photoclubbingme.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import www.foxcoders.com.photoclubbingme.R;
import www.foxcoders.com.photoclubbingme.VideoEnabledWebChromeClient;
import www.foxcoders.com.photoclubbingme.VideoEnabledWebView;
import www.foxcoders.com.photoclubbingme.adapter.SlideshowAdapter;

/**
 * Created by fadedreamz on 1/17/18.
 */

public class TabFragmentWebVideo extends Fragment {
    private VideoEnabledWebView webView;
    private VideoEnabledWebChromeClient webChromeClient;
    private ProgressDialog pd;
    private SlideshowAdapter mAdapter;

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
