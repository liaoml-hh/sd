package com.sd365.common.core.common.api;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @class Head
 * @classdesc  将请求转化为JSON String 放在 Http 请求的 commonInfo
 * 自定义字段中
 * @author Administrator
 * @date 2020-10-2  17:24
 * @version 1.0.0
 * @see
 * @since
 */
public  class Head implements Serializable {
    private static final long serialVersionUID = -8134386938077724762L;
    /**
     * 应用程序版本
     */
    private String version;

    /**
     * 全局流水-用户工号-YYYYMMDDHHMMSS，这个流水号记录于调用链中
     */
    @NotNull
    private String gMessage;

    /**
     * 服务之间发起请求的时候填写
     */
    private String rMessage;

    /**
     * 依据该字段区分 body 对象类型
     */
    private String bodyType;

    /**
     * 设备号
     */
    private String deviceId;

    /**
     * 设备类型
     */
    private Integer deviceType;

    /**
     * 例如 android 4.0 chrome 7
     * Ios 6.0 chrome 6
     */
    private String osAndBrowser;

    /**
     * 1标记加密 0不加密
     * 默认为 0
     */
    private Integer encryptFlag = 0;

    public Head() {
        // non-args constructor
        gMessage = "xxxxxx";
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getgMessage() {
        return gMessage;
    }

    public void setgMessage(String gMessage) {
        this.gMessage = gMessage;
    }

    public String getrMessage() {
        return rMessage;
    }

    public void setrMessage(String rMessage) {
        this.rMessage = rMessage;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getOsAndBrowser() {
        return osAndBrowser;
    }

    public void setOsAndBrowser(String osAndBrowser) {
        this.osAndBrowser = osAndBrowser;
    }

    public Integer getEncryptFlag() {
        return encryptFlag;
    }

    public void setEncryptFlag(Integer encryptFlag) {
        this.encryptFlag = encryptFlag;
    }

}
