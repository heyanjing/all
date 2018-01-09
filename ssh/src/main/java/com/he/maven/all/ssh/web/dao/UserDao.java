package com.he.maven.all.ssh.web.dao;

import com.he.maven.all.ssh.base.repo.BaseRepo;
import com.he.maven.all.ssh.entity.User;
import com.he.maven.all.ssh.web.dao.custom.UserCustomDao;

/**
 * Created by heyanjing on 2017/12/19 10:29.
 */
public interface UserDao extends BaseRepo<User,String>,UserCustomDao<User> {

    User getById(String id);

}
