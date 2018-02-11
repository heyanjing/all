package com.he.maven.all.ssh.web.service;

import com.he.maven.all.ssh.base.bean.Holiday2;
import com.he.maven.all.ssh.base.core.Guava;
import com.he.maven.all.ssh.base.core.enumm.HolidayEnum;
import com.he.maven.all.ssh.entity.Holiday;
import com.he.maven.all.ssh.web.dao.HolidayDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by heyanjing on 2017/12/19 10:37.
 */
@Service
public class HolidayService {
    private static final Logger log = LoggerFactory.getLogger(HolidayService.class);
    private HolidayDao holidayDao;

    @Autowired
    public void setHolidayDao(HolidayDao holidayDao) {
        this.holidayDao = holidayDao;
    }

    public void save(Holiday holiday) {
        this.holidayDao.save(holiday);
    }

    public void saveAll(Iterable<Holiday> holiday) {
        this.holidayDao.saveAll(holiday);
    }

    public Holiday getByLocalDateStr(String localDateStr) {
        return this.holidayDao.getByLocalDateStr(localDateStr);
    }

    public List<Holiday> findByLocalDateStrs(String localDateStrs) {
        List<Holiday> list = Guava.newArrayList();
        Arrays.asList(localDateStrs.split(",")).forEach(localDateStr -> {
            list.add(this.getByLocalDateStr(localDateStr));
        });
        return list;
    }

    public List<Holiday> findByMonth(String localDateStr) {
        return this.holidayDao.findByLocalDateStrLikeAndTypeNotAndHolidayTypeIsNotNullOrderByLocalDateAsc(localDateStr + "%", HolidayEnum.txgzr.getValue());
    }

    public List<Holiday> findAll() {
        return this.holidayDao.findAll();
    }

    public List<Holiday> findBySql() {
        this.holidayDao.findBySql();
        return this.holidayDao.findByJpql();
    }

    public List<Holiday2> findBeanBySql() {
        return this.holidayDao.findBeanBySql();
    }

    public Page<Holiday> pageBySql(Integer pageNumber, Integer pageSize) {
        this.holidayDao.pageBySql(pageNumber, pageSize);
        return this.holidayDao.pageByJpql(pageNumber, pageSize);
    }

    public List<Map<String, Object>> findMapListBySql() {
        return this.holidayDao.findMapListBySql();
    }

    public Page<Map<String, Object>> pageMapListBySql(Integer pageNumber, Integer pageSize) {
        return this.holidayDao.pageMapListBySql(pageNumber, pageSize);
    }

    public Page<Holiday2> pageBeanBySql(Integer pageNumber, Integer pageSize) {
        return this.holidayDao.pageBeanBySql(pageNumber, pageSize);
    }
}
