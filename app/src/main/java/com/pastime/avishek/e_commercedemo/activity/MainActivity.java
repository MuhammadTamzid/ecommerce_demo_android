package com.pastime.avishek.e_commercedemo.activity;

import android.Manifest;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.pastime.avishek.e_commercedemo.R;
import com.pastime.avishek.e_commercedemo.fragment.DrawerFragment;
import com.pastime.avishek.e_commercedemo.fragment.HomeFragment;
import com.pastime.avishek.e_commercedemo.fragment.ProductFragment;
import com.pastime.avishek.e_commercedemo.interfaces.DrawerSubmenuListener;
import com.pastime.avishek.e_commercedemo.interfaces.FragmentCommunicator;
import com.pastime.avishek.e_commercedemo.model.MovieModel;
import com.pastime.avishek.e_commercedemo.util.ExpandableListDataSource;
import com.pastime.avishek.e_commercedemo.util.T;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static com.pastime.avishek.e_commercedemo.constants.GlobalConstants.KEY_MESSAGE;

@RuntimePermissions
public class MainActivity extends BaseActivity implements DrawerSubmenuListener,
        FragmentCommunicator {

    @BindView(R.id.drawer_layout_main)
    DrawerLayout drawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;
    private ActionBar mActionBar; // might be needed later
    private DrawerFragment mDrawerFragment;
    /**
     * Indicates that app will be closed on next back press
     */
    private boolean isAppReadyToFinish = false;

    private FragmentManager.OnBackStackChangedListener
            mOnBackStackChangedListener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            syncActionBarArrowState();
        }
    };

    private void syncActionBarArrowState() {
        int backStackEntryCount = getFragmentManager().getBackStackEntryCount();
        mDrawerToggle.setDrawerIndicatorEnabled(backStackEntryCount == 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        ButterKnife.bind(this);

        setUpToolbar();

        setUpDrawerFragment();

        setUpDrawer();

        navigateToHomeFragment(new ArrayList<>(ExpandableListDataSource.getData(this)
                .keySet()).get(0));

        MainActivityPermissionsDispatcher.openStorageWithCheck(this);
    }

    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        //toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setDisplayShowHomeEnabled(true);
            mActionBar.setDisplayHomeAsUpEnabled(true);
        } else {
            T.e(new RuntimeException(), "GetSupportActionBar returned null.");
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
                syncActionBarArrowState();
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(mDrawerToggle);
        getFragmentManager().addOnBackStackChangedListener(mOnBackStackChangedListener);
    }

    @Override
    protected void onDestroy() {
        getFragmentManager().removeOnBackStackChangedListener(mOnBackStackChangedListener);
        super.onDestroy();
    }

    private void setUpDrawerFragment() {
        mDrawerFragment = (DrawerFragment) getFragmentManager().findFragmentById(R.id
                .main_navigation_drawer_fragment);
        mDrawerFragment.setUp(this, drawerLayout);
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

        if (id == android.R.id.home &&
                getFragmentManager().popBackStackImmediate()) {
            return true;
        }

        switch (id) {
            case R.id.action_search:
            case R.id.action_cart:
            case R.id.action_settings:
                showToastMessage(R.string.message_future_implementation);
                break;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.isDrawerIndicatorEnabled() && mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSubmenuGroupClicked(int groupPosition, String groupName) {
        mDrawerFragment.getDrawerView().closeDrawer();
        navigateToHomeFragment(groupName);
    }

    @Override
    public void onSubmenuChildClicked(int groupPosition, int childPosition, String childName) {
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
            if (getFragmentManager().getBackStackEntryCount() > 0 || isAppReadyToFinish) {
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode,
                grantResults);
    }

    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission
            .WRITE_EXTERNAL_STORAGE})
    void openStorage() {
        showToastMessage("Permission granted");
    }

    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission
            .WRITE_EXTERNAL_STORAGE})
    void showRationaleForStorage(PermissionRequest request) {
        // NOTE: Show a rationale to explain why the permission is needed, e.g. with a dialog or
        // in this case a snackbar.
        // Call proceed() or cancel() on the provided PermissionRequest to continue or abort
        //showRationaleDialog(R.string.permission_storage_rationale, request);
        showRationaleSnackbar(drawerLayout, R.string.permission_storage_rationale, request);
    }

    @OnPermissionDenied({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission
            .WRITE_EXTERNAL_STORAGE})
    void onStorageDenied() {
        // NOTE: Deal with a denied permission, e.g. by showing specific UI
        // or disabling certain functionality
        showToastMessage(getResources().getString(R.string.permission_denied));
    }

    @OnNeverAskAgain({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission
            .WRITE_EXTERNAL_STORAGE})
    void onStorageNeverAskAgain() {
        showToastMessage(getResources().getString(R.string.permission_never_ask_again));
    }

    @Override
    public void onFragmentResponse(Object object) {
        navigateToProductDetailsFragment((MovieModel) object);
    }

    private void navigateToHomeFragment(String value) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MESSAGE, value);
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        replaceFragment(R.id.main_content_frame, homeFragment, null);
    }

    private void navigateToProductDetailsFragment(MovieModel movieModel) {
        ProductFragment productFragment = new ProductFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(productFragment.getFragmentTag(), movieModel);
        productFragment.setArguments(bundle);
        replaceFragment(R.id.main_content_frame, productFragment, productFragment.getFragmentTag());
    }
}
