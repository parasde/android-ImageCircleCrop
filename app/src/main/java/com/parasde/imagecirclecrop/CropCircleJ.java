package com.parasde.imagecirclecrop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

// Kotlin 으로 변경
@Deprecated
@SuppressLint("AppCompatCustomView")
public class CropCircleJ extends ImageView {

    private float downX;
    private float downY;
    private float moveX;
    private float moveY;

    public CropCircleJ(Context context) {
        super(context);
        this.setBackgroundResource(R.drawable.circle);
        setOnTouchListener(new onTouch());
    }

    public CropCircleJ(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setBackgroundResource(R.drawable.circle);
        setOnTouchListener(new onTouch());
    }

    public CropCircleJ(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setBackgroundResource(R.drawable.circle);
        setOnTouchListener(new onTouch());
    }

    public CropCircleJ(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.setBackgroundResource(R.drawable.circle);
        setOnTouchListener(new onTouch());
    }

    class onTouch implements OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    moveX = event.getX();
                    moveY = event.getY();

                    float r = moveX - downX;

                    ViewGroup.LayoutParams layoutParams = getLayoutParams();
                    if(r < 120) {
                        downX = 120;
                        downY = 120;

                        layoutParams.width = (int)(downX);
                        layoutParams.height = (int)(downY);

                        return true;
                    }else if(r > 360) {
                        downX = 360;
                        downY = 360;

                        layoutParams.width = (int)(downX);
                        layoutParams.height = (int)(downY);

                        return true;
                    }else {
                        layoutParams.width = (int)(downX + r);
                        layoutParams.height = (int)(downY + r);

                        downX += r;
                        downY += r;
                    }
                    setLayoutParams(layoutParams);

                    break;
                case MotionEvent.ACTION_DOWN:
                    downX = event.getX();
                    downY = event.getY();
                    break;
                default:
                    return false;
            }

            return true;
        }
    }
}
