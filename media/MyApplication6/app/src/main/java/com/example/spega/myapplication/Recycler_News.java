package com.example.spega.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin on 2/13/2018.
 */

public class Recycler_News extends RecyclerView.Adapter<Recycler_News.Newsdata> {
    Context context;
    List<Dataclass_News> dataclass_newsList;
    public Recycler_News(Context context, List<Dataclass_News> dataclass_newsList) {
        this.context = context;
        this.dataclass_newsList = dataclass_newsList;
    }

    @Override
    public Newsdata onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_cardview,parent,false);
        Newsdata newsdata=new Newsdata(view);
        return newsdata;
    }

    @Override
    public void onBindViewHolder(Newsdata holder, int position) {
        final Dataclass_News datas = dataclass_newsList.get(position);
        holder.head.setText(datas.getTitle());
        if (datas.getImage().isEmpty()) { //url.isEmpty()
            Picasso.with(context)
                    .load(R.drawable.error_message)
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.loading)
                    .into(holder.img);
        }else{
            Picasso.with(context).load(datas.getImage())
                    .error(R.drawable.loading)
                    .placeholder(R.drawable.loading)
                    .into(holder.img); //this is your ImageView
        }
        if(position%2==0){

            holder.cv.setBackgroundColor(ContextCompat.getColor(context,R.color.list_odd_color));

        }else{
            holder.cv.setBackgroundColor(ContextCompat.getColor(context,R.color.list_even_color));

        }
     /*   holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new   Intent(view.getContext(),News_Description.class);
                intent.putExtra("title",datas.getTitle());
                intent.putExtra("thumbnail",datas.getImage());
                intent.putExtra("desc",datas.getDesc());
                view.getContext().startActivity(intent);
            }


        });*/

    }

    @Override
    public int getItemCount() {
        return  dataclass_newsList.size();
    }

    public class Newsdata extends RecyclerView.ViewHolder {
        CardView cv;
        TextView head,play;
        ImageView img;
        public Newsdata(View itemView) {
            super(itemView);
            cv=(CardView)itemView.findViewById(R.id.news_cardview);
            head=(TextView)itemView.findViewById(R.id.textView_heading_news);
            //play=(TextView)itemView.findViewById(R.id.textView_read);
          //  img=(ImageView)itemView.findViewById(R.id.imageView_news);
        }
    }
}
