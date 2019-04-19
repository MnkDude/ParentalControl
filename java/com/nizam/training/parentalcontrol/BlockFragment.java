package com.nizam.training.parentalcontrol;


import android.os.Bundle;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
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
        final String summ = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("lang_select", "en");
        listPreference.setSummary(listPreference.getEntries()[listPreference.findIndexOfValue(summ)]);
        listPreference.setDefaultValue(summ);
        listPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                //listPreference.setDefaultValue(summ);
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
    }
}