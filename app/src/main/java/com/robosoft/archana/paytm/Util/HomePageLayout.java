package com.robosoft.archana.paytm.Util;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by archana on 4/8/16.
 */
public class HomePageLayout {
    @SerializedName("items")
    public List<Items> items;
    @SerializedName("layout")
    public String layout;
    @SerializedName("name")
    public String name;
}
