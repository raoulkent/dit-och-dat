package com.example.simplegolf;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.simplegolf.ui.strokes.StrokesMainFragment;
import com.example.simplegolf.ui.strokes.StrokesViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.NavType;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class GameOverview extends AppCompatActivity {

    StrokesMainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_overview);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_strokes_main, R.id.navigation_scorecard).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        int nHoles = getIntent().getIntExtra(GameActivity.N_HOLES, 1);
        StrokesViewModel viewModel = ViewModelProviders.of(this).get(StrokesViewModel.class);
        viewModel.setnHoles(nHoles);
    }

    private StrokesMainFragment getMainFragment() {
        Fragment navHostFragment = getSupportFragmentManager().getPrimaryNavigationFragment(); // Requires view to be initialized.

        for (Fragment fragment : navHostFragment.getChildFragmentManager().getFragments()) {
            if (fragment instanceof StrokesMainFragment) {
                return (StrokesMainFragment) fragment;
            }
        }
        return null;
    }

    public void goToPreviousHole(View view) {
        mainFragment = getMainFragment();
        mainFragment.goToPreviousHole(view);
    }

    public void goToNextHole(View view) {
        mainFragment = getMainFragment();
        mainFragment.goToNextHole(view);
    }
}
