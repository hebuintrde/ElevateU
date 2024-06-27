package com.example.navigation_drawer.SurgicalProcedures;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.navigation_drawer.R;

/**
 * A simple {@link Fragment} subclass that shows information about liposuction
 * using a WebView to load a web page.
 * Use the {@link LiposuctionFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * @version 1.0
 *
 * Authors: Beyza Arbaz, Lana Cvijic
 */
public class LiposuctionFragment extends Fragment {

    public LiposuctionFragment() {
        // Required empty public constructor
    }

    /**
     * Factory method to create a new instance of this fragment using the provided parameters.
     *
     * @return A new instance of fragment LiposuctionFragment.
     *
     */
    public static LiposuctionFragment newInstance() {
        return new LiposuctionFragment();
    }

    /**
     * Called when the fragment is created. This method sets up the fragment's parameters.
     *
     * @param savedInstanceState If the fragment is being re-created from a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * Creates and returns the view for the fragment's UI.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return The View for the fragment's UI, or null.
     *
     * TODO: create the own web page that contains information about other surgical procedures when it comes to BODY.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_liposuction, container, false);

        WebView webView = view.findViewById(R.id.webView3);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript if needed

        // Set a WebViewClient to handle loading within the WebView
        webView.setWebViewClient(new WebViewClient());

        // Load the specified URL
        webView.loadUrl("https://www.mayoclinic.org/tests-procedures/liposuction/about/pac-20384586");

        return view;
    }

}