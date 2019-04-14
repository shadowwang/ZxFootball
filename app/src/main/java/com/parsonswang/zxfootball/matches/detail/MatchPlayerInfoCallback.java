package com.parsonswang.zxfootball.matches.detail;

import com.parsonswang.common.network.HtmlCallback;
import com.parsonswang.common.utils.StringUtils;
import com.parsonswang.zxfootball.bean.MatchPlayerStatInfo;
import com.parsonswang.zxfootball.matches.MatchContract;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import okhttp3.Call;
import timber.log.Timber;

public class MatchPlayerInfoCallback extends HtmlCallback {

    private MatchContract.IMatchPlayerInfoView mMatchPlayerInfoView;

    public MatchPlayerInfoCallback(MatchContract.IMatchPlayerInfoView matchPlayerInfoView) {
        this.mMatchPlayerInfoView = matchPlayerInfoView;
    }

    private  ArrayList<MatchPlayerStatInfo.PlayerStatInfo> getPlayerStatInfo(Element summary) {
        ArrayList<MatchPlayerStatInfo.PlayerStatInfo> playerStatInfos = new ArrayList<>();
        try {
            if (summary != null) {
                final Element homePlayerInfoTable = summary.select("table").get(0);
                final Elements trs = homePlayerInfoTable.select("tr");

                for (int i = 1; i < trs.size(); i++) {
                    MatchPlayerStatInfo.PlayerStatInfo playerStatInfo = new MatchPlayerStatInfo.PlayerStatInfo();
                    playerStatInfo.playerPageUrl = trs.get(i).select("a").attr("href");

                    final String avatarStr = trs.get(i).select("a").select("div.logo-md").attr("style");
                    playerStatInfo.avatar = StringUtils.getUrlFromString(avatarStr);

                    final Elements tds = trs.get(i).select("td");

                    playerStatInfo.desc = tds.get(1).select("span").get(1).text();
                    playerStatInfo.name = tds.get(1).select("div.limittext").text();
                    playerStatInfo.shootCnt = Integer.parseInt(tds.get(2).text());
                    playerStatInfo.shootOnTargetCnt = Integer.parseInt(tds.get(3).text());
                    playerStatInfo.keyPass = Integer.parseInt(tds.get(4).text());
                    playerStatInfo.psPercent = Float.parseFloat(tds.get(5).text());
                    playerStatInfo.makeChanceCnt = Integer.parseInt(tds.get(6).text());
                    playerStatInfo.headingDuelCnt = Integer.parseInt(tds.get(7).text());
                    playerStatInfo.touchBallCnt = Integer.parseInt(tds.get(8).text());
                    playerStatInfo.scoreGrade = Float.parseFloat(tds.get(9).text());
                    playerStatInfos.add(playerStatInfo);
                    Timber.i(playerStatInfo + "");
                }
            }
        } catch (Exception e) {
            Timber.e(e);
        }

        return playerStatInfos;
    }

    @Override
    protected void onSuccess(String s) {
        Document document = Jsoup.parse(s);
        ArrayList<MatchPlayerStatInfo.PlayerStatInfo> homePlayerStatInfos = null;
        ArrayList<MatchPlayerStatInfo.PlayerStatInfo> awayPlayerStatInfos = null;
        if (document != null) {
            final Element homeSummary = document.getElementById("summary");
            homePlayerStatInfos = getPlayerStatInfo(homeSummary);

            final Element awaySummary = document.getElementById("summary1");
            awayPlayerStatInfos = getPlayerStatInfo(awaySummary);
        }

        if (mMatchPlayerInfoView != null) {
            mMatchPlayerInfoView.getMatchPlayerInfo(homePlayerStatInfos, awayPlayerStatInfos );
        }
    }

    @Override
    protected void onFail(Call call, String reson) {

    }
}
