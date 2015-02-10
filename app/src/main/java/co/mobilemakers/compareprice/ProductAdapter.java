package co.mobilemakers.compareprice;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by USER on 08/02/2015.
 */
public class ProductAdapter extends ArrayAdapter<Product>{

    private static final String LOG_TAG = ProductAdapter.class.getSimpleName();
    Context mContext;
    List<Product> products;


    public ProductAdapter(Context context, List<Product> products) {
        super(context, R.layout.product_list_entry, products);
        mContext = context;
        this.products = products;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = prepareRowView(position, convertView, parent);
        return rowView;
    }

    private View prepareRowView(int position, View convertView, ViewGroup parent) {
        View rowView;
        if(convertView != null) {
            rowView = convertView;
        } else {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.product_list_entry, parent, false);
        }

        if(rowView != null) {
            showProduct(position, rowView);
        }
        return rowView;
    }

    private void showProduct(int position, View rowView) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        Log.d(LOG_TAG, "que pex: " + products.get(position).getName());
        TextView textViewProductName = (TextView) rowView.findViewById(R.id.text_view_product_name);
        TextView textViewProductPrice = (TextView) rowView.findViewById(R.id.text_view_product_price);
        TextView textViewProductCode = (TextView) rowView.findViewById(R.id.text_view_product_code);
        textViewProductName.setText(products.get(position).getName());
        textViewProductPrice.setText(String.valueOf("Price: " + decimalFormat.format(products.get(position).getPrice())));
        textViewProductCode.setText(products.get(position).getCode());
    }
}
