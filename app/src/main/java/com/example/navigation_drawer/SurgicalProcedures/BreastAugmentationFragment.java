package com.example.navigation_drawer.SurgicalProcedures;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.navigation_drawer.R;

/**
 * A simple {@link Fragment} subclass that displays information about breast augmentation
 * using a WebView to load a web page (Mayo Clinic).
 * Use the {@link BreastAugmentationFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * @version 1.0
 *
 * Authors: Beyza Arbaz, Lana Cvijic
 */
public class BreastAugmentationFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public BreastAugmentationFragment() {
        // Required empty public constructor
    }

    /**
     * Method to create a new instance of this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BreastAugmentationFragment.
     */
    public static BreastAugmentationFragment newInstance(String param1, String param2) {
        BreastAugmentationFragment fragment = new BreastAugmentationFragment();
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
     * @return The View for the fragment's UI, or null.
     *
     * TODO: create the own web page that contain information about other surgical procedures when it comes to BREAST.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_breast_augmentation, container, false);

        WebView webView = view.findViewById(R.id.webView);

        webView.loadUrl("https://www.mayoclinic.org/tests-procedures/breast-augmentation/about/pac-20393178");

        return view;
    }

}