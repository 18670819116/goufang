package com.ljcs.cxwl.entity;

/**
 * @author xlei
 * @Date 2018/7/3.
 */

public class RegisterBean extends BaseEntity {
    private Bean rows;
    private Bean data;

    public Bean getRows() {
        return rows;
    }

    public void setRows(Bean rows) {
        this.rows = rows;
    }

    public Bean getData() {
        return data;
    }

    public void setData(Bean data) {
        this.data = data;
    }

    public static class Bean {
        /**
         * bh : 4
         * sjhm : 15426482512
         * mm : 123456
         * zt : 1
         */
        private int bh;
        private String sjhm;
        private String mm;
        private String zt;

        public int getBh() {
            return bh;
        }

        public void setBh(int bh) {
            this.bh = bh;
        }

        public String getSjhm() {
            return sjhm;
        }

        public void setSjhm(String sjhm) {
            this.sjhm = sjhm;
        }

        public String getMm() {
            return mm;
        }

        public void setMm(String mm) {
            this.mm = mm;
        }

        public String getZt() {
            return zt;
        }

        public void setZt(String zt) {
            this.zt = zt;
        }
    }


}
