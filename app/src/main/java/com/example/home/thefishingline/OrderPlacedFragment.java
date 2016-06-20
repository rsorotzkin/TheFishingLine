package com.example.home.thefishingline;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class OrderPlacedFragment extends Fragment {




    // Declare activities
    MainActivity mainActivity;

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Confirmation");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        MainActivity.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.getActivity().getFragmentManager().popBackStack();
            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_order_placed, container, false);

        // Initialize the views for this fragment
        initializeViews(rootView);
        registerListeners();

        return rootView;
    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(final View rootView) {

    }

    /**
     * Function to set up RecyclerView
     */
    public void setupRecyclerView() {

    }


    /**
     * Function to register listeners
     */
    public void registerListeners() {
        // set onClickListeners
    }

    /**
     * OnClickListener for checkoutCoordinatorLayoutListener
     */
    View.OnClickListener checkoutCoordinatorLayoutListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            //Util.replaceFragment(new );

        }
    };







    /**
     * Function to set fragment to this main activity
     *
     * @param mainActivity - set main activity
     */
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

}