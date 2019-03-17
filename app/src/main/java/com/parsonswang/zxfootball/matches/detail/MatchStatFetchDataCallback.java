package com.parsonswang.zxfootball.matches.detail;

import com.google.gson.reflect.TypeToken;
import com.parsonswang.common.network.HtmlCallback;
import com.parsonswang.common.utils.JsonObjectMap;
import com.parsonswang.common.utils.StringUtils;
import com.parsonswang.zxfootball.bean.GoalPlayers;
import com.parsonswang.zxfootball.bean.MatchStatBean;
import com.parsonswang.zxfootball.bean.MatchTimelines;
import com.parsonswang.zxfootball.bean.PlayerInfo;
import com.parsonswang.zxfootball.bean.PlayerStatics;
import com.parsonswang.zxfootball.common.Constant;
import com.parsonswang.zxfootball.matches.MatchContract;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import timber.log.Timber;

/**
 * Created by parsonswang on 2018/2/5.
 */

public class MatchStatFetchDataCallback extends HtmlCallback {

    private MatchContract.IMatchStatView mMatchStatView;

    public MatchStatFetchDataCallback(MatchContract.IMatchStatView matchStatView) {
        this.mMatchStatView = matchStatView;
    }

    @Override
    protected void onSuccess(String s) {
        Document document = Jsoup.parse(s);

        final MatchStatBean matchStatBean = getMatchTimelines(document);
        mMatchStatView.getMatchTimelineInfo(matchStatBean);

        final GoalPlayers goalPlayers = getGoalPlayers(matchStatBean);
        mMatchStatView.getGoalPlayersInfo(goalPlayers);
    }

    @Override
    protected void onFail(Call call, String reson) {

    }

    private int getMatchStatIndex(Document document) {
        Elements elements = document.getElementsByTag("script");
        int index = 0;
        for (int i = elements.size() - 1; i >= 0; i--) {
            final Element element = elements.get(i);
            String text = element.toString();
            if (text.contains("timelines")) {
                index = i;
                break;
            }
        }
        Timber.i("getMatchStatIndex: " + index);
        return index;
    }

    private MatchStatBean getMatchTimelines(Document document) {
        MatchStatBean matchStatBean = new MatchStatBean();

        Elements jsValElements = document.getElementsByTag("script").eq(getMatchStatIndex(document));

        for (Element element : jsValElements) {
            //var表达式：var keyName = values;
            final String[] jsVarExps = element.data().split("var");
            final Map<String, String> kvMap = StringUtils.kvToKeyString(jsVarExps, "=");

            //==========time lines===========================
            String timelinsJsonArrayStr = kvMap.get("timelines");
            if (!StringUtils.isEmptyString(timelinsJsonArrayStr)) {
                if (timelinsJsonArrayStr.lastIndexOf(";") != -1) {
                    timelinsJsonArrayStr = timelinsJsonArrayStr.substring(0, timelinsJsonArrayStr.length() - 1);
                }
                matchStatBean.matchTimelinesList = JsonObjectMap.getInstance().fromJson(timelinsJsonArrayStr,  new TypeToken<List<MatchTimelines>>(){}.getType());
            }

            //==========主队id===========================
            String homeTeamId = kvMap.get("homeTeamId");
            if (!StringUtils.isEmptyString(homeTeamId)) {
                if (homeTeamId.startsWith("'") && homeTeamId.endsWith("';")) {
                    homeTeamId = homeTeamId.substring(1, homeTeamId.length() - 2);
                    matchStatBean.homeTeamId = homeTeamId;
                }
            }

            //==========客队id===========================
            String awayTeamId = kvMap.get("awayTeamId");
            if (!StringUtils.isEmptyString(awayTeamId)) {
                if (awayTeamId.startsWith("'") && awayTeamId.endsWith("';")) {
                    awayTeamId = awayTeamId.substring(1, awayTeamId.length() - 2);
                    matchStatBean.awayTeamId = awayTeamId;
                }
            }

            //主队阵容
            String homePlayerStatistics = kvMap.get("homePlayerStatistics");
            if (!StringUtils.isEmptyString(homePlayerStatistics)) {
                if (homePlayerStatistics.lastIndexOf(";") != -1) {
                    homePlayerStatistics = homePlayerStatistics.substring(0, homePlayerStatistics.length() - 1);
                }
                matchStatBean.homePlayerStaticsList = JsonObjectMap.getInstance().fromJson(homePlayerStatistics,  new TypeToken<List<PlayerStatics>>(){}.getType());
            }

            //客队阵容
            String awayPlayerStatistics = kvMap.get("awayPlayerStatistics");
            if (!StringUtils.isEmptyString(awayPlayerStatistics)) {
                if (awayPlayerStatistics.lastIndexOf(";") != -1) {
                    awayPlayerStatistics = awayPlayerStatistics.substring(0, awayPlayerStatistics.length() - 1);
                }
                matchStatBean.awayPlayerStaticsList = JsonObjectMap.getInstance().fromJson(awayPlayerStatistics,  new TypeToken<List<PlayerStatics>>(){}.getType());
            }
        }

        //得到主队首发阵容
        Element homeTeamSummary = document.getElementById("match-summary-header-home-team");
        //阵型
        matchStatBean.homeTeamFormation = homeTeamSummary.select("div.team-formation").text();

        final List<PlayerStatics> homePlayerStaticsList = matchStatBean.homePlayerStaticsList;
        getMatchPlayerInfo(homePlayerStaticsList, matchStatBean, true);

        //得到客队首发阵容
        Element awayTeamSummary = document.getElementById("match-summary-header-away-team");
        matchStatBean.awayTeamFormation = awayTeamSummary.select("div.team-formation").text();

        final List<PlayerStatics> awayPlayerStaticsList = matchStatBean.awayPlayerStaticsList;
        getMatchPlayerInfo(awayPlayerStaticsList, matchStatBean, false);

        return matchStatBean;
    }

