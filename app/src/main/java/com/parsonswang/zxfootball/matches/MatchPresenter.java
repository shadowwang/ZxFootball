package com.parsonswang.zxfootball.matches;

import com.parsonswang.common.utils.JsonBinder;
import com.parsonswang.common.utils.StringUtils;
import com.parsonswang.zxfootball.bean.HeaderTabTitle;
import com.parsonswang.zxfootball.common.mvp.AbsPresenter;
import com.parsonswang.zxfootball.common.utils.ConfigUtil;

import timber.log.Timber;

/**
 * Created by wangchun on 2017/10/22.
 */

public class MatchPresenter extends AbsPresenter implements MatchContract.IMatchPresenter{

    private MatchContract.IMatchView matchView;
    private MatchModel matchModel;

    public MatchPresenter(MatchContract.IMatchView matchView) {
        this.matchView = matchView;
        matchModel = new MatchModel(this);
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
        matchModel.getMatchInfoDatas(comeptitionId, dataBetween, new MachesFetchDataCallback(matchView));
    }

    private void getHeaderTabTitle() {
        String leagueConfig = ConfigUtil.getOnlineConfig("league_config");

        Timber.i("leagueConfig|: " + leagueConfig);

        if (StringUtils.isEmptyString(leagueConfig)) {
            matchView.showHeaderTabTitle(null);
        } else {
            HeaderTabTitle headerTabTitle = JsonBinder.getInstance().fromJson(leagueConfig, HeaderTabTitle.class);
            matchView.showHeaderTabTitle(headerTabTitle);
        }
    }
}
