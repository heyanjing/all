package com.he.maven.all.ssh.web.dao.impl;

import com.alibaba.fastjson.JSON;
import com.he.maven.all.ssh.base.BaseDao;
import com.he.maven.all.ssh.base.bean.Holiday2;
import com.he.maven.all.ssh.base.core.Guava;
import com.he.maven.all.ssh.entity.Holiday;
import com.he.maven.all.ssh.web.dao.custom.HolidayCustomDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by heyanjing on 2017/12/19 10:35.
 */
@Slf4j
public class HolidayDaoImpl extends BaseDao<Holiday> implements HolidayCustomDao<Holiday> {

    @Override
    public List<Holiday> findBySql() {
        String sql = "select * from holiday where 1=1 ";

        String tempSql1 = sql + " order by localdate desc ";
        List<Holiday> list1 = super.findBySql(tempSql1);
        log.info("{}", JSON.toJSONString(list1));

        Map<String, Object> params1 = Guava.newHashMap();
        String tempSql2 = sql + " and localdatestr =:localdatestr";
        params1.put("localdatestr", "20181231");
        List<Holiday> list2 = super.findBySql(tempSql2, params1);
        log.info("{}", JSON.toJSONString(list2));

        List<Object> params2 = Guava.newArrayList();
        String tempSql3 = sql + " and localdatestr =?0";
        params2.add("20181230");
        List<Holiday> list3 = super.findBySql(tempSql3, params2);
        log.info("{}", JSON.toJSONString(list3));
        return list3;
    }

    @Override
    public List<Holiday> findByJpql() {
        String jpql = "select c from Holiday c where 1=1";
        String tempSql1 = jpql + " order by c.localDate desc ";
        List<Holiday> list1 = super.findByJpql(tempSql1);
        log.info("{}", JSON.toJSONString(list1));

        Map<String, Object> params1 = Guava.newHashMap();
        String tempSql2 = jpql + " and c.localDateStr =:localDateStr";
        params1.put("localDateStr", "20181231");
        List<Holiday> list2 = super.findByJpql(tempSql2, params1);
        log.info("{}", JSON.toJSONString(list2));

        List<Object> params2 = Guava.newArrayList();
        String tempSql3 = jpql + " and c.localDateStr =?0";
        params2.add("20181230");
        List<Holiday> list3 = super.findByJpql(tempSql3, params2);
        log.info("{}", JSON.toJSONString(list3));
        return list3;
    }


    @Override
    public Page<Holiday> pageBySql(Integer pageNumber, Integer pageSize) {
        String sql = "select * from holiday where 1=1 ";
        String tempSql1 = sql + " order by localdate desc ";
        Page<Holiday> list1 = super.pageBySql(tempSql1, pageNumber, pageSize);
        log.info("{}", JSON.toJSONString(list1));

        Map<String, Object> params1 = Guava.newHashMap();
        String tempSql2 = sql + " and localdatestr =:localdatestr";
        params1.put("localdatestr", "20181231");
        Page<Holiday> list2 = super.pageBySql(tempSql2, pageNumber, pageSize, params1);
        log.info("{}", JSON.toJSONString(list2));

        List<Object> params2 = Guava.newArrayList();
        String tempSql3 = sql + " and localdatestr =?0";
        params2.add("20181230");
        Page<Holiday> list3 = super.pageBySql(tempSql3, pageNumber, pageSize, params2);
        log.info("{}", JSON.toJSONString(list3));
        return list3;
    }

    @Override
    public Page<Holiday> pageByJpql(Integer pageNumber, Integer pageSize) {
        String jpql = "select c from Holiday c where 1=1";
        String tempSql1 = jpql + " order by c.localDate desc ";
        Page<Holiday> list1 = super.pageByJpql(tempSql1, pageNumber, pageSize);
        log.info("{}", JSON.toJSONString(list1));

        Map<String, Object> params1 = Guava.newHashMap();
        String tempSql2 = jpql + " and c.localDateStr =:localDateStr";
        params1.put("localDateStr", "20181231");
        Page<Holiday> list2 = super.pageByJpql(tempSql2, pageNumber, pageSize, params1);
        log.info("{}", JSON.toJSONString(list2));

        List<Object> params2 = Guava.newArrayList();
        String tempSql3 = jpql + " and c.localDateStr =?0";
        params2.add("20181230");
        Page<Holiday> list3 = super.pageByJpql(tempSql3, pageNumber, pageSize, params2);
        log.info("{}", JSON.toJSONString(list3));
        return list3;
    }