    private void getMatchPlayerInfo(List<PlayerStatics> playerStaticsList, MatchStatBean matchStatBean,boolean isHome) {
        final LinkedList<PlayerInfo> mainPlayerInfos = new LinkedList<>();
        final LinkedList<PlayerInfo> benchPlayerInfos = new LinkedList<>();
        for (PlayerStatics ps : playerStaticsList) {
            PlayerInfo playerInfo = new PlayerInfo();
            playerInfo.playerName = ps.getPlayerName();
            playerInfo.shirtNum = ps.getShirtNo();
            playerInfo.playerId = ps.getPlayerId();
            playerInfo.teamId = ps.getTeamId();
            playerInfo.avatarUrl = String.format(Constant.PlayerConstant.PLAYER_AVATAR_URL, playerInfo.playerId);
            if (ps.isIsFirstEleven()) {
                mainPlayerInfos.add(playerInfo);
            } else {
                benchPlayerInfos.add(playerInfo);
            }
        }
        if (isHome) {
            matchStatBean.homeMainPlayerInfos = mainPlayerInfos;
            matchStatBean.homeBenchPlayerInfos = benchPlayerInfos;
        } else {
            matchStatBean.awayMainPlayerInfos  = mainPlayerInfos;
            matchStatBean.awayBenchPlayerInfos = benchPlayerInfos;
        }
    }

    private GoalPlayers getGoalPlayers(MatchStatBean matchStatBean) {
        GoalPlayers goalPlayers = new GoalPlayers();

        final String homeTeamId = matchStatBean.homeTeamId;
        final String awayTeamId = matchStatBean.awayTeamId;

        StringBuilder homeTeamGoalPlayers = new StringBuilder();
        StringBuilder awayTeamGoalPlayers = new StringBuilder();

        for (MatchTimelines matchTimeline : matchStatBean.matchTimelinesList) {
            if (matchTimeline.teamId.equals(homeTeamId)
                    && (matchTimeline.eventType == Constant.MatchTimelineEventType.EVENTTYPE_GOAL
                    || matchTimeline.eventType == Constant.MatchTimelineEventType.EVENTTYPE_GOAL_DIAN)
                    || matchTimeline.eventType == Constant.MatchTimelineEventType.EVENTTYPE_WULONG_GOAL) {

                homeTeamGoalPlayers.append("(" + matchTimeline.minute + "')" + matchTimeline.playerName);
                if (matchTimeline.eventType == Constant.MatchTimelineEventType.EVENTTYPE_WULONG_GOAL) {
                    homeTeamGoalPlayers.append("(乌龙)");
                }
                homeTeamGoalPlayers.append("\r\n");
            } else if (matchTimeline.teamId.equals(awayTeamId)
                    && (matchTimeline.eventType == Constant.MatchTimelineEventType.EVENTTYPE_GOAL
                    || matchTimeline.eventType == Constant.MatchTimelineEventType.EVENTTYPE_GOAL_DIAN
                    || matchTimeline.eventType == Constant.MatchTimelineEventType.EVENTTYPE_WULONG_GOAL)) {

                awayTeamGoalPlayers.append("(" + matchTimeline.minute + "')" + matchTimeline.playerName);
                if (matchTimeline.eventType == Constant.MatchTimelineEventType.EVENTTYPE_WULONG_GOAL) {
                    awayTeamGoalPlayers.append("(乌龙)");
                }
                awayTeamGoalPlayers.append("\r\n");
            }
        }

        goalPlayers.homeGoalPlayers = homeTeamGoalPlayers.toString();
        goalPlayers.awayGoalPlayers = awayTeamGoalPlayers.toString();

        return goalPlayers;
    }
}
