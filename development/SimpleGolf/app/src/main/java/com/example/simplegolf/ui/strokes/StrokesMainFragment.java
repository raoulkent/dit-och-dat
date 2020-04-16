package com.example.simplegolf.ui.strokes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.simplegolf.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StrokesMainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StrokesMainFragment extends Fragment {
    private List<Fragment> fragmentHoleList = new ArrayList<>();
    private ViewPager viewPager;
    private int currentHole = 0;
    private TextView textHoleNumber;

    public StrokesMainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance.
     * @return A new instance of fragment StrokesMainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StrokesMainFragment newInstance() {
        StrokesMainFragment fragment = new StrokesMainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SWIPER", "fragment id " + getId());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Swipe & Fragment switching
        return inflater.inflate(R.layout.fragment_strokes_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        for (int i = 0; i < 18; i++) {
            Fragment fragment = StrokesFragment.newInstance(i);
            fragmentHoleList.add(fragment);
        }

        StrokesPageAdapter adapter = new StrokesPageAdapter(getFragmentManager(), StrokesPageAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragmentHoleList);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(adapter);

        textHoleNumber = view.findViewById(R.id.holeNumber);
        textHoleNumber.setText("1");
    }

    public void goToPreviousHole(View view) {
        if (!(currentHole > 0)) {
            return;
        }
        currentHole--;
        updateFragment();
    }

    public void goToNextHole(View view) {
        if (!(currentHole < 17)) {
            return;
        }
        currentHole++;
        updateFragment();
    }

    public void updateFragment() {
        viewPager.setCurrentItem(currentHole);
        textHoleNumber.setText(currentHole + 1 + "");
    }
}
