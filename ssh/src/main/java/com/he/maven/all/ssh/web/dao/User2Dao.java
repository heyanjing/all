package com.he.maven.all.ssh.web.dao;

import com.he.maven.all.ssh.base.repo.BaseRepo;
import com.he.maven.all.ssh.entity.User2;
import com.he.maven.all.ssh.web.dao.custom.UserCustomDao;

/**
 * Created by heyanjing on 2017/12/19 10:29.
 */
public interface User2Dao extends BaseRepo<User2,Long>,UserCustomDao<User2> {
}
