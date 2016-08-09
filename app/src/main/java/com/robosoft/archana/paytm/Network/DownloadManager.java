package com.robosoft.archana.paytm.Network;

import android.content.Context;

import com.android.volley.Response;

import com.robosoft.archana.paytm.Util.HomePage;

/**
 * Created by archana on 4/8/16.
 */
public class DownloadManager {
   public static void downloadData(Context ctx, String url, Response.Listener<HomePage> listener, Response.ErrorListener errorListener){
        GsonRequest<HomePage> request = new GsonRequest<>(url,HomePage.class,listener,errorListener);
        request.setTag(url);
        VolleyHelper.getInstance(ctx).addToRequestQueue(request);
   }
}