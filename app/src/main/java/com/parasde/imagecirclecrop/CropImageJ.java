package com.parasde.imagecirclecrop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

// Kotlin 으로 변경
@Deprecated
@SuppressLint("AppCompatCustomView")
public class CropImageJ extends ImageView {
    private float downX;
    private float downY;

    public CropImageJ(Context context) {
        super(context);
        setOnTouchListener(new onMoveImage());
    }

    public CropImageJ(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(new onMoveImage());
    }

    public CropImageJ(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(new onMoveImage());
    }

    public CropImageJ(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setOnTouchListener(new onMoveImage());
    }

    class onMoveImage implements OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    animate()
                            .x(event.getRawX() + downX)
                            .y(event.getRawY() + downY)
                            .setDuration(0).start();
                    break;
                case MotionEvent.ACTION_DOWN:
                    downX = getX() - event.getRawX();
                    downY = getY() - event.getRawY();
                    break;
                default:
                    return false;
            }

            return true;
        }
    }
}
