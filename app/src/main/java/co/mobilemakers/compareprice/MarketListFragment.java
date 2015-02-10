package co.mobilemakers.compareprice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MarketListFragment extends ListFragment {

    private static final String LOG_TAG= MarketListFragment.class.getSimpleName();
    public static final int REQUEST_CODE_CREATE_MARKET = 0 ;
    private ArrayAdapter<Market> mArrayAdapter;

    private static final String [] LOCATIONS = {"North","West","South"};

    private String [] mResourceMarkets;

    public MarketListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_market_list, container, false);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_market_list, menu);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_CREATE_MARKET:
                    if(resultCode == Activity.RESULT_OK) {
                        Log.d(LOG_TAG, "ResultOk");
                        addMarketToList(data);
                    }
            break;
        }
    }

    private void addMarketToList(Intent data) {
        if(data != null ) {
            Market market = (Market)data.getExtras().getParcelable("market");
            Log.d(LOG_TAG, "si hay data" + market.getLocation());

            mArrayAdapter.add((Market)data.getExtras().getParcelable("market"));

        } else Log.d(LOG_TAG, "no hay data");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prepareListView();
    }

    private void prepareListView() {
        List<Market> markets = getDefaultMarkets();
        mArrayAdapter  = new MarketAdapter(getActivity(), markets);
        setListAdapter(mArrayAdapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Market market = (Market) parent.getItemAtPosition(position);

                Toast.makeText(getActivity(), "Market: " + market.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<Market> getDefaultMarkets() {
        Market market;
        List<Market> markets = new ArrayList<>();
        mResourceMarkets = getActivity().getResources().getStringArray(R.array.market);
        for(int i = 0; i < mResourceMarkets.length; i++) {
            market = new Market();
            market.setId(i);
            market.setLocation(LOCATIONS[i]);
            market.setName(mResourceMarkets[i]);
            markets.add(market);
        }
        return markets;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handle = false;

        switch (item.getItemId()) {
            case R.id.menu_add_market:

                Intent intent = new Intent(getActivity(), MarketCreationActivity.class);
                startActivityForResult(intent, REQUEST_CODE_CREATE_MARKET);

                handle = true;
            break;

            case R.id.action_settings:
                handle = true;

            break;
        }
        if(!handle) {
            handle = super.onOptionsItemSelected(item);
        }
        return handle;
    }
}
