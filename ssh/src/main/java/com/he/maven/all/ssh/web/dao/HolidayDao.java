package com.he.maven.all.ssh.web.dao;

import com.he.maven.all.ssh.base.repo.BaseRepo;
import com.he.maven.all.ssh.entity.Holiday;
import com.he.maven.all.ssh.web.dao.custom.HolidayCustomDao;

import java.util.List;

/**
 * Created by heyanjing on 2017/12/19 10:29.
 */
public interface HolidayDao extends BaseRepo<Holiday,String>,HolidayCustomDao<Holiday> {
    Holiday getByLocalDateStr(String localDateStr);
    List<Holiday> findByLocalDateStrLikeAndTypeNotAndHolidayTypeIsNotNullOrderByLocalDateAsc(String localDateStr, Integer type);
}
