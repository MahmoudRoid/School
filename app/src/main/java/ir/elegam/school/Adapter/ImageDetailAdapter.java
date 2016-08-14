package ir.elegam.school.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import ir.elegam.school.Helper.ImagesDetailGallery;
import ir.elegam.school.R;

/**
 * Created by Droid on 8/14/2016.
 */
public class ImageDetailAdapter extends RecyclerView.Adapter<ImageDetailAdapter.DataObjectHolder> {

    public ArrayList<ImagesDetailGallery> imagesDetailGalleryArrayList;
    public Context context;

    public ImageDetailAdapter(Context context,ArrayList<ImagesDetailGallery> arrayList){
        this.context=context;
        this.imagesDetailGalleryArrayList=arrayList;
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder{

        ImageView image;

        public DataObjectHolder(View itemView){
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.images_detail_image);
        }

    }

    @Override
    public ImageDetailAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.images_detail_item,parent,false);
        return new DataObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageDetailAdapter.DataObjectHolder holder, int position) {
        Glide.with(context).load(imagesDetailGalleryArrayList.get(position).getImage_url())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.mipmap.ic_launcher).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return imagesDetailGalleryArrayList.size();
    }

}


