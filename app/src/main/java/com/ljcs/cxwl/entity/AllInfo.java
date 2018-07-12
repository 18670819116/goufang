package com.ljcs.cxwl.entity;

import java.util.List;

/**
 * @author xlei
 * @Date 2018/7/4.
 */

public class AllInfo extends BaseEntity {

    /**
     * smyz : {"bh":47,"yhbh":2,"sfzhm":"430121199308047031","mz":"汉","csrq":"1993-08-04","xm":"向磊","xb":"男",
     * "sfzzm":"android_zjw/1530678268415","sfzfm":"android_zjw/1530678278397","dz":"湖南省长沙县白少镇曾家坊村桥上屋组437号",
     * "qfjg":"长沙县公安局","yxq":"20140117-20240117","sqrq":"2018-07-04","zt":"2"}
     * hjxx : {"bh":2,"yhbh":"2","mz":"","csrq":"","hyzt":"已婚","hklx":"城市","hkxz":"集体户口",
     * "hkzp":"android_zjw/1530673008681","xb":"","sqrq":"","jhzzp":"","zt":"1","hjszd":"默默"}
     * poxx : {"bh":2,"yhbh":"2","sfzhm":"430121199308047031","mz":"汉","csrq":"1993-08-04","hyzt":"","hklx":"",
     * "hkxz":"","hkzp":"","xm":"向磊","xb":"男","sfzzm":"android_zjw/1530670457282",
     * "sfzfm":"android_zjw/1530670474172","gx":"","hjszd":"","dz":"湖南省长沙县白沙镇管家坊村桥上屋组437号","qfjg":"长沙县公安局",
     * "yxq":"20140117-20240117","lxdh":"","jhzzp":"","zt":"1"}
     * jtcyList : null
     */
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private SmyzBean smyz;
        private HjxxBean hjxx;
        private PoxxBean poxx;
        private List<JtcyBean> jtcyList;
        private String smyzyj;
        private String gfzgyj;

        public SmyzBean getSmyz() {
            return smyz;
        }

        public void setSmyz(SmyzBean smyz) {
            this.smyz = smyz;
        }

        public HjxxBean getHjxx() {
            return hjxx;
        }

        public void setHjxx(HjxxBean hjxx) {
            this.hjxx = hjxx;
        }

        public PoxxBean getPoxx() {
            return poxx;
        }

        public void setPoxx(PoxxBean poxx) {
            this.poxx = poxx;
        }

        public List<JtcyBean> getJtcyList() {
            return jtcyList;
        }

        public void setJtcyList(List<JtcyBean> jtcyList) {
            this.jtcyList = jtcyList;
        }

        public String getSmyzyj() {
            return smyzyj;
        }

        public void setSmyzyj(String smyzyj) {
            this.smyzyj = smyzyj;
        }

        public String getGfzgyj() {
            return gfzgyj;
        }

        public void setGfzgyj(String gfzgyj) {
            this.gfzgyj = gfzgyj;
        }

        public static class JtcyBean {

            /**
             * bh : 1
             * yhbh : 2
             * sfzhm : 43045464944994
             * csrq : null
             * xm : 哦哦哦
             * mz : null
             * xb : 男
             * sfzzm : null
             * sfzfm : null
             * gx : 父子
             * hklx : 城市
             * hkxz : 集体户口
             * hyzt : 已婚
             * hkzp : android_zjw/1530698785920
             * hjszd : null
             * zt : 1
             */

            private int bh;
            private String yhbh;
            private String sfzhm;
            private Object csrq;
            private String xm;
            private Object mz;
            private String xb;
            private Object sfzzm;
            private Object sfzfm;
            private String gx;
            private String hklx;
            private String hkxz;
            private String hyzt;
            private String hkzp;
            private Object hjszd;
            private String zt;

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

            public String getSfzhm() {
                return sfzhm;
            }

            public void setSfzhm(String sfzhm) {
                this.sfzhm = sfzhm;
            }

            public Object getCsrq() {
                return csrq;
            }

            public void setCsrq(Object csrq) {
                this.csrq = csrq;
            }

            public String getXm() {
                return xm;
            }

            public void setXm(String xm) {
                this.xm = xm;
            }

            public Object getMz() {
                return mz;
            }

            public void setMz(Object mz) {
                this.mz = mz;
            }

            public String getXb() {
                return xb;
            }

            public void setXb(String xb) {
                this.xb = xb;
            }

            public Object getSfzzm() {
                return sfzzm;
            }

            public void setSfzzm(Object sfzzm) {
                this.sfzzm = sfzzm;
            }

            public Object getSfzfm() {
                return sfzfm;
            }

            public void setSfzfm(Object sfzfm) {
                this.sfzfm = sfzfm;
            }

            public String getGx() {
                return gx;
            }

            public void setGx(String gx) {
                this.gx = gx;
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

            public String getHyzt() {
                return hyzt;
            }

            public void setHyzt(String hyzt) {
                this.hyzt = hyzt;
            }

            public String getHkzp() {
                return hkzp;
            }

            public void setHkzp(String hkzp) {
                this.hkzp = hkzp;
            }

            public Object getHjszd() {
                return hjszd;
            }

            public void setHjszd(Object hjszd) {
                this.hjszd = hjszd;
            }

            public String getZt() {
                return zt;
            }

            public void setZt(String zt) {
                this.zt = zt;
            }
        }

        public static class SmyzBean {
            /**
             * bh : 47
             * yhbh : 2
             * sfzhm : 430121199308047031
             * mz : 汉
             * csrq : 1993-08-04
             * xm : 向磊
             * xb : 男
             * sfzzm : android_zjw/1530678268415
             * sfzfm : android_zjw/1530678278397
             * dz : 湖南省长沙县白少镇曾家坊村桥上屋组437号
             * qfjg : 长沙县公安局
             * yxq : 20140117-20240117
             * sqrq : 2018-07-04
             * zt : 2
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

        public static class HjxxBean {
            /**
             * bh : 2
             * yhbh : 2
             * mz :
             * csrq :
             * hyzt : 已婚
             * hklx : 城市
             * hkxz : 集体户口
             * hkzp : android_zjw/1530673008681
             * xb :
             * sqrq :
             * jhzzp :
             * zt : 1
             * hjszd : 默默
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

        public static class PoxxBean {
            /**
             * bh : 2
             * yhbh : 2
             * sfzhm : 430121199308047031
             * mz : 汉
             * csrq : 1993-08-04
             * hyzt :
             * hklx :
             * hkxz :
             * hkzp :
             * xm : 向磊
             * xb : 男
             * sfzzm : android_zjw/1530670457282
             * sfzfm : android_zjw/1530670474172
             * gx :
             * hjszd :
             * dz : 湖南省长沙县白沙镇管家坊村桥上屋组437号
             * qfjg : 长沙县公安局
             * yxq : 20140117-20240117
             * lxdh :
             * jhzzp :
             * zt : 1
             */

            private int bh;
            private String yhbh;
            private String sfzhm;
            private String mz;
            private String csrq;
            private String hyzt;
            private String hklx;
            private String hkxz;
            private String hkzp;
            private String xm;
            private String xb;
            private String sfzzm;
            private String sfzfm;
            private String gx;
            private String hjszd;
            private String dz;
            private String qfjg;
            private String yxq;
            private String lxdh;
            private String jhzzp;
            private String zt;
            private String lhrq;
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

            public String getGx() {
                return gx;
            }

            public void setGx(String gx) {
                this.gx = gx;
            }

            public String getHjszd() {
                return hjszd;
            }

            public void setHjszd(String hjszd) {
                this.hjszd = hjszd;
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

            public String getLxdh() {
                return lxdh;
            }

            public void setLxdh(String lxdh) {
                this.lxdh = lxdh;
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

            public String getLhrq() {
                return lhrq;
            }

            public void setLhrq(String lhrq) {
                this.lhrq = lhrq;
            }
        }
    }
}
