package com.example.e_accountopening.Fragments;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;


import android.os.Handler;
import android.os.ResultReceiver;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_accountopening.Activities.MainActivity;
import com.example.e_accountopening.Helper.Utils;
import com.example.e_accountopening.Interfaces.ScreenInterface;
import com.example.e_accountopening.R;
import com.example.e_accountopening.Services.FormIntentService;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormFragment extends Fragment implements View.OnClickListener, View.OnFocusChangeListener {
    private EditText edt_cnic;
    private EditText edt_issue_date;
    private EditText edt_full_name;
    private EditText edt_mother_name;
    private EditText edt_dob;
    private EditText edt_pob;
    private Button btn_next;
    private View rootView;
    public static final String CNIC="cnic";
    public static final String ISSUE_DATE="issue_date";
    public static final String FULL_NAME="full_name";
    public static final String MOTHER_NAME="mother_name";
    public static final String DOB="dob";
    public static final String POB="pob";
    private ScreenInterface screenInterface;
    private boolean isValidCnic=false;
    private boolean isValidFullname=false;
    private boolean isValidMothername=false;
    private boolean isValidPob=false;

    public FormFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            screenInterface = (ScreenInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_form, container, false);

        edt_cnic=rootView.findViewById(R.id.edt_cnic);
        edt_issue_date=rootView.findViewById(R.id.edt_issue_date);
        edt_full_name=rootView.findViewById(R.id.edt_full_name);
        edt_mother_name=rootView.findViewById(R.id.edt_mother_name);
        edt_dob=rootView.findViewById(R.id.edt_dob);
        edt_pob=rootView.findViewById(R.id.edt_pob);
        btn_next=rootView.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
        edt_issue_date.setInputType(InputType.TYPE_NULL);
        edt_dob.setInputType(InputType.TYPE_NULL);
        edt_issue_date.setOnFocusChangeListener(this);
        edt_dob.setOnFocusChangeListener(this);
        edt_full_name.setInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        edt_mother_name.setInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        edt_pob.setInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btn_next){
            if(edt_cnic.getText().length()==15){
                isValidCnic=true;
            }

            if(edt_full_name.getText().length()>4 && edt_full_name.getText().length()<50){
                isValidFullname=true;
            }

            if(edt_mother_name.getText().length()>4 && edt_mother_name.getText().length()<50){
                isValidMothername=true;
            }

            if(edt_pob.getText().length()>4 && edt_pob.getText().length()<20){
                isValidPob=true;
            }




            if(!TextUtils.isEmpty(edt_cnic.getText()) && !TextUtils.isEmpty(edt_issue_date.getText())
                    && !TextUtils.isEmpty(edt_full_name.getText()) && !TextUtils.isEmpty(edt_mother_name.getText())
                    && !TextUtils.isEmpty(edt_dob.getText()) && !TextUtils.isEmpty(edt_pob.getText())){
                if(isValidCnic && isValidPob && isValidMothername && isValidFullname) {
                    Intent intent = new Intent(rootView.getContext(), FormIntentService.class);
                    intent.putExtra(CNIC, edt_cnic.getText().toString());
                    intent.putExtra(ISSUE_DATE, edt_issue_date.getText().toString());
                    intent.putExtra(FULL_NAME, edt_full_name.getText().toString());
                    intent.putExtra(MOTHER_NAME, edt_mother_name.getText().toString());
                    intent.putExtra(DOB, edt_dob.getText().toString());
                    intent.putExtra(POB, edt_pob.getText().toString());
                    Utils.savePreferences(rootView.getContext(), CNIC, edt_cnic.getText().toString());
                    getActivity().startService(intent);
                    replaceFragment(new ImageFragment());

                }else{
                    Toast.makeText(rootView.getContext(), "Please provide valid input...", Toast.LENGTH_SHORT).show();
                }

            }
            else{
                Toast.makeText(rootView.getContext(), "Please check the fields...", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction ft=this.getFragmentManager().beginTransaction();
        ft.replace(R.id.main_container,fragment);
        ft.commit();
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if(b) {
            if (view.getId() == R.id.edt_issue_date) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                DatePickerDialog picker = new DatePickerDialog(rootView.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                        edt_issue_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, year, month, day);
                picker.show();

            }

            if (view.getId() == R.id.edt_dob) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                DatePickerDialog picker = new DatePickerDialog(rootView.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                        edt_dob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, year, month, day);
                picker.show();

            }
        }
    }


}
