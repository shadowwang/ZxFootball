package com.parsonswang.zxfootball.matches.detail;


import com.parsonswang.common.network.HtmlCallback;
import com.parsonswang.common.utils.StringUtils;
import com.parsonswang.zxfootball.bean.MatchDetailHeaderInfoBean;
import com.parsonswang.zxfootball.bean.MatchPassBean;
import com.parsonswang.zxfootball.bean.MatchShortBean;
import com.parsonswang.zxfootball.bean.MatchSummary;
import com.parsonswang.zxfootball.matches.MatchContract;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.Call;
import timber.log.Timber;

/**
 * Created by wangchun on 2017/12/23.
 */

public class MatchDetailsFetchDataCallback extends HtmlCallback {

    private MatchContract.IMatchDetailView matchDetailView;

    public MatchDetailsFetchDataCallback(MatchContract.IMatchDetailView matchDetailView) {
        this.matchDetailView = matchDetailView;
    }

    @Override
    protected void onSuccess(String s) {
        final Document document = Jsoup.parse(s);

        //得到比赛头部信息
        MatchDetailHeaderInfoBean matchDetailHeaderInfoBean = getMatchHeaderBean(document);

        if (matchDetailView == null) {
            Timber.e("matchDetailView is null");
            return;
        }

        if (matchDetailHeaderInfoBean == null) {
            matchDetailView.showExceptionView();
        } else {
            Timber.i(matchDetailHeaderInfoBean.toString());
            matchDetailView.showMatchInfoHeader(matchDetailHeaderInfoBean);
        }

        //得到比赛总结
        MatchSummary matchSummary = getMatchSumary(document);
        matchDetailView.showMatchSummary(matchSummary);
        Timber.i(matchSummary.toString());

        //得到射门数据
        MatchShortBean matchShortBean = getMatchShortBean(document);
        Timber.i(matchShortBean.toString());

        //得到传球数据
        MatchPassBean matchPassBean = getMatchPassBean(document);
        Timber.i(matchPassBean.toString());

    }

    @Override
    protected void onFail(Call call, String reson) {
        matchDetailView.showExceptionView();
    }


    /**
     * 得到传球数据
     * @param document
     * @return
     */
    private MatchPassBean getMatchPassBean(Document document) {
        MatchPassBean matchPassBean = new MatchPassBean();

        final Element matchShortRoot = document.getElementById("passesTab");
        final Elements matchShortElements = matchShortRoot.select("div.team-stats-group");
        final Element matchShortDataRoot = matchShortElements.get(0);
        final Elements matchShortDatas = matchShortDataRoot.select("div.team-stat");
        for (Element element : matchShortDatas) {
            String attr = element.attr("data-type");
            final Elements statDataElement = element.select("span.team-stat-data");
            final String homeData = statDataElement.get(0).text();
            final String awayData = statDataElement.get(1).text();

            if ("total".equals(attr)) {
                matchPassBean.homePassTotal = homeData;
                matchPassBean.awayPassTotal = awayData;
            } else if ("short".equals(attr)) {
                matchPassBean.homeShortPass = homeData;
                matchPassBean.awayShortPass = awayData;
            } else if ("long".equals(attr)) {
                matchPassBean.homeLongPass = homeData;
                matchPassBean.awayLongPass = awayData;
            } else if ("cross".equals(attr)) {
                matchPassBean.homeCrossPass = homeData;
                matchPassBean.awayCrossPass = awayData;
            } else if ("through".equals(attr)) {
                matchPassBean.homeThroughPass = homeData;
                matchPassBean.awayThroughPass = awayData;
            }
        }

        final Elements matchTotalShortRoot = matchShortRoot.select("div.team-stats-table");
        final Elements matchTotalDataRoot = matchTotalShortRoot.select("div.team-stat");

        final Elements matchGoalDataTotalRoot = matchTotalDataRoot.get(1).select("span.stat-compare-block").get(0).select("span.stat-compare-data");
        matchPassBean.homeTotalPer = matchGoalDataTotalRoot.get(0).text();
        matchPassBean.awayTotalPer = matchGoalDataTotalRoot.get(1).text();

        return matchPassBean;

    }

