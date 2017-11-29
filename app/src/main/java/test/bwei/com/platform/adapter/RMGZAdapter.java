package test.bwei.com.platform.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import test.bwei.com.platform.R;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/25
 * Description:
 */

public class RMGZAdapter extends RecyclerView.Adapter<RMGZAdapter.myViewHolder>{
    private Context context;
 //   private List<String> list;


    public RMGZAdapter(Context context) {
        this.context = context;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.rmgz_adapter,null);
        myViewHolder myViewHolder=new myViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 40;
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        public myViewHolder(View itemView) {
            super(itemView);
        }
    }
}
