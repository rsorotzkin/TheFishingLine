package com.example.home.thefishingline;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {

    private List<Products> productsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, details, inStock, price;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.productTitleTextView);
            details = (TextView) view.findViewById(R.id.produtctDetailsTextView);
            price = (TextView) view.findViewById(R.id.productPriceTextView);
            inStock = (TextView) view.findViewById(R.id.productInStockTextView);
        }
    }


    public ProductsAdapter(List<Products> productsList) {
        this.productsList = productsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_products_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Products products = productsList.get(position);
        holder.title.setText(products.getTitle().toUpperCase());
        holder.details.setText(products.getDescription());
        holder.price.setText(products.getPrice());
        holder.inStock.setText(products.getInStock());
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }
}
