package com.pastime.avishek.e_commercedemo.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pastime.avishek.e_commercedemo.R;
import com.pastime.avishek.e_commercedemo.interfaces.DrawerSubmenuListener;

import java.util.List;
import java.util.Map;

/**
 * Created by Avishek on 4/18/17.
 */

public class DrawerSubcategoryExpandableListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<String> mExpandableListTitle;
    private Map<String, List<String>> mExpandableListDetail;
    private LayoutInflater mLayoutInflater;
    private DrawerSubmenuListener mDrawerSubmenuListener;

    public DrawerSubcategoryExpandableListAdapter(Context context, List<String> expandableListTitle,
                                                  Map<String, List<String>> expandableListDetail,
                                                  DrawerSubmenuListener drawerSubmenuListener) {
        mContext = context;
        mExpandableListTitle = expandableListTitle;
        mExpandableListDetail = expandableListDetail;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        mDrawerSubmenuListener = drawerSubmenuListener;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return mExpandableListDetail.get(mExpandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(final int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerSubmenuListener.onSubmenuChildClicked(listPosition, expandedListPosition,
                        (String) getChild(listPosition, expandedListPosition));
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return mExpandableListDetail.get(mExpandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return mExpandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return mExpandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(final int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        ImageView imageIndicator = (ImageView) convertView.findViewById(R.id
                .image_drawer_subcategory_indicator);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        if (isExpanded) {
            imageIndicator.setImageResource(android.R.drawable.arrow_up_float);
        } else {
            imageIndicator.setImageResource(android.R.drawable.arrow_down_float);
        }
        if (getChildrenCount(listPosition) == 0) {
            imageIndicator.setVisibility(View.GONE);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDrawerSubmenuListener.onSubmenuGroupClicked(listPosition, (String) getGroup
                            (listPosition));
                }
            });
        }
        return convertView;

        // TODO:
        /*String listTitle = (String) getGroup(listPosition);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder =  new ViewHolder(mLayoutInflater.inflate(R.layout.list_group,
                    null));
            convertView = mLayoutInflater.inflate(R.layout.list_group, null);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.listTitleTextView.setTypeface(null, Typeface.BOLD);
        viewHolder.listTitleTextView.setText(listTitle);
        if (isExpanded) {
            viewHolder.imageIndicator.setImageResource(android.R.drawable.arrow_up_float);
        } else {
            viewHolder.imageIndicator.setImageResource(android.R.drawable.arrow_down_float);
        }
        if (getChildrenCount(listPosition) == 0) {
            viewHolder.imageIndicator.setVisibility(View.GONE);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDrawerSubmenuListener.onSubmenuGroupClicked(listPosition, (String) getGroup
                            (listPosition));
                }
            });
        }
        return convertView;*/
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
