package com.example.simplegolf.ui.strokes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.simplegolf.R;
import com.example.simplegolf.model.Hole;

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
    private StrokesViewModel viewModel;

    public StrokesMainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance.
     *
     * @return A new instance of fragment StrokesMainFragment.
     */
    private static StrokesMainFragment newInstance() {
        return new StrokesMainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(StrokesViewModel.class);
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
        for (int i = viewModel.getScorecard().getStartHole(); i <= viewModel.getScorecard().getEndHole(); i++) {
            Fragment fragment = StrokesFragment.newInstance(i);
            fragmentHoleList.add(fragment);
        }

        StrokesPageAdapter adapter = new StrokesPageAdapter(getChildFragmentManager(), StrokesPageAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragmentHoleList);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(adapter);

        Log.d("HOLE", viewModel.getCurrentHole() + "");

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


    public void goToPreviousHole() {
        if (!(viewModel.getCurrentHole() > 0)) {
            return;
        }
        viewModel.setCurrentHole(viewModel.getCurrentHole() - 1);
        updateFragment();
    }

    public void goToNextHole() {
        int size = viewModel.getScorecard().getEndHole() - viewModel.getScorecard().getStartHole();
        Log.d("HOLE", "Size " + size);
        if (!(viewModel.getCurrentHole() < size)) {
            return;
        }
        viewModel.setCurrentHole(viewModel.getCurrentHole() + 1);
        updateFragment();
    }

    private void updateFragment() {
        viewPager.setCurrentItem(viewModel.getCurrentHole());
    }

    private String getCurrentHoleNumber() {
        return viewModel.getCurrentHole() + 1 + "";
    }

    private String getCurrentPar() {
        List<Hole> holes = viewModel.getScorecard().getCourse().getHoles();

        return String.valueOf(holes.get(viewModel.getCurrentHole()).getPar());
    }
}
