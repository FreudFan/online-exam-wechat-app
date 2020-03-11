package edu.sandau.entity;

import java.util.Date;

public class ScheduleInfo {
    private String name;
    private Integer scheduleId;
    private String typeName;
    private Integer type;
    private String description;
    private Double score;
    private Date beginTime;
    private Date endTime;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getScheduleId() {
        return scheduleId;
    }
    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Double getScore() {
        return score;
    }
    public void setScore(Double score) {
        this.score = score;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
