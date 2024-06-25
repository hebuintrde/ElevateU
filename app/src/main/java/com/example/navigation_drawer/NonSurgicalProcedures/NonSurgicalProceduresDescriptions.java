package com.example.navigation_drawer.NonSurgicalProcedures;

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
import com.example.navigation_drawer.SurgicalProcedures.NoseReconstructionFragment;
import com.google.android.material.tabs.TabLayout;
/**
 * An activity that displays descriptions of non-surgical procedures using fragments.
 * It uses a {@link TabLayout} to switch between different fragments that show information about procedures for face, hair and skin
 * about face, hair, and skin procedures.
 *
 * @version 1.0
 *
 * @author  Beyza Arbaz
 * @author Lana Cvijic
 */
public class NonSurgicalProceduresDescriptions extends AppCompatActivity {

    FrameLayout frameLayout_nonSurgical;
    TabLayout tabLayout_nonSurgical;

    /**
     * Called when the activity is first created.
     * This method sets up the layout, initializes the tab layout and the frame layout,
     * and sets the default fragment to be displayed when the activity is opened.
     * Uses the same logic as SurgicalProcedures Activity
     *
     * @param savedInstanceState If the activity is being re-created from a previous saved state, this is the state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_non_surgical_procedures_descriptions);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        frameLayout_nonSurgical = (FrameLayout) findViewById(R.id.frameLayout2);
        tabLayout_nonSurgical = (TabLayout) findViewById(R.id.tabLayout2);

        //when we open the activity for the first time, it opens the fragment about nose reconstruction
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout2, new NoseReconstructionFragment())
                .addToBackStack(null)
                .commit();

        tabLayout_nonSurgical.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Fragment fragment1 = null;
                switch (tab.getPosition()){
                    case 0:
                        fragment1 = new FaceFragment();
                        break;
                    case 1:
                        fragment1 = new HairFragment();
                        break;
                    case 2:
                        fragment1 = new SkinFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout2, fragment1)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
