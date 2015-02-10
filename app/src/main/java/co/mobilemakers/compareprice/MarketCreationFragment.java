package co.mobilemakers.compareprice;

import android.app.Activity;
import android.content.Intent;
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

import java.util.EnumSet;

public class MarketCreationFragment extends Fragment {

    private EditText mEditTextMarketName;
    private EditText mEditTextMarketLocation;
    private Button mButtonAddMarket;

    private enum FilledFields {
        MarketName,
        MarketLocation,
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
    public MarketCreationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_market_creation, container, false);
        prepareView(rootView);
        setViewEvents();
        mButtonAddMarket.setEnabled(false);
        return rootView;
    }

    private void setViewEvents() {
        mEditTextMarketName.addTextChangedListener(new ContentWatcher(FilledFields.MarketName));
        mEditTextMarketLocation.addTextChangedListener(new ContentWatcher(FilledFields.MarketLocation));
        mButtonAddMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity();
                Market market = new Market();
                market.setName(mEditTextMarketName.getText().toString());
                market.setLocation(mEditTextMarketLocation.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("market", market);
                activity.setResult(Activity.RESULT_OK, intent);
                activity.finish();

            }
        });
    }

    private void prepareView(View rootView) {
        mEditTextMarketName = (EditText) rootView.findViewById(R.id.edit_text_market_name);
        mEditTextMarketLocation =  (EditText) rootView.findViewById(R.id.edit_text_market_location);
        mButtonAddMarket = (Button) rootView.findViewById(R.id.button_add_market);
    }

    private void changeButtonStatus () {
        if (mFilledFieldsEnumSet.containsAll(EnumSet.allOf(FilledFields.class))) {
            mButtonAddMarket.setEnabled(true);
        } else {
            mButtonAddMarket.setEnabled(false);
        }
    }

}
