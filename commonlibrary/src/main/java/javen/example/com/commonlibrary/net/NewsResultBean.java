package javen.example.com.commonlibrary.net;

import java.util.List;

import javen.example.com.commonlibrary.bean.news.NewsBean;


/**
 * Created by Javen on 21/11/2017.
 */

public class NewsResultBean {
    private String reason ;
    private Result result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }



    public static class Result{
      private String stat;
      private List<NewsBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<NewsBean> getData() {
            return data;
        }

        public void setData(List<NewsBean> data) {
            this.data = data;
        }
    }
}
