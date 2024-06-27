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
 * A simple {@link Fragment} subclass that displays information about nose reconstruction
 * using a WebView to load a web page (Mayo Clinic).
 * Use the {@link NoseReconstructionFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * @version 1.0
 *
 * Authors: Beyza Arbaz, Lana Cvijic
 */
public class NoseReconstructionFragment extends Fragment {

    /**
     * Required empty public constructor.
     */
    public NoseReconstructionFragment() {
    }

    /**
     * Factory method to create a new instance of this fragment.
     *
     * @return A new instance of fragment NoseReconstructionFragment.
     */
    public static NoseReconstructionFragment newInstance() {
        return new NoseReconstructionFragment();
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
     * Creates and returns the view hierarchy associated with the fragment.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     *
     * TODO: create the own web page that contains information about other surgical procedures when it comes to BODY.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nose_reconstruction, container, false);

        WebView webView = view.findViewById(R.id.webView2);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript if needed

        // Set a WebViewClient to handle loading within the WebView
        webView.setWebViewClient(new WebViewClient());

        // Load the specified URL
        webView.loadUrl("https://www.mayoclinic.org/tests-procedures/rhinoplasty/about/pac-20384532");

        return view;
    }

}
