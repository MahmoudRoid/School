package ir.elegam.school.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ir.elegam.school.Classes.Variables;
import ir.elegam.school.Helper.Object_News;
import ir.elegam.school.Helper.Object_Schdual;
import ir.elegam.school.R;


public class Schedual_Adapter extends RecyclerView.Adapter<Schedual_Adapter.ViewHolder> {

    List<Object_Schdual> ItemsList;
    Typeface fontTypeFave;
    Context mContext;
    boolean isStaticImage;

    public Schedual_Adapter(List<Object_Schdual> row, Typeface San, Context context) {
        this.ItemsList = row;
        this.fontTypeFave = San;
        this.mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtDate, txtTeacher;

        ViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtName_rowsch);
            txtDate = (TextView) itemView.findViewById(R.id.txtDate_rowsch);
            txtTeacher = (TextView) itemView.findViewById(R.id.txtTeacher_rowsch);

            txtName.setTypeface(fontTypeFave);
            txtDate.setTypeface(fontTypeFave);
            txtTeacher.setTypeface(fontTypeFave);

        }// Cunstrator()
    }// end class ViewHolder{}


    @Override
    public int getItemCount() {
        return ItemsList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.row_schedual, viewGroup, false);

        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder RowViewHolder, final int position) {
        RowViewHolder.txtName.setText(ItemsList.get(position).OName);
        RowViewHolder.txtDate.setText(ItemsList.get(position).ODate);
        RowViewHolder.txtTeacher.setText(ItemsList.get(position).OTeacher);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void add(Object_Schdual ob) {
        ItemsList.add(ob);
        notifyDataSetChanged();
    }

    public void clear() {
        ItemsList.clear();
        notifyDataSetChanged();
    }
}
