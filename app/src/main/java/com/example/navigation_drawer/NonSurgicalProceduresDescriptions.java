package com.example.navigation_drawer;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

public class NonSurgicalProceduresDescriptions extends AppCompatActivity {

    FrameLayout frameLayout_nonSurgical;
    TabLayout tabLayout_nonSurgical;

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
