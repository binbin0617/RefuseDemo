package com.bin.refusedemo.bean;

import java.util.List;

public class TianXingBean {
    private String reason;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<NewsList> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewsList> newslist) {
        this.newslist = newslist;
    }

    private List<NewsList> newslist;

    public static class NewsList {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        private int type;
    }
}
