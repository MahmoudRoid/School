package ir.elegam.school.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ir.elegam.school.Helper.Object_Video;
import ir.elegam.school.R;


public class Thumbnail_Adapter extends RecyclerView.Adapter<Thumbnail_Adapter.ViewHolder> {

    List<Object_Video> ItemsList;
    Typeface fontTypeFave;
    Context mContext;

    public Thumbnail_Adapter(List<Object_Video> row, Typeface San, Context context) {
        this.ItemsList = row;
        this.fontTypeFave = San;
        this.mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        ImageView ivRow;

        ViewHolder(View itemView) {
            super(itemView);
            ivRow = (ImageView) itemView.findViewById(R.id.ivIcon_rowthumbnail);
            txtTitle = (TextView) itemView.findViewById(R.id.txtAbout_rowthumbnail);

            txtTitle.setTypeface(fontTypeFave);

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
                .inflate(R.layout.row_thumbnails, viewGroup, false);

        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder RowViewHolder, final int position) {
        RowViewHolder.txtTitle.setText(ItemsList.get(position).OText);

        //String ImageUrl = Variables.DOMAIN + "images/gallery/videos/"+ ItemsList.get(position).Oid +".jpeg";

        Glide.with(mContext)
                .load(ItemsList.get(position).OThumb_url)
                .override(200,200)
                .into(RowViewHolder.ivRow);


        /*RowViewHolder.ivRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VideoGallaryActivity.VideoPlayer(
                        ItemsList.get(position).OVideo_url,
                        ItemsList.get(position).OText,
                        mContext
                );
            }
        });*/
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void add(Object_Video ob) {
        ItemsList.add(ob);
        notifyDataSetChanged();
    }

    public void clear() {
        ItemsList.clear();
        notifyDataSetChanged();
    }
}

