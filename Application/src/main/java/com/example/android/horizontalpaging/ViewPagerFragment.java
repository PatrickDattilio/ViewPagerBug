package com.example.android.horizontalpaging;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Locale;

public class ViewPagerFragment extends Fragment  implements ActionBar.TabListener{

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private ActionBar actionBar;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        actionBar = activity.getActionBar();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(container.getContext(),R.layout.fragment_view_pager,null);
        // BEGIN_INCLUDE (setup_view_pager)
        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) view.findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        // END_INCLUDE (setup_view_pager)

        // When swiping between different sections, select the corresponding tab. We can also use
        // ActionBar.Tab#select() to do this if we have a reference to the Tab.
        // BEGIN_INCLUDE (page_change_listener)
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });
        // END_INCLUDE (page_change_listener)

        // BEGIN_INCLUDE (add_tabs)
        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter. Also
            // specify this Activity object, which implements the TabListener interface, as the
            // callback (listener) for when this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
        // END_INCLUDE (add_tabs)
        return view;
    }

        // BEGIN_INCLUDE (fragment_pager_adapter)
        /**
         * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
         * one of the sections/tabs/pages. This provides the data for the {@link ViewPager}.
         */
        public class SectionsPagerAdapter extends FragmentPagerAdapter {
            // END_INCLUDE (fragment_pager_adapter)

            public SectionsPagerAdapter(FragmentManager fm) {
                super(fm);
            }

            // BEGIN_INCLUDE (fragment_pager_adapter_getitem)
            /**
             * Get fragment corresponding to a specific position. This will be used to populate the
             * contents of the {@link ViewPager}.
             *
             * @param position Position to fetch fragment for.
             * @return Fragment for specified position.
             */
            @Override
            public Fragment getItem(int position) {
                // getItem is called to instantiate the fragment for the given page.
                // Return a DummySectionFragment (defined as a static inner class
                // below) with the page number as its lone argument.
                DummySectionFragment fragment = new DummySectionFragment();
                fragment.setViewPager(mViewPager);
                Bundle args = new Bundle();
                args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
                fragment.setArguments(args);
                return fragment;
            }
            // END_INCLUDE (fragment_pager_adapter_getitem)

            // BEGIN_INCLUDE (fragment_pager_adapter_getcount)
            /**
             * Get number of pages the {@link ViewPager} should render.
             *
             * @return Number of fragments to be rendered as pages.
             */
            @Override
            public int getCount() {
                // Show 3 total pages.
                return 3;
            }
            // END_INCLUDE (fragment_pager_adapter_getcount)

            // BEGIN_INCLUDE (fragment_pager_adapter_getpagetitle)
            /**
             * Get title for each of the pages. This will be displayed on each of the tabs.
             *
             * @param position Page to fetch title for.
             * @return Title for specified page.
             */
            @Override
            public CharSequence getPageTitle(int position) {
                Locale l = Locale.getDefault();
                switch (position) {
                    case 0:
                        return getString(R.string.title_section1).toUpperCase(l);
                    case 1:
                        return getString(R.string.title_section2).toUpperCase(l);
                    case 2:
                        return getString(R.string.title_section3).toUpperCase(l);
                }
                return null;
            }
            // END_INCLUDE (fragment_pager_adapter_getpagetitle)
        }


    /**
     * Update {@link ViewPager} after a tab has been selected in the ActionBar.
     *
     * @param tab Tab that was selected.
     * @param fragmentTransaction A {@link android.app.FragmentTransaction} for queuing fragment operations to
     *                            execute once this method returns. This FragmentTransaction does
     *                            not support being added to the back stack.
     */
    // BEGIN_INCLUDE (on_tab_selected)
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, tell the ViewPager to switch to the corresponding page.
        mViewPager.setCurrentItem(tab.getPosition());
    }
    // END_INCLUDE (on_tab_selected)

    /**
     * Unused. Required for {@link android.app.ActionBar.TabListener}.
     */
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * Unused. Required for {@link android.app.ActionBar.TabListener}.
     */
    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

}
