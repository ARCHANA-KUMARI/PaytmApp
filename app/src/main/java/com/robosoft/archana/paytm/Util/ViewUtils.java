package com.robosoft.archana.paytm.Util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;

/**
 * Created by archana on 6/8/16.
 */
public class ViewUtils {

    public static void showProgressDialog(ProgressDialog progressDialog){
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
    }

    public static void dismissProgressDialog(ProgressDialog progressDialog){
        if(progressDialog != null&&progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
    public static LinearLayoutManager setLinearLayoutManager(int orientation,Context context){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(orientation);
        return linearLayoutManager;
    }

    public static int getScreenWidth(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        int width;
        if (activity != null && !activity.isFinishing()) {
            activity.getWindowManager().getDefaultDisplay()
                    .getMetrics(displayMetrics);
            width = displayMetrics.widthPixels;
        } else {
            width = 0;
        }
        return width;
    }

    public static int getScreenHeight(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay()
                .getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        return height;
    }



}
