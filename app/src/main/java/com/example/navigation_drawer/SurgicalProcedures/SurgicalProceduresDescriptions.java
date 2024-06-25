package com.example.navigation_drawer.SurgicalProcedures;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.navigation_drawer.R;
import com.example.navigation_drawer.SurgicalProcedures.BreastAugmentationFragment;
import com.example.navigation_drawer.SurgicalProcedures.LiposuctionFragment;
import com.example.navigation_drawer.SurgicalProcedures.NoseReconstructionFragment;
import com.google.android.material.tabs.TabLayout;
/**
 * This activity displays descriptions of various surgical procedures using fragments.
 * It contains a TabLayout to switch between different surgical procedures descriptions (categories: nose, breast and body).
 *
 * @version 1.0
 *
 * @see BreastAugmentationFragment
 * @see LiposuctionFragment
 * @see NoseReconstructionFragment
 * @see TabLayout
 * @see FragmentTransaction
 * @see EdgeToEdge --> using the entire width and height of the display
 * @see WindowInsetsCompat
 *
 * Authors: Beyza Arbaz, Lana Cvijic
 */

public class SurgicalProceduresDescriptions extends AppCompatActivity {

    FrameLayout frameLayout;
    TabLayout tabLayout;

    /**
     * Called when the activity is first created. This method sets up the layout and initializes
     * the TabLayout and FrameLayout for displaying 3 different surgical procedure fragments.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_surgical_procedures_descriptions2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //when we open the activity for the first time, it opens the fragment about nose reconstruction (rhinoplasty)
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new NoseReconstructionFragment())
                .addToBackStack(null)
                .commit();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


                Fragment fragment = null;
                switch (tab.getPosition()){
                    case 0:
                        fragment = new NoseReconstructionFragment();
                        break;
                    case 1:
                        fragment = new BreastAugmentationFragment();
                        break;
                    case 2:
                        fragment = new LiposuctionFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //no action needed
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //no action needed
            }
        });
    }
}