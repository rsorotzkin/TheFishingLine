package com.example.home.thefishingline;

/**
 * Created by faigy on 6/16/2016.
 */
public class Items {

    String Item, ItemPrice;

    public Items() {

    }

    public Items(String Item, String ItemPrice) {
        this.Item = Item;
        this.ItemPrice = ItemPrice;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public String getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(String itemPrice) {
        ItemPrice = itemPrice;
    }
}
