package com.robosoft.archana.paytm.Util;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

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

}
