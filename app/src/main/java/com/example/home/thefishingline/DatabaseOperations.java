package com.example.home.thefishingline;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;

public class DatabaseOperations {
    ProgressDialog pDialog;
    MainActivity mainActivity;

    /**
     * Method to make json array request where response starts with
     */
    public void makeJsonArrayRequest(String urlJsonArry, final String TAG,
                                     final TextView errorTextView, final VolleyCallback callback) {
//        if (pDialog == null) {
//            pDialog = createProgressDialog(mainActivity.getApplicationContext());
//        }
//        pDialog.show();

        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        // hide progress dialog
                        //pDialog.hide();
                        // initialize string to store json result
                        String jsonOutput = response.toString();
                        try {
                            // pass jsonOutput to callback interface
                            callback.onSuccessResponse(jsonOutput);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        } catch (JsonParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // handle error exceptions
               handleVolleyError(error, errorTextView);
                //pDialog.hide();
            }
        });
        //requestQueue.add(req);
        MyApplication.getInstance().addToRequestQueue(req, TAG);
    }

    public interface VolleyCallback {
        void onSuccessResponse(final String result);

    }

    public static ProgressDialog createProgressDialog(Context mContext) {
        ProgressDialog dialog = new ProgressDialog(mContext);
        try {
            dialog.show();
        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.progressdialog);
        // dialog.setMessage(Message);
        return dialog;
    }


    /**
     * Function to handle error exceptions for making a request to download data
     *
     * @param error         - set error
     * @param errorTextView - set textView to display error message
     */
    public static void handleVolleyError(VolleyError error, TextView errorTextView) {
        errorTextView.setVisibility(View.VISIBLE);
        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            errorTextView.setText(R.string.error_no_connection_found);
        } else if (error instanceof AuthFailureError) {
            errorTextView.setText(R.string.error_authentication_failed);
        } else if (error instanceof ServerError) {
            errorTextView.setText(R.string.error_server_error);
        } else if (error instanceof NetworkError) {
            errorTextView.setText(R.string.error_network_error);
        } else if (error instanceof ParseError) {
            errorTextView.setText(R.string.error_parsing_error);
        }
    }

}



