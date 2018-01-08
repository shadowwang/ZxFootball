package com.parsonswang.zxfootball.matches.detail;


import com.parsonswang.common.network.HtmlCallback;
import com.parsonswang.zxfootball.bean.MatchDetailHeaderInfoBean;
import com.parsonswang.zxfootball.matches.MatchContract;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import okhttp3.Call;
import timber.log.Timber;

/**
 * Created by wangchun on 2017/12/23.
 */

public class MatchDetailsFetchDataCallback extends HtmlCallback{

    private MatchContract.IMatchDetailView matchDetailView;

    public MatchDetailsFetchDataCallback(MatchContract.IMatchDetailView matchDetailView) {
        this.matchDetailView = matchDetailView;
    }

    @Override
    protected void onSuccess(String s) {
        final Document document = Jsoup.parse(s);

        //得到比赛头部信息
        MatchDetailHeaderInfoBean matchDetailHeaderInfoBean = getMatchHeaderBean(document);
        if (matchDetailHeaderInfoBean == null) {
            matchDetailView.showExceptionView();
        } else {
            matchDetailView.showMatchInfoHeader(matchDetailHeaderInfoBean);
        }

        //得到比赛总结

    }

    @Override
    protected void onFail(Call call, String reson) {
        matchDetailView.showExceptionView();
    }

    private MatchDetailHeaderInfoBean getMatchHeaderBean(Document document) {
        MatchDetailHeaderInfoBean detailHeaderInfoBean = new MatchDetailHeaderInfoBean();

        final Elements trs = document.select("div.match-header").select("table");

        //============球队名称============
        final Elements teamNameElements = trs.select("td.team-name");
        if (teamNameElements.size() < 2) {
            return null;
        }

        String homeTeamName = teamNameElements.get(0).text();
        detailHeaderInfoBean.homeTeamName = homeTeamName;
        String awayTeamName = teamNameElements.get(1).text();
        detailHeaderInfoBean.awayTeamName = awayTeamName;

        String homeTeamUrl = teamNameElements.get(0).select("a").attr("href");
        detailHeaderInfoBean.homeTeamUrl = homeTeamUrl;
        String awayTeamUrl = teamNameElements.get(1).select("a").attr("href");
        detailHeaderInfoBean.awayTeamUrl = awayTeamUrl;

        //============球队队徽=================
        final Elements teamPicElements = trs.select("td.team-pic");
        if (teamPicElements.size() < 2) {
            return null;
        }

        String homeTeamLogo = teamPicElements.get(0).select("img").attr("src");
        detailHeaderInfoBean.homeTeamLogo = homeTeamLogo;
        String awayTeamLogo = teamPicElements.get(1).select("img").attr("src");
        detailHeaderInfoBean.awayTeamLogo = awayTeamLogo;

        Elements matchInfoTrs = trs.select("td.match-info").select("table").select("tr");

        String lun = matchInfoTrs.select("td.stat-box").text();
        detailHeaderInfoBean.matchLun = lun;

        String matchStatus = matchInfoTrs.get(1).select("td.col2").text();
        detailHeaderInfoBean.matchStatus = matchStatus;

        String halfTimeScore = matchInfoTrs.get(2).select("td.col2").text();
        detailHeaderInfoBean.halfTimeScore = halfTimeScore;

        String endTimeScore = matchInfoTrs.get(3).select("td.col2").text();
        detailHeaderInfoBean.endTimeScore = endTimeScore;

        String matchTime = matchInfoTrs.get(4).select("td.col3").text();
        detailHeaderInfoBean.matchTime = matchTime;

        return detailHeaderInfoBean;
    }
}
