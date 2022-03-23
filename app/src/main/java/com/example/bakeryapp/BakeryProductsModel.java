package com.example.bakeryapp;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class BakeryProductsModel implements Parcelable {

    private int productImage;
    private String productName;
    private String productDescription;
    private String productPrice;

    public BakeryProductsModel(int productImage, String productName, String productDescription,String productPrice) {
        this.productImage = productImage;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    protected BakeryProductsModel(Parcel in) {
        productName = in.readString();
        productDescription = in.readString();
        productImage = in.readInt();
        productPrice = in.readString();
    }

    public static final Creator<BakeryProductsModel> CREATOR = new Creator<BakeryProductsModel>() {
        @Override
        public BakeryProductsModel createFromParcel(Parcel in) {
            return new BakeryProductsModel(in);
        }

        @Override
        public BakeryProductsModel[] newArray(int size) {
            return new BakeryProductsModel[size];
        }
    };

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productPrice = productPrice; }
    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productDescription) {
        this.productPrice = productPrice; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeString(productDescription);
        dest.writeInt(productImage);
        dest.writeString(productPrice);
    }
}
