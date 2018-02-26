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

    public static MatchDetailHerderFragment newInstance(String matchId) {
        MatchDetailHerderFragment matchDetailHerderFragment = new  MatchDetailHerderFragment();
        Bundle data = new Bundle();
        data.putString("matchId", matchId);
        matchDetailHerderFragment.setArguments(data);
        return matchDetailHerderFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matchdetail_header, container, false);

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
        mAwayTeamName.setText(matchDetailHeaderInfoBean.awayTeamName);
    }

    @Override
    public void showMatchSummary(MatchSummary matchSummary) {
        mHomeTeamSummary.setText(matchSummary.getHomeMatchSummary());
        mAwayTeamSummary.setText(matchSummary.getAwayMatchSummary());
    }
}
