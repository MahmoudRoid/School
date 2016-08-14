package ir.elegam.school.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ir.elegam.school.Helper.PunishEncourage;
import ir.elegam.school.R;

/**
 * Created by Droid on 8/14/2016.
 */
public class PunishEncourageAdapter extends RecyclerView.Adapter<PunishEncourageAdapter.DataObjectHolder> {

    public ArrayList<PunishEncourage> punishEncourageArrayList;

    public PunishEncourageAdapter(ArrayList<PunishEncourage> arrayList){
        this.punishEncourageArrayList=arrayList;
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder{

        TextView tv_date,tv_description;

        public DataObjectHolder(View itemView){
            super(itemView);
            tv_date = (TextView) itemView.findViewById(R.id.punish_encourage_date);
            tv_description = (TextView) itemView.findViewById(R.id.punish_encourage_description);
        }

    }


    @Override
    public PunishEncourageAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.punish_encourage_item,parent,false);
        return new DataObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(PunishEncourageAdapter.DataObjectHolder holder, int position) {
        holder.tv_date.setText(punishEncourageArrayList.get(position).getDate());
        holder.tv_description.setText(punishEncourageArrayList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return punishEncourageArrayList.size();
    }

}

