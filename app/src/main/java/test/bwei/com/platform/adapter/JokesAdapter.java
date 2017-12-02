package test.bwei.com.platform.adapter;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.bwei.com.platform.R;
import test.bwei.com.platform.bean.UseJokeBean;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/28
 * Description:
 */

public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.myViewHolder> {
    private List<UseJokeBean> list;
    private Context context;
    private Map<Integer, Boolean> map = new HashMap<>();

    public JokesAdapter(List<UseJokeBean> list, Context context) {
        this.list = list;
        this.context = context;
}

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.userinfo, null);
        myViewHolder myViewHolder = new myViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {

        if (map.get(position) == null) {
            map.put(position, true);
        }
        UseJokeBean useJokeBean = list.get(position);
        holder.icon.setImageURI(useJokeBean.image);
        holder.nickname.setText(useJokeBean.nickname);
        holder.createtime.setText(useJokeBean.createtime);
        holder.comment_count.setText(useJokeBean.commentNum);
        holder.share_count.setText(useJokeBean.shareNum);
        holder.love_count.setText(useJokeBean.praiseNum);
        holder.content.setText(useJokeBean.content);
        if(!TextUtils.isEmpty(list.get(position).imgurls)){
            holder.item_rlv.setVisibility(View.VISIBLE);
            String[] split = list.get(position).imgurls.split("\\|");
            JokeItemAdapter adapter=new JokeItemAdapter(context,split);
            holder.item_rlv.setLayoutManager(new GridLayoutManager(context,3));
            holder.item_rlv.setAdapter(adapter);
        }else{
            holder.item_rlv.setVisibility(View.GONE);
        }
        holder.user_love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userOperation.LoveOperation(position);
                holder.share_count.setText(" ");
                holder.comment_count.setText(" ");
                holder.love_count.setText(" ");
                holder.user_show.setImageResource(R.mipmap.item_jia);
                AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.love_anmi_second);
                set.setTarget(holder.user_show);
                set.start();

                AnimatorSet set1 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.comment_anim_second);
                set1.setTarget(holder.user_love);
                set1.start();
                AnimatorSet set5 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.comment_anim_second);
                set5.setTarget(holder.user_comment);
                set5.start();
                AnimatorSet set6 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.comment_anim_second);
                set6.setTarget(holder.user_share);
                set6.start();

                AnimatorSet set2 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.comment_tran_second);
                set2.setTarget(holder.ll_comment);
                set2.start();
                AnimatorSet set3 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.share_tran_second);
                set3.setTarget(holder.ll_share);
                set3.start();
                AnimatorSet set4 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.love_tran_second);
                set4.setTarget(holder.ll_love);
                set4.start();



                map.put(position, true);
            }
        });
        holder.ll_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map.get(position)) {
                    holder.love_count.setText(list.get(position).praiseNum);
                    holder.share_count.setText(list.get(position).shareNum);
                    holder.comment_count.setText(list.get(position).commentNum);
                    holder.user_show.setImageResource(R.mipmap.item_jian);
                    AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.love_anmi);
                    set.setTarget(holder.user_show);
                    set.start();

                    AnimatorSet set1 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.comment_anim);
                    set1.setTarget(holder.user_love);
                    set1.start();
                    AnimatorSet set5 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.comment_anim);
                    set5.setTarget(holder.user_comment);
                    set5.start();
                    AnimatorSet set6 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.comment_anim);
                    set6.setTarget(holder.user_share);
                    set6.start();

                    AnimatorSet set2 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.comment_tran);
                    set2.setTarget(holder.ll_comment);
                    set2.start();
                    AnimatorSet set3 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.share_tran);
                    set3.setTarget(holder.ll_share);
                    set3.start();
                    AnimatorSet set4 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.love_tran);
                    set4.setTarget(holder.ll_love);
                    set4.start();
                    map.put(position, false);
                } else {
                    holder.share_count.setText(" ");
                    holder.comment_count.setText(" ");
                    holder.love_count.setText(" ");
                    holder.user_show.setImageResource(R.mipmap.item_jia);
                    AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.love_anmi_second);
                    set.setTarget(holder.user_show);
                    set.start();

                    AnimatorSet set1 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.comment_anim_second);
                    set1.setTarget(holder.user_love);
                    set1.start();
                    AnimatorSet set5 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.comment_anim_second);
                    set5.setTarget(holder.user_comment);
                    set5.start();
                    AnimatorSet set6 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.comment_anim_second);
                    set6.setTarget(holder.user_share);
                    set6.start();

                    AnimatorSet set2 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.comment_tran_second);
                    set2.setTarget(holder.ll_comment);
                    set2.start();
                    AnimatorSet set3 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.share_tran_second);
                    set3.setTarget(holder.ll_share);
                    set3.start();
                    AnimatorSet set4 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.love_tran_second);
                    set4.setTarget(holder.ll_love);
                    set4.start();



                    map.put(position, true);
                }

            }
        });
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView icon;
        private final TextView nickname;
        private final TextView createtime;
        private final ImageView user_show;
        private final ImageView user_comment;
        private final ImageView user_share;
        private final ImageView user_love;
        private final TextView comment_count;
        private final TextView share_count;
        private final TextView love_count;
        private final TextView content;
        private final LinearLayout ll_jia;
        private final LinearLayout ll_share;
        private final LinearLayout ll_love;
        private final LinearLayout ll_comment;
        private final RecyclerView item_rlv;

        public myViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.uf_header);
            nickname = itemView.findViewById(R.id.user_nickname);
            createtime = itemView.findViewById(R.id.user_createtime);
            user_show = itemView.findViewById(R.id.user_show);
            user_comment = itemView.findViewById(R.id.user_comment);
            user_share = itemView.findViewById(R.id.user_share);
            user_love = itemView.findViewById(R.id.user_love);
            comment_count = itemView.findViewById(R.id.comment_count);
            share_count = itemView.findViewById(R.id.share_count);
            love_count = itemView.findViewById(R.id.love_count);
            content = itemView.findViewById(R.id.content);
            ll_jia = itemView.findViewById(R.id.ll_jia);
            ll_share = itemView.findViewById(R.id.ll_share);
            ll_love = itemView.findViewById(R.id.ll_love);
            ll_comment = itemView.findViewById(R.id.ll_comment);
            item_rlv = itemView.findViewById(R.id.item_rlv);
        }
    }

    private UserOperation userOperation;

    public void setUserOperation(UserOperation userOperation) {
        this.userOperation = userOperation;
    }

    public interface UserOperation {
        void CommentOperation(int postion);

        void LoveOperation(int postion);

        void ShareOperation(int postion);

        void ShowOperation(int postion);
    }
}
