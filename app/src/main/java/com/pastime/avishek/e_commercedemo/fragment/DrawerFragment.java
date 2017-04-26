package com.pastime.avishek.e_commercedemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pastime.avishek.e_commercedemo.R;
import com.pastime.avishek.e_commercedemo.adapter.DrawerCategoryRecyclerAdapter;
import com.pastime.avishek.e_commercedemo.adapter.DrawerSubcategoryExpandableListAdapter;
import com.pastime.avishek.e_commercedemo.interfaces.DrawerMenuListener;
import com.pastime.avishek.e_commercedemo.interfaces.DrawerSubmenuListener;
import com.pastime.avishek.e_commercedemo.interfaces.DrawerView;
import com.pastime.avishek.e_commercedemo.util.ExpandableListDataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.pastime.avishek.e_commercedemo.R.id.text_drawer_submenu_title;

/**
 * Created by Avishek on 4/19/17.
 */

public class DrawerFragment extends BaseFragment implements View.OnClickListener,
        DrawerSubmenuListener, DrawerView {

    @BindView(R.id.recycler_drawer_category)
    RecyclerView recyclerViewDrawerMenu;
    @BindView(R.id.drawer_submenu_layout)
    LinearLayout layoutDrawerSubmenu;
    @BindView(R.id.list_drawer_submenu)
    ExpandableListView expandableListView;
    @BindView(R.id.button_drawer_submenu_back)
    Button buttonSubmenuBack;
    @BindView(text_drawer_submenu_title)
    TextView textViewDrawerSubmenuTitle;
    @BindView(R.id.relative_progress)
    RelativeLayout relativeLayoutProgress;
    @BindView(R.id.relative_retry)
    RelativeLayout relativeLayoutRetry;
    @BindView(R.id.button_retry)
    Button buttonRetry;


    private List<String> mExpandableListTitles;
    private Map<String, List<String>> mExpandableListData;
    private ExpandableListAdapter mExpandableListAdapter;

    private ArrayList<String> mCategories;
    private DrawerSubmenuListener mDrawerListener;
    private DrawerLayout mDrawerLayout;

    /**
     * Sets up DrawerFragment
     *
     * @param listener {@link DrawerSubmenuListener} instance for callback to the calling Activity
     */
    public void setUp(DrawerSubmenuListener listener, DrawerLayout drawerLayout) {
        mDrawerListener = listener;
        mDrawerLayout = drawerLayout;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_drawer, container, false);
        ButterKnife.bind(this, layout);
        init();
        return layout;
    }

    /**
     * Initializes necessary components, gets data for recyclerViewDrawerMenu and sets adapter
     */
    private void init() {
        mCategories = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array
                .film_genre)));
        mCategories.addAll(Arrays.asList(getResources().getStringArray(R.array.film_genre)));
        mCategories.addAll(Arrays.asList(getResources().getStringArray(R.array.film_genre)));
        mCategories.addAll(Arrays.asList(getResources().getStringArray(R.array.film_genre)));
        DrawerCategoryRecyclerAdapter adapter = new DrawerCategoryRecyclerAdapter(getActivity(),
                mCategories, new DrawerMenuListener() {
            @Override
            public void onDrawerItemSelected(int position) {
                showSubMenu(position);
            }
        });
        recyclerViewDrawerMenu.setAdapter(adapter);
        recyclerViewDrawerMenu.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    /**
     * Shows demo data on the submenu's expandable listView. Needs to be removed when main
     * project is implemented.
     *
     * @param position an integer representing the position of the recyclerView item clicked
     */
    private void showSubMenu(int position) {
        buttonSubmenuBack.setOnClickListener(this);
        textViewDrawerSubmenuTitle.setText(mCategories.get(position));
        getDrawerSubmenuData();
        addDrawerSubmenuItems();
        showSubmenu();
    }

    /**
     * Adds subcategory items of drawer menu.
     */
    private void addDrawerSubmenuItems() {
        mExpandableListAdapter = new DrawerSubcategoryExpandableListAdapter(getActivity(),
                mExpandableListTitles,
                mExpandableListData,
                this);
        expandableListView.setAdapter(mExpandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                // TODO: by April, 2017
                //getSupportActionBar().setTitle(mExpandableListTitles.get(groupPosition)
                // .toString());
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView
                .OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                // TODO: by April, 2017
                //getSupportActionBar().setTitle(R.string.film_genres);
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
    }

    /**
     * Gets subcategory data
     * <p>
     * This data will be obtained from rest API in the main project
     * </p>
     */
    public void getDrawerSubmenuData() {
        mExpandableListData = ExpandableListDataSource.getData(getActivity());
        mExpandableListTitles = new ArrayList<>(mExpandableListData.keySet());
    }

    /**
     * Shows submenu of drawer with animation
     */
    private void animateSubListShow() {
        Animation slideInDisappear = AnimationUtils.loadAnimation(getActivity(), R.anim
                .slide_in_disappear);
        final Animation slideInAppear = AnimationUtils.loadAnimation(getActivity(), R.anim
                .slide_in_appear);
        slideInDisappear.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                layoutDrawerSubmenu.setVisibility(View.VISIBLE);
                layoutDrawerSubmenu.startAnimation(slideInAppear);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                recyclerViewDrawerMenu.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        recyclerViewDrawerMenu.startAnimation(slideInDisappear);
    }

    /**
     * Hides submenu of drawer with animation
     */
    private void animateSubListHide() {
        Animation slideAwayDisappear = AnimationUtils.loadAnimation(getActivity(), R.anim
                .slide_away_disappear);
        final Animation slideAwayAppear = AnimationUtils.loadAnimation(getActivity(), R.anim
                .slide_away_appear);
        slideAwayDisappear.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                recyclerViewDrawerMenu.setVisibility(View.VISIBLE);
                recyclerViewDrawerMenu.startAnimation(slideAwayAppear);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                layoutDrawerSubmenu.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        layoutDrawerSubmenu.startAnimation(slideAwayDisappear);
    }

    public DrawerView getDrawerView() {
        return this;
    }

    /**
     * Check if drawer is open. If so close it.
     *
     * @return false if drawer was already closed
     */
    public boolean onBackHide() {
        if (mDrawerLayout != null && mDrawerLayout.isDrawerVisible(GravityCompat.START)) {
            if (layoutDrawerSubmenu.getVisibility() == View.VISIBLE)
                this.hideSubmenu();
            else
                this.closeDrawer();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_drawer_submenu_back:
                animateSubListHide();
            default:
                break;
        }
    }

    @Override
    public void onSubmenuGroupClicked(int groupPosition, String groupName) {
        mDrawerListener.onSubmenuGroupClicked(groupPosition, groupName);
    }

    @Override
    public void onSubmenuChildClicked(int groupPosition, int childPosition, String childName) {
        mDrawerListener.onSubmenuChildClicked(groupPosition, childPosition, childName);
    }

    @Override
    public void openDrawer() {

    }

    @Override
    public void closeDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void showSubmenu() {
        animateSubListShow();
    }

    @Override
    public void hideSubmenu() {
        animateSubListHide();
    }

    @Override
    public void showLoading() {
        this.relativeLayoutProgress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        this.relativeLayoutProgress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showRetry() {
        this.relativeLayoutRetry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        this.relativeLayoutRetry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mDrawerListener = null;
    }
}
