package com.example.launcherdemorajendra.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.launcherdemorajendra.Models.AppInfo;
import com.example.launcherdemorajendra.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class InstalledAppsAdapter extends RecyclerView.Adapter<InstalledAppsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<AppInfo> mDataSet;

    public InstalledAppsAdapter(Context context, ArrayList<AppInfo> list) {
        mContext = context;
        mDataSet = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewLabel;
        public ImageView mImageViewIcon;
        public RelativeLayout mItem;

        public ViewHolder(View v) {
            super(v);
            // Get the widgets reference from custom layout
            mTextViewLabel = (TextView) v.findViewById(R.id.Apk_Name);
            mImageViewIcon = (ImageView) v.findViewById(R.id.packageImage);
            mItem = (RelativeLayout) v.findViewById(R.id.item);
        }

    }

    @Override
    public InstalledAppsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.app_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        // Get the current package name
        final String packageName = mDataSet.get(position).getAppPackage();

        // Get the current app icon
        Drawable icon = mDataSet.get(position).getAppIcon();

        // Get the current app label
        String label = mDataSet.get(position).getAppName();

        // Set the current app label
        holder.mTextViewLabel.setText(label);

        // Set the current app icon
        holder.mImageViewIcon.setImageDrawable(icon);


        holder.mItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = mContext.getPackageManager().getLaunchIntentForPackage(mDataSet.get(position).getAppPackage());
                mContext.startActivity(launchIntent);
            }
        });

    }
    @Override
    public int getItemCount() {
        // Count the installed apps
        return mDataSet.size();
    }

}

