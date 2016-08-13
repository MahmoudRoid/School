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
import ir.elegam.school.R;


public class News_Adapter extends RecyclerView.Adapter<News_Adapter.ViewHolder> {

    List<Object_News> ItemsList;
    Typeface fontTypeFave;
    Context mContext;
    boolean isStaticImage;

    public News_Adapter(List<Object_News> row, Typeface San, Context context,boolean isStaticImage) {
        this.ItemsList = row;
        this.fontTypeFave = San;
        this.mContext = context;
        this.isStaticImage = isStaticImage;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtDate;
        ImageView ivRow;

        ViewHolder(View itemView) {
            super(itemView);
            ivRow = (ImageView) itemView.findViewById(R.id.ivIcon_rownews);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle_rownews);
            txtDate = (TextView) itemView.findViewById(R.id.txtDate_rownews);

            txtTitle.setTypeface(fontTypeFave);
            txtDate.setTypeface(fontTypeFave);

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
                .inflate(R.layout.row_news, viewGroup, false);

        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder RowViewHolder, final int position) {
        RowViewHolder.txtTitle.setText(ItemsList.get(position).Oid);
        RowViewHolder.txtDate.setText(ItemsList.get(position).ODate);

        Log.i(Variables.Tag, "texts: "+ ItemsList.get(position).OTile + "   "+ ItemsList.get(position).ODate);

        if(isStaticImage){
            RowViewHolder.ivRow.setImageResource(R.mipmap.ic_launcher);
        }else{
            Glide.with(mContext)
                    .load(ItemsList.get(position).OImageUrl)
                    .override(200,200)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(RowViewHolder.ivRow);

        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void add(Object_News ob) {
        ItemsList.add(ob);
        notifyDataSetChanged();
    }

    public void clear() {
        ItemsList.clear();
        notifyDataSetChanged();
    }
}
