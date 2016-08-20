package ir.elegam.school.Fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.elegam.school.Adapter.News_Adapter;
import ir.elegam.school.Helper.Object_News;
import ir.elegam.school.R;

public class ConsultFragment3 extends Fragment {


    private ViewGroup layout;
    private Typeface San;
    private RecyclerView rv;
    private String FACTION = "اولیا";
    private List<Object_News> mylist = new ArrayList<>();

    public ConsultFragment3(){

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){

        }else{

        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        San = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SansLight.ttf");
        layout = (ViewGroup) inflater.inflate(R.layout.recycle_fragment, container, false);

        return layout;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = (RecyclerView) layout.findViewById(R.id.rv_recycle_fragment);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(horizontalLayoutManagaer);
        mylist.clear();
        SetFaction();
    }

    private void SetFaction(){
        for (int i=0;i<3;i++){
            mylist.add(new Object_News(FACTION,FACTION,FACTION,FACTION,FACTION,FACTION,FACTION));
        }
        rv.setAdapter(new News_Adapter(mylist,San,getActivity(),true));
    }// end SetFaction()

}// end class
