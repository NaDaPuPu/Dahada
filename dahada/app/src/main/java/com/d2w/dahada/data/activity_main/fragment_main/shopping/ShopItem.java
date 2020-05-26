package com.d2w.dahada.data.activity_main.fragment_main.shopping;

public class ShopItem {

    private String ShopImage;
    private String ShopName;
    private int ShopGram;
    private int ShopKcal;

    public ShopItem(String shopImage, String shopName, int shopGram, int shopKcal) {
        ShopImage = shopImage;
        ShopName = shopName;
        ShopGram = shopGram;
        ShopKcal = shopKcal;
    }

    public ShopItem() {

    }

    public String getShopImage() {
        return ShopImage;
    }

    public void setShopImage(String shopImage) {
        ShopImage = shopImage;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public int getShopGram() {
        return ShopGram;
    }

    public void setShopGram(int shopGram) {
        ShopGram = shopGram;
    }

    public int getShopKcal() {
        return ShopKcal;
    }

    public void setShopKcal(int shopKcal) {
        ShopKcal = shopKcal;
    }
}
