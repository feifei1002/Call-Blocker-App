package com.example.mob_dev_portfolio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.mob_dev_portfolio.R;
import com.example.mob_dev_portfolio.databinding.ActivityMainBinding;
import com.example.mob_dev_portfolio.fragments.BlockFragment;
import com.example.mob_dev_portfolio.fragments.HomeFragment;
import com.example.mob_dev_portfolio.fragments.ReportListFragment;
import com.example.mob_dev_portfolio.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){

                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.block:
                    replaceFragment(new BlockFragment());
                    break;
                case R.id.search:
                    replaceFragment(new SearchFragment());
                    break;
                case R.id.report:
                    replaceFragment(new ReportListFragment());
                    break;
            }
            return true;

        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

