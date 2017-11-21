package javen.example.com.smartnews.net.top_news;

import java.util.List;

import javen.example.com.smartnews.main.fragment.home.bean.top_news.TopNewsBean;

/**
 * Created by Javen on 21/11/2017.
 */

public class TopNewsResultBean {
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
      private List<TopNewsBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<TopNewsBean> getData() {
            return data;
        }

        public void setData(List<TopNewsBean> data) {
            this.data = data;
        }
    }
}
