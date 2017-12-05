package test.bwei.com.platform.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.IjkVideoView;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hxe.platform.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.bwei.com.platform.jsonbean.VedioBean;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/25
 * Description:
 */

public class RMGZAdapter extends RecyclerView.Adapter<RMGZAdapter.myViewHolder> {



    private Context context;
    private List<VedioBean.DataBean> data;


    public RMGZAdapter(Context context, List<VedioBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rmgz_adapter, null);
        myViewHolder myViewHolder = new myViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, int position) {
        final VedioBean.DataBean dataBean = data.get(position);
        Glide.with(context).load(dataBean.user.icon).into(holder.rmHeader);
        holder.rmNickname.setText(dataBean.user.nickname + "");
        holder.rmCreatetime.setText(dataBean.createTime);
        String videoUrl = dataBean.videoUrl;

        String[] split = videoUrl.split(".cn/");
        String url=null;
        if(split.length>=2){
            url="http://120.27.23.105/"+ split[1];
        }

        System.out.println(url+"777777777777777");
        View inflate = View.inflate(context, R.layout.simple_player_view_player, holder.rlvedios);
        PlayerView pv=new PlayerView((Activity) context,inflate)
                .setTitle(dataBean.workDesc)
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        Glide.with(context).load(dataBean.cover).into(ivThumbnail);
                        
                    }
                })
                .forbidTouch(false)
                .setPlaySource(url)
                .startPlay();
        if (TextUtils.isEmpty(dataBean.comments + "")) {
            holder.rm_description.setText("  ");
        }
        holder.rm_description.setText(dataBean.workDesc + "");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rm_header)
        SimpleDraweeView rmHeader;
        @BindView(R.id.rm_nickname)
        TextView rmNickname;
        @BindView(R.id.rm_createtime)
        TextView rmCreatetime;
        @BindView(R.id.linearLayout2)
        LinearLayout linearLayout2;
        @BindView(R.id.user_comment)
        ImageView userComment;
        @BindView(R.id.tv_pb)
        TextView tvPb;
        @BindView(R.id.ll_pb)
        LinearLayout llPb;
        @BindView(R.id.user_share)
        ImageView userShare;
        @BindView(R.id.tv_lj)
        TextView tvLj;
        @BindView(R.id.ll_lj)
        LinearLayout llLj;
        @BindView(R.id.user_love)
        ImageView userLove;
        @BindView(R.id.tv_jb)
        TextView tvJb;
        @BindView(R.id.ll_jb)
        LinearLayout llJb;
        @BindView(R.id.user_show)
        ImageView userShow;
        @BindView(R.id.ll_jia)
        LinearLayout llJia;
        @BindView(R.id.r1)
        RelativeLayout r1;
        @BindView(R.id.relativeLayout7)
        RelativeLayout relativeLayout7;
        @BindView(R.id.rm_description)
        TextView rm_description;
        @BindView(R.id.detail_item)
        LinearLayout detailItem;
        @BindView(R.id.rlvedios)
        RelativeLayout rlvedios;
        public myViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
