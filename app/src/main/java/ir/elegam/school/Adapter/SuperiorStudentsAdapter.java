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

import ir.elegam.school.Helper.SuperiorStudents;
import ir.elegam.school.R;

/**
 * Created by Droid on 8/11/2016.
 */
public class SuperiorStudentsAdapter extends  RecyclerView.Adapter<SuperiorStudentsAdapter.DataObjectHolder> {

    public ArrayList<SuperiorStudents> studentsArrayList;
    public Context context;


    public SuperiorStudentsAdapter(Context context, ArrayList<SuperiorStudents> arrayList){
        this.context=context;
        this.studentsArrayList=arrayList;
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder{
        TextView tv_name,tv_sal_tahsili,tv_class_number;
        ImageView imageView;

        public DataObjectHolder(View itemView){
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.superior_student_name);
            tv_sal_tahsili = (TextView) itemView.findViewById(R.id.superior_student_sale_tahsili);
            tv_class_number = (TextView) itemView.findViewById(R.id.superior_student_class_number);
            imageView = (ImageView) itemView.findViewById(R.id.superior_student_image);
        }

    }

    @Override
    public SuperiorStudentsAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.superior_student_item,parent,false);
        return new DataObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(SuperiorStudentsAdapter.DataObjectHolder holder, int position) {
        holder.tv_name.setText(studentsArrayList.get(position).getname());
        holder.tv_sal_tahsili.setText(studentsArrayList.get(position).getAcademic_year());
        holder.tv_class_number.setText(studentsArrayList.get(position).getClass_number());
        Glide.with(context).load(studentsArrayList.get(position).getImage_url())
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder(R.mipmap.ic_launcher).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return studentsArrayList.size();
    }

}


