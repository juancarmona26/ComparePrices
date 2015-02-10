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

import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends ListFragment {

    private static final int REQUEST_CODE_PRODUCT = 0;
    private static final String LOG_TAG = ProductListFragment.class.getSimpleName();
    List<Product> mProducts = new ArrayList<>();
    ArrayAdapter<Product> mArrayAdapter;


    private String [] mWalmartProducts;
    private String [] mWalmartCode;
    private String [] mWalmartPrices;

    private String [] mHBOProducts;
    private String [] mHBOCode;
    private String [] mHBOPrices;

    private String [] m7Elevenroducts;
    private String [] m7ElevenCode;
    private String [] m7ElevenPrices;

    public ProductListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_list, container, false);
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_PRODUCT:
                    addMarketToList(data);
                break;
            }
        }
    }

    private void addMarketToList(Intent data) {
        if(data != null ) {
            Product product = (Product)data.getExtras().getParcelable("product");
            Log.d(LOG_TAG, "si hay data" + product.getName());

            mArrayAdapter.add(product);

        } else Log.d(LOG_TAG, "no hay data");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getArguments() != null) {
            getResourcesXml(getArguments().getInt("marketId"));
        }
        mArrayAdapter = new ProductAdapter(getActivity(), mProducts);
        setListAdapter(mArrayAdapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_product_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handle = false;

        switch (item.getItemId()) {
            case R.id.menu_add_product:
                Intent intent = new Intent(getActivity(), ProductCreationActivity.class);
                startActivityForResult(intent, REQUEST_CODE_PRODUCT);
                handle = true;
                break;
        }

        if(!handle) {
            handle = super.onOptionsItemSelected(item);
        }

        return handle;
    }

    private void getResourcesXml(int id) {

        switch (id) {
            case 0: mWalmartCode = getActivity().getResources().getStringArray(R.array.code_products_walmart);
                    mWalmartPrices = getActivity().getResources().getStringArray(R.array.product_prices_walmart);
                    mWalmartProducts = getActivity().getResources().getStringArray(R.array.products_walmart);
                    prepareProduct(mWalmartCode, mWalmartPrices, mWalmartProducts);


                break;

            case 1: mHBOCode = getActivity().getResources().getStringArray(R.array.code_products_hbo);
                    mHBOPrices = getActivity().getResources().getStringArray(R.array.product_prices_hbo);
                    mHBOProducts = getActivity().getResources().getStringArray(R.array.products_hbo);

                    prepareProduct(mHBOCode, mHBOPrices, mHBOProducts);

                break;
            case 2: m7ElevenCode = getActivity().getResources().getStringArray(R.array.code_products_7eleven);
                    m7ElevenPrices = getActivity().getResources().getStringArray(R.array.product_prices_7eleven);
                    m7Elevenroducts = getActivity().getResources().getStringArray(R.array.products_7eleven);
                prepareProduct(m7ElevenCode, m7ElevenPrices, m7Elevenroducts);
            break;
            default: mProducts = new ArrayList<>();
        }

    }

    private void prepareProduct(String[] productCode, String[] productPrice, String[] productNames) {
        Product product;

        for (int i = 0; i < productNames.length; i++) {
            product = new Product();
            product.setId(i);
            product.setName(productNames[i]);
            product.setPrice(Double.parseDouble(productPrice[i]));
            product.setCode(productCode[i]);
            mProducts.add(product);
        }
    }
}
