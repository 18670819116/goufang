package com.ljcs.cxwl.entity;

/**
 * @author xlei
 * @Date 2018/7/3.
 */

public class CerInfo extends BaseEntity {
    private Bean data;
    private Bean rows;

    public Bean getData() {
        return data;
    }

    public void setData(Bean data) {
        this.data = data;
    }

    public Bean getRows() {
        return rows;
    }

    public void setRows(Bean rows) {
        this.rows = rows;
    }

    public static class Bean {

        /**
         * bh : 35
         * yhbh : 2
         * sfzhm : 430121199308047031
         * mz : 汉
         * csrq : 19930804
         * xm : 向磊
         * xb : 男
         * sfzzm : /data/user/0/com.ljcs.cxwl/cache/idcard_zheng
         * sfzfm : null
         * dz : null
         * qfjg : null
         * yxq : null
         * sqrq : null
         * zt : 1
         */

        private int bh;
        private int yhbh;
        private String sfzhm;
        private String mz;
        private String csrq;
        private String xm;
        private String xb;
        private String sfzzm;
        private String sfzfm;
        private String dz;
        private String qfjg;
        private String yxq;
        private String sqrq;
        private String zt;

        public int getBh() {
            return bh;
        }

        public void setBh(int bh) {
            this.bh = bh;
        }

        public int getYhbh() {
            return yhbh;
        }

        public void setYhbh(int yhbh) {
            this.yhbh = yhbh;
        }

        public String getSfzhm() {
            return sfzhm;
        }

        public void setSfzhm(String sfzhm) {
            this.sfzhm = sfzhm;
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

        public String getXm() {
            return xm;
        }

        public void setXm(String xm) {
            this.xm = xm;
        }

        public String getXb() {
            return xb;
        }

        public void setXb(String xb) {
            this.xb = xb;
        }

        public String getSfzzm() {
            return sfzzm;
        }

        public void setSfzzm(String sfzzm) {
            this.sfzzm = sfzzm;
        }

        public String getSfzfm() {
            return sfzfm;
        }

        public void setSfzfm(String sfzfm) {
            this.sfzfm = sfzfm;
        }

        public String getDz() {
            return dz;
        }

        public void setDz(String dz) {
            this.dz = dz;
        }

        public String getQfjg() {
            return qfjg;
        }

        public void setQfjg(String qfjg) {
            this.qfjg = qfjg;
        }

        public String getYxq() {
            return yxq;
        }

        public void setYxq(String yxq) {
            this.yxq = yxq;
        }

        public String getSqrq() {
            return sqrq;
        }

        public void setSqrq(String sqrq) {
            this.sqrq = sqrq;
        }

        public String getZt() {
            return zt;
        }

        public void setZt(String zt) {
            this.zt = zt;
        }
    }
}
