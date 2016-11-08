package com.mashazavolnyuk.effectstudio.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mashazavolnyuk.effectstudio.R;

/**
 * Created by Dark Maleficent on 03.08.2016.
 */
public class FragmentPagerOverlay extends android.support.v4.app.Fragment {

    FragmentCoverStickers tab1;
    FragmentCoverFrames tab2;

    private int[] tabIcons = {
            R.mipmap.ic_cat_white_36dp,
            R.mipmap.ic_image_filter_frames_white_36dp,
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_add_stickers, container, false);
        setHasOptionsMenu(true);
        TabLayout tabLayout = (TabLayout) inflatedView.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Stickers"));
        tabLayout.addTab(tabLayout.newTab().setText("Frames"));
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        final ViewPager viewPager = (ViewPager) inflatedView.findViewById(R.id.viewpager);
        viewPager.setAdapter(new PagerAdapter
                (getChildFragmentManager(), tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tab1 = new FragmentCoverStickers();
        tab2 = new FragmentCoverFrames();

        return inflatedView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return tab1;
                case 1:
                    return tab2;
                default:
                    return null;
            }
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public Object instantiateItem(View container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }
}
