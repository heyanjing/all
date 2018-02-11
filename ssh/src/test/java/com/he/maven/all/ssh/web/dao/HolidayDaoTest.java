package com.he.maven.all.ssh.web.dao;

import com.alibaba.fastjson.JSON;
import com.he.maven.all.ssh.entity.Holiday;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by heyanjing on 2018/2/10 16:58.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-base*.xml"})
@Slf4j
public class HolidayDaoTest {
    @Autowired
    HolidayDao holidayDao;

    @Test
    public void holiday3() {
        List<Holiday> holidayList = this.holidayDao.findBySql();
        log.info("{}", JSON.toJSONString(holidayList));
        while (true) {

        }
    }
}