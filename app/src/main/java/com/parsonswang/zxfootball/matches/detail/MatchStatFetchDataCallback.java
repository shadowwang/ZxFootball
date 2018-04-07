package com.parsonswang.zxfootball.matches.detail;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.parsonswang.common.network.HtmlCallback;
import com.parsonswang.common.utils.JsonObjectMap;
import com.parsonswang.common.utils.StringUtils;
import com.parsonswang.zxfootball.bean.GoalPlayers;
import com.parsonswang.zxfootball.bean.MatchStatBean;
import com.parsonswang.zxfootball.bean.MatchTimelines;
import com.parsonswang.zxfootball.common.Constant;
import com.parsonswang.zxfootball.matches.MatchContract;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
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

        final GoalPlayers goalPlayers = getGoalPlayers(matchStatBean);
        mMatchStatView.getGoalPlayersInfo(goalPlayers);
    }

    @Override
    protected void onFail(Call call, String reson) {

    }

    private MatchStatBean getMatchTimelines(Document document) {
        MatchStatBean matchStatBean = new MatchStatBean();

        Elements jsValElements = document.getElementsByTag("script").eq(16);

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
        }

        return matchStatBean;
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
                    || matchTimeline.eventType == Constant.MatchTimelineEventType.EVENTTYPE_GOAL_DIAN)) {

                homeTeamGoalPlayers.append("(" + matchTimeline.minute + "')" + matchTimeline.playerName).append("\r\n");
            }

            if (matchTimeline.teamId.equals(awayTeamId)
                    && (matchTimeline.eventType == Constant.MatchTimelineEventType.EVENTTYPE_GOAL
                    || matchTimeline.eventType == Constant.MatchTimelineEventType.EVENTTYPE_GOAL_DIAN)) {

                awayTeamGoalPlayers.append("(" + matchTimeline.minute + "')" + matchTimeline.playerName).append("\r\n");
            }
        }

        goalPlayers.homeGoalPlayers = homeTeamGoalPlayers.toString();
        goalPlayers.awayGoalPlayers = awayTeamGoalPlayers.toString();

        return goalPlayers;
    }
}
