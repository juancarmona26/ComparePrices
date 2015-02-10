package co.mobilemakers.compareprice;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class AppSettingsActivity extends Activity {
    public static class AppSettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);

        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getFragmentManager().beginTransaction().
            add(R.id.container_settings, new AppSettingsFragment()).commit();
    }
}
