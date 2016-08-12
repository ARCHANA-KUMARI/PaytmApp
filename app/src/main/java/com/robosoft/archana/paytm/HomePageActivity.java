package com.robosoft.archana.paytm;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.robosoft.archana.paytm.Adapter.HomePageAdapter;
import com.robosoft.archana.paytm.Network.DownloadManager;
import com.robosoft.archana.paytm.Network.NetworkStatus;
import com.robosoft.archana.paytm.Network.VolleyHelper;
import com.robosoft.archana.paytm.Util.HomePage;
import com.robosoft.archana.paytm.Util.HomePageLayout;
import com.robosoft.archana.paytm.Util.Items;
import com.robosoft.archana.paytm.Util.ViewUtils;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener{

    private String mUrl = "https://dl.dropboxusercontent.com/s/u1rnvb8oow2xs5c/homepage.json?dl=0";
    private ImageView mImgErrorMsg;
    private List<String> mImgUrlList = new ArrayList<>();
    private List<Items> mRowLytList = new ArrayList<>();
    private ProgressDialog mProgressDialog;
    private RecyclerView mHomePageRecycleView;
    private List<HomePageLayout> mHomePageLayout;
    private CoordinatorLayout mCoordinatorLayout;
    private FloatingActionButton mFloatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        initUi();
        downloadServerData();
    }
    private void initUi() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mProgressDialog = new ProgressDialog(this);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.container);
        mHomePageRecycleView = (RecyclerView) findViewById(R.id.layout_recycler);
        mImgErrorMsg = (ImageView) findViewById(R.id.networkerrorimg);
        mFloatBtn = (FloatingActionButton) findViewById(R.id.fab);
        mFloatBtn.setOnClickListener(this);

    }
    private void downloadServerData() {
        if(NetworkStatus.isNetworkAvailable(this)) {
            if(URLUtil.isValidUrl(mUrl)){
            ViewUtils.showProgressDialog(mProgressDialog);
            DownloadManager.downloadData(this, mUrl, new Response.Listener<HomePage>() {
                @Override
                public void onResponse(HomePage response) {
                    ViewUtils.dismissProgressDialog(mProgressDialog);
                    if (response != null) {
                        mHomePageLayout = response.homePageLayout;
                        for (int i = 0; i < mHomePageLayout.size(); i++) {
                            List<Items> items = mHomePageLayout.get(i).items;
                            if (mHomePageLayout.get(i).layout != null && mHomePageLayout.get(i).layout.equals("carousel-1")) {
                                for (int j = 0; j < items.size(); j++) {
                                    mImgUrlList.add(items.get(j).imageUrl);
                                }
                            } else if (mHomePageLayout.get(i).layout != null && mHomePageLayout.get(i).layout.equals("row")) {
                                for (int j = 0; j < items.size(); j++) {
                                    mRowLytList.add(items.get(j));
                                }
                            }
                        }
                    }
                    setHomepagerAdapter();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    ViewUtils.dismissProgressDialog(mProgressDialog);
                    mImgErrorMsg.setVisibility(View.VISIBLE);
                }
            });

        }
        }
        else {
            mFloatBtn.setVisibility(View.VISIBLE);
            setSnackBar();
        }
    }


    private void setHomepagerAdapter() {
        mFloatBtn.setVisibility(View.GONE);
        HomePageAdapter homePageAdapter = new HomePageAdapter(mHomePageLayout, this, mImgUrlList, mRowLytList);
        mHomePageRecycleView.setLayoutManager(ViewUtils.setLinearLayoutManager(LinearLayoutManager.VERTICAL, this));
        mHomePageRecycleView.setAdapter(homePageAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        ViewUtils.dismissProgressDialog(mProgressDialog);
        VolleyHelper.getInstance(this).cancelRequest(mUrl);

    }
    private void setSnackBar() {
        Snackbar.make(mCoordinatorLayout, "No internet connection!", Snackbar.LENGTH_LONG)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (NetworkStatus.isNetworkAvailable(getApplicationContext())) {
                            downloadServerData();
                        }
                    }
                }).show();
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.fab){
            setSnackBar();
        }
    }
}
