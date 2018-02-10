package com.he.maven.all.ssh.web.dao.impl;

import com.he.maven.all.ssh.base.hibernate.BaseHibernateDao;
import com.he.maven.all.ssh.entity.Holiday;
import com.he.maven.all.ssh.web.dao.custom.HolidayCustomDao;
import org.springframework.data.domain.Page;

/**
 * Created by heyanjing on 2017/12/19 10:35.
 */
public class HolidayDaoImpl extends BaseHibernateDao<Holiday> implements HolidayCustomDao<Holiday> {
    @Override
    public Page<Holiday> page(Integer pageNumber, Integer pageSize) {
        String sql="SELECT h.* FROM holiday h ";
        return super.pageBySql(sql,pageNumber,pageSize);
    }

}
