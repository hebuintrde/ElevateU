package com.example.navigation_drawer.NonSurgicalProcedures;

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
 * A simple {@link Fragment} subclass that displays information about face procedures.
 * This fragment uses a {@link WebView} to load and display content from a URL.
 *
 * @version 1.0
 *
 * @author  Beyza Arbaz
 * @author Lana Cvijic
 */

public class FaceFragment extends Fragment {

    public FaceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this method to create a new instance of this fragment.
     *
     * @return A new instance of fragment FaceFragment.
     */
    public static FaceFragment newInstance() {
        return new FaceFragment();
    }

    /**
     * Called to do initial creation of a fragment.
     *
     * @param savedInstanceState If the fragment is being re-created from a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Called to create the view hierarchy associated with the fragment.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return The View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_face, container, false);

        WebView webView = view.findViewById(R.id.webView4);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript if needed

        // Set a WebViewClient to handle loading within the WebView
        webView.setWebViewClient(new WebViewClient());

        // Load the specified URL
        webView.loadUrl("https://www.mayoclinic.org/tests-procedures/botox/about/pac-20384658");

        return view;
    }
}