    /**
     * 比赛射门数据
     * @param document
     * @return
     */
    private MatchShortBean getMatchShortBean(Document document) {
        MatchShortBean matchShortBean = new MatchShortBean();
        final Element matchShortRoot = document.getElementById("shotsTab");
        final Elements matchShortElements = matchShortRoot.select("div.team-stats-group");
        final Element matchShortDataRoot = matchShortElements.get(0);
        final Elements matchShortDatas = matchShortDataRoot.select("div.team-stat");
        for (Element element : matchShortDatas) {
            String attr = element.attr("data-type");
            final Elements statDataElement = element.select("span.team-stat-data");
            final String homeData = statDataElement.get(0).text();
            final String awayData = statDataElement.get(1).text();

            if ("total".equals(attr)) {
                matchShortBean.homeTotalShort = homeData;
                matchShortBean.awayTotalShort = awayData;
            } else if ("openPlay".equals(attr)) {
                matchShortBean.homeOpenPlay = homeData;
                matchShortBean.awayOpenPlay = awayData;
            } else if ("setPiece".equals(attr)) {
                matchShortBean.homeSetPiece = homeData;
                matchShortBean.awaySetPiece = awayData;
            } else if ("fastBrk".equals(attr)) {
                matchShortBean.homeFastBrk = homeData;
                matchShortBean.awayFastBrk = awayData;
            } else if ("penalty".equals(attr)) {
                matchShortBean.homePenalty = homeData;
                matchShortBean.awayPenalty = awayData;
            } else if ("ownGoal".equals(attr)) {
                matchShortBean.homeOwnGoal = homeData;
                matchShortBean.awayOwnGoal = awayData;
            }
        }

        final Elements matchTotalShortRoot = matchShortRoot.select("div.team-stats-table");
        final Elements matchTotalDataRoot = matchTotalShortRoot.select("div.team-stat");

        final Elements matchShortDataTotalRoot = matchTotalDataRoot.get(0).select("span.stat-compare-block").get(0).select("span.stat-compare-data");
        matchShortBean.homeTotalShort = matchShortDataTotalRoot.get(0).text();
        matchShortBean.awayTotalShort = matchShortDataTotalRoot.get(1).text();

        final Elements matchGoalDataTotalRoot = matchTotalDataRoot.get(1).select("span.stat-compare-block").get(0).select("span.stat-compare-data");
        matchShortBean.homeTotalGoal = matchGoalDataTotalRoot.get(0).text();
        matchShortBean.awayTotalGoal = matchGoalDataTotalRoot.get(1).text();

        final Elements matchGoalRateDataTotalRoot = matchTotalDataRoot.get(2).select("span.stat-compare-block").get(0).select("span.stat-compare-data");
        matchShortBean.homeGoalRate = matchGoalRateDataTotalRoot.get(0).text();
        matchShortBean.awayGoalRate = matchGoalRateDataTotalRoot.get(1).text();

        Timber.i(matchGoalDataTotalRoot.toString());

        return matchShortBean;
    }
    /**
     * 得到比赛总结
     * @param document
     * @return
     */
    private MatchSummary getMatchSumary(Document document) {
        MatchSummary matchSummary = new MatchSummary();

        //没有比赛总结
        final Elements matchSumaryElements = document.select("div.match-character");
        if (matchSumaryElements == null) {
            return null;
        }

        final Elements tables = matchSumaryElements.select("table");
        if (tables == null) {
            return null;
        }

        final Elements types = tables.select("tr.match-character-type");
        int[] headerIndexInTable = new int[types.size()];
        for (int i = 0; i < types.size(); i++) {
            final int index = types.get(i).elementSiblingIndex();
            Timber.i("elementSiblingIndex: " + index);
            headerIndexInTable[i] = index;
        }

        Map<Integer, Integer> mSparseIntArray = new LinkedHashMap<>();

        int lastEndIndex = 0;
        for (int i = 0, j = 0; i < headerIndexInTable.length && j < headerIndexInTable.length; i = i + j, j++) {
            final int startIndex = headerIndexInTable[i];
            final int endIndex = headerIndexInTable[j];
            mSparseIntArray.put(startIndex, endIndex);
            lastEndIndex = endIndex;
        }

        final int trSize = tables.select("tr").size();
        if (lastEndIndex < trSize) {
            mSparseIntArray.put(lastEndIndex, trSize);
        }

        int index = 0;
        for (Map.Entry<Integer, Integer> entry: mSparseIntArray.entrySet()) {
            int startIndex = entry.getKey();
            int endIndex = entry.getValue();

            for (int i = startIndex + 1; i < endIndex; i++) {
                final Element element = tables.select("tr").get(i);

                String homeText = element.select("td").get(0).text();
                String awayText = element.select("td").get(1).text();

                switch (index) {
                    //强项
                    case 0:
                        if (!StringUtils.isEmptyString(homeText)) {
                            matchSummary.homeStronger.add(homeText);
                        }

                        if (!StringUtils.isEmptyString(awayText)) {
                            matchSummary.awayStronger.add(awayText);
                        }
                        break;
                    case 1:
                        if (!StringUtils.isEmptyString(homeText)) {
                            matchSummary.homeWeaker.add(homeText);
                        }

                        if (!StringUtils.isEmptyString(awayText)) {
                            matchSummary.awayWeaker.add(awayText);
                        }
                        break;
                    case 2:
                        if (!StringUtils.isEmptyString(homeText)) {
                            matchSummary.homeMatchStyle.add(homeText);
                        }

                        if (!StringUtils.isEmptyString(awayText)) {
                            matchSummary.awayMatchStyle.add(awayText);
                        }
                        break;
                }
            }
            index ++;
        }


        return matchSummary;
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
