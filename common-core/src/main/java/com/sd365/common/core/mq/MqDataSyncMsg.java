package com.sd365.common.core.mq;

import java.io.Serializable;
import java.util.List;

/**
 * @Author jxd
 * @Date 2020/12/6  11:12 上午
 * @Version 1.0
 * @Write For MQ的数据同步
 * @Email waniiu@126.com
 */
public class MqDataSyncMsg implements Serializable{

    private static final long serialVersionUID = -3816147229912696442L;
    /**
     * 操作的表名
     */
    private String tableName;
    /**
     * 操作的类型：INSERT,UPDATE,DELETE
     */
    private ActionType actionType;
    /**
     * 传输的数据，list
     */
    private List<Object> dataList;

    /**
     * 一次 单条或者 多条操作
     * @param tableName
     * @param actionType
     * @param dataList
     */
    public MqDataSyncMsg(String tableName, ActionType actionType, List<Object> dataList) {
        this.tableName = tableName;
        this.actionType = actionType;
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "MqDataSyncMsg{" +
                "tableName='" + tableName + '\'' +
                ", actionType=" + actionType +
                ", dataList=" + dataList +
                '}';
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public List<Object> getDataList() {
        return dataList;
    }

    public void setDataList(List<Object> dataList) {
        this.dataList = dataList;
    }
}
