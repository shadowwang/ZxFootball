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

import com.parsonswang.common.image.GlideImageLoader;
import com.parsonswang.common.image.Imageloaders;
import com.parsonswang.common.utils.UIUtils;
import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.common.utils.ImageUtils;

/**
 * 比赛球员信息view
 */
public class MatchPlayerView extends FrameLayout {

    private ImageView mIvPlayer;
    private TextView mTvPlayernName;

    private Context mContext;

    public MatchPlayerView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public MatchPlayerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context,attrs);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_match_player, null, false);
        mIvPlayer = view.findViewById(R.id.iv_player);
        mTvPlayernName = view.findViewById(R.id.tv_playername);
        addView(view);
    }

    public void setData(String avatarUrl, String name) {
        ImageUtils.loadMatchPlayerAvatar(mContext, mIvPlayer, avatarUrl);
        mTvPlayernName.setText(name);
    }
}
