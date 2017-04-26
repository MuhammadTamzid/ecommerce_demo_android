package com.pastime.avishek.e_commercedemo.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.pastime.avishek.e_commercedemo.HomeFragment;
import com.pastime.avishek.e_commercedemo.R;
import com.pastime.avishek.e_commercedemo.fragment.DrawerFragment;
import com.pastime.avishek.e_commercedemo.interfaces.DrawerSubmenuListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.pastime.avishek.e_commercedemo.constants.GlobalConstants.*;

public class MainActivity extends BaseActivity implements DrawerSubmenuListener {


    @BindView(R.id.drawer_layout_main)
    DrawerLayout drawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;
    private ActionBar mActionBar; // might be needed later
    private DrawerFragment mDrawerFragment;
    /**
     * Indicates that app will be closed on next back press
     */
    private boolean isAppReadyToFinish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        ButterKnife.bind(this);

        setUpToolbar();

        setUpDrawerFragment();

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

    private void setUpDrawerFragment() {
        mDrawerFragment = (DrawerFragment) getFragmentManager().findFragmentById(R.id
                .main_navigation_drawer_fragment);
        mDrawerFragment.setUp(this, drawerLayout);
    }

    private void navigateToHomeFragment(String value){
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_MESSAGE, value);
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        replaceFragment(R.id.main_content_frame, homeFragment, null);
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

    @Override
    public void onSubmenuGroupClicked(int groupPosition, String groupName) {
        showToastMessage(groupName);
        mDrawerFragment.getDrawerView().closeDrawer();
        navigateToHomeFragment(groupName);
    }

    @Override
    public void onSubmenuChildClicked(int groupPosition, int childPosition, String childName) {
        showToastMessage(childName);
        mDrawerFragment.getDrawerView().closeDrawer();
        navigateToHomeFragment(childName);
    }

    @Override
    public void onBackPressed() {
        // If back button pressed, try close drawer if exist and is open. If drawer is already
        // closed continue.
        if (mDrawerFragment == null || !mDrawerFragment.onBackHide()) {
            // If app should be finished or some fragment transaction still remains on backStack,
            // let the system do the job.
            if (getSupportFragmentManager().getBackStackEntryCount() > 0 || isAppReadyToFinish) {
                super.onBackPressed();
            } else {
                // BackStack is empty. For closing the app user have to tap the back button two
                // times in two seconds.
                showToastMessage(getString(R.string.Another_click_for_leaving_app));
                isAppReadyToFinish = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isAppReadyToFinish = false;
                    }
                }, 2000);
            }
        }
    }
}
