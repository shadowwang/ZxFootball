package com.parsonswang.zxfootball.matches.detail;

import android.util.Patterns;

import com.parsonswang.common.network.HtmlCallback;
import com.parsonswang.common.utils.StringUtils;
import com.parsonswang.zxfootball.bean.MatchPlayerStatInfo;
import com.parsonswang.zxfootball.matches.MatchContract;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.Time;
import java.util.ArrayList;
import java.util.regex.Matcher;

import okhttp3.Call;
import timber.log.Timber;

public class MatchPlayerInfoCallback extends HtmlCallback {

    private MatchContract.IMatchPlayerInfoView mMatchPlayerInfoView;

    public MatchPlayerInfoCallback(MatchContract.IMatchPlayerInfoView matchPlayerInfoView) {
        this.mMatchPlayerInfoView = matchPlayerInfoView;
    }

    @Override
    protected void onSuccess(String s) {
        Document document = Jsoup.parse(s);
        if (document != null) {
            final Element homeSummary = document.getElementById("summary");
            if (homeSummary != null) {
                final Element homePlayerInfoTable = homeSummary.select("table").get(0);
                final Elements trs = homePlayerInfoTable.select("tr");
                ArrayList<MatchPlayerStatInfo.PlayerStatInfo> homePlayerInfo = new ArrayList<>();

                for (int i = 1; i < trs.size(); i++) {
                    MatchPlayerStatInfo.PlayerStatInfo playerStatInfo = new MatchPlayerStatInfo.PlayerStatInfo();
                    playerStatInfo.playerPageUrl = trs.get(i).select("a").attr("href");

                    final String avatarStr = trs.get(i).select("a").select("div.logo-md").attr("style");
                    playerStatInfo.avatar = StringUtils.getUrlFromString(avatarStr);

                    final String playerInfoTxt = trs.get(i).text();
                    String[] playerInfos = playerInfoTxt.split(" ");
                    playerStatInfo.name = playerInfos[1];
                    playerStatInfo.age = Integer.parseInt(playerInfos[2]);
                    playerStatInfo.location = playerInfos[3];
                    playerStatInfo.shootCnt = Integer.parseInt(playerInfos[4]);
                    playerStatInfo.shootOnTargetCnt = Integer.parseInt(playerInfos[5]);
                    

                }
            }


            final Element awaySummary = document.getElementById("summary1");
            if (awaySummary != null) {
                final Element awayPlaerInfoTable = awaySummary.select("table").get(0);
                //Timber.i(awayPlaerInfoTable.toString());
            }
        }
    }

    @Override
    protected void onFail(Call call, String reson) {

    }
}
