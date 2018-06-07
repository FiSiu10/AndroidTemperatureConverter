package com.example.fionasiu.temperatureconverter;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.fionasiu.temperatureconverter.Converters;

import static com.example.fionasiu.temperatureconverter.Converters.leftConversion;
import static com.example.fionasiu.temperatureconverter.Converters.rightConversion;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment implements View.OnClickListener{
    Button leftButton;
    Button rightButton;

    /**
     * The fragment argument representing the item ID that this fragment represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The converter content this fragment is presenting.
     */
    private Converters.ConverterItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            Object o = getArguments().getString(ARG_ITEM_ID);
            mItem = Converters.ITEM_MAP.get(o);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        // Show the converter content as text in a TextView.
        if (mItem != null) {
            leftButton = (Button) rootView.findViewById(R.id.leftButton);
            leftButton.setText(mItem.leftButtonLabel);
            leftButton.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        EditText enteredValue = (EditText) rootView.findViewById(R.id.enteredValue);
                        EditText convertedValue = (EditText) rootView.findViewById(R.id.convertedValue);
                        String fieldString = enteredValue.getText().toString();
                        try{
                            if (mItem.leftConversion != null){
                                // Sets the text with the conversion selected
                                convertedValue.setText(String.valueOf(mItem.leftConversion.convert(Double.parseDouble(fieldString))));
                            }
                        }
                        catch (Exception e){
                            // Prints "n/a" if no value is entered
                            convertedValue.setText("N/A");
                        }
                }
            });

            rightButton = (Button) rootView.findViewById(R.id.rightButton);
            rightButton.setText(mItem.rightButtonLabel);
            rightButton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    EditText enteredValue = (EditText) rootView.findViewById(R.id.enteredValue);
                    EditText converterField = (EditText) rootView.findViewById(R.id.convertedValue);
                    String fieldString = enteredValue.getText().toString();
                    try{
                        if (mItem.rightConversion != null){
                            converterField.setText(String.valueOf(mItem.rightConversion.convert(Double.parseDouble(fieldString))));
                        }
                    }
                    catch (Exception e){
                        // Prints "n/a" if no value is entered
                        converterField.setText("N/A");
                    }
                }
            });
        }
        return rootView;
    }

    public void onClick(View v) {

    }
}
