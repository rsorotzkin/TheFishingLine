package com.example.home.thefishingline;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {

    private List<Products> productsList;
    private List<Items> itemsList;
    // Declare ArrayList

    Context context;

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


    /**
     * Function sets the newsList
     */
    public void setServicesList(ArrayList<Items> servicesList){
        this.itemsList = servicesList;
        // notify the adapter of item range changed
        notifyItemRangeChanged(0, servicesList.size());
    }

    public ProductsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_products_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Items items = itemsList.get(position);
        holder.title.setText(items.getItem().toUpperCase());
        //holder.details.setText(products.getDescription());
        holder.price.setText(items.getItemPrice());
        holder.inStock.setText("Available");
    }


    /**
     * Function that returns the number of items in newsList
     * @return int
     */
    @Override
    public int getItemCount() {
        // if newsList is not null
        if (itemsList != null) {
            // return size of newsList
            return itemsList.size();
        }
        return 0;
    }

}
