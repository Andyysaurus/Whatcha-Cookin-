package com.cookApp;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;


public class ImageViewAdapter extends ImageView { //might change the view of the mole from its image processed version
	public ImageViewAdapter(Context context)
    {
        super(context);
    }

    public ImageViewAdapter(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public ImageViewAdapter(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth()); //Snap to width
    }

}
