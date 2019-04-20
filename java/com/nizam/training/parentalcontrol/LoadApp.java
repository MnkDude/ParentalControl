//LoadApp.java
package com.nizam.training.parentalcontrol;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LoadApp extends AsyncTask<Void, Integer, ListAppAdapter> {
    private RecyclerView rv;
    private Context ct;
    private ProgressDialog progressBar;

    LoadApp(RecyclerView rv, Context ct, ProgressDialog progressBar) {
        super();
        this.rv = rv;
        this.ct = ct;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBar.show();
    }

    @Override
    protected void onPostExecute(ListAppAdapter listAppAdapter) {
        super.onPostExecute(listAppAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ct);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listAppAdapter.notifyDataSetChanged();
        rv.setAdapter(listAppAdapter);
        rv.setLayoutManager(linearLayoutManager);
        progressBar.dismiss();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
    }

    @Override
    protected ListAppAdapter doInBackground(Void... voids) {
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<Drawable> drawables = new ArrayList<>();
        ArrayList<String> packages = new ArrayList<>();
        int i = 0, j = 0;
        List<ApplicationInfo> apps = ct.getPackageManager().getInstalledApplications(0);
        progressBar.setMax(apps.size());
        for (ApplicationInfo app : apps) {
            if (ct.getPackageManager().getLaunchIntentForPackage(app.packageName) != null && !app.packageName.contains("com.nizam.training.parentalcontrol")) {
                strings.add(i, ct.getPackageManager().getApplicationLabel(app).toString());
                drawables.add(i, ct.getPackageManager().getApplicationIcon(app));
                packages.add(app.packageName);

                //
                i++;
            }
            publishProgress(j++);
        }
        return new ListAppAdapter(ct, strings, drawables, packages);
    }
}