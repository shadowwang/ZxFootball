package com.parsonswang.zxfootball.common.view;

import android.content.Context;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parsonswang.common.utils.UIUtils;
import com.parsonswang.common.view.MarqueTextView;
import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.bean.MatchTimelines;
import com.parsonswang.zxfootball.bean.PlayerInfo;
import com.parsonswang.zxfootball.common.Constant;
import com.parsonswang.zxfootball.common.utils.ImageUtils;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * 比赛球员信息view
 */
public class MatchPlayerView extends FrameLayout {

    private ImageView mIvMainPlayer;
    private MarqueTextView mTvMainPlayerName;
    private LinearLayout mMainEventContainer;
    private Context mContext;

    public boolean showUp;

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

        mIvMainPlayer = view.findViewById(R.id.iv_player);
        mTvMainPlayerName = view.findViewById(R.id.tv_playername);
        mMainEventContainer = view.findViewById(R.id.event_container);

        addView(view);
    }

    private PlayerInfo mainPlayerInfo, benchPlayerInfo;
    private ArrayList<MatchTimelines> mainMatchTimelineList, benchMatchTimelineList;
    private SparseIntArray mainTimeLineEventResMap, benchTimeLineEventResMap;

    /**
     * 设置队员信息
     * @param playerInfo
     * @param matchTimelineList
     * @param timeLineEventResMap
     */
    public void setData(PlayerInfo playerInfo, ArrayList<MatchTimelines> matchTimelineList,
                        SparseIntArray timeLineEventResMap) {
        if (this.mainPlayerInfo == null) {
            this.mainPlayerInfo = playerInfo;
        }
        if (this.mainMatchTimelineList == null) {
            this.mainMatchTimelineList = matchTimelineList;
        }
        if (this.mainTimeLineEventResMap == null) {
            this.mainTimeLineEventResMap = timeLineEventResMap;
        }

        ImageUtils.loadMatchPlayerAvatar(mContext, mIvMainPlayer, playerInfo.avatarUrl);

        String name = playerInfo.playerName;
        if (name.contains("·") && name.split("·").length > 1) {
            name = name.split("·")[1];
        } else if (name.contains("-") && name.split("-").length > 1) {
            name = name.split("-")[1];
        }
        mTvMainPlayerName.setText(name);

        if (matchTimelineList != null) {
            mMainEventContainer.removeAllViews();
            for (MatchTimelines matchTimeline : matchTimelineList) {
                final int eventType = matchTimeline.eventType;
                if (eventType == Constant.MatchTimelineEventType.EVENTTYPE_SUBSTITUTES_DOWN) {
                    playerInfo.hasExchangeDown = true;
                    playerInfo.downMinute = matchTimeline.minute;//被换下时间
                }

                int res = timeLineEventResMap.get(eventType);

                TextView textView = new TextView(getContext());
                textView.setTextColor(getContext().getResources().getColor(R.color.colorWhite));
//                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, UIUtils.dip2px(getContext(), 2));
                textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, res, 0);
                textView.setText(String.valueOf(matchTimeline.minute));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 8);
                mMainEventContainer.addView(textView);

                final LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
                layoutParams.leftMargin = UIUtils.dip2px(mContext, 2);
            }
        }
    }


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (showUp) {//显示替换上场球员信息
                setData(benchPlayerInfo, benchMatchTimelineList, benchTimeLineEventResMap);
                showUp = false;
            } else {//显示主力球员信息
                setData(mainPlayerInfo, mainMatchTimelineList, mainTimeLineEventResMap);
                showUp = true;
            }

            if (mHandler != null) {
                mHandler.postDelayed(this, 2000);
            }
        }
    };

    /**
     * 替补球员信息
     * @param playerInfo
     * @param matchTimelineList
     * @param timeLineEventResMap
     */
    public void substitution(PlayerInfo playerInfo, ArrayList<MatchTimelines> matchTimelineList,
                             SparseIntArray timeLineEventResMap) {
        if (this.benchPlayerInfo == null) {
            this.benchPlayerInfo = playerInfo;
        }
        if (this.benchMatchTimelineList == null) {
            this.benchMatchTimelineList = matchTimelineList;
        }
        if (this.benchTimeLineEventResMap == null) {
            this.benchTimeLineEventResMap = timeLineEventResMap;
        }

        if (mHandler != null) {
            mHandler.postDelayed(runnable, 1000);
        }
    }

    private Handler mHandler;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Timber.i("---onAttachedToWindow---");
        mHandler = new Handler();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Timber.i("---onDetachedFromWindow---");
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}
