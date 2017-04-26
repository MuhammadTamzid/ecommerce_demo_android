package com.pastime.avishek.e_commercedemo.interfaces;

/**
 * Created by Avishek on 4/24/17.
 */

public interface DrawerView extends LoadDataView {
    /**
     * Called when drawer needs to be shown
     */
    void openDrawer();

    /**
     * Called when drawer needs to be closed
     */
    void closeDrawer();

    /**
     * Called when submenu of the drawer needs to be shown
     */
    void showSubmenu();

    /**
     * Called when submenu of the drawer needs to be hidden
     */
    void hideSubmenu();
}
