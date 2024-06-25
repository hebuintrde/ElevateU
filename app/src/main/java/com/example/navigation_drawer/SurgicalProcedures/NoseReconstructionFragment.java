package com.example.navigation_drawer.SurgicalProcedures;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

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

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    /**
     * Required empty public constructor.
     */
    public NoseReconstructionFragment() {
    }

    /**
     * Factory method to create a new instance of this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoseReconstructionFragment.
     */
    public static NoseReconstructionFragment newInstance(String param1, String param2) {
        NoseReconstructionFragment fragment = new NoseReconstructionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Called to do initial creation of a fragment.
     *
     * @param savedInstanceState If the fragment is being re-created from a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     * Creates and returns the view hierarchy associated with the fragment.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to. The fragment should not add the view itself, but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     *
     * TODO: create the own web page that contain information about other surgical procedures when it comes to NOSE.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nose_reconstruction, container, false);

        WebView webView = view.findViewById(R.id.webView2);

        // Loading the local HTML file from assets folder? --> TODO
        // Load the web page with information about nose reconstruction
        webView.loadUrl("https://www.mayoclinic.org/tests-procedures/rhinoplasty/about/pac-20384532");

        return view;
    }
}