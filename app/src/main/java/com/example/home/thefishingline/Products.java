package com.example.home.thefishingline;

public class Products {
    // class members
    public String title, description, price, inStock;

    // constructor
    public Products() {

    }

    public Products(String title, String description, String price, String inStock) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.inStock = inStock;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInStock() {
        return inStock;
    }

    public void setInStock(String inStock) {
        this.inStock = inStock;
    }
}
