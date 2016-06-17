package com.example.home.thefishingline;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ProductsFragment extends Fragment {

    private List<Products> productsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProductsAdapter mAdapter;
    DatabaseOperations databaseOperations;
    TextView errorTextView;


    // Declare variables
    Items[] itemsData;
    ArrayList<Items> itemsList;

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
        databaseOperations = new DatabaseOperations();




        // Initialize the views for this fragment
        initializeViews(rootView);
        registerListeners();

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
        errorTextView = (TextView)rootView.findViewById(R.id.errorTextView);

    }

    /**
     * Function to set up RecyclerView
     */
    public void setupRecyclerView() {


        mAdapter = new ProductsAdapter(getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager
                (getActivity().getBaseContext());
        recyclerView.setLayoutManager(mLayoutManager);
        // add item decorator to recyclerView
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        downloadData();
    }


    /**
     * Function to register listeners
     */
    public void registerListeners() {
        // set onClickListeners
        errorTextView.setOnClickListener(errorTextViewListener);
        //recyclerView.addOnItemTouchListener(recyclerViewOnItemTouchListener);
    }

    /**
     * OnClickListener for errorTextView
     */
    View.OnClickListener errorTextViewListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            errorTextView.setText("");
            // download data from url
            downloadData();
        }
    };



    /**
     * Function to download data from url
     */
    public void downloadData() {
        // set url data to corresponding language of phone settings

        // call makeJsonArrayRequest and send url, tag, errorTextView and instantiate a callBack
        databaseOperations.makeJsonArrayRequest("https://fishingline.mnl-consulting.com/var/www/html/test2.php", "json_items_request", errorTextView,
                new DatabaseOperations.VolleyCallback() {
                    @Override
                    public void onSuccessResponse(String result) {
                        // initialize gson object
                        //Toast.makeText(mainActivity.getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                       Gson gson = new Gson();
                        try {
                            // convert json array into array of class type
                            itemsData = gson.fromJson(result, Items[].class);
                            // convert array to arrayList
                           itemsList = new ArrayList<>(Arrays.asList(itemsData));
                            // set list to adapter
                            mAdapter.setServicesList(itemsList);

                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });
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