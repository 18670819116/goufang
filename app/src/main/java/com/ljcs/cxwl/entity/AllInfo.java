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
        private ZcyhBean zcyh;
        private GrxxBean grxx;
        private List<ZinvBean> znxxlist;

        public List<ZinvBean> getZnxxlist() {
            return znxxlist;
        }

        public void setZnxxlist(List<ZinvBean> znxxlist) {
            this.znxxlist = znxxlist;
        }

        public GrxxBean getGrxx() {
            return grxx;
        }

        public void setGrxx(GrxxBean grxx) {
            this.grxx = grxx;
        }

        public ZcyhBean getZcyh() {
            return zcyh;
        }

        public void setZcyh(ZcyhBean zcyh) {
            this.zcyh = zcyh;
        }

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

        public static class ZinvBean {

            /**
             * jtcy : {"jtlh":"J0000012","yhbh":"A000000047","xm":"哦哦哦","zjlx":null,"zjhm":null,"qtzjlx":null,
             * "qtzjhm":null,"lxdh":null,"xb":"男","sfzcr":null,"jtgx":"子女","hyzk":"未婚","lysj":null,"hjlx":"居民户口簿",
             * "hjfl":"集体户口","hjszd":"北京市北京市东城区","rwrq":null,"twbz":null,"sbrq":null,"rzrq":null,"rzzt":null,
             * "rzbz":null}
             * zzxx : {"yhbh":"A000000047","sfzzm":null,"sfzfm":null,"hkb":"/2018/07/23/1532354840365.jpg",
             * "jhz":null,"lhz":null,"jgz":null,"hz":null}
             * sfz : null
             */

            private JtcyBean jtcy;
            private ZzxxBean zzxx;
            private Object sfz;

            public JtcyBean getJtcy() {
                return jtcy;
            }

            public void setJtcy(JtcyBean jtcy) {
                this.jtcy = jtcy;
            }

            public ZzxxBean getZzxx() {
                return zzxx;
            }

            public void setZzxx(ZzxxBean zzxx) {
                this.zzxx = zzxx;
            }

            public Object getSfz() {
                return sfz;
            }

            public void setSfz(Object sfz) {
                this.sfz = sfz;
            }

            public static class JtcyBean {
                /**
                 * jtlh : J0000012
                 * yhbh : A000000047
                 * xm : 哦哦哦
                 * zjlx : null
                 * zjhm : null
                 * qtzjlx : null
                 * qtzjhm : null
                 * lxdh : null
                 * xb : 男
                 * sfzcr : null
                 * jtgx : 子女
                 * hyzk : 未婚
                 * lysj : null
                 * hjlx : 居民户口簿
                 * hjfl : 集体户口
                 * hjszd : 北京市北京市东城区
                 * rwrq : null
                 * twbz : null
                 * sbrq : null
                 * rzrq : null
                 * rzzt : null
                 * rzbz : null
                 */

                private String jtlh;
                private String yhbh;
                private String xm;
                private Object zjlx;
                private String zjhm;
                private Object qtzjlx;
                private Object qtzjhm;
                private Object lxdh;
                private String xb;
                private Object sfzcr;
                private String jtgx;
                private String hyzk;
                private Object lysj;
                private String hjlx;
                private String hjfl;
                private String hjszd;
                private Object rwrq;
                private Object twbz;
                private Object sbrq;
                private Object rzrq;
                private Object rzzt;
                private Object rzbz;

                public String getJtlh() {
                    return jtlh;
                }

                public void setJtlh(String jtlh) {
                    this.jtlh = jtlh;
                }

                public String getYhbh() {
                    return yhbh;
                }

                public void setYhbh(String yhbh) {
                    this.yhbh = yhbh;
                }

                public String getXm() {
                    return xm;
                }

                public void setXm(String xm) {
                    this.xm = xm;
                }

                public Object getZjlx() {
                    return zjlx;
                }

                public void setZjlx(Object zjlx) {
                    this.zjlx = zjlx;
                }

                public String getZjhm() {
                    return zjhm;
                }

                public void setZjhm(String zjhm) {
                    this.zjhm = zjhm;
                }

                public Object getQtzjlx() {
                    return qtzjlx;
                }

                public void setQtzjlx(Object qtzjlx) {
                    this.qtzjlx = qtzjlx;
                }

                public Object getQtzjhm() {
                    return qtzjhm;
                }

                public void setQtzjhm(Object qtzjhm) {
                    this.qtzjhm = qtzjhm;
                }

                public Object getLxdh() {
                    return lxdh;
                }

                public void setLxdh(Object lxdh) {
                    this.lxdh = lxdh;
                }

                public String getXb() {
                    return xb;
                }

                public void setXb(String xb) {
                    this.xb = xb;
                }

                public Object getSfzcr() {
                    return sfzcr;
                }

                public void setSfzcr(Object sfzcr) {
                    this.sfzcr = sfzcr;
                }

                public String getJtgx() {
                    return jtgx;
                }

                public void setJtgx(String jtgx) {
                    this.jtgx = jtgx;
                }

                public String getHyzk() {
                    return hyzk;
                }

                public void setHyzk(String hyzk) {
                    this.hyzk = hyzk;
                }

                public Object getLysj() {
                    return lysj;
                }

                public void setLysj(Object lysj) {
                    this.lysj = lysj;
                }

                public String getHjlx() {
                    return hjlx;
                }

                public void setHjlx(String hjlx) {
                    this.hjlx = hjlx;
                }

                public String getHjfl() {
                    return hjfl;
                }

                public void setHjfl(String hjfl) {
                    this.hjfl = hjfl;
                }

                public String getHjszd() {
                    return hjszd;
                }

                public void setHjszd(String hjszd) {
                    this.hjszd = hjszd;
                }

                public Object getRwrq() {
                    return rwrq;
                }

                public void setRwrq(Object rwrq) {
                    this.rwrq = rwrq;
                }

                public Object getTwbz() {
                    return twbz;
                }

                public void setTwbz(Object twbz) {
                    this.twbz = twbz;
                }

                public Object getSbrq() {
                    return sbrq;
                }

                public void setSbrq(Object sbrq) {
                    this.sbrq = sbrq;
                }

                public Object getRzrq() {
                    return rzrq;
                }

                public void setRzrq(Object rzrq) {
                    this.rzrq = rzrq;
                }

                public Object getRzzt() {
                    return rzzt;
                }

                public void setRzzt(Object rzzt) {
                    this.rzzt = rzzt;
                }

                public Object getRzbz() {
                    return rzbz;
                }

                public void setRzbz(Object rzbz) {
                    this.rzbz = rzbz;
                }
            }

            public static class ZzxxBean {
                /**
                 * yhbh : A000000047
                 * sfzzm : null
                 * sfzfm : null
                 * hkb : /2018/07/23/1532354840365.jpg
                 * jhz : null
                 * lhz : null
                 * jgz : null
                 * hz : null
                 */

                private String yhbh;
                private Object sfzzm;
                private Object sfzfm;
                private String hkb;
                private Object jhz;
                private Object lhz;
                private Object jgz;
                private Object hz;

                public String getYhbh() {
                    return yhbh;
                }

                public void setYhbh(String yhbh) {
                    this.yhbh = yhbh;
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

                public String getHkb() {
                    return hkb;
                }

                public void setHkb(String hkb) {
                    this.hkb = hkb;
                }

                public Object getJhz() {
                    return jhz;
                }

                public void setJhz(Object jhz) {
                    this.jhz = jhz;
                }

                public Object getLhz() {
                    return lhz;
                }

                public void setLhz(Object lhz) {
                    this.lhz = lhz;
                }

                public Object getJgz() {
                    return jgz;
                }

                public void setJgz(Object jgz) {
                    this.jgz = jgz;
                }

                public Object getHz() {
                    return hz;
                }

                public void setHz(Object hz) {
                    this.hz = hz;
                }
            }
        }

        public static class GrxxBean {

            /**
             * jtcy : null
             * zzxx : {"yhbh":"A000000004","sfzzm":"/2018/07/23/1532327817621.jpg",
             * "sfzfm":"/2018/07/23/1532327817850.jpg","hkb":null,"jhz":null,"lhz":null,"jgz":null,"hz":null}
             * sfz : {"yhbh":"A000000004","xm":"方红","zjhm":"430602199207085643","xb":"女","mz":"汉",
             * "zz":"湖南省岳阳市岳阳楼区奇家社区居委会付家组","qfjg":"岳阳市公安局岳阳楼分局","yxq":"20120112-20220112","csrq":"19920708",
             * "cjrq":"2018-07-23 14:36:44"}
             */

            private JtcyBean jtcy;
            private ZzxxBean zzxx;
            private SfzBean sfz;

            public JtcyBean getJtcy() {
                return jtcy;
            }

            public void setJtcy(JtcyBean jtcy) {
                this.jtcy = jtcy;
            }

            public ZzxxBean getZzxx() {
                return zzxx;
            }

            public void setZzxx(ZzxxBean zzxx) {
                this.zzxx = zzxx;
            }

            public SfzBean getSfz() {
                return sfz;
            }

            public void setSfz(SfzBean sfz) {
                this.sfz = sfz;
            }

            public static class JtcyBean {

                /**
                 * jtlh : J0000004
                 * yhbh : A000000004
                 * xm : 向磊
                 * zjlx : 身份证
                 * zjhm : 430121199308047031
                 * qtzjlx : null
                 * qtzjhm : null
                 * lxdh : 15243648097
                 * xb : 男
                 * sfzcr : 是
                 * jtgx : null
                 * hyzk : 已婚
                 * lysj : null
                 * hjlx : 居民户口簿
                 * hjfl : 集体户口
                 * hjszd : 北京市北京市东城区
                 * rwrq : null
                 * twbz : null
                 * sbrq : null
                 * rzrq : null
                 * rzzt : null
                 * rzbz : null
                 */

                private String jtlh;
                private String yhbh;
                private String xm;
                private String zjlx;
                private String zjhm;
                private Object qtzjlx;
                private Object qtzjhm;
                private String lxdh;
                private String xb;
                private String sfzcr;
                private Object jtgx;
                private String hyzk;
                private Object lysj;
                private String hjlx;
                private String hjfl;
                private String hjszd;
                private Object rwrq;
                private Object twbz;
                private Object sbrq;
                private Object rzrq;
                private Integer rzzt;
                private Object rzbz;

                public String getJtlh() {
                    return jtlh;
                }

                public void setJtlh(String jtlh) {
                    this.jtlh = jtlh;
                }

                public String getYhbh() {
                    return yhbh;
                }

                public void setYhbh(String yhbh) {
                    this.yhbh = yhbh;
                }

                public String getXm() {
                    return xm;
                }

                public void setXm(String xm) {
                    this.xm = xm;
                }

                public String getZjlx() {
                    return zjlx;
                }

                public void setZjlx(String zjlx) {
                    this.zjlx = zjlx;
                }

                public String getZjhm() {
                    return zjhm;
                }

                public void setZjhm(String zjhm) {
                    this.zjhm = zjhm;
                }

                public Object getQtzjlx() {
                    return qtzjlx;
                }

                public void setQtzjlx(Object qtzjlx) {
                    this.qtzjlx = qtzjlx;
                }

                public Object getQtzjhm() {
                    return qtzjhm;
                }

                public void setQtzjhm(Object qtzjhm) {
                    this.qtzjhm = qtzjhm;
                }

                public String getLxdh() {
                    return lxdh;
                }

                public void setLxdh(String lxdh) {
                    this.lxdh = lxdh;
                }

                public String getXb() {
                    return xb;
                }

                public void setXb(String xb) {
                    this.xb = xb;
                }

                public String getSfzcr() {
                    return sfzcr;
                }

                public void setSfzcr(String sfzcr) {
                    this.sfzcr = sfzcr;
                }

                public Object getJtgx() {
                    return jtgx;
                }

                public void setJtgx(Object jtgx) {
                    this.jtgx = jtgx;
                }

                public String getHyzk() {
                    return hyzk;
                }

                public void setHyzk(String hyzk) {
                    this.hyzk = hyzk;
                }

                public Object getLysj() {
                    return lysj;
                }

                public void setLysj(Object lysj) {
                    this.lysj = lysj;
                }

                public String getHjlx() {
                    return hjlx;
                }

                public void setHjlx(String hjlx) {
                    this.hjlx = hjlx;
                }

                public String getHjfl() {
                    return hjfl;
                }

                public void setHjfl(String hjfl) {
                    this.hjfl = hjfl;
                }

                public String getHjszd() {
                    return hjszd;
                }

                public void setHjszd(String hjszd) {
                    this.hjszd = hjszd;
                }

                public Object getRwrq() {
                    return rwrq;
                }

                public void setRwrq(Object rwrq) {
                    this.rwrq = rwrq;
                }

                public Object getTwbz() {
                    return twbz;
                }

                public void setTwbz(Object twbz) {
                    this.twbz = twbz;
                }

                public Object getSbrq() {
                    return sbrq;
                }

                public void setSbrq(Object sbrq) {
                    this.sbrq = sbrq;
                }

                public Object getRzrq() {
                    return rzrq;
                }

                public void setRzrq(Object rzrq) {
                    this.rzrq = rzrq;
                }

                public Integer getRzzt() {
                    return rzzt;
                }

                public void setRzzt(Integer rzzt) {
                    this.rzzt = rzzt;
                }

                public Object getRzbz() {
                    return rzbz;
                }

                public void setRzbz(Object rzbz) {
                    this.rzbz = rzbz;
                }
            }

            public static class ZzxxBean {
                /**
                 * yhbh : A000000004
                 * sfzzm : /2018/07/23/1532327817621.jpg
                 * sfzfm : /2018/07/23/1532327817850.jpg
                 * hkb : null
                 * jhz : null
                 * lhz : null
                 * jgz : null
                 * hz : null
                 */

                private String yhbh;
                private String sfzzm;
                private String sfzfm;
                private String hkb;
                private String jhz;
                private String lhz;
                private String jgz;
                private Object hz;

                public String getYhbh() {
                    return yhbh;
                }

                public void setYhbh(String yhbh) {
                    this.yhbh = yhbh;
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

                public String getHkb() {
                    return hkb;
                }

                public void setHkb(String hkb) {
                    this.hkb = hkb;
                }

                public String getJhz() {
                    return jhz;
                }

                public void setJhz(String jhz) {
                    this.jhz = jhz;
                }

                public String getLhz() {
                    return lhz;
                }

                public void setLhz(String lhz) {
                    this.lhz = lhz;
                }

                public String getJgz() {
                    return jgz;
                }

                public void setJgz(String jgz) {
                    this.jgz = jgz;
                }

                public Object getHz() {
                    return hz;
                }

                public void setHz(Object hz) {
                    this.hz = hz;
                }
            }

            public static class SfzBean {
                /**
                 * yhbh : A000000004
                 * xm : 方红
                 * zjhm : 430602199207085643
                 * xb : 女
                 * mz : 汉
                 * zz : 湖南省岳阳市岳阳楼区奇家社区居委会付家组
                 * qfjg : 岳阳市公安局岳阳楼分局
                 * yxq : 20120112-20220112
                 * csrq : 19920708
                 * cjrq : 2018-07-23 14:36:44
                 */

                private String yhbh;
                private String xm;
                private String zjhm;
                private String xb;
                private String mz;
                private String zz;
                private String qfjg;
                private String yxq;
                private String csrq;
                private String cjrq;

                public String getYhbh() {
                    return yhbh;
                }

                public void setYhbh(String yhbh) {
                    this.yhbh = yhbh;
                }

                public String getXm() {
                    return xm;
                }

                public void setXm(String xm) {
                    this.xm = xm;
                }

                public String getZjhm() {
                    return zjhm;
                }

                public void setZjhm(String zjhm) {
                    this.zjhm = zjhm;
                }

                public String getXb() {
                    return xb;
                }

                public void setXb(String xb) {
                    this.xb = xb;
                }

                public String getMz() {
                    return mz;
                }

                public void setMz(String mz) {
                    this.mz = mz;
                }

                public String getZz() {
                    return zz;
                }

                public void setZz(String zz) {
                    this.zz = zz;
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

                public String getCsrq() {
                    return csrq;
                }

                public void setCsrq(String csrq) {
                    this.csrq = csrq;
                }

                public String getCjrq() {
                    return cjrq;
                }

                public void setCjrq(String cjrq) {
                    this.cjrq = cjrq;
                }
            }
        }

        public static class ZcyhBean {

            /**
             * yhbh : A000000006
             * sjhm : 15573110804
             * yhmm : 706546DDC8E1D83524D580D355CEDBEE7D7DC53C
             * yhxm : 向磊
             * zjlx : 身份证
             * zjhm : 430121199308047031
             * rzzt : 0
             * rzrq : null
             * yhzt : 0
             */

            private String yhbh;
            private String sjhm;
            private String yhmm;
            private String yhxm;
            private String zjlx;
            private String zjhm;
            private Integer rzzt;
            private Object rzrq;
            private int yhzt;

            public String getYhbh() {
                return yhbh;
            }

            public void setYhbh(String yhbh) {
                this.yhbh = yhbh;
            }

            public String getSjhm() {
                return sjhm;
            }

            public void setSjhm(String sjhm) {
                this.sjhm = sjhm;
            }

            public String getYhmm() {
                return yhmm;
            }

            public void setYhmm(String yhmm) {
                this.yhmm = yhmm;
            }

            public String getYhxm() {
                return yhxm;
            }

            public void setYhxm(String yhxm) {
                this.yhxm = yhxm;
            }

            public String getZjlx() {
                return zjlx;
            }

            public void setZjlx(String zjlx) {
                this.zjlx = zjlx;
            }

            public String getZjhm() {
                return zjhm;
            }

            public void setZjhm(String zjhm) {
                this.zjhm = zjhm;
            }

            public Integer getRzzt() {
                return rzzt;
            }

            public void setRzzt(Integer rzzt) {
                this.rzzt = rzzt;
            }

            public Object getRzrq() {
                return rzrq;
            }

            public void setRzrq(Object rzrq) {
                this.rzrq = rzrq;
            }

            public int getYhzt() {
                return yhzt;
            }

            public void setYhzt(int yhzt) {
                this.yhzt = yhzt;
            }
        }

        //        public static class
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
             * jtcy : {"jtlh":"J0000012","yhbh":"A000000028","xm":"向磊","zjlx":null,"zjhm":"430121199308047031",
             * "qtzjlx":null,"qtzjhm":null,"lxdh":"15243648097","xb":"女","sfzcr":"否","jtgx":"夫妻","hyzk":"已婚",
             * "lysj":null,"hjlx":"居民户口簿","hjfl":"集体户口","hjszd":"北京市北京市东城区","rwrq":null,"twbz":null,"sbrq":null,
             * "rzrq":null,"rzzt":null,"rzbz":null}
             * zzxx : {"yhbh":"A000000028","sfzzm":"/2018/07/23/1532351614440.jpg",
             * "sfzfm":"/2018/07/23/1532351614519.jpg","hkb":"/2018/07/23/1532351614309.jpg",
             * "jhz":"/2018/07/23/1532351614366.jpg","lhz":null,"jgz":null,"hz":null}
             * sfz : {"yhbh":"A000000028","xm":"向磊","zjhm":"430121199308047031","xb":"女","mz":"汉",
             * "zz":"湖南省长沙县白沙镇曾家坊村桥上屋组437号","qfjg":"长沙县公安局","yxq":"20140117-20240117","csrq":"19930804","cjrq":null}
             */

            private JtcyBean jtcy;
            private ZzxxBean zzxx;
            private SfzBean sfz;

            public JtcyBean getJtcy() {
                return jtcy;
            }

            public void setJtcy(JtcyBean jtcy) {
                this.jtcy = jtcy;
            }

            public ZzxxBean getZzxx() {
                return zzxx;
            }

            public void setZzxx(ZzxxBean zzxx) {
                this.zzxx = zzxx;
            }

            public SfzBean getSfz() {
                return sfz;
            }

            public void setSfz(SfzBean sfz) {
                this.sfz = sfz;
            }

            public static class JtcyBean {
                /**
                 * jtlh : J0000012
                 * yhbh : A000000028
                 * xm : 向磊
                 * zjlx : null
                 * zjhm : 430121199308047031
                 * qtzjlx : null
                 * qtzjhm : null
                 * lxdh : 15243648097
                 * xb : 女
                 * sfzcr : 否
                 * jtgx : 夫妻
                 * hyzk : 已婚
                 * lysj : null
                 * hjlx : 居民户口簿
                 * hjfl : 集体户口
                 * hjszd : 北京市北京市东城区
                 * rwrq : null
                 * twbz : null
                 * sbrq : null
                 * rzrq : null
                 * rzzt : null
                 * rzbz : null
                 */

                private String jtlh;
                private String yhbh;
                private String xm;
                private Object zjlx;
                private String zjhm;
                private Object qtzjlx;
                private Object qtzjhm;
                private String lxdh;
                private String xb;
                private String sfzcr;
                private String jtgx;
                private String hyzk;
                private String lysj;
                private String hjlx;
                private String hjfl;
                private String hjszd;
                private Object rwrq;
                private Object twbz;
                private Object sbrq;
                private Object rzrq;
                private Object rzzt;
                private Object rzbz;

                public String getJtlh() {
                    return jtlh;
                }

                public void setJtlh(String jtlh) {
                    this.jtlh = jtlh;
                }

                public String getYhbh() {
                    return yhbh;
                }

                public void setYhbh(String yhbh) {
                    this.yhbh = yhbh;
                }

                public String getXm() {
                    return xm;
                }

                public void setXm(String xm) {
                    this.xm = xm;
                }

                public Object getZjlx() {
                    return zjlx;
                }

                public void setZjlx(Object zjlx) {
                    this.zjlx = zjlx;
                }

                public String getZjhm() {
                    return zjhm;
                }

                public void setZjhm(String zjhm) {
                    this.zjhm = zjhm;
                }

                public Object getQtzjlx() {
                    return qtzjlx;
                }

                public void setQtzjlx(Object qtzjlx) {
                    this.qtzjlx = qtzjlx;
                }

                public Object getQtzjhm() {
                    return qtzjhm;
                }

                public void setQtzjhm(Object qtzjhm) {
                    this.qtzjhm = qtzjhm;
                }

                public String getLxdh() {
                    return lxdh;
                }

                public void setLxdh(String lxdh) {
                    this.lxdh = lxdh;
                }

                public String getXb() {
                    return xb;
                }

                public void setXb(String xb) {
                    this.xb = xb;
                }

                public String getSfzcr() {
                    return sfzcr;
                }

                public void setSfzcr(String sfzcr) {
                    this.sfzcr = sfzcr;
                }

                public String getJtgx() {
                    return jtgx;
                }

                public void setJtgx(String jtgx) {
                    this.jtgx = jtgx;
                }

                public String getHyzk() {
                    return hyzk;
                }

                public void setHyzk(String hyzk) {
                    this.hyzk = hyzk;
                }

                public String getLysj() {
                    return lysj;
                }

                public void setLysj(String lysj) {
                    this.lysj = lysj;
                }

                public String getHjlx() {
                    return hjlx;
                }

                public void setHjlx(String hjlx) {
                    this.hjlx = hjlx;
                }

                public String getHjfl() {
                    return hjfl;
                }

                public void setHjfl(String hjfl) {
                    this.hjfl = hjfl;
                }

                public String getHjszd() {
                    return hjszd;
                }

                public void setHjszd(String hjszd) {
                    this.hjszd = hjszd;
                }

                public Object getRwrq() {
                    return rwrq;
                }

                public void setRwrq(Object rwrq) {
                    this.rwrq = rwrq;
                }

                public Object getTwbz() {
                    return twbz;
                }

                public void setTwbz(Object twbz) {
                    this.twbz = twbz;
                }

                public Object getSbrq() {
                    return sbrq;
                }

                public void setSbrq(Object sbrq) {
                    this.sbrq = sbrq;
                }

                public Object getRzrq() {
                    return rzrq;
                }

                public void setRzrq(Object rzrq) {
                    this.rzrq = rzrq;
                }

                public Object getRzzt() {
                    return rzzt;
                }

                public void setRzzt(Object rzzt) {
                    this.rzzt = rzzt;
                }

                public Object getRzbz() {
                    return rzbz;
                }

                public void setRzbz(Object rzbz) {
                    this.rzbz = rzbz;
                }
            }

            public static class ZzxxBean {
                /**
                 * yhbh : A000000028
                 * sfzzm : /2018/07/23/1532351614440.jpg
                 * sfzfm : /2018/07/23/1532351614519.jpg
                 * hkb : /2018/07/23/1532351614309.jpg
                 * jhz : /2018/07/23/1532351614366.jpg
                 * lhz : null
                 * jgz : null
                 * hz : null
                 */

                private String yhbh;
                private String sfzzm;
                private String sfzfm;
                private String hkb;
                private String jhz;
                private String lhz;
                private Object jgz;
                private Object hz;

                public String getYhbh() {
                    return yhbh;
                }

                public void setYhbh(String yhbh) {
                    this.yhbh = yhbh;
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

                public String getHkb() {
                    return hkb;
                }

                public void setHkb(String hkb) {
                    this.hkb = hkb;
                }

                public String getJhz() {
                    return jhz;
                }

                public void setJhz(String jhz) {
                    this.jhz = jhz;
                }

                public String getLhz() {
                    return lhz;
                }

                public void setLhz(String lhz) {
                    this.lhz = lhz;
                }

                public Object getJgz() {
                    return jgz;
                }

                public void setJgz(Object jgz) {
                    this.jgz = jgz;
                }

                public Object getHz() {
                    return hz;
                }

                public void setHz(Object hz) {
                    this.hz = hz;
                }
            }

            public static class SfzBean {
                /**
                 * yhbh : A000000028
                 * xm : 向磊
                 * zjhm : 430121199308047031
                 * xb : 女
                 * mz : 汉
                 * zz : 湖南省长沙县白沙镇曾家坊村桥上屋组437号
                 * qfjg : 长沙县公安局
                 * yxq : 20140117-20240117
                 * csrq : 19930804
                 * cjrq : null
                 */

                private String yhbh;
                private String xm;
                private String zjhm;
                private String xb;
                private String mz;
                private String zz;
                private String qfjg;
                private String yxq;
                private String csrq;
                private Object cjrq;

                public String getYhbh() {
                    return yhbh;
                }

                public void setYhbh(String yhbh) {
                    this.yhbh = yhbh;
                }

                public String getXm() {
                    return xm;
                }

                public void setXm(String xm) {
                    this.xm = xm;
                }

                public String getZjhm() {
                    return zjhm;
                }

                public void setZjhm(String zjhm) {
                    this.zjhm = zjhm;
                }

                public String getXb() {
                    return xb;
                }

                public void setXb(String xb) {
                    this.xb = xb;
                }

                public String getMz() {
                    return mz;
                }

                public void setMz(String mz) {
                    this.mz = mz;
                }

                public String getZz() {
                    return zz;
                }

                public void setZz(String zz) {
                    this.zz = zz;
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

                public String getCsrq() {
                    return csrq;
                }

                public void setCsrq(String csrq) {
                    this.csrq = csrq;
                }

                public Object getCjrq() {
                    return cjrq;
                }

                public void setCjrq(Object cjrq) {
                    this.cjrq = cjrq;
                }
            }
        }
    }
}
