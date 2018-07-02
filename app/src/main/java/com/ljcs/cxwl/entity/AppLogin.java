package com.ljcs.cxwl.entity;

/**
 * @ClassName: AppXinZhushouLogin
 * @Description: 封装登录实体
 * @author wwx
 * @date 2016-12-24 上午9:42:00
 */

public class AppLogin  extends BaseEntity{

    private String uuid;
    private LoginInfo row;
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LoginInfo getRow() {
        return row;
    }

    public void setRow(LoginInfo row) {
        this.row = row;
    }


	class  LoginInfo{
        // Fields
        /** 管理员ID */
        private Integer adminId;

        /** 管理员账号 */
        private String adminUserName;

        /** 管理员密码 */
        private String adminPassWord;

        /** 管理员等级 */
        private Long adminLevel;

        /** 项目编号 */
        private Integer adminXiangmuId;

        /** 管理员楼盘 */
        private String adminXiangmuName;

        /** 管理员昵称 */
        private String adminNickName;

        /** 管理员状态 0启用(管理员) 1禁用 2临时(值班) 3配送员 4管理员+配送员 */
        private Integer adminState;
        /** 管理员部门ID */
        private Integer adminDepartId;
        /** 管理员上级编号 */
        private Integer adminMgr;


        //配送人详细信息
        /** 配送人电话 */
        private String cxwyPeisongPhone;
        /** 配送次数 */
        private String cxwyPeisongTotal;
        /** 配送人总收入 */
        private String cxwyPeisongTotalPrice;
        /** 配送人是否接单状态 开启 关闭 */
        private String cxwyPeisongState;
        /** 是否是负责人：0负责人 1非负责人 */
        private Integer cxwyPeisongFuzeren;
        /** 配送人使用状态0使用 1禁用 */
        private Integer cxwyType;


        public Integer getAdminId() {
            return adminId;
        }
        public void setAdminId(Integer adminId) {
            this.adminId = adminId;
        }
        public String getAdminUserName() {
            return adminUserName;
        }
        public void setAdminUserName(String adminUserName) {
            this.adminUserName = adminUserName;
        }
        public String getAdminPassWord() {
            return adminPassWord;
        }
        public void setAdminPassWord(String adminPassWord) {
            this.adminPassWord = adminPassWord;
        }
        public Long getAdminLevel() {
            return adminLevel;
        }
        public void setAdminLevel(Long adminLevel) {
            this.adminLevel = adminLevel;
        }
        public Integer getAdminXiangmuId() {
            return adminXiangmuId;
        }
        public void setAdminXiangmuId(Integer adminXiangmuId) {
            this.adminXiangmuId = adminXiangmuId;
        }
        public String getAdminXiangmuName() {
            return adminXiangmuName;
        }
        public void setAdminXiangmuName(String adminXiangmuName) {
            this.adminXiangmuName = adminXiangmuName;
        }
        public String getAdminNickName() {
            return adminNickName;
        }
        public void setAdminNickName(String adminNickName) {
            this.adminNickName = adminNickName;
        }
        public Integer getAdminState() {
            return adminState;
        }
        public void setAdminState(Integer adminState) {
            this.adminState = adminState;
        }
        public Integer getAdminDepartId() {
            return adminDepartId;
        }
        public void setAdminDepartId(Integer adminDepartId) {
            this.adminDepartId = adminDepartId;
        }
        public Integer getAdminMgr() {
            return adminMgr;
        }
        public void setAdminMgr(Integer adminMgr) {
            this.adminMgr = adminMgr;
        }
        public String getCxwyPeisongPhone() {
            return cxwyPeisongPhone;
        }
        public void setCxwyPeisongPhone(String cxwyPeisongPhone) {
            this.cxwyPeisongPhone = cxwyPeisongPhone;
        }
        public String getCxwyPeisongTotal() {
            return cxwyPeisongTotal;
        }
        public void setCxwyPeisongTotal(String cxwyPeisongTotal) {
            this.cxwyPeisongTotal = cxwyPeisongTotal;
        }
        public String getCxwyPeisongTotalPrice() {
            return cxwyPeisongTotalPrice;
        }
        public void setCxwyPeisongTotalPrice(String cxwyPeisongTotalPrice) {
            this.cxwyPeisongTotalPrice = cxwyPeisongTotalPrice;
        }
        public String getCxwyPeisongState() {
            return cxwyPeisongState;
        }
        public void setCxwyPeisongState(String cxwyPeisongState) {
            this.cxwyPeisongState = cxwyPeisongState;
        }
        public Integer getCxwyPeisongFuzeren() {
            return cxwyPeisongFuzeren;
        }
        public void setCxwyPeisongFuzeren(Integer cxwyPeisongFuzeren) {
            this.cxwyPeisongFuzeren = cxwyPeisongFuzeren;
        }
        public Integer getCxwyType() {
            return cxwyType;
        }
        public void setCxwyType(Integer cxwyType) {
            this.cxwyType = cxwyType;
        }

        public LoginInfo() {
        }

        public LoginInfo(Integer adminId, String adminUserName,
                        String adminPassWord, Long adminLevel, Integer adminXiangmuId,
                        String adminXiangmuName, String adminNickName, Integer adminState,
                        Integer adminDepartId, Integer adminMgr, String cxwyPeisongPhone,
                        String cxwyPeisongTotal, String cxwyPeisongTotalPrice,
                        String cxwyPeisongState, Integer cxwyPeisongFuzeren,
                        Integer cxwyType) {
            super();
            this.adminId = adminId;
            this.adminUserName = adminUserName;
            this.adminPassWord = adminPassWord;
            this.adminLevel = adminLevel;
            this.adminXiangmuId = adminXiangmuId;
            this.adminXiangmuName = adminXiangmuName;
            this.adminNickName = adminNickName;
            this.adminState = adminState;
            this.adminDepartId = adminDepartId;
            this.adminMgr = adminMgr;
            this.cxwyPeisongPhone = cxwyPeisongPhone;
            this.cxwyPeisongTotal = cxwyPeisongTotal;
            this.cxwyPeisongTotalPrice = cxwyPeisongTotalPrice;
            this.cxwyPeisongState = cxwyPeisongState;
            this.cxwyPeisongFuzeren = cxwyPeisongFuzeren;
            this.cxwyType = cxwyType;
        }


        @Override
        public String toString() {
            return "AppXinZhushouLogin [adminId=" + adminId + ", adminUserName="
                    + adminUserName + ", adminPassWord=" + adminPassWord
                    + ", adminLevel=" + adminLevel + ", adminXiangmuId="
                    + adminXiangmuId + ", adminXiangmuName=" + adminXiangmuName
                    + ", adminNickName=" + adminNickName + ", adminState="
                    + adminState + ", adminDepartId=" + adminDepartId
                    + ", adminMgr=" + adminMgr + ", cxwyPeisongPhone="
                    + cxwyPeisongPhone + ", cxwyPeisongTotal=" + cxwyPeisongTotal
                    + ", cxwyPeisongTotalPrice=" + cxwyPeisongTotalPrice
                    + ", cxwyPeisongState=" + cxwyPeisongState
                    + ", cxwyPeisongFuzeren=" + cxwyPeisongFuzeren + ", cxwyType="
                    + cxwyType + "]";
        }

    }


}
