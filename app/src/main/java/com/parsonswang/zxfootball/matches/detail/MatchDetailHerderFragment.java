package com.parsonswang.zxfootball.matches.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parsonswang.common.base.BaseFragment;
import com.parsonswang.common.image.Imageloaders;
import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.bean.MatchDetailHeaderInfoBean;
import com.parsonswang.zxfootball.bean.MatchSummary;
import com.parsonswang.zxfootball.matches.MatchContract;
import com.parsonswang.zxfootball.matches.MatchPresenter;

/**比赛头部信息Fragment
 * Created by parsonswang on 2018/2/6.
 */
public class MatchDetailHerderFragment extends BaseFragment implements MatchContract.IMatchDetailView {

    private ImageView mHomeTeamLogo;
    private TextView mHomeTeamName, mHomeTeamSummary;

    private TextView mAllTimeScore, mLun, mHalfTimeScore, mMatchTime;

    private ImageView mAwayTeamLogo;
    private TextView mAwayTeamName, mAwayTeamSummary;

    public static MatchDetailHerderFragment newInstance(String messageId) {
        MatchDetailHerderFragment matchDetailHerderFragment = new  MatchDetailHerderFragment();
        Bundle data = new Bundle();
        data.putString("messageId", messageId);
        matchDetailHerderFragment.setArguments(data);
        return matchDetailHerderFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matchdetail_header, container, false);
        mHomeTeamLogo = view.findViewById(R.id.iv_home_team_logo);
        mHomeTeamName = view.findViewById(R.id.tv_home_team_name);
        mHomeTeamSummary = view.findViewById(R.id.tv_home_team_summary);

        mAllTimeScore = view.findViewById(R.id.tv_all_time_score);
        mLun = view.findViewById(R.id.tv_lun);
        mHalfTimeScore = view.findViewById(R.id.tv_half_time_score);
        mMatchTime = view.findViewById(R.id.tv_match_time);

        mAwayTeamLogo = view.findViewById(R.id.iv_away_team_logo);
        mAwayTeamName = view.findViewById(R.id.tv_away_team_name);
        mAwayTeamSummary = view.findViewById(R.id.tv_away_team_summary);

        new MatchPresenter(this).getMatchDetail(getArguments().getString("matchId"));
        return view;
    }

    @Override
    public void showExceptionView() {

    }

    @Override
    public void showMatchInfoHeader(MatchDetailHeaderInfoBean matchDetailHeaderInfoBean) {
        Imageloaders.loadImage(mContext, matchDetailHeaderInfoBean.homeTeamLogo, mHomeTeamLogo, 0);
        mHomeTeamName.setText(matchDetailHeaderInfoBean.homeTeamName);

        Imageloaders.loadImage(mContext, matchDetailHeaderInfoBean.awayTeamLogo, mAwayTeamLogo, 0);
        mHomeTeamName.setText(matchDetailHeaderInfoBean.awayTeamName);
    }

    @Override
    public void showMatchSummary(MatchSummary matchSummary) {
        mHomeTeamSummary.setText(matchSummary.getHomeMatchSummary());
        mAwayTeamSummary.setText(matchSummary.getAwayMatchSummary());
    }
}
