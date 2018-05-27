package com.parsonswang.zxfootball.bean;

import java.util.List;

/**
 * Created by parsonswang on 2017/10/20.
 */

public class MatchesBean {
    /**
     * datas : [{"stageName":"第20轮","competitionPositiion":6,"score":"1 : 0","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5055,"matchDate":"2017-08-05","awayTeamId":215,"homeTeamId":627,"isFinish":true,"dateNumOfWeek":"星期六","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"重庆力帆","homeTeamName":"贵州恒丰智诚"},{"stageName":"第20轮","competitionPositiion":6,"score":"2 : 2","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5057,"matchDate":"2017-08-05","awayTeamId":48,"homeTeamId":53,"isFinish":true,"dateNumOfWeek":"星期六","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"河南建业","homeTeamName":"江苏苏宁"},{"stageName":"第20轮","competitionPositiion":6,"score":"3 : 0","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5058,"matchDate":"2017-08-05","awayTeamId":46,"homeTeamId":41,"isFinish":true,"dateNumOfWeek":"星期六","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"天津泰达","homeTeamName":"广州恒大"},{"stageName":"第20轮","competitionPositiion":6,"score":"2 : 2","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5059,"matchDate":"2017-08-05","awayTeamId":49,"homeTeamId":42,"isFinish":true,"dateNumOfWeek":"星期六","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"山东鲁能","homeTeamName":"北京国安"},{"stageName":"第20轮","competitionPositiion":6,"score":"3 : 0","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5060,"matchDate":"2017-08-05","awayTeamId":216,"homeTeamId":213,"isFinish":true,"dateNumOfWeek":"星期六","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"延边富德","homeTeamName":"河北华夏幸福"},{"stageName":"第20轮","competitionPositiion":6,"score":"1 : 1","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5266,"matchDate":"2017-08-05","awayTeamId":44,"homeTeamId":50,"isFinish":true,"dateNumOfWeek":"星期六","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"长春亚泰","homeTeamName":"上海绿地申花"},{"stageName":"第20轮","competitionPositiion":6,"score":"0 : 0","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5062,"matchDate":"2017-08-06","awayTeamId":626,"homeTeamId":45,"isFinish":true,"dateNumOfWeek":"星期日","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"天津权健","homeTeamName":"上海上港"},{"stageName":"第20轮","competitionPositiion":6,"score":"4 : 1","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5264,"matchDate":"2017-08-06","awayTeamId":47,"homeTeamId":51,"isFinish":true,"dateNumOfWeek":"星期日","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"辽宁宏运","homeTeamName":"广州富力"},{"stageName":"第21轮","competitionPositiion":6,"score":"3 : 1","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5063,"matchDate":"2017-08-09","awayTeamId":46,"homeTeamId":216,"isFinish":true,"dateNumOfWeek":"星期三","startHMStr":"16:00","competitionNameFull":"中国足球超级联赛","awayTeamName":"天津泰达","homeTeamName":"延边富德"},{"stageName":"第21轮","competitionPositiion":6,"score":"4 : 2","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5064,"matchDate":"2017-08-09","awayTeamId":53,"homeTeamId":51,"isFinish":true,"dateNumOfWeek":"星期三","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"江苏苏宁","homeTeamName":"广州富力"},{"stageName":"第21轮","competitionPositiion":6,"score":"0 : 2","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5065,"matchDate":"2017-08-09","awayTeamId":44,"homeTeamId":48,"isFinish":true,"dateNumOfWeek":"星期三","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"长春亚泰","homeTeamName":"河南建业"},{"stageName":"第21轮","competitionPositiion":6,"score":"1 : 0","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5066,"matchDate":"2017-08-09","awayTeamId":49,"homeTeamId":215,"isFinish":true,"dateNumOfWeek":"星期三","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"山东鲁能","homeTeamName":"重庆力帆"},{"stageName":"第21轮","competitionPositiion":6,"score":"0 : 3","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5067,"matchDate":"2017-08-09","awayTeamId":627,"homeTeamId":50,"isFinish":true,"dateNumOfWeek":"星期三","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"贵州恒丰智诚","homeTeamName":"上海绿地申花"},{"stageName":"第21轮","competitionPositiion":6,"score":"2 : 2","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5068,"matchDate":"2017-08-10","awayTeamId":42,"homeTeamId":626,"isFinish":true,"dateNumOfWeek":"星期四","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"北京国安","homeTeamName":"天津权健"},{"stageName":"第21轮","competitionPositiion":6,"score":"0 : 3","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5069,"matchDate":"2017-08-10","awayTeamId":41,"homeTeamId":47,"isFinish":true,"dateNumOfWeek":"星期四","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"广州恒大","homeTeamName":"辽宁宏运"},{"stageName":"第21轮","competitionPositiion":6,"score":"2 : 2","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5070,"matchDate":"2017-08-10","awayTeamId":45,"homeTeamId":213,"isFinish":true,"dateNumOfWeek":"星期四","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"上海上港","homeTeamName":"河北华夏幸福"},{"stageName":"第22轮","competitionPositiion":6,"score":"5 : 0","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5071,"matchDate":"2017-08-12","awayTeamId":50,"homeTeamId":49,"isFinish":true,"dateNumOfWeek":"星期六","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"上海绿地申花","homeTeamName":"山东鲁能"},{"stageName":"第22轮","competitionPositiion":6,"score":"2 : 0","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5072,"matchDate":"2017-08-13","awayTeamId":51,"homeTeamId":627,"isFinish":true,"dateNumOfWeek":"星期日","startHMStr":"15:30","competitionNameFull":"中国足球超级联赛","awayTeamName":"广州富力","homeTeamName":"贵州恒丰智诚"},{"stageName":"第22轮","competitionPositiion":6,"score":"1 : 1","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5076,"matchDate":"2017-08-13","awayTeamId":216,"homeTeamId":44,"isFinish":true,"dateNumOfWeek":"星期日","startHMStr":"16:00","competitionNameFull":"中国足球超级联赛","awayTeamName":"延边富德","homeTeamName":"长春亚泰"},{"stageName":"第22轮","competitionPositiion":6,"score":"4 : 0","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5073,"matchDate":"2017-08-13","awayTeamId":47,"homeTeamId":42,"isFinish":true,"dateNumOfWeek":"星期日","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"辽宁宏运","homeTeamName":"北京国安"},{"stageName":"第22轮","competitionPositiion":6,"score":"3 : 2","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5074,"matchDate":"2017-08-13","awayTeamId":215,"homeTeamId":45,"isFinish":true,"dateNumOfWeek":"星期日","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"重庆力帆","homeTeamName":"上海上港"},{"stageName":"第22轮","competitionPositiion":6,"score":"2 : 1","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5077,"matchDate":"2017-08-13","awayTeamId":48,"homeTeamId":41,"isFinish":true,"dateNumOfWeek":"星期日","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"河南建业","homeTeamName":"广州恒大"},{"stageName":"第22轮","competitionPositiion":6,"score":"0 : 2","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5075,"matchDate":"2017-08-14","awayTeamId":213,"homeTeamId":46,"isFinish":true,"dateNumOfWeek":"星期一","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"河北华夏幸福","homeTeamName":"天津泰达"},{"stageName":"第23轮","competitionPositiion":6,"score":"0 : 4","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5079,"matchDate":"2017-08-19","awayTeamId":53,"homeTeamId":216,"isFinish":true,"dateNumOfWeek":"星期六","startHMStr":"16:00","competitionNameFull":"中国足球超级联赛","awayTeamName":"江苏苏宁","homeTeamName":"延边富德"},{"stageName":"第23轮","competitionPositiion":6,"score":"2 : 1","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5083,"matchDate":"2017-08-19","awayTeamId":45,"homeTeamId":627,"isFinish":true,"dateNumOfWeek":"星期六","startHMStr":"17:30","competitionNameFull":"中国足球超级联赛","awayTeamName":"上海上港","homeTeamName":"贵州恒丰智诚"},{"stageName":"第23轮","competitionPositiion":6,"score":"2 : 0","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5080,"matchDate":"2017-08-19","awayTeamId":42,"homeTeamId":213,"isFinish":true,"dateNumOfWeek":"星期六","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"北京国安","homeTeamName":"河北华夏幸福"},{"stageName":"第23轮","competitionPositiion":6,"score":"0 : 3","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5081,"matchDate":"2017-08-19","awayTeamId":41,"homeTeamId":50,"isFinish":true,"dateNumOfWeek":"星期六","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"广州恒大","homeTeamName":"上海绿地申花"},{"stageName":"第23轮","competitionPositiion":6,"score":"1 : 1","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5082,"matchDate":"2017-08-19","awayTeamId":46,"homeTeamId":47,"isFinish":true,"dateNumOfWeek":"星期六","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"天津泰达","homeTeamName":"辽宁宏运"},{"stageName":"第23轮","competitionPositiion":6,"score":"1 : 1","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5085,"matchDate":"2017-08-20","awayTeamId":626,"homeTeamId":44,"isFinish":true,"dateNumOfWeek":"星期日","startHMStr":"16:00","competitionNameFull":"中国足球超级联赛","awayTeamName":"天津权健","homeTeamName":"长春亚泰"},{"stageName":"第23轮","competitionPositiion":6,"score":"0 : 0","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5084,"matchDate":"2017-08-20","awayTeamId":215,"homeTeamId":48,"isFinish":true,"dateNumOfWeek":"星期日","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"重庆力帆","homeTeamName":"河南建业"},{"stageName":"第23轮","competitionPositiion":6,"score":"1 : 1","competitionName":"中超","competitionId":3,"hasReport":true,"competitionCountry":"China","id":5086,"matchDate":"2017-08-20","awayTeamId":49,"homeTeamId":51,"isFinish":true,"dateNumOfWeek":"星期日","startHMStr":"19:35","competitionNameFull":"中国足球超级联赛","awayTeamName":"山东鲁能","homeTeamName":"广州富力"}]
     * startDate : 2017.08.01
     * endDate : 2017.08.31
     */

