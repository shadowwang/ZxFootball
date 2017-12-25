package com.parsonswang.zxfootball.matches;

import com.parsonswang.common.utils.JsonBinder;
import com.parsonswang.common.utils.StringUtils;
import com.parsonswang.zxfootball.bean.HeaderTabTitle;
import com.parsonswang.zxfootball.bean.MatchesBean;
import com.parsonswang.zxfootball.common.mvp.AbsPresenter;
import com.parsonswang.zxfootball.common.utils.ConfigUtil;
import com.parsonswang.zxfootball.matches.detail.MatchDetailsFetchDataCallback;

import timber.log.Timber;

/**
 * Created by wangchun on 2017/10/22.
 */

public class MatchPresenter extends AbsPresenter implements MatchContract.IMatchPresenter {

    private MatchContract.IMatchView matchView;
    private MatchContract.IMatchInfoView matchInfoView;
    private MatchModel matchModel;

    public MatchPresenter(MatchContract.IMatchView matchView) {
        this.matchView = matchView;
        matchModel = new MatchModel();
    }

    public MatchPresenter(MatchContract.IMatchInfoView matchInfoView) {
        this.matchInfoView = matchInfoView;
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

    @Override
    public void getMatchDetail(String matchId) {
        matchModel.getMatchDetailInfo(matchId, new MatchDetailsFetchDataCallback());
    }

    private void getHeaderTabTitle() {
        String leagueConfig = ConfigUtil.getOnlineConfig("league_config", "{\"data\":[{ \"name\":\"中超\", \"id\": 3 },{ \"name\":\"西甲\", \"id\": 2 },{ \"name\":\"英超\", \"id\": 1 },{ \"name\":\"意甲\", \"id\": 5 },{ \"name\":\"德甲\", \"id\": 4 },{ \"name\":\"法甲\", \"id\": 6 }]}");

        Timber.i("leagueConfig|: " + leagueConfig);

        if (StringUtils.isEmptyString(leagueConfig)) {
            matchView.showHeaderTabTitle(null);
        } else {
            HeaderTabTitle headerTabTitle = JsonBinder.getInstance().fromJson(leagueConfig, HeaderTabTitle.class);
            matchView.showHeaderTabTitle(headerTabTitle);
        }
    }
}
