package com.parsonswang.zxfootball.matches;

import com.parsonswang.zxfootball.bean.HeaderTabTitle;
import com.parsonswang.zxfootball.bean.MatchesBean;

/**
 * Created by wangchun on 2017/10/22.
 */

public class MatchContract {

    public interface IMatchView {

        public void showHeaderTabTitle(HeaderTabTitle headerTabTitle);

    }

    public interface IMatchInfoView {
        public void showMatchInfoList(MatchesBean matchesBean);
    }

    public interface IMatchPresenter {

        void getMatchInfos(String comeptitionId, String dataBetween);
    }
}
