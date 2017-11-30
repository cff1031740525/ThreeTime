package test.bwei.com.platform.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import test.bwei.com.platform.R;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/30
 * Description:
 */

public class JokeItemAdapter extends RecyclerView.Adapter<JokeItemAdapter.myViewHolder> {
    private Context context;
    private String[] list;

    public JokeItemAdapter(Context context, String[] list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_joke_rlv, null);
        myViewHolder myViewHolder = new myViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        Glide.with(context).load(list[position]).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;

        public myViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item_rlvimg);
        }
    }
}
