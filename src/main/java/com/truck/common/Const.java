package com.truck.common;

import com.google.common.collect.Sets;

import java.util.Set;

public class Const {
    public static final String CURRENT_USER = "currentUser";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String CURRENT_ADMIN = "currentADMIN";
    public static final String ADMINNAME = "adminname";
    public static final String COMPANYNAME = "companyname";
    public static final String SHOPNAME = "shopname";
    public static final String SHOPEMAIL = "shopemail";
    public static final String SHOPNUM = "adminId";
    public static final String DEVICE_SYSTEM_USER_PHONE = "88888888";


    public interface Role {
        int ROLE_COSTOMER = 0;//普通用户
        int ROLE_ADMIN = 1;//超级管理员
        int ROLE_VIPUSER = 2;//vip用户
    }

    public interface AdminRole {
        int ADMINROLE_ADMIN = 0;//管理员
        int ADMINROLE_SUPERADMIN = 1; //超级管理员
    }

    public enum TransportStatusEnum{
        OVER_EXIT(0,"已出口"),
        CONFIRM(1,"已接收"),
        ON_ENTRY(2,"正在入库");

        private String value;
        private int code;
        TransportStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
        public static TransportStatusEnum codeOf(int code){
            for(TransportStatusEnum transportStatusEnum : values()){
                if(transportStatusEnum.getCode() == code){
                    return transportStatusEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }

    }

    public enum OutStatusEnum{
        UN_OUT(0,"未出库"),
        OVER_OUT(1,"已出库");

        private String value;
        private int code;
        OutStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
        public static OutStatusEnum codeOf(int code){
            for(OutStatusEnum outStatusEnum : values()){
                if(outStatusEnum.getCode() == code){
                    return outStatusEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }

    }

    public enum InventoryStatusEnum{
        OVER_INVENTORY(1,"已盘点");

        private String value;
        private int code;
        InventoryStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
        public static InventoryStatusEnum codeOf(int code){
            for(InventoryStatusEnum inventoryStatusEnum : values()){
                if(inventoryStatusEnum.getCode() == code){
                    return inventoryStatusEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }

    }

    public interface Cart {
        int CHECKED = 1;//即购物车选中状态
        int UN_CHECKED = 0;//购物车未选中状态

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }

    public enum EntryStatusEnum{
        STANDBY(0,"未入库"),
        CONFIRM(1,"检查中"),
        FINISH(2,"已入库");

        private String value;
        private int code;
        EntryStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
        public static EntryStatusEnum codeOf(int code){
            for(EntryStatusEnum entryStatusEnum : values()){
                if(entryStatusEnum.getCode() == code){
                    return entryStatusEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }

    }

    public interface AlipayCallback {
        String TRADE_STATUS_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
        String TRADE_STATUS_TRADE_SUCCESS = "TRADE_SUCCESS";

        String RESPONSE_SUCCESS = "success";
        String RESPONSE_FAILED = "failed";
    }


    public enum PayPlatformEnum {
        ALIPAY(1, "支付宝");

        PayPlatformEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

    public enum PaymentTypeEnum {
        ARRIVE_PAY(0, "货到付款"),
        ONLINE_PAY(1, "在线支付");

        PaymentTypeEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }


        public static PaymentTypeEnum codeOf(int code) {
            for (PaymentTypeEnum paymentTypeEnum : values()) {
                if (paymentTypeEnum.getCode() == code) {
                    return paymentTypeEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }

    }
}
