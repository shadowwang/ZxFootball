package com.parsonswang.zxfootball.matches.detail;

import com.parsonswang.common.network.HtmlCallback;
import com.parsonswang.zxfootball.matches.MatchContract;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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
            Elements elements = document.select("div.col-xs-8");
            Timber.i(elements.toString());
        }
    }

    @Override
    protected void onFail(Call call, String reson) {

    }
}
