package com.example.simplegolf.ui.strokes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

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
    private TextView textHoleNumber;
    private StrokesViewModel viewModel;

    public StrokesMainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance.
     *
     * @return A new instance of fragment StrokesMainFragment.
     */
    // TODO: Rename and change types and number of parameters
    private static StrokesMainFragment newInstance() {
        return new StrokesMainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(StrokesViewModel.class);
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
        for (int i = 0; i < viewModel.getNHoles(); i++) {
            Fragment fragment = StrokesFragment.newInstance(i);
            fragmentHoleList.add(fragment);
        }

        textHoleNumber = view.findViewById(R.id.holeNumber);
        textHoleNumber.setText(getCurrentHoleNumber());

        StrokesPageAdapter adapter = new StrokesPageAdapter(getFragmentManager(), StrokesPageAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragmentHoleList);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(viewModel.getCurrentHole(), false);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewModel.setCurrentHole(position);
                updateFragment();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public void goToPreviousHole(View view) {
        if (!(viewModel.getCurrentHole() > 0)) {
            return;
        }
        viewModel.setCurrentHole(viewModel.getCurrentHole() - 1);
        updateFragment();
    }

    public void goToNextHole(View view) {
        if (!(viewModel.getCurrentHole() < viewModel.getNHoles() - 1)) {
            return;
        }
        viewModel.setCurrentHole(viewModel.getCurrentHole() + 1);
        updateFragment();
    }

    private void updateFragment() {
        viewPager.setCurrentItem(viewModel.getCurrentHole());
        textHoleNumber.setText(getCurrentHoleNumber());
    }

    private String getCurrentHoleNumber() {
        return viewModel.getCurrentHole() + 1 + "";
    }
}