    private String startDate;
    private String endDate;
    private List<MatchInfo> datas;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<MatchInfo> getDatas() {
        return datas;
    }

    public void setDatas(List<MatchInfo> datas) {
        this.datas = datas;
    }

    public static class MatchInfo {

        public static final int TYPE_TITLE = 0x01;
        public static final int TYPE_NORMAL = 0x02;
        /**
         * stageName : 第20轮
         * competitionPositiion : 6
         * score : 1 : 0
         * competitionName : 中超
         * competitionId : 3
         * hasReport : true
         * competitionCountry : China
         * id : 5055
         * matchDate : 2017-08-05
         * awayTeamId : 215
         * homeTeamId : 627
         * isFinish : true
         * dateNumOfWeek : 星期六
         * startHMStr : 19:35
         * competitionNameFull : 中国足球超级联赛
         * awayTeamName : 重庆力帆
         * homeTeamName : 贵州恒丰智诚?_],o=/
         */

        public int type = TYPE_NORMAL;

        private String stageName;
        private float competitionPositiion;
        private String score;
        private String competitionName;
        private int competitionId;
        private boolean hasReport;
        private String competitionCountry;
        private int id;
        private String matchDate;
        private int awayTeamId;
        private int homeTeamId;
        private boolean isFinish;
        private String dateNumOfWeek;
        private String startHMStr;
        private String competitionNameFull;
        private String awayTeamName;
        private String homeTeamName;

