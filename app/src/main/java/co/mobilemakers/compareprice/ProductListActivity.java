package co.mobilemakers.compareprice;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class ProductListActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        if (savedInstanceState == null) {
            Bundle bundle = null;
            if(getIntent() != null ) {
                bundle = new Bundle();
                bundle.putInt("marketId",getIntent().getExtras().getInt("marketId"));
            }

            ProductListFragment productListFragment = new ProductListFragment();
            productListFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, productListFragment)
                    .commit();
        }
    }

}
