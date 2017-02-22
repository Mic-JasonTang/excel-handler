package com.iclass.excel.mybatis.po;

import java.util.Date;

public class TSysSystem {
    private Long id;

    private Date createdTime;

    private String creatorId;

    private Date modifiedTime;

    private String modifierId;

    private String code;

    private String lableKey;

    private String name;

    private String prefixFlag;

    private Integer sortNum;

    private String url;

    private Integer status;

    public TSysSystem(Long id, Date createdTime, String creatorId, Date modifiedTime, String modifierId, String code, String lableKey, String name, String prefixFlag, Integer sortNum, String url, Integer status) {
        this.id = id;
        this.createdTime = createdTime;
        this.creatorId = creatorId;
        this.modifiedTime = modifiedTime;
        this.modifierId = modifierId;
        this.code = code;
        this.lableKey = lableKey;
        this.name = name;
        this.prefixFlag = prefixFlag;
        this.sortNum = sortNum;
        this.url = url;
        this.status = status;
    }

    public TSysSystem() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId == null ? null : modifierId.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getLableKey() {
        return lableKey;
    }

    public void setLableKey(String lableKey) {
        this.lableKey = lableKey == null ? null : lableKey.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPrefixFlag() {
        return prefixFlag;
    }

    public void setPrefixFlag(String prefixFlag) {
        this.prefixFlag = prefixFlag == null ? null : prefixFlag.trim();
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}