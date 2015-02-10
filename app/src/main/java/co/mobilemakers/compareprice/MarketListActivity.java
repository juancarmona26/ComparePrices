package co.mobilemakers.compareprice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MarketListActivity extends ActionBarActivity {

    private static final int RESULT_SETTINGS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_list);
        customizeActionBar();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment, new MarketListFragment())
                    .commit();
        }
    }

    private void customizeActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.action_bar_title);
        actionBar.setIcon(R.drawable.android_market_materialiced);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                Intent intent = new Intent(this, AppSettingsActivity.class);
                startActivityForResult(intent, RESULT_SETTINGS);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
