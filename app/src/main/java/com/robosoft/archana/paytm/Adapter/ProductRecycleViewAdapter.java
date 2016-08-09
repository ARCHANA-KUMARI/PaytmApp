package com.robosoft.archana.paytm.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.robosoft.archana.paytm.Network.VolleyHelper;
import com.robosoft.archana.paytm.R;
import com.robosoft.archana.paytm.Util.Items;

import java.util.List;

/**
 * Created by archana on 5/8/16.
 */
public class ProductRecycleViewAdapter extends RecyclerView.Adapter<ProductRecycleViewAdapter.ProductViewHolder> {

    private List<Items> mItemList;
    private Context mContext;
    private ImageLoader mImgLoader;
    private View mOneRow;

    public ProductRecycleViewAdapter(Context mContext, List<Items> mItemList) {
        this.mItemList = mItemList;
        this.mContext = mContext;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mOneRow = LayoutInflater.from(mContext).inflate(R.layout.horizental_list_row, parent, false);
        return new ProductViewHolder(mOneRow);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        Items items = mItemList.get(position);
        if (!TextUtils.isEmpty(items.name)) {
            holder.mTxtProductName.setText(items.name);
        }
        if (!TextUtils.isEmpty(items.discount)) {
            holder.mTxtDiscountPercent.setText("-" + items.discount + "%");
        }
        if (!TextUtils.isEmpty(items.actualPrice)) {
            holder.mTxtActualPrice.setText(mContext.getResources().getString(R.string.rs_str) + " " + items.actualPrice);
        }
        if (!TextUtils.isEmpty(items.offerPrice)) {
            holder.mTxtOfferPrice.setTypeface(Typeface.DEFAULT_BOLD);
            holder.mTxtOfferPrice.setText(mContext.getResources().getString(R.string.rs_str) + " " + items.offerPrice);
        }

        mImgLoader = VolleyHelper.getInstance(mContext).getImageLoader();
        mImgLoader.get(items.imageUrl, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if (response != null) {
                    holder.mProductImg.setImageBitmap(response.getBitmap());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                holder.mProductImg.setImageResource(R.drawable.placeholder);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxtProductName, mTxtDiscountPercent, mTxtOfferPrice, mTxtActualPrice;
        private ImageView mProductImg;

        public ProductViewHolder(View itemView) {
            super(itemView);
            mTxtProductName = (TextView) itemView.findViewById(R.id.txt_product_name);
            mTxtActualPrice = (TextView) itemView.findViewById(R.id.txt_actual_price);
            mTxtDiscountPercent = (TextView) itemView.findViewById(R.id.txt_discount_percent);
            mTxtOfferPrice = (TextView) itemView.findViewById(R.id.txt_offer_price);
            mProductImg = (ImageView) itemView.findViewById(R.id.img_product_img);

        }
    }

}
