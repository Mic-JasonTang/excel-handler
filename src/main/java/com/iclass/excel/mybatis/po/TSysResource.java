package com.iclass.excel.mybatis.po;

import java.util.Date;

public class TSysResource {
    // 不需要
    private Long id;

    private String accessCode;

    private Boolean available;

    // 不需要
    private String cls;

    // 不需要
    private String lableKey;

    private String name;

    private Long parentId;

    // 不需要
    private String permission;

    private String resourceType;

    private Integer sortNum;

    private String url;

    private String systemId;

    private String creatorId;

    // 后台设置
    private Date createdTime;

    private String modifierId;

    // 后台设置
    private Date modifiedTime;

    private Integer status;

    public TSysResource(Long id, String accessCode, Boolean available, String cls, String lableKey, String name, Long parentId, String permission, String resourceType, Integer sortNum, String url, String systemId, String creatorId, Date createdTime, String modifierId, Date modifiedTime, Integer status) {
        this.id = id;
        this.accessCode = accessCode;
        this.available = available;
        this.cls = cls;
        this.lableKey = lableKey;
        this.name = name;
        this.parentId = parentId;
        this.permission = permission;
        this.resourceType = resourceType;
        this.sortNum = sortNum;
        this.url = url;
        this.systemId = systemId;
        this.creatorId = creatorId;
        this.createdTime = createdTime;
        this.modifierId = modifierId;
        this.modifiedTime = modifiedTime;
        this.status = status;
    }

    public TSysResource() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode == null ? null : accessCode.trim();
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls == null ? null : cls.trim();
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType == null ? null : resourceType.trim();
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

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId == null ? null : modifierId.trim();
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TSysResource{" +
                "id=" + id +
                ", accessCode='" + accessCode + '\'' +
                ", available=" + available +
                ", cls='" + cls + '\'' +
                ", lableKey='" + lableKey + '\'' +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", permission='" + permission + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", sortNum=" + sortNum +
                ", url='" + url + '\'' +
                ", systemId='" + systemId + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", createdTime=" + createdTime +
                ", modifierId='" + modifierId + '\'' +
                ", modifiedTime=" + modifiedTime +
                ", status=" + status +
                '}';
    }
}