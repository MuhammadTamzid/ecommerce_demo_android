package com.pastime.avishek.e_commercedemo.interfaces;

/**
 * Created by Avishek on 4/21/17.
 */

public interface DrawerSubmenuListener {
    /**
     * Called when the 'group' of submenu expandable list is clicked
     */
    void onSubmenuGroupClicked(int groupPosition, String groupName);

    /**
     * Called when the 'child' of submenu expandable list is clicked
     */
    void onSubmenuChildClicked(int groupPosition, int childPosition, String childName);
}
