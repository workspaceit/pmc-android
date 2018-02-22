package www.foxcoders.com.photoclubbingme.components;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.GridLayoutAnimationController;

import www.foxcoders.com.photoclubbingme.interfaces.OnZoomListener;

/**
 * Created by fadedreamz on 2/7/18.
 */

public class GridRecycleView extends RecyclerView {

    private boolean isTouched = false;
    private boolean isEventNotified = false;
    private float xStart, yStart, xCur, yCur;
    private final static int ZOOM_THRESHOLD = 200;
    private long scaleTimeout = 0;
    private final static long SCALE_TO = 1000;
    private OnZoomListener listener = null;
    private boolean contGestureDetector = false;

    private class MyScaleGestureDetector extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        private float initialSpan = 0.0f;

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            if (listener == null) return true;
            if (System.currentTimeMillis() - scaleTimeout <= SCALE_TO) return false;
            if (aboveScaleThreshold(detector)) {
                scaleTimeout = System.currentTimeMillis();
                final float scaling = detector.getScaleFactor();
                if (scaling > 1F) {
                    initialSpan = detector.getCurrentSpan();
                    listener.onZoomIn();
                    return false;
                } else if (scaling < 1F) {
                    initialSpan = detector.getCurrentSpan();
                    listener.onZoomOut();
                    return false;
                }
            }
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            initialSpan = detector.getCurrentSpan();
            return true;
        }

        private boolean aboveScaleThreshold(@NonNull ScaleGestureDetector detector) {
            final float diffSpan = Math.abs(initialSpan - detector.getCurrentSpan());
            return diffSpan > ZOOM_THRESHOLD;
        }
    }

    private ScaleGestureDetector mDetector = new ScaleGestureDetector(getContext(), new MyScaleGestureDetector());


    public GridRecycleView(Context context) {
        super(context);
    }

    public GridRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GridRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnZoomListener(OnZoomListener listener) {
        this.listener = listener;
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        if (layout instanceof GridLayoutManager)
            super.setLayoutManager(layout);
        else
            throw new ClassCastException("GridLayoutManager can only be used with it");
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        mDetector.onTouchEvent(e);
        return super.onTouchEvent(e);
    }

    @Override
    protected void attachLayoutAnimationParameters(View child, ViewGroup.LayoutParams params, int index, int count) {
        if (getLayoutManager() instanceof GridLayoutManager) {
            GridLayoutAnimationController.AnimationParameters animationParameters = (GridLayoutAnimationController.AnimationParameters) params.layoutAnimationParameters;
            if (animationParameters == null) {
                animationParameters = new GridLayoutAnimationController.AnimationParameters();
                params.layoutAnimationParameters = animationParameters;
            }
            int currentColumns = ((GridLayoutManager) getLayoutManager()).getSpanCount();
            animationParameters.count = count;
            animationParameters.columnsCount = currentColumns;
            animationParameters.index = index;
            animationParameters.rowsCount = count / currentColumns;
            final int indexFromEnd = count - index - 1;
            animationParameters.row = animationParameters.rowsCount - 1 - (indexFromEnd / currentColumns);
            animationParameters.column = currentColumns - 1 - (indexFromEnd % currentColumns);
        } else {
            super.attachLayoutAnimationParameters(child, params, index, count);
        }
    }
}
