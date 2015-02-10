package co.mobilemakers.compareprice;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MarketAdapter extends ArrayAdapter<Market> {

    private static final String LOG_TAG = MarketAdapter.class.getSimpleName();
    Context mContext;
    List<Market> mMarkets;

    public MarketAdapter(Context context, List<Market> markets) {
        super(context, R.layout.market_list_entry, markets);
        mContext = context;
        this.mMarkets = markets;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        if(convertView != null) {
            rowView = convertView;
        } else {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.market_list_entry, parent, false);
        }

        if(rowView != null ) {
            TextView textViewMarketName = (TextView) rowView.findViewById(R.id.text_view_market_name);
            TextView textViewMarketLocation = (TextView) rowView.findViewById(R.id.text_view_market_location);
            Log.d(LOG_TAG, "nombre market: " + mMarkets.get(position).getName() );
            Log.d(LOG_TAG, "nombre market: " + mMarkets.get(position).getLocation());
            textViewMarketName.setText(mMarkets.get(position).getName());
            textViewMarketLocation.setText(mMarkets.get(position).getLocation());
        }

        return rowView;
    }
}
