package ir.elegam.school.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ir.elegam.school.Helper.ImageCategoryGallery;
import ir.elegam.school.R;

/**
 * Created by Droid on 8/14/2016.
 */
public class ImageCategoryAdapter extends RecyclerView.Adapter<ImageCategoryAdapter.DataObjectHolder> {

    public ArrayList<ImageCategoryGallery> imageCategoryGalleries;

    public ImageCategoryAdapter(ArrayList<ImageCategoryGallery> arrayList){
        this.imageCategoryGalleries=arrayList;
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder{

        TextView tv_title;

        public DataObjectHolder(View itemView){
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.image_category_title);
        }

    }

    @Override
    public ImageCategoryAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.image_category_item,parent,false);
        return new DataObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageCategoryAdapter.DataObjectHolder holder, int position) {
        holder.tv_title.setText(imageCategoryGalleries.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return imageCategoryGalleries.size();
    }

}

