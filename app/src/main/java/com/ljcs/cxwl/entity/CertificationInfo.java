package com.ljcs.cxwl.entity;

import java.util.List;

/**
 * @author xlei
 * @Date 2018/6/27.
 */

public class CertificationInfo {

    /**
     * 实名认证 身份证信息
     */
    private String name;//姓名
    private String sex;
    private String ethnic;//民族
    private String birthday;//出生年月
    private String address;
    private String idcard;//身份证号码
    private String pic_path_zheng;//身份证正面照路劲
    private String signDate;//身份证开始时间
    private String expiryDate;//身份证失效时间
    private String issueAuthority;//身份证办理机构
    private String pic_path_fan;//身份证反面照路劲
    private String pic_path_hk;//户口面照路劲
    private String leixing1;//户籍类型
    private String leixing2;//户口类型
    private String leixing3;//婚姻状况

    /**
     * 配偶身份证信息
     */
    private String name_peiou;//姓名
    private String sex_peiou;
    private String ethnic_peiou;//民族
    private String birthday_peiou;//出生年月
    private String address_peiou;
    private String idcard_peiou;//身份证号码
    private String pic_path_zheng_peiou;//身份证正面照路劲
    private String signDate_peiou;//身份证开始时间
    private String expiryDate_peiou;//身份证失效时间
    private String issueAuthority_peiou;//身份证办理机构
    private String pic_path_fan_peiou;//身份证反面照路劲
    private String pic_path_hk_peiou;//户口面照路劲
    private String pic_path_jhz_peiou;//结婚照路劲
    private String leixing1_peiou;//户籍类型
    private String leixing2_peiou;//户口类型
    private String leixing3_peiou;//婚姻状况
    /**
     * 子女信息
     */
    private String name_zinv;//姓名
    private String sex_zinv;
    private String idcard_zinv;//身份证号码
    private String leixing1_zinv;//户籍类型
    private String leixing2_zinv;//户口类型
    private String leixing3_zinv;//婚姻状况
    private String guangxi_zinv;//与申请人关系
    private String pic_path_hk_zinv;//户口面照路劲

    private List<CertificationInfo> list_zinv;//子女集合

    public CertificationInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPic_path_zheng() {
        return pic_path_zheng;
    }

