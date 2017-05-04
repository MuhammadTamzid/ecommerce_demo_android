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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Avishek on 4/18/17.
 */

public class DrawerSubcategoryExpandableListAdapter extends BaseExpandableListAdapter {
    private List<String> mExpandableListTitle;
    private Map<String, List<String>> mExpandableListDetail;
    private LayoutInflater mLayoutInflater;
    private DrawerSubmenuListener mDrawerSubmenuListener;

    public DrawerSubcategoryExpandableListAdapter(Context context, List<String> expandableListTitle,
                                                  Map<String, List<String>> expandableListDetail,
                                                  DrawerSubmenuListener drawerSubmenuListener) {
        mExpandableListTitle = expandableListTitle;
        mExpandableListDetail = expandableListDetail;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context
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
        ChildViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item, null);
            viewHolder = new ChildViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }
        viewHolder.expandedListTextView.setText(expandedListText);
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
        GroupViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_group, parent, false);
            viewHolder = new GroupViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GroupViewHolder) convertView.getTag();
        }
        viewHolder.listTitleTextView.setTypeface(null, Typeface.BOLD);
        viewHolder.listTitleTextView.setText((String) getGroup(listPosition));
        if (isExpanded) {
            viewHolder.imageIndicator.setImageResource(R.drawable.arrow_up_black);
        } else {
            viewHolder.imageIndicator.setImageResource(R.drawable.arrow_down_black);
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
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

    public class GroupViewHolder {

        @BindView(R.id.listTitle)
        TextView listTitleTextView;
        @BindView(R.id.image_drawer_subcategory_indicator)
        ImageView imageIndicator;

        public GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public class ChildViewHolder {
        @BindView(R.id.expandedListItem)
        TextView expandedListTextView;

        public ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
