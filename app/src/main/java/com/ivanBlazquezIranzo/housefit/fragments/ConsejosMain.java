package com.ivanBlazquezIranzo.housefit.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivanBlazquezIranzo.housefit.R;
import com.ivanBlazquezIranzo.housefit.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConsejosMain#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsejosMain extends Fragment {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    public ConsejosMain() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ConsejosMain.
     */
    public static ConsejosMain newInstance() {
        return new ConsejosMain();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consejos_main, container, false);
        viewPager = view.findViewById(R.id.viewpager);
        tabLayout = view.findViewById(R.id.tabs);
        iniciarViewPager();
        iniciarTabLayout();
        return view;
    }

    private void iniciarViewPager() {
        //noinspection ConstantConditions
        viewPager.setAdapter(new ViewPagerAdapter(getFragmentManager(), getLifecycle()));
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });
    }

    private void iniciarTabLayout() {
        new TabLayoutMediator(tabLayout, viewPager,(tab, position) -> tab.setText(ViewPagerAdapter.Tab.byPosition(position).titulo)).attach();
    }

}