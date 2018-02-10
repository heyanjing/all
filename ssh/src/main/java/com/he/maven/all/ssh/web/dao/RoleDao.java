package com.he.maven.all.ssh.web.dao;

import com.he.maven.all.ssh.base.repo.BaseRepo;
import com.he.maven.all.ssh.entity.Role;
import com.he.maven.all.ssh.web.dao.custom.RoleCustomDao;

/**
 * Created by heyanjing on 2017/12/19 10:29.
 */
public interface RoleDao extends BaseRepo<Role,String>,RoleCustomDao<Role> {
}