    public void setPic_path_zheng(String pic_path_zheng) {
        this.pic_path_zheng = pic_path_zheng;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getIssueAuthority() {
        return issueAuthority;
    }

    public void setIssueAuthority(String issueAuthority) {
        this.issueAuthority = issueAuthority;
    }

    public String getPic_path_fan() {
        return pic_path_fan;
    }

    public void setPic_path_fan(String pic_path_fan) {
        this.pic_path_fan = pic_path_fan;
    }

    public String getName_peiou() {
        return name_peiou;
    }

    public void setName_peiou(String name_peiou) {
        this.name_peiou = name_peiou;
    }

    public String getSex_peiou() {
        return sex_peiou;
    }

    public void setSex_peiou(String sex_peiou) {
        this.sex_peiou = sex_peiou;
    }

    public String getEthnic_peiou() {
        return ethnic_peiou;
    }

    public void setEthnic_peiou(String ethnic_peiou) {
        this.ethnic_peiou = ethnic_peiou;
    }

    public String getBirthday_peiou() {
        return birthday_peiou;
    }

    public void setBirthday_peiou(String birthday_peiou) {
        this.birthday_peiou = birthday_peiou;
    }

    public String getAddress_peiou() {
        return address_peiou;
    }

    public void setAddress_peiou(String address_peiou) {
        this.address_peiou = address_peiou;
    }

    public String getIdcard_peiou() {
        return idcard_peiou;
    }

    public void setIdcard_peiou(String idcard_peiou) {
        this.idcard_peiou = idcard_peiou;
    }

    public String getPic_path_zheng_peiou() {
        return pic_path_zheng_peiou;
    }

    public void setPic_path_zheng_peiou(String pic_path_zheng_peiou) {
        this.pic_path_zheng_peiou = pic_path_zheng_peiou;
    }

    public String getSignDate_peiou() {
        return signDate_peiou;
    }

    public void setSignDate_peiou(String signDate_peiou) {
        this.signDate_peiou = signDate_peiou;
    }

    public String getExpiryDate_peiou() {
        return expiryDate_peiou;
    }

    public void setExpiryDate_peiou(String expiryDate_peiou) {
        this.expiryDate_peiou = expiryDate_peiou;
    }

    public String getIssueAuthority_peiou() {
        return issueAuthority_peiou;
    }

    public void setIssueAuthority_peiou(String issueAuthority_peiou) {
        this.issueAuthority_peiou = issueAuthority_peiou;
    }

    public String getPic_path_fan_peiou() {
        return pic_path_fan_peiou;
    }

    public void setPic_path_fan_peiou(String pic_path_fan_peiou) {
        this.pic_path_fan_peiou = pic_path_fan_peiou;
    }

    public String getPic_path_hk() {
        return pic_path_hk;
    }

    public void setPic_path_hk(String pic_path_hk) {
        this.pic_path_hk = pic_path_hk;
    }

    public String getPic_path_hk_peiou() {
        return pic_path_hk_peiou;
    }

    public void setPic_path_hk_peiou(String pic_path_hk_peiou) {
        this.pic_path_hk_peiou = pic_path_hk_peiou;
    }

    public String getPic_path_jhz_peiou() {
        return pic_path_jhz_peiou;
    }

    public void setPic_path_jhz_peiou(String pic_path_jhz_peiou) {
        this.pic_path_jhz_peiou = pic_path_jhz_peiou;
    }

    public String getLeixing1() {
        return leixing1;
    }

    public void setLeixing1(String leixing1) {
        this.leixing1 = leixing1;
    }

    public String getLeixing2() {
        return leixing2;
    }

    public void setLeixing2(String leixing2) {
        this.leixing2 = leixing2;
    }

    public String getLeixing3() {
        return leixing3;
    }

    public void setLeixing3(String leixing3) {
        this.leixing3 = leixing3;
    }

    public String getLeixing1_peiou() {
        return leixing1_peiou;
    }

    public void setLeixing1_peiou(String leixing1_peiou) {
        this.leixing1_peiou = leixing1_peiou;
    }

    public String getLeixing2_peiou() {
        return leixing2_peiou;
    }

    public void setLeixing2_peiou(String leixing2_peiou) {
        this.leixing2_peiou = leixing2_peiou;
    }

    public String getLeixing3_peiou() {
        return leixing3_peiou;
    }

    public void setLeixing3_peiou(String leixing3_peiou) {
        this.leixing3_peiou = leixing3_peiou;
    }

    public String getName_zinv() {
        return name_zinv;
    }

    public void setName_zinv(String name_zinv) {
        this.name_zinv = name_zinv;
    }

    public String getSex_zinv() {
        return sex_zinv;
    }

    public void setSex_zinv(String sex_zinv) {
        this.sex_zinv = sex_zinv;
    }

    public String getIdcard_zinv() {
        return idcard_zinv;
    }

    public void setIdcard_zinv(String idcard_zinv) {
        this.idcard_zinv = idcard_zinv;
    }

    public String getLeixing1_zinv() {
        return leixing1_zinv;
    }

    public void setLeixing1_zinv(String leixing1_zinv) {
        this.leixing1_zinv = leixing1_zinv;
    }

    public String getLeixing2_zinv() {
        return leixing2_zinv;
    }

    public void setLeixing2_zinv(String leixing2_zinv) {
        this.leixing2_zinv = leixing2_zinv;
    }

    public String getLeixing3_zinv() {
        return leixing3_zinv;
    }

    public void setLeixing3_zinv(String leixing3_zinv) {
        this.leixing3_zinv = leixing3_zinv;
    }

    public String getGuangxi_zinv() {
        return guangxi_zinv;
    }

    public void setGuangxi_zinv(String guangxi_zinv) {
        this.guangxi_zinv = guangxi_zinv;
    }

    public List<CertificationInfo> getList_zinv() {
        return list_zinv;
    }

    public void setList_zinv(List<CertificationInfo> list_zinv) {
        this.list_zinv = list_zinv;
    }

    public String getPic_path_hk_zinv() {
        return pic_path_hk_zinv;
    }

    public void setPic_path_hk_zinv(String pic_path_hk_zinv) {
        this.pic_path_hk_zinv = pic_path_hk_zinv;
    }
}
