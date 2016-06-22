package com.gzcc.CodingGarfield.shopping.Finaldb_dbclass;

import net.tsz.afinal.annotation.sqlite.Id;
import net.tsz.afinal.annotation.sqlite.Table;

import java.sql.Blob;

/**
 * Created by Wicker on 2016/6/18.
 */
@Table(name = "goods")
public class goods {
    @Id(column = "goodID")
    private int goodID;
    private String goodTitle;
    private String goodName;
    private String goodItem;
    private String goodDetails;
    private double goodPrice;
    private String goodPicUri;
    private String goodPicDetailUri;
    private Blob goodLocalPic;
    private Blob goodLocalDetailPic;
    private int goodSumurplus;//剩余商品数量
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }




    public String getGoodTitle() {
        return goodTitle;
    }

    public void setGoodTitle(String goodTitle) {
        this.goodTitle = goodTitle;
    }


    public int getGoodID() {
        return goodID;
    }

    public void setGoodID(int goodID) {
        this.goodID = goodID;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodItem() {
        return goodItem;
    }

    public void setGoodItem(String goodItem) {
        this.goodItem = goodItem;
    }

    public String getGoodDetails() {
        return goodDetails;
    }

    public void setGoodDetails(String goodDetails) {
        this.goodDetails = goodDetails;
    }

    public double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public String getGoodPicUri() {
        return goodPicUri;
    }

    public void setGoodPicUri(String goodPicUri) {
        this.goodPicUri = goodPicUri;
    }

    public String getGoodPicDetailUri() {
        return goodPicDetailUri;
    }

    public void setGoodPicDetailUri(String goodPicDetailUri) {
        this.goodPicDetailUri = goodPicDetailUri;
    }

    public Blob getGoodLocalPic() {
        return goodLocalPic;
    }

    public void setGoodLocalPic(Blob goodLocalPic) {
        this.goodLocalPic = goodLocalPic;
    }

    public Blob getGoodLocalDetailPic() {
        return goodLocalDetailPic;
    }

    public void setGoodLocalDetailPic(Blob goodLocalDetailPic) {
        this.goodLocalDetailPic = goodLocalDetailPic;
    }

    public int getGoodSumurplus() {
        return goodSumurplus;
    }

    public void setGoodSumurplus(int goodSumurplus) {
        this.goodSumurplus = goodSumurplus;
    }
}
