package com.parsonswang.zxfootball.common.view;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parsonswang.common.utils.UIUtils;
import com.parsonswang.zxfootball.R;

/**
 * Created by wangchun on 2018/2/14.
 */

public class MatchScoreInfoView<T> extends FrameLayout {

    public ConstraintLayout mRootLayput;
    public LinearLayout mLlMatchScore;
    public TextView mTvScore, mTvMatchStatus, mTvStage;
    public TeamInfoView mHomeTeam, mAwayTeam;
    public T t;

    private Context mContext;

    public void setData(T t) {
        this.t = t;
    }

    public MatchScoreInfoView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public MatchScoreInfoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        View itemView = LayoutInflater.from(context).inflate(R.layout.rv_item_match_score, null, false);

        mLlMatchScore = itemView.findViewById(R.id.mLlMatchScore);
        mRootLayput = itemView.findViewById(R.id.mRootLayput);
        mRootLayput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener == null) {
                    return;
                }

                mOnItemClickListener.onItemClick(t);
            }
        });

        mTvScore = itemView.findViewById(R.id.mTvScore);
        mTvMatchStatus = itemView.findViewById(R.id.mTvMatchStatus);
        mHomeTeam = itemView.findViewById(R.id.mLlHomeTeam);
        mAwayTeam = itemView.findViewById(R.id.mLlAwayTeam);
        mTvStage = itemView.findViewById(R.id.mTvStage);

        addView(itemView);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(T t);
    }

    public void setCorner() {
        if (mRootLayput == null) {
            return;
        }

        mRootLayput.setBackgroundResource(R.drawable.border_corner_common_bg);
    }

    public void setMarginLeftAndRight(float leftDp, float rightDp) {
        if (mRootLayput == null) {
            return;
        }

        FrameLayout.LayoutParams params = (LayoutParams) mRootLayput.getLayoutParams();
        if (params == null) {
            return;
        }

        params.leftMargin = UIUtils.dip2px(mContext, leftDp);
        params.rightMargin = UIUtils.dip2px(mContext, rightDp);
        mRootLayput.setLayoutParams(params);
    }

    public void setHomeTeamGoalInfo(String homeTeamGoalInfo) {
        mHomeTeam.setHomeTeamGoalInfo(homeTeamGoalInfo);
    }

    public void setAwayTeamGoalInfo(String awayTeamGoalInfo) {
        mAwayTeam.setAwayTeamGoalInfo(awayTeamGoalInfo);
    }
}
