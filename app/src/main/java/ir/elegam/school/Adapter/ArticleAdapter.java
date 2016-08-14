package ir.elegam.school.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import ir.elegam.school.Classes.Article;
import ir.elegam.school.R;


/**
 * Created by Amoozesh on 7/18/2016.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.DataObjectHolder> {
    public Context context;
    public ArrayList<Article> articleArrayList;

    public ArticleAdapter(Context context, ArrayList<Article> arrayList){
        this.articleArrayList=arrayList;
        this.context=context;
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder{

        TextView tv_number;
        ImageView image;

        public DataObjectHolder(View itemView){
            super(itemView);
            tv_number = (TextView) itemView.findViewById(R.id.article_content);
            image = (ImageView) itemView.findViewById(R.id.article_image);
        }

    }


    @Override
    public ArticleAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item,parent,false);
        return new DataObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleAdapter.DataObjectHolder holder, int position) {
        holder.tv_number.setText(articleArrayList.get(position).getNumber());
        Glide.with(context).load(articleArrayList.get(position).getImage_url())
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder(R.mipmap.ic_launcher).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

}
