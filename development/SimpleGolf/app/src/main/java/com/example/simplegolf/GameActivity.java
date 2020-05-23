package com.example.simplegolf;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.simplegolf.model.Repository;
import com.example.simplegolf.model.Scorecard;
import com.example.simplegolf.ui.strokes.StrokesMainFragment;
import com.example.simplegolf.ui.strokes.StrokesViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class GameActivity extends AppCompatActivity {

    StrokesMainFragment mainFragment;
    private Repository repository;
    Scorecard scorecard;

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

        scorecard = (Scorecard) getIntent().getSerializableExtra("scorecard");
        StrokesViewModel viewModel = new ViewModelProvider(this).get(StrokesViewModel.class);
        viewModel.setScorecard(scorecard);
        //viewModel.setCurrentHole(scorecard.getStartHole());
        repository = Repository.getRepository(this);

        /*
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent startScreen = new Intent(getApplicationContext(), StartScreenActivity.class);
                startScreen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(startScreen);
            }
        };

        this.getOnBackPressedDispatcher().addCallback(this, callback);
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_name) {
            createEndGameDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    private void createEndGameDialog() {
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(this);
        dialogBuilder.setTitle(R.string.end_game_title);
        dialogBuilder.setMessage(R.string.end_game_text);
        dialogBuilder.setNegativeButton(R.string.cancel, (dialog, which) -> {
        });
        dialogBuilder.setPositiveButton(R.string.finish, (dialog, which) -> {
            scorecard.setFinishedRound(true);
            new Thread(() -> repository.getDb().scorecardDAO().update(scorecard)).start();
            finish();
        });
        dialogBuilder.show();
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
