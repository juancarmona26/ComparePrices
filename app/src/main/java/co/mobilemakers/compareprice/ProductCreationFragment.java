package co.mobilemakers.compareprice;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.util.EnumSet;

public class ProductCreationFragment extends Fragment {
    private EditText mEditTextProductName;
    private EditText mEditTextProductPrice;
    private EditText mEditTextProductCode;
    private Button mButtonAddProduct;

    private enum FilledFields {
        ProductName,
        ProductPrice,
        ProductCode
    }

    EnumSet<FilledFields> mFilledFieldsEnumSet = EnumSet.noneOf(FilledFields.class);

    public class ContentWatcher implements TextWatcher {
        FilledFields mField;

        public ContentWatcher(FilledFields field) {
            mField = field;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (TextUtils.isEmpty(s)) {
                mFilledFieldsEnumSet.remove(mField);
            } else {
                mFilledFieldsEnumSet.add(mField);
            }
            changeButtonStatus();
        }
    }


    public ProductCreationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_creation, container, false);
        prepareViews(rootView);

        mEditTextProductName.addTextChangedListener(new ContentWatcher(FilledFields.ProductName));
        mEditTextProductPrice.addTextChangedListener(new ContentWatcher(FilledFields.ProductPrice));
        mEditTextProductCode.addTextChangedListener(new ContentWatcher(FilledFields.ProductCode));
        mButtonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double price = !mEditTextProductPrice.getText().toString().equals("") ? Double.valueOf(mEditTextProductPrice.getText().toString()) : 0.0;


                Activity activity = getActivity();
                Product product = new Product();
                product.setName(mEditTextProductName.getText().toString());
                product.setPrice(price);
                product.setCode(mEditTextProductCode.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("product", product);
                activity.setResult(Activity.RESULT_OK, intent);
                activity.finish();

            }
        });

        return rootView;
    }

    private void prepareViews(View rootView) {
        mEditTextProductName = (EditText) rootView.findViewById(R.id.edit_text_product_name);
        mEditTextProductPrice = (EditText) rootView.findViewById(R.id.edit_text_product_precio);
        mEditTextProductCode = (EditText) rootView.findViewById(R.id.edit_text_product_code);
        mButtonAddProduct = (Button) rootView.findViewById(R.id.button_add_product);
    }

    private void changeButtonStatus () {
        if (mFilledFieldsEnumSet.containsAll(EnumSet.allOf(FilledFields.class))) {
            mButtonAddProduct.setEnabled(true);
        } else {
            mButtonAddProduct.setEnabled(false);
        }
    }

 }
