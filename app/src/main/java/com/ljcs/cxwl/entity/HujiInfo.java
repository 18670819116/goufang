package com.ljcs.cxwl.entity;

/**
 * @author xlei
 * @Date 2018/7/4.
 */

public class HujiInfo extends BaseEntity {
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

    public static class Data {

        /**
         * bh : 2
         * yhbh : 2
         * mz : null
         * csrq : null
         * hyzt : 已婚
         * hklx : 城市
         * hkxz : 集体户口
         * hkzp : android_zjw/1530628028710
         * xb : null
         * sqrq : null
         * jhzzp : null
         * zt : 1
         * hjszd : 明
         */

        private int bh;
        private String yhbh;
        private String mz;
        private String csrq;
        private String hyzt;
        private String hklx;
        private String hkxz;
        private String hkzp;
        private String xb;
        private String sqrq;
        private String jhzzp;
        private String zt;
        private String hjszd;

        public int getBh() {
            return bh;
        }

        public void setBh(int bh) {
            this.bh = bh;
        }

        public String getYhbh() {
            return yhbh;
        }

        public void setYhbh(String yhbh) {
            this.yhbh = yhbh;
        }

        public String getMz() {
            return mz;
        }

        public void setMz(String mz) {
            this.mz = mz;
        }

        public String getCsrq() {
            return csrq;
        }

        public void setCsrq(String csrq) {
            this.csrq = csrq;
        }

        public String getHyzt() {
            return hyzt;
        }

        public void setHyzt(String hyzt) {
            this.hyzt = hyzt;
        }

        public String getHklx() {
            return hklx;
        }

        public void setHklx(String hklx) {
            this.hklx = hklx;
        }

        public String getHkxz() {
            return hkxz;
        }

        public void setHkxz(String hkxz) {
            this.hkxz = hkxz;
        }

        public String getHkzp() {
            return hkzp;
        }

        public void setHkzp(String hkzp) {
            this.hkzp = hkzp;
        }

        public String getXb() {
            return xb;
        }

        public void setXb(String xb) {
            this.xb = xb;
        }

        public String getSqrq() {
            return sqrq;
        }

        public void setSqrq(String sqrq) {
            this.sqrq = sqrq;
        }

        public String getJhzzp() {
            return jhzzp;
        }

        public void setJhzzp(String jhzzp) {
            this.jhzzp = jhzzp;
        }

        public String getZt() {
            return zt;
        }

        public void setZt(String zt) {
            this.zt = zt;
        }

        public String getHjszd() {
            return hjszd;
        }

        public void setHjszd(String hjszd) {
            this.hjszd = hjszd;
        }
    }
}
