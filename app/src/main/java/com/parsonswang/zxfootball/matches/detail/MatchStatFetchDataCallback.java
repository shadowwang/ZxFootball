package com.parsonswang.zxfootball.matches.detail;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.parsonswang.common.network.HtmlCallback;
import com.parsonswang.common.utils.JsonObjectMap;
import com.parsonswang.common.utils.StringUtils;
import com.parsonswang.zxfootball.bean.GoalPlayers;
import com.parsonswang.zxfootball.bean.MatchTimelines;
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

        final List<MatchTimelines> matchTimelines = getMatchTimelines(document);
        Timber.d(matchTimelines.toString());

        final GoalPlayers goalPlayers = getGoalPlayers(document);
        mMatchStatView.getGoalPlayersInfo(goalPlayers);
    }

    @Override
    protected void onFail(Call call, String reson) {

    }

    private List<MatchTimelines> getMatchTimelines(Document document) {
        List<MatchTimelines> matchTimelinesList = new ArrayList<>();

        Elements jsValElements = document.getElementsByTag("script").eq(16);

        for (Element element : jsValElements) {
            //var表达式：var keyName = values;
            final String[] jsVarExps = element.data().split("var");
            final Map<String, String> kvMap = StringUtils.kvToKeyString(jsVarExps, "=");
            final String timelinsJsonArrayStr = kvMap.get("timelines");
            if (!StringUtils.isEmptyString(timelinsJsonArrayStr)) {
                matchTimelinesList = JsonObjectMap.getInstance().fromJson(timelinsJsonArrayStr,  new TypeToken<List<MatchTimelines>>(){}.getType());
                break;
            }
        }

        return matchTimelinesList;
    }

    private GoalPlayers getGoalPlayers(Document document) {
        GoalPlayers goalPlayers = new GoalPlayers();



        return goalPlayers;
    }
}