        private String awayTeamCountry;
        private String competitionTeamType;
        private String homeTeamCountry;

        public String getAwayTeamCountry() {
            return awayTeamCountry;
        }

        public void setAwayTeamCountry(String awayTeamCountry) {
            this.awayTeamCountry = awayTeamCountry;
        }

        public String getCompetitionTeamType() {
            return competitionTeamType;
        }

        public void setCompetitionTeamType(String competitionTeamType) {
            this.competitionTeamType = competitionTeamType;
        }

        public String getHomeTeamCountry() {
            return homeTeamCountry;
        }

        public void setHomeTeamCountry(String homeTeamCountry) {
            this.homeTeamCountry = homeTeamCountry;
        }

        public String getStageName() {
            return stageName;
        }

        public void setStageName(String stageName) {
            this.stageName = stageName;
        }

        public float getCompetitionPositiion() {
            return competitionPositiion;
        }

        public void setCompetitionPositiion(float competitionPositiion) {
            this.competitionPositiion = competitionPositiion;
        }

        public String getScore() {
            return score.replaceAll("\\*", "");
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getCompetitionName() {
            return competitionName;
        }

        public void setCompetitionName(String competitionName) {
            this.competitionName = competitionName;
        }

        public int getCompetitionId() {
            return competitionId;
        }

        public void setCompetitionId(int competitionId) {
            this.competitionId = competitionId;
        }

        public boolean isHasReport() {
            return hasReport;
        }

        public void setHasReport(boolean hasReport) {
            this.hasReport = hasReport;
        }

        public String getCompetitionCountry() {
            return competitionCountry;
        }

        public void setCompetitionCountry(String competitionCountry) {
            this.competitionCountry = competitionCountry;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMatchDate() {
            return matchDate;
        }

        public void setMatchDate(String matchDate) {
            this.matchDate = matchDate;
        }

        public int getAwayTeamId() {
            return awayTeamId;
        }

        public void setAwayTeamId(int awayTeamId) {
            this.awayTeamId = awayTeamId;
        }

        public int getHomeTeamId() {
            return homeTeamId;
        }

        public void setHomeTeamId(int homeTeamId) {
            this.homeTeamId = homeTeamId;
        }

        public boolean isIsFinish() {
            return isFinish;
        }

        public void setIsFinish(boolean isFinish) {
            this.isFinish = isFinish;
        }

        public String getDateNumOfWeek() {
            return dateNumOfWeek;
        }

        public void setDateNumOfWeek(String dateNumOfWeek) {
            this.dateNumOfWeek = dateNumOfWeek;
        }

        public String getStartHMStr() {
            return startHMStr;
        }

        public void setStartHMStr(String startHMStr) {
            this.startHMStr = startHMStr;
        }

        public String getCompetitionNameFull() {
            return competitionNameFull;
        }

        public void setCompetitionNameFull(String competitionNameFull) {
            this.competitionNameFull = competitionNameFull;
        }

        public String getAwayTeamName() {
            return awayTeamName;
        }

        public void setAwayTeamName(String awayTeamName) {
            this.awayTeamName = awayTeamName;
        }

        public String getHomeTeamName() {
            return homeTeamName;
        }

        public void setHomeTeamName(String homeTeamName) {
            this.homeTeamName = homeTeamName;
        }

        @Override
        public String toString() {
            return "MatchInfo{" +
                    "type=" + type +
                    ", stageName='" + stageName + '\'' +
                    ", competitionPositiion=" + competitionPositiion +
                    ", score='" + score + '\'' +
                    ", competitionName='" + competitionName + '\'' +
                    ", competitionId=" + competitionId +
                    ", hasReport=" + hasReport +
                    ", competitionCountry='" + competitionCountry + '\'' +
                    ", id=" + id +
                    ", matchDate='" + matchDate + '\'' +
                    ", awayTeamId=" + awayTeamId +
                    ", homeTeamId=" + homeTeamId +
                    ", isFinish=" + isFinish +
                    ", dateNumOfWeek='" + dateNumOfWeek + '\'' +
                    ", startHMStr='" + startHMStr + '\'' +
                    ", competitionNameFull='" + competitionNameFull + '\'' +
                    ", awayTeamName='" + awayTeamName + '\'' +
                    ", homeTeamName='" + homeTeamName + '\'' +
                    ", awayTeamCountry='" + awayTeamCountry + '\'' +
                    ", competitionTeamType='" + competitionTeamType + '\'' +
                    ", homeTeamCountry='" + homeTeamCountry + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MatchesBean{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", datas=" + datas +
                '}';
    }
}
