package com.sprybit.practical.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.sprybit.practical.R;
import com.sprybit.practical.databinding.ActivityMainBinding;
import com.sprybit.practical.fragment.QuestionOneFragment;
import com.sprybit.practical.fragment.QuestionTwoFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private Toolbar toolbar;
    private boolean isBackPress = true;
    private Fragment mCallFragment;
    private TextView txtToolbarTitle;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {
        binding.navigationView.setNavigationItemSelectedListener(this);
        binding.navigationView.getMenu().getItem(0).setChecked(true);
        toolbar = findViewById(R.id.toolbar);
        txtToolbarTitle = findViewById(R.id.txtToolbarTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar();
        getSupportActionBar().setTitle("");
        //show hamburger
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHomeIndicatorIcon(R.drawable.ic_menu_24);
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frmFragmentContainer, new QuestionOneFragment());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_question_1:
                if (!isBackPress && binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                }
                binding.navigationView.getMenu().getItem(0).setChecked(true);

                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frmFragmentContainer, new QuestionOneFragment());
                fragmentTransaction.commit();
                break;
            case R.id.nav_question_2:
                if (!isBackPress && binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                }
                binding.navigationView.getMenu().getItem(1).setChecked(true);

                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frmFragmentContainer, new QuestionTwoFragment());
                fragmentTransaction.commit();
                break;
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Log.d(TAG, "onBackPressed: " + fragmentManager.getBackStackEntryCount());
        if (!isBackPress && binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else if (fragmentManager.getBackStackEntryCount() != 1) {
            fragmentManager.popBackStack();
        } else {
            fragmentManager.popBackStack();
        }
    }

    /**
     * Set Title
     */
    public void setTitle(String title) {
        txtToolbarTitle.setText(title);
    }

    /**
     * set Home Indicator Icon
     */
    public void setHomeIndicatorIcon(int iconImage) {
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(iconImage));
    }


}
