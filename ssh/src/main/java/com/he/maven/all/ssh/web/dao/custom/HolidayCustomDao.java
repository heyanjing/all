package com.he.maven.all.ssh.web.dao.custom;

import org.springframework.data.domain.Page;

/**
 * Created by heyanjing on 2017/12/19 10:31.
 */
public interface HolidayCustomDao<Holiday> {
    Page<Holiday> page(Integer pageNumber, Integer pageSize);
}
