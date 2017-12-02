package test.bwei.com.platform.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/12/2
 * Description:
 */

public class RmAdapter extends RecyclerView.Adapter<RmAdapter.myViewHolder>{
    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        public myViewHolder(View itemView) {
            super(itemView);
        }
    }
}
