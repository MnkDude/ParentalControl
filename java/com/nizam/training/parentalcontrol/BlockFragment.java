package com.nizam.training.parentalcontrol;

import android.os.Bundle;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;

public class BlockFragment extends PreferenceFragmentCompat {
    public BlockFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        setPreferencesFromResource(R.xml.activity_setting, s);
        final ListPreference listPreference = (ListPreference) findPreference("lang_select");
        final ListPreference listPreference1 = (ListPreference) findPreference("block_method");
        final String summ = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("lang_select", "en");
        String summm = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("block_method", "0");
        listPreference1.setSummary(listPreference1.getEntries()[listPreference1.findIndexOfValue(summm)]);
        listPreference.setSummary(listPreference.getEntries()[listPreference.findIndexOfValue(summ)]);
//        Toast.makeText(getContext(), "called1", Toast.LENGTH_SHORT).show();
        /*listPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(getContext(), "called2", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                listPreference.setSummary(listPreference.getEntries()[listPreference.findIndexOfValue(o.toString())]);
                PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("lang_select", o.toString()).apply();
                return false;
            }
        });
        listPreference1.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                listPreference1.setSummary(listPreference1.getEntries()[listPreference1.findIndexOfValue(o.toString())]);
                PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("block_method", o.toString()).apply();
                return false;
            }
        });*/
    }
}