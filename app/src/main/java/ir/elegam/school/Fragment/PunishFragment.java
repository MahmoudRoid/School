package ir.elegam.school.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ir.elegam.school.Activity.PunishEncourageActivity;
import ir.elegam.school.Adapter.PunishEncourageAdapter;
import ir.elegam.school.Helper.PunishEncourage;
import ir.elegam.school.R;

/**
 * Created by Droid on 8/14/2016.
 */
public class PunishFragment extends Fragment {

    ArrayList<PunishEncourage> punish_arraylist;
    private RecyclerView mRecyclerView;
    private PunishEncourageAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public PunishFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        punish_arraylist= PunishEncourageActivity.getPunish_arraylist_static();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.punish_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showList(this.punish_arraylist);
    }

    public void showList(ArrayList<PunishEncourage> arrayList){

        mRecyclerView = (RecyclerView) getView().findViewById(R.id.punish_recyclerview);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PunishEncourageAdapter(arrayList);
        mRecyclerView.setAdapter(mAdapter);

    }
}
