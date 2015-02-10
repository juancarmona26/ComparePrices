package co.mobilemakers.compareprice;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class MarketCreationActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_creation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new MarketCreationFragment()).commit();
    }
}
