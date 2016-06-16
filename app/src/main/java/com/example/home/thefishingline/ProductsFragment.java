package com.example.home.thefishingline;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ProductsFragment extends Fragment {

    private List<Products> productsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProductsAdapter mAdapter;

    // Declare activities
    MainActivity mainActivity;

    @Override
    public void onResume() {
        super.onResume();
        //((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Products");

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_products, container, false);





        // Initialize the views for this fragment
        initializeViews(rootView);

        // set up recyclerView
        setupRecyclerView();



        return rootView;
    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(final View rootView) {

        // initialize and reference RecyclerView
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

    }

    /**
     * Function to set up RecyclerView
     */
    public void setupRecyclerView() {



        mAdapter = new ProductsAdapter(productsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager
                (getActivity().getBaseContext());
        recyclerView.setLayoutManager(mLayoutManager);
        // add item decorator to recyclerView
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();
    }


    private void prepareMovieData() {
        Products products = new Products("Salmon Slice", "1' with no skin", "$7.99", "In Stock");
        productsList.add(products);

       products = new Products("Salmon Slice", "1' with no skin", "$7.99", "In Stock");
        productsList.add(products);

        products = new Products("Salmon Slice", "1' with no skin", "$7.99", "In Stock");
        productsList.add(products);

        products = new Products("Salmon Slice", "1' with no skin", "$7.99", "In Stock");
        productsList.add(products);

        products = new Products("Salmon Slice", "1' with no skin", "$7.99", "In Stock");
        productsList.add(products);

        products = new Products("Salmon Slice", "1' with no skin", "$7.99", "In Stock");
        productsList.add(products);

        products = new Products("Salmon Slice", "1' with no skin", "$7.99", "In Stock");
        productsList.add(products);

        products = new Products("Salmon Slice", "1' with no skin", "$7.99", "In Stock");
        productsList.add(products);

        mAdapter.notifyDataSetChanged();
    }
    /**
     * Function to set fragment to this main activity
     *
     * @param mainActivity - set main activity
     */
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

}