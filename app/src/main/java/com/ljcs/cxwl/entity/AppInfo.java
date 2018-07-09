package com.ljcs.cxwl.entity;

/**
 * @author xlei
 * @Date 2018/7/9.
 */

public class AppInfo extends BaseEntity {
    private Data data;
    private Data rows;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getRows() {
        return rows;
    }

    public void setRows(Data rows) {
        this.rows = rows;
    }

    public static class Data{

        /**
         * versionUid : 1.0.0
         * versionExplain : 新版本发布啦！带来更多更新的体验
         * versionIsCompulsory : 2
         * versionDownloadUrl : http://img0.hnchxwl.com/loadGf/gofang.apk
         * versionTime : 2018-07-09
         */

        private String versionUid;
        private String versionExplain;
        private int versionIsCompulsory;
        private String versionDownloadUrl;
        private String versionTime;

        public String getVersionUid() {
            return versionUid;
        }

        public void setVersionUid(String versionUid) {
            this.versionUid = versionUid;
        }

        public String getVersionExplain() {
            return versionExplain;
        }

        public void setVersionExplain(String versionExplain) {
            this.versionExplain = versionExplain;
        }

        public int getVersionIsCompulsory() {
            return versionIsCompulsory;
        }

        public void setVersionIsCompulsory(int versionIsCompulsory) {
            this.versionIsCompulsory = versionIsCompulsory;
        }

        public String getVersionDownloadUrl() {
            return versionDownloadUrl;
        }

        public void setVersionDownloadUrl(String versionDownloadUrl) {
            this.versionDownloadUrl = versionDownloadUrl;
        }

        public String getVersionTime() {
            return versionTime;
        }

        public void setVersionTime(String versionTime) {
            this.versionTime = versionTime;
        }
    }
}
