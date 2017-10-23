package com.parsonswang.zxfootball.bean;

import java.util.List;

/**
 * Created by wangchun on 2017/10/23.
 */

public class HeaderTabTitle {

    private List<TabInfo> data;

    public List<TabInfo> getData() {
        return data;
    }

    public void setData(List<TabInfo> data) {
        this.data = data;
    }

    public static class TabInfo {
        /**
         * name : 中超
         * id : 3
         */

        private String name;
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HeaderTabTitle{" +
                "data=" + data +
                '}';
    }
}
