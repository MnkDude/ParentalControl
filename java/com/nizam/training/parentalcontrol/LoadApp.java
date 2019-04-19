//LoadApp.java
package com.nizam.training.parentalcontrol;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LoadApp extends AsyncTask<Void, Void, ListAppAdapter> {
    private RecyclerView rv;
    private Context ct;

    LoadApp(RecyclerView rv, Context ct) {
        super();
        this.rv = rv;
        this.ct = ct;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ListAppAdapter listAppAdapter) {
        super.onPostExecute(listAppAdapter);
        listAppAdapter.notifyDataSetChanged();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ct);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setAdapter(listAppAdapter);
        rv.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected ListAppAdapter doInBackground(Void... voids) {
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<Drawable> drawables = new ArrayList<>();
        ArrayList<String> packages = new ArrayList<>();
        int i = 0;
        List<ApplicationInfo> apps = ct.getPackageManager().getInstalledApplications(0);
        int j = 0;
        for (ApplicationInfo app : apps) {
            if (ct.getPackageManager().getLaunchIntentForPackage(app.packageName) != null && !app.packageName.contains("com.nizam.training.parentalcontrol")) {
                strings.add(i, ct.getPackageManager().getApplicationLabel(app).toString());
                drawables.add(i, ct.getPackageManager().getApplicationIcon(app));
                packages.add(app.packageName);
                // new ListAppAdapter(ct,strings,drawables,packages);
                i++;
            }
        }
        return new ListAppAdapter(ct, strings, drawables, packages);
    }
}