package com.he.maven.all.ssh.base.bean;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by heyanjing on 2017/12/19 9:43.
 */
public class Holiday2  {

    private String localDateStr;//日期字符串
    private LocalDate localDate;//日期
    private Integer type;//日期类型
    private String typeStr;//日期类型字符串
    private Integer totalDay;//当年的总天数
    private Integer holidayType;//节假日类型
    private String holidayTypeStr;//节假日类型字符串
    private Date date;//节假日类型字符串

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHolidayTypeStr() {
        return holidayTypeStr;
    }

    public void setHolidayTypeStr(String holidayTypeStr) {
        this.holidayTypeStr = holidayTypeStr;
    }

    public Integer getHolidayType() {
        return holidayType;
    }

    public void setHolidayType(Integer holidayType) {
        this.holidayType = holidayType;
    }

    public Holiday2(String localDateStr, LocalDate localDate, Integer type, String typeStr, Integer totalDay, Integer holidayType) {

        this.localDateStr = localDateStr;
        this.localDate = localDate;
        this.type = type;
        this.typeStr = typeStr;
        this.totalDay = totalDay;
        this.holidayType = holidayType;
    }

    public Holiday2(String localDateStr, LocalDate localDate, Integer totalDay) {
        this.localDateStr = localDateStr;
        this.localDate = localDate;
        this.totalDay = totalDay;
    }

    public String getLocalDateStr() {
        return localDateStr;
    }

    public void setLocalDateStr(String localDateStr) {
        this.localDateStr = localDateStr;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public Integer getTotalDay() {
        return totalDay;
    }

    public void setTotalDay(Integer totalDay) {
        this.totalDay = totalDay;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Holiday2(String localDateStr, LocalDate localDate, Integer type, String typeStr, Integer totalDay) {
        this.localDateStr = localDateStr;
        this.localDate = localDate;
        this.type = type;
        this.typeStr = typeStr;
        this.totalDay = totalDay;
    }

    public Holiday2() {

    }
}
