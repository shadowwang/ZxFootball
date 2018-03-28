package com.parsonswang.zxfootball.common.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.parsonswang.common.image.Imageloaders;
import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.common.Constant;

/**
 * Created by parsonswang on 2017/11/6.
 */

public class TeamInfoView extends FrameLayout {

    public ImageView mIvTeam;
    public TextView mTvTeam;
    public Context mContext;

    public TeamInfoView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public TeamInfoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;

        View view = LayoutInflater.from(context).inflate(R.layout.common_team_info_view, null, false);
        mIvTeam = view.findViewById(R.id.mIvHomeTeam);
        mTvTeam = view.findViewById(R.id.mTvHomeTeam);

        addView(view);
    }

    public void setInfo(int teamId, String teamName) {
        String url = "http://ov68gixwy.bkt.clouddn.com/teams/" + teamId + ".png?imageView2/2/w/" + Constant.TeamImageSize.IMAGE_SIZE_LARGE;
        Imageloaders.loadImage(mContext, url, mIvTeam, 0);
        mTvTeam.setText(teamName);
    }
}
