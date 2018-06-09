package com.parsonswang.zxfootball.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.LinkedList;
import java.util.List;

public class MatchStatBean implements Parcelable{

    public List<MatchTimelines> matchTimelinesList;

    public List<PlayerStatics> homePlayerStaticsList;

    public List<PlayerStatics> awayPlayerStaticsList;

    public String homeTeamId;

    public String awayTeamId;

    public String homeTeamFormation;//主队阵形

    public String awayTeamFormation;//客队阵形

    public LinkedList<PlayerInfo> homeMainPlayerInfos;
    public LinkedList<PlayerInfo> homeBenchPlayerInfos;

    public LinkedList<PlayerInfo> awayMainPlayerInfos;
    public LinkedList<PlayerInfo> awayBenchPlayerInfos;

    public MatchStatBean() {}


    protected MatchStatBean(Parcel in) {
        homeTeamId = in.readString();
        awayTeamId = in.readString();
        homeTeamFormation = in.readString();
        awayTeamFormation = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(homeTeamId);
        dest.writeString(awayTeamId);
        dest.writeString(homeTeamFormation);
        dest.writeString(awayTeamFormation);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MatchStatBean> CREATOR = new Creator<MatchStatBean>() {
        @Override
        public MatchStatBean createFromParcel(Parcel in) {
            return new MatchStatBean(in);
        }

        @Override
        public MatchStatBean[] newArray(int size) {
            return new MatchStatBean[size];
        }
    };
}
