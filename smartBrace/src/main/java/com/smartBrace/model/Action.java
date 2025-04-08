package com.smartBrace.model;

import java.time.LocalDateTime;

public class Action {
    private String id;  // 动作的唯一标识
    private String actionName;  // 动作名称
    private LocalDateTime createdAt;  // 创建时间

    // 构造函数、getters 和 setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
