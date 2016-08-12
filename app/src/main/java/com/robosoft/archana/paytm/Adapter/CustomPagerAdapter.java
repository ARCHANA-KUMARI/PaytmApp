package com.robosoft.archana.paytm.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.robosoft.archana.paytm.Network.VolleyHelper;
import com.robosoft.archana.paytm.R;

import java.util.List;

/**
 * Created by archana on 5/8/16.
 */
public class CustomPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> mImgList;
    private ImageLoader mImgLoader;

    public CustomPagerAdapter(Context mContext, List<String> mImgList) {
        this.mContext = mContext;
        this.mImgList = mImgList;
    }

    @Override
    public int getCount() {
        return mImgList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        View viewItem = inflater.inflate(R.layout.imgview, container, false);
        final ImageView imageView = (ImageView) viewItem.findViewById(R.id.pagerimg);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        downloadImgFromServer(position, imageView);
        ((ViewPager) container).addView(viewItem);
        viewItem.setTag(position);
        return viewItem;
    }

    private void downloadImgFromServer(int position, final ImageView imageView) {
        mImgLoader = VolleyHelper.getInstance(mContext).getImageLoader();
        mImgLoader.get(mImgList.get(position), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if (response.getBitmap() != null) {
                    imageView.setImageBitmap(response.getBitmap());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                imageView.setImageResource(R.drawable.error);
            }
        });
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

}
