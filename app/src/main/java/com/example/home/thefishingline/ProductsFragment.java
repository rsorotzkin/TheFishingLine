package com.example.home.thefishingline;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

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
    SearchView searchView;


    // Declare variables
    Items[] itemsData;
    ArrayList<Items> itemsList;
    private ArrayList<Items> itemsCopy;

    // Declare activities
    MainActivity mainActivity;

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("The Fishing Line");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

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

        final FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Action to call the Fishing Line", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                // instantiating itemsCopy arraylist
                itemsCopy = new ArrayList<>();
                searchView.setVisibility(View.VISIBLE);
                searchView.setIconifiedByDefault(true);
                searchView.setFocusable(true);
                searchView.setIconified(false);
                searchView.requestFocusFromTouch();
                fab.hide();
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        //Toast.makeText(Util.getActivity(), query + "", Toast.LENGTH_LONG).show();
                        filter(query);
                        Util.hideSoftKeyboard();
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        filter(newText);
                        //Toast.makeText(Util.getActivity(), newText + "", Toast.LENGTH_LONG).show();
                        return true;
                    }
                });

            }
        });





        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

                searchView.setVisibility(View.GONE);
                mAdapter.setServicesList(itemsList);
                mAdapter.notifyDataSetChanged();
                fab.show();
                return false;
            }
        });


        return rootView;
    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(final View rootView) {

        // initialize and reference RecyclerView
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        errorTextView = (TextView)rootView.findViewById(R.id.errorTextView);
        searchView = (SearchView)rootView.findViewById(R.id.searchView);


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
        //addItems();
    }

    public void addItems(){
        itemsList = new ArrayList<>();
        Items items = new Items("sliced salmon","3.99");
        itemsList.add(items);

        items = new Items("whole salmon","5.99");
        itemsList.add(items);

        items = new Items("gefilta fish","3.99");
        itemsList.add(items);

        mAdapter.setServicesList(itemsList);

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
                            //mAdapter.setServicesList(itemsList);
                            mAdapter.setServicesList(itemsList);

                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    public void filter(String text) {
        // clear itemsCopy
        itemsCopy.clear();
        // if text is empty
        if(text.isEmpty()){
            // clear itemsCopy
            //itemsList.addAll(itemsCopy);
        } else{
            // instantiating results arraylist
            ArrayList<Items> result = new ArrayList<>();
            // iterating through items in itemsCopy list
            for(Items item: itemsList){
                // if item contains text in it
                if(item.getItem().toLowerCase().contains(text.toLowerCase())){
                    // add item to result arrayList
                    result.add(item);
                }
            }
            // adding result to itemsCopy
            itemsCopy.addAll(result);
        }
        // setting adapter with itemsCopy
        mAdapter.setServicesList(itemsCopy);
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