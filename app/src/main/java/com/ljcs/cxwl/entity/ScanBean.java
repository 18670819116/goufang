package com.ljcs.cxwl.entity;

/**
 * @author xlei
 * @Date 2018/7/24.
 */

public class ScanBean extends BaseEntity {
    private ScanData data;

    public ScanData getData() {
        return data;
    }

    public void setData(ScanData data) {
        this.data = data;
    }

    public static class ScanData {

        /**
         * xmmc : 中央公馆
         * kprq : 2018-06-01
         * jsrq : 2018-08-01
         * kfgsmc : 创欣
         * xmdz : 雨花区中央公馆
         * ksts : 1000
         * gxts : 800
         * rgxh : 8
         */

        private String xmmc;
        private String kprq;
        private String jsrq;
        private String kfgsmc;
        private String xmdz;
        private int ksts;
        private int gxts;
        private int rgxh;

        public String getXmmc() {
            return xmmc;
        }

        public void setXmmc(String xmmc) {
            this.xmmc = xmmc;
        }

        public String getKprq() {
            return kprq;
        }

        public void setKprq(String kprq) {
            this.kprq = kprq;
        }

        public String getJsrq() {
            return jsrq;
        }

        public void setJsrq(String jsrq) {
            this.jsrq = jsrq;
        }

        public String getKfgsmc() {
            return kfgsmc;
        }

        public void setKfgsmc(String kfgsmc) {
            this.kfgsmc = kfgsmc;
        }

        public String getXmdz() {
            return xmdz;
        }

        public void setXmdz(String xmdz) {
            this.xmdz = xmdz;
        }

        public int getKsts() {
            return ksts;
        }

        public void setKsts(int ksts) {
            this.ksts = ksts;
        }

        public int getGxts() {
            return gxts;
        }

        public void setGxts(int gxts) {
            this.gxts = gxts;
        }

        public int getRgxh() {
            return rgxh;
        }

        public void setRgxh(int rgxh) {
            this.rgxh = rgxh;
        }
    }

}
