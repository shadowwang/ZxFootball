package com.parsonswang.zxfootball.common.view;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parsonswang.common.image.Imageloaders;
import com.parsonswang.common.utils.StringUtils;
import com.parsonswang.common.utils.UIUtils;
import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.bean.MatchesBean;
import com.parsonswang.zxfootball.common.Constant;

/**
 * Created by parsonswang on 2017/11/6.
 */

public class TeamInfoView extends FrameLayout {

    private RelativeLayout mRlRoot;
    public ImageView mIvTeam;
    public TextView mTvTeam, mTvGoalPlayers;
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
        mRlRoot = view.findViewById(R.id.mRlRoot);
        mIvTeam = view.findViewById(R.id.mIvHomeTeam);
        mTvTeam = view.findViewById(R.id.mTvHomeTeam);
        mTvGoalPlayers = view.findViewById(R.id.mTvGoalPlayers);

        addView(view);
    }

    private static final int WORDCUP_COMPETION_ID = 21;

    public void setInfo(MatchesBean.MatchInfo matchInfo, boolean isHome) {
        //http://ov68gixwy.bkt.clouddn.com/countries/Russia.png?imageView2/2/w/24
        // 2019-01-26变更：http://pks73m1c4.bkt.clouddn.com/teams/34.png?imageView2/2/w/20
        if (isHome) {
            if (matchInfo.getCompetitionId() == WORDCUP_COMPETION_ID) {
                String url = Constant.TeamImage.TEAM_LOGO_PREF + matchInfo.getHomeTeamCountry() + ".png?imageView2/2/w/" + Constant.TeamImage.IMAGE_SIZE_LARGE;
                Imageloaders.loadImage(mContext, url, mIvTeam, 0);
            } else {
                String url = Constant.TeamImage.TEAM_LOGO_PREF + matchInfo.getHomeTeamId() + ".png?imageView2/2/w/" + Constant.TeamImage.IMAGE_SIZE_LARGE;
                Imageloaders.loadImage(mContext, url, mIvTeam, 0);
            }
            mTvTeam.setText(matchInfo.getHomeTeamName());
        } else {
            if (matchInfo.getCompetitionId() == WORDCUP_COMPETION_ID) {
                String url = Constant.TeamImage.TEAM_LOGO_PREF + matchInfo.getAwayTeamCountry() + ".png?imageView2/2/w/" + Constant.TeamImage.IMAGE_SIZE_LARGE;
                Imageloaders.loadImage(mContext, url, mIvTeam, 0);
            } else {
                String url = Constant.TeamImage.TEAM_LOGO_PREF + matchInfo.getAwayTeamId() + ".png?imageView2/2/w/" + Constant.TeamImage.IMAGE_SIZE_LARGE;
                Imageloaders.loadImage(mContext, url, mIvTeam, 0);
            }
            mTvTeam.setText(matchInfo.getAwayTeamName());
        }

    }

    public void setHomeTeamGoalInfo(String goalPlayers) {
//        mTvGoalPlayers.setVisibility(VISIBLE);
//        mTvGoalPlayers.setText(goalPlayers);
        if (StringUtils.isEmptyString(goalPlayers)) {
            return;
        }

        TextView homeTeamGoalInfoTx = new TextView(mContext);
        mRlRoot.addView(homeTeamGoalInfoTx);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) homeTeamGoalInfoTx.getLayoutParams();
        layoutParams.width = LayoutParams.WRAP_CONTENT;
        layoutParams.height= LayoutParams.WRAP_CONTENT;
        layoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.mIvHomeTeam);
        layoutParams.leftMargin = UIUtils.dip2px(mContext, 5);
        homeTeamGoalInfoTx.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 8);
        homeTeamGoalInfoTx.setLineSpacing(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2.0f,  getResources().getDisplayMetrics()), 1.0f);
        homeTeamGoalInfoTx.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        homeTeamGoalInfoTx.setText(goalPlayers);
    }

    public void setAwayTeamGoalInfo(String goalPlayers) {
        if (StringUtils.isEmptyString(goalPlayers)) {
            return;
        }

        TextView awayTeamGoalInfoTx = new TextView(mContext);
        mRlRoot.addView(awayTeamGoalInfoTx);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) awayTeamGoalInfoTx.getLayoutParams();
        layoutParams.width = LayoutParams.WRAP_CONTENT;
        layoutParams.height= LayoutParams.WRAP_CONTENT;
        layoutParams.addRule(RelativeLayout.LEFT_OF, R.id.mIvHomeTeam);
        layoutParams.rightMargin = UIUtils.dip2px(mContext, 5);
        awayTeamGoalInfoTx.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 8);
        awayTeamGoalInfoTx.setLineSpacing(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2.0f,  getResources().getDisplayMetrics()), 1.0f);
        awayTeamGoalInfoTx.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        awayTeamGoalInfoTx.setText(goalPlayers);
    }
}
