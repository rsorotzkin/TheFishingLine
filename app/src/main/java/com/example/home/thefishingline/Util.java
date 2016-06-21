package com.example.home.thefishingline;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


/**
 * Created by Home on 6/7/2016.
 */
public class Util extends Activity{
    // initialize variables
    private static Context context = null;
    private static Activity activity = null;

    /**
     * Function to set reference of current activity
     *
     * @param activity - current activity
     */
    public static void setActivity(Activity activity) {
        // set this activity to current activity
        Util.activity = activity;
    }

    /**
     * Function to return reference of current activity
     *
     * @return activity
     */
    public static Activity getActivity() {
        return activity;
    }

    /**
     * Function to return reference of current context
     *
     * @return context
     */
    public static Context getContext() {
        return context;
    }

    /**
     * Function to set reference of current context
     *
     * @param context - current context
     */
    public static void setContext(Context context) {

        // set this context to current context
        Util.context = context;
    }

    /**
     * Function to call methods that set reference to current activity
     *
     * @param activity - current activity
     */
    public static void setReference(Activity activity) {
        // set context to activity
        setContext(activity);
        // set activity to activity
        setActivity(activity);
    }

    /**
     * Function to add / replace fragment
     *
     * @param fragment - new fragment
     * @param tag      - tag to add along with the fragment to the back stack
     */
    public static void replaceFragment(Fragment fragment, int tag) {
        // replace fragment in container
        getActivity().getFragmentManager().beginTransaction().replace(R.id.container,
                fragment, tag + "").addToBackStack(tag + "").commit();

    }

    /**
     * @param toolbar - set the icon to this toolbar
     * @param drawer - navigation drawer
     */
    public static void enableBackButton(Toolbar toolbar, DrawerLayout drawer) {



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// pop the backStack
                activity.getFragmentManager().popBackStack();
            }
        });

// set drawerLockMode to LOCK_MODE_LOCKED_CLOSED
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }



    /**
     * Function to hide keyboard
     */
    public static void hideSoftKeyboard() {
// get instance of keyboard through InputMethodManager
        InputMethodManager inputMethodManager = (InputMethodManager)
                Util.getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);

// find the currently focused view, so we can get the correct window token from it.
        View view = Util.getActivity().getCurrentFocus();

// if no view currently has focus, create a new one, so we can grab a window token from it
        if (view == null) {
            view = new View(Util.getActivity());
        }

// hide keyboard
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }







}
