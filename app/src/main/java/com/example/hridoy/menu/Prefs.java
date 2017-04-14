package com.example.hridoy.menu;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Hridoy on 10-07-16.
 */
public class Prefs extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);

    }
}
