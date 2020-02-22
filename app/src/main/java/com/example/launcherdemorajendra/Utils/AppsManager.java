package com.example.launcherdemorajendra.Utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import com.example.launcherdemorajendra.Models.AppInfo;
import com.example.launcherdemorajendra.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.core.content.ContextCompat;

public class AppsManager {
    private Context mContext;
    private AppInfo appInfo;
    private ArrayList<AppInfo> myApps;

    public AppsManager(Context c) {
        mContext = c;
        myApps = new ArrayList<AppInfo>();
    }

    public ArrayList<AppInfo> getApps() {
        //loadApps();
        getInstalledApps();
        return myApps;
    }


    private ArrayList<AppInfo> getInstalledApps() {
        List<PackageInfo> packages = mContext.getPackageManager().getInstalledPackages(0);
            for (int i = 0; i < packages.size(); i++) {
                    PackageInfo p = packages.get(i);
                    if ((isSystemPackage(p) == false)) {
                        AppInfo newApp = new AppInfo();
                        newApp.setAppName(getApplicationLabelByPackageName(p.packageName));
                        newApp.setAppPackage(p.packageName);
                        newApp.setAppIcon(getAppIconByPackageName(p.packageName));
                        myApps.add(newApp);
                    }
        }
        return myApps;
    }
    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true : false;
    }
    // Custom method to get application icon by package name
    private Drawable getAppIconByPackageName(String packageName) {
        Drawable icon;
        try {
            icon = mContext.getPackageManager().getApplicationIcon(packageName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            // Get a default icon
            icon = ContextCompat.getDrawable(mContext, R.drawable.ic_launcher_background);
        }
        return icon;
    }

    // Custom method to get application label by package name
    private String getApplicationLabelByPackageName(String packageName) {
        PackageManager packageManager = mContext.getPackageManager();
        ApplicationInfo applicationInfo;
        String label = "Unknown";
        try {
            applicationInfo = packageManager.getApplicationInfo(packageName, 0);
            if (applicationInfo != null) {
                label = (String) packageManager.getApplicationLabel(applicationInfo);
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return label;
    }
}