    @Override
    public List<Holiday2> findBeanBySql() {
        String sql = "select * from holiday where 1=1 ";

        String tempSql1 = sql + " order by localdate desc ";
        List<Holiday2> list1 = super.findEntityClassBySql(tempSql1, Holiday2.class);
        log.info("{}", JSON.toJSONString(list1));

        Map<String, Object> params1 = Guava.newHashMap();
        String tempSql2 = sql + " and localdatestr =:localdatestr";
        params1.put("localdatestr", "20181231");
        List<Holiday2> list2 = super.findEntityClassBySql(tempSql2, Holiday2.class, params1);
        log.info("{}", JSON.toJSONString(list2));

        List<Object> params2 = Guava.newArrayList();
        String tempSql3 = sql + " and localdatestr =?0";
        params2.add("20181230");
        List<Holiday2> list3 = super.findEntityClassBySql(tempSql3, Holiday2.class, params2);
        log.info("{}", JSON.toJSONString(list3));
        return list3;
        //return null;
    }


    @Override
    public List<Map<String, Object>> findMapListBySql() {
        String sql = "select * from holiday where 1=1 ";

        String tempSql1 = sql + " order by localdate desc ";
        List<Map<String, Object>> list1 = super.findMapListBySql(tempSql1);
        log.info("{}", JSON.toJSONString(list1));

        Map<String, Object> params1 = Guava.newHashMap();
        String tempSql2 = sql + " and localdatestr =:localdatestr";
        params1.put("localdatestr", "20181231");
        List<Map<String, Object>> list2 = super.findMapListBySql(tempSql2, params1);
        log.info("{}", JSON.toJSONString(list2));

        List<Object> params2 = Guava.newArrayList();
        String tempSql3 = sql + " and localdatestr =?0";
        params2.add("20181230");
        List<Map<String, Object>> list3 = super.findMapListBySql(tempSql3, params2);
        log.info("{}", JSON.toJSONString(list3));
        return list3;
    }

    @Override
    public Page<Map<String, Object>> pageMapListBySql(Integer pageNumber, Integer pageSize) {
        String sql = "select * from holiday where 1=1 ";
        String tempSql1 = sql + " order by localdate desc ";
        Page<Map<String, Object>> list1 = super.pageMapListBySql(tempSql1, pageNumber, pageSize);
        log.info("{}", JSON.toJSONString(list1));

        Map<String, Object> params1 = Guava.newHashMap();
        String tempSql2 = sql + " and localdatestr =:localdatestr";
        params1.put("localdatestr", "20181231");
        Page<Map<String, Object>> list2 = super.pageMapListBySql(tempSql2, pageNumber, pageSize, params1);
        log.info("{}", JSON.toJSONString(list2));

        List<Object> params2 = Guava.newArrayList();
        String tempSql3 = sql + " and localdatestr =?0";
        params2.add("20181230");
        Page<Map<String, Object>> list3 = super.pageMapListBySql(tempSql3, pageNumber, pageSize, params2);
        log.info("{}", JSON.toJSONString(list3));
        return list3;
    }

    @Override
    public Page<Holiday2> pageBeanBySql(Integer pageNumber, Integer pageSize) {
        String sql = "select * from holiday where 1=1 ";
        String tempSql1 = sql + " order by localdate desc ";
        Page<Holiday2> list1 = super.pageEntityClassBySql(tempSql1, Holiday2.class, pageNumber, pageSize);
        log.info("{}", JSON.toJSONString(list1));

        Map<String, Object> params1 = Guava.newHashMap();
        String tempSql2 = sql + " and localdatestr =:localdatestr";
        params1.put("localdatestr", "20181231");
        Page<Holiday2> list2 = super.pageEntityClassBySql(tempSql2, Holiday2.class, pageNumber, pageSize, params1);
        log.info("{}", JSON.toJSONString(list2));

        List<Object> params2 = Guava.newArrayList();
        String tempSql3 = sql + " and localdatestr =?0";
        params2.add("20181230");
        Page<Holiday2> list3 = super.pageEntityClassBySql(tempSql3, Holiday2.class, pageNumber, pageSize, params2);
        log.info("{}", JSON.toJSONString(list3));
        return list3;
    }
}
