package com.parsonswang.zxfootball.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MatchStatBean implements Parcelable{

    public List<MatchTimelines> matchTimelinesList;

    public String homeTeamId;

    public String awayTeamId;

    public MatchStatBean() {}

    protected MatchStatBean(Parcel in) {
        homeTeamId = in.readString();
        awayTeamId = in.readString();
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

    @Override
    public String toString() {
        return "MatchStatBean{" +
                "matchTimelinesList=" + matchTimelinesList +
                ", homeTeamId='" + homeTeamId + '\'' +
                ", awayTeamId='" + awayTeamId + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(homeTeamId);
        dest.writeString(awayTeamId);
    }
}
