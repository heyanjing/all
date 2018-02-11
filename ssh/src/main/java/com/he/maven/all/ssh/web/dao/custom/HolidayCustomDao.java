package com.he.maven.all.ssh.web.dao.custom;

import com.he.maven.all.ssh.base.bean.Holiday2;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by heyanjing on 2017/12/19 10:31.
 */
public interface HolidayCustomDao<Holiday> {
    List<Holiday> findBySql();

    List<Holiday> findByJpql();


    Page<Holiday> pageBySql(Integer pageNumber, Integer pageSize);

    Page<Holiday> pageByJpql(Integer pageNumber, Integer pageSize);


    List<Map<String, Object>> findMapListBySql();

    Page<Map<String, Object>> pageMapListBySql(Integer pageNumber, Integer pageSize);

    List<Holiday2> findBeanBySql();

    Page<Holiday2> pageBeanBySql(Integer pageNumber, Integer pageSize);
}
