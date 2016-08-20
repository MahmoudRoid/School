package ir.elegam.school.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ir.elegam.school.AsyncTask.PostMessage;
import ir.elegam.school.Classes.Internet;
import ir.elegam.school.Interface.IWebservice;
import ir.elegam.school.R;

/**
 * Created by Droid on 8/15/2016.
 */
public class MessageFragment extends Fragment implements  AdapterView.OnItemSelectedListener,IWebservice{

    @BindView(R.id.name_edt)
    AppCompatEditText nameEdt;
    @BindView(R.id.phone_edt)
    AppCompatEditText phoneEdt;
    @BindView(R.id.matn_edt)
    AppCompatEditText matnEdt;
    @BindView(R.id.send_btn)
    AppCompatButton sendBtn;
    @BindView(R.id.choose_receiver_spinner)
    AppCompatSpinner chooseReceiverSpinner;

    public MessageFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chooseReceiverSpinner.setOnItemSelectedListener(this);
        List<String> spn_item = new ArrayList<String>();
        spn_item.add("مدیر");
        spn_item.add("معاونین");
        spn_item.add("مشاورین");
        spn_item.add("روابط عمومی");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spn_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseReceiverSpinner.setAdapter(dataAdapter);


    }

    @OnClick(R.id.send_btn)
    public void onClick() {

        if(matnEdt.getText().toString().equals("")){
            // show error
            matnEdt.setError("این فیلد را تکمیل نمایید");
            Snackbar snackbar = Snackbar
                    .make(getView(), " فیلد متن را تکمیل نمایید", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        else {

            if(Internet.isNetworkAvailable(getActivity())){

                String name = (nameEdt.getText().equals("") ? "" : nameEdt.getText().toString());
                String phone = (phoneEdt.getText().equals("") ? "" : phoneEdt.getText().toString());
                String matn = (matnEdt.getText().equals("") ? "" : matnEdt.getText().toString());
                // meghdar spinner check shavad
                String receiver =chooseReceiverSpinner.getSelectedItem().toString();

                PostMessage postMessage = new PostMessage(getActivity(),MessageFragment.this,name,phone,receiver,matn);
                postMessage.execute();
            }
            else {
                Snackbar snackbar = Snackbar
                        .make(getView(), "دسترسی اینترنت را بررسی نمایید", Snackbar.LENGTH_LONG);
                snackbar.show();
            }

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void getResult(Object result) throws Exception {
        Snackbar snackbar = Snackbar
                .make(getView(), "با موفقیت ارسال شد", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void getError(String ErrorCodeTitle) throws Exception {
        Snackbar snackbar = Snackbar
                .make(getView(), "متاسفانه ارسال نشد", Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
