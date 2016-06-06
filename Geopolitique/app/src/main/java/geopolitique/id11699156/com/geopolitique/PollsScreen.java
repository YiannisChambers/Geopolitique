/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package geopolitique.id11699156.com.geopolitique;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;

import java.util.ArrayList;
import java.util.List;

import fragments.PollsScreenFragment;
import fragments.StatisticsScreenFragment;
import util.ToolbarHelper;

public class PollsScreen extends AppCompatActivity{

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polls_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Set up tool bar
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.polls_screen_bottom_navigation);
        ToolbarHelper.setUpToolbar(bottomNavigation, this, 4);

        //Set up view pager for tabs
        viewPager = (ViewPager) findViewById(R.id.polls_screen_viewpager);
        setupViewPager(viewPager);

        //Set up tabs with the newly created view pager
        tabLayout = (TabLayout) findViewById(R.id.polls_screen_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    /**
     * Sets up the View Pager for the Tabs on the screen
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PollsScreenFragment(), getString(R.string.polls_screen_tab_polls));
        adapter.addFragment(new StatisticsScreenFragment(), getString(R.string.polls_screen_tab_statistics));
        viewPager.setAdapter(adapter);
    }


    /**
     * Custom View Page adapter to switch between Polls and Statistics fragments
     */
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        /**
         * Constructor
         * @param manager
         */
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        /**
         * Add Fragment method
         * @param fragment
         * @param title
         */
        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}


