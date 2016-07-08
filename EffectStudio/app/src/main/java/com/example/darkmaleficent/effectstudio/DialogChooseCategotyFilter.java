package com.example.darkmaleficent.effectstudio;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dark Maleficent on 04.07.2016.
 */
public class DialogChooseCategotyFilter extends DialogFragment implements View.OnClickListener {
    List<String> test = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog_choose_filters, null);
        test.add("hyt6jeeeeeeeeeeeeeeeeeeeee");
        test.add("fre");
        RadioGroup chooser = (RadioGroup) v.findViewById(R.id.radio);
        RadioButton newRadioButton;
        for (int i = 0; i < test.size(); i++) {
            newRadioButton = new RadioButton(getActivity());
            newRadioButton.setTextSize(14f);
            if (i % 2 == 0)
                newRadioButton.setTextColor(getResources().getColor(R.color.colorAccent));
            else
                newRadioButton.setTextColor(getResources().getColor(R.color.colorPrimary));
            newRadioButton.setText(test.get(i));
            chooser.addView(newRadioButton);
            newRadioButton=null;
        }
        chooser.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });
        return v;
    }


    @Override
    public void onClick(View v) {

    }
}
