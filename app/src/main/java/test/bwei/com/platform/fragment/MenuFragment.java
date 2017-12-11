package test.bwei.com.platform.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hxe.platform.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import test.bwei.com.platform.activity.SetActivity;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/26
 * Description:
 */

public class MenuFragment extends Fragment {

    @BindView(R.id.qm)
    TextView qm;
    @BindView(R.id.simpleDraweeView)
    SimpleDraweeView simpleDraweeView;
    @BindView(R.id.relativeLayout5)
    RelativeLayout relativeLayout5;
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.relativeLayout8)
    RelativeLayout relativeLayout8;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.relativeLayout9)
    RelativeLayout relativeLayout9;
    @BindView(R.id.imageView6)
    ImageView imageView6;
    @BindView(R.id.relativeLayout10)
    RelativeLayout relativeLayout10;
    @BindView(R.id.imageView7)
    ImageView imageView7;
    @BindView(R.id.mset)
    LinearLayout mset;
    Unbinder unbinder;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view = LayoutInflater.from(getActivity()).inflate(R.layout.leftmenu, null);
        }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SetActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
