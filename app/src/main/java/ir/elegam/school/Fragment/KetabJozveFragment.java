package ir.elegam.school.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ir.elegam.school.R;

/**
 * Created by Droid on 8/16/2016.
 */
public class KetabJozveFragment extends Fragment {

    public KetabJozveFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ketab_jozve_fragment, container, false);
    }
}
