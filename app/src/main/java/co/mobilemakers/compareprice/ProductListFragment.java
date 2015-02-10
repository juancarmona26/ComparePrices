package co.mobilemakers.compareprice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
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

    List<Product> mProducts;
    ArrayAdapter<Product> mArrayAdapater;


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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getArguments() != null) {
            getResourcesXml(getArguments().getInt("marketId"));
        }
        mArrayAdapater = new ProductAdapter(getActivity(), mProducts);
        setListAdapter(mArrayAdapater);
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
        mProducts = new ArrayList<>();
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
