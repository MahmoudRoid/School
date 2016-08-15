package ir.elegam.school.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ir.elegam.school.R;

/**
 * Created by Droid on 8/15/2016.
 */
public class MessageFragment extends Fragment {

    @BindView(R.id.name_edt)
    AppCompatEditText nameEdt;
    @BindView(R.id.phone_edt)
    AppCompatEditText phoneEdt;
    @BindView(R.id.address_edt)
    AppCompatEditText addressEdt;
    @BindView(R.id.send_btn)
    AppCompatButton sendBtn;

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
    }

    @OnClick(R.id.send_btn)
    public void onClick() {
    }
}
