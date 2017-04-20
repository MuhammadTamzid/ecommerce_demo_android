package com.pastime.avishek.e_commercedemo.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;

import com.pastime.avishek.e_commercedemo.R;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends BaseActivity {

    @BindView(R.id.drawer_layout_main)
    DrawerLayout drawerLayout;

   /* @BindView(R.id.drawer_list)
    ExpandableListView expandableListView;*/

    private String[] mDrawerItems;
    private List<String> mExpandableListTitles;
    private Map<String, List<String>> mExpandableListData;
    private ExpandableListAdapter mExpandableListAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        ButterKnife.bind(this);
        setUpToolbar();

        //mActivityTitle = getTitle().toString();

//        initItems();
//        getData();
//        addDrawerItems();
        setUpDrawer();
    }

    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeButtonEnabled(true);
        } else {
            Timber.e(new RuntimeException(), "GetSupportActionBar returned null.");
        }
    }

    private void setUpDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R
                .string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*private void addDrawerItems() {
        mExpandableListAdapter = new CustomExpandableListAdapter(this, mExpandableListTitles,
                mExpandableListData);
        expandableListView.setAdapter(mExpandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                getSupportActionBar().setTitle(mExpandableListTitles.get(groupPosition).toString());
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                getSupportActionBar().setTitle(R.string.film_genres);
            }
        });

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int
                    groupPosition, long id) {
                if (mExpandableListData.get(mExpandableListTitles.get(groupPosition)) != null &&
                        mExpandableListData.get(mExpandableListTitles.get(groupPosition)).size()
                                == 0) {
                    expandableListView.collapseGroup(groupPosition);
                }
                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                String selectedItem = ((List) (mExpandableListData.get(mExpandableListTitles.get
                        (groupPosition))))
                        .get(childPosition).toString();
                getSupportActionBar().setTitle(selectedItem);

                *//*if (mDrawerItems[0].equals(mExpandableListTitles.get(groupPosition))) {
                    mNavigationManager.showFragmentAction(selectedItem);
                } else if (items[1].equals(mExpandableListTitles.get(groupPosition))) {
                    mNavigationManager.showFragmentComedy(selectedItem);
                } else if (items[2].equals(mExpandableListTitles.get(groupPosition))) {
                    mNavigationManager.showFragmentDrama(selectedItem);
                } else if (items[3].equals(mExpandableListTitles.get(groupPosition))) {
                    mNavigationManager.showFragmentMusical(selectedItem);
                } else if (items[4].equals(mExpandableListTitles.get(groupPosition))) {
                    mNavigationManager.showFragmentThriller(selectedItem);
                } else {
                    throw new IllegalArgumentException("Not supported fragment type");
                }*//*

                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    private void initItems() {
        mDrawerItems = getResources().getStringArray(R.array.film_genre);
    }

    public void getData() {
        mExpandableListData = ExpandableListDataSource.getData(this);
        mExpandableListTitles = new ArrayList<>(mExpandableListData.keySet());
    }*/
}
