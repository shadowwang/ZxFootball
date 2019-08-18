package com.parsonswang.zxfootball.matches;

import androidx.core.content.ContextCompat;

import com.parsonswang.common.utils.JsonObjectMap;
import com.parsonswang.common.utils.StringUtils;
import com.parsonswang.zxfootball.bean.HeaderTabTitle;
import com.parsonswang.zxfootball.bean.MatchesBean;
import com.parsonswang.zxfootball.common.mvp.AbsPresenter;
import com.parsonswang.zxfootball.common.utils.ConfigUtil;
import com.parsonswang.zxfootball.matches.detail.MatchDetailsFetchDataCallback;
import com.parsonswang.zxfootball.matches.detail.MatchPlayerInfoCallback;
import com.parsonswang.zxfootball.matches.detail.MatchStatFetchDataCallback;

import timber.log.Timber;

/**
 * Created by wangchun on 2017/10/22.
 */

public class MatchPresenter extends AbsPresenter implements MatchContract.IMatchPresenter {

    private MatchContract.IMatchView matchView;
    private MatchContract.IMatchInfoView matchInfoView;
    private MatchContract.IMatchDetailView mIMatchDetailView;
    private MatchContract.IMatchStatView mIMatchStatView;
    private MatchContract.IMatchPlayerInfoView mIMatchPlayerInfoView;
    private MatchContract.IMatchDetailStatView mIMatchDetailStatView;

    private MatchModel matchModel;

    public MatchPresenter(MatchContract.IMatchView matchView) {
        this.matchView = matchView;
        matchModel = new MatchModel();
    }

    public MatchPresenter(MatchContract.IMatchInfoView matchInfoView) {
        this.matchInfoView = matchInfoView;
        matchModel = new MatchModel();
    }

    public MatchPresenter(MatchContract.IMatchDetailView iMatchDetailView, MatchContract.IMatchStatView iMatchStatView) {
        this.mIMatchDetailView = iMatchDetailView;
        this.mIMatchStatView = iMatchStatView;
        matchModel = new MatchModel();
    }

    public MatchPresenter(MatchContract.IMatchStatView iMatchStatView) {
        this.mIMatchStatView = iMatchStatView;
        matchModel = new MatchModel();
    }

    public MatchPresenter(MatchContract.IMatchPlayerInfoView iMatchPlayerInfoView) {
        this.mIMatchPlayerInfoView = iMatchPlayerInfoView;
        matchModel = new MatchModel();
    }

    public MatchPresenter(MatchContract.IMatchPlayerInfoView iMatchPlayerInfoView, MatchContract.IMatchDetailView iMatchDetailView) {
        this.mIMatchPlayerInfoView = iMatchPlayerInfoView;
        this.mIMatchDetailView = iMatchDetailView;
        matchModel = new MatchModel();
    }

    @Override
    protected void start() {
        getHeaderTabTitle();
    }

    @Override
    protected void stop() {

    }

    /**
     * 得到比赛数据
     * @param comeptitionId 联赛id
     * @param dataBetween 比赛时间区间
     * @return
     */
    @Override
    public void getMatchInfos(String comeptitionId, String dataBetween) {
        matchModel.getMatchInfoDatas(comeptitionId, dataBetween, new MachesFetchDataCallback<MatchesBean>(matchInfoView));
    }

    /**
     * 得到比赛详情
     * @param matchId
     */
    @Override
    public void getMatchDetail(String matchId) {
        matchModel.getMatchDetailInfo(matchId, new MatchDetailsFetchDataCallback(mIMatchDetailView));
    }

    @Override
    public void getMatchStat(String matchId) {
        matchModel.getMatchStatInfo(matchId, new MatchStatFetchDataCallback(mIMatchStatView));
    }

    /**
     * 得到比赛球员信息
     * @param matchId
     */
    @Override
    public void getMatchPlayerInfos(String matchId) {
        matchModel.getMatchPlayerInfo(matchId, new MatchPlayerInfoCallback(mIMatchPlayerInfoView));
    }

    private void getHeaderTabTitle() {
        String leagueConfig = ConfigUtil.getOnlineConfig("league_config", "{\"data\":[{ \"name\":\"中超\", \"id\": 3 },{ \"name\":\"西甲\", \"id\": 2 },{ \"name\":\"英超\", \"id\": 1 },{ \"name\":\"意甲\", \"id\": 5 },{ \"name\":\"德甲\", \"id\": 4 },{ \"name\":\"法甲\", \"id\": 6 }]}");

        Timber.i("leagueConfig|: " + leagueConfig);

        if (StringUtils.isEmptyString(leagueConfig)) {
            matchView.showHeaderTabTitle(null);
        } else {
            HeaderTabTitle headerTabTitle = JsonObjectMap.getInstance().fromJson(leagueConfig, HeaderTabTitle.class);
            matchView.showHeaderTabTitle(headerTabTitle);
        }
    }
}
