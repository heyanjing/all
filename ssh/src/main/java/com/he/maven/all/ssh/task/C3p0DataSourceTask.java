package com.he.maven.all.ssh.task;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by heyanjing on 2018/2/10 12:06.
 */
@Component
@Slf4j
public class C3p0DataSourceTask {
    @Autowired
    ComboPooledDataSource pooledDatasource;

    @Scheduled(cron = "0/10 * * * * ? ")
    public void ScheduledTask3() throws Exception {
        String msg = "";
        msg += "============================== 数据源状态报告 ==============================\n";
        msg += "CurrentTime：" + DateFormat.getInstance().format(new Date()) + "\n";
        msg += "TotalConnections：" + this.pooledDatasource.getNumConnectionsAllUsers() + "\n";
        msg += "BusyConnections：" + this.pooledDatasource.getNumBusyConnectionsAllUsers() + "\n";
        msg += "IdleConnections：" + this.pooledDatasource.getNumIdleConnectionsAllUsers() + "\n";
        msg += "FailedCheckins：" + this.pooledDatasource.getNumFailedCheckinsDefaultUser() + "\n";
        msg += "FailedCheckouts：" + this.pooledDatasource.getNumFailedCheckoutsDefaultUser() + "\n";
        msg += "FailedIdleTests：" + this.pooledDatasource.getNumFailedIdleTestsDefaultUser() + "\n";
        msg += "UnclosedOrphanedConnections：" + this.pooledDatasource.getNumUnclosedOrphanedConnectionsAllUsers() + "\n";
        msg += "StatementCacheNumStatements：" + this.pooledDatasource.getStatementCacheNumStatementsAllUsers() + "\n";
        msg += "StatementCacheNumCheckedOutStatements：" + this.pooledDatasource.getStatementCacheNumCheckedOutStatementsAllUsers() + "\n";
        msg += "StatementCacheNumConnectionsWithCachedStatements：" + this.pooledDatasource.getStatementCacheNumConnectionsWithCachedStatementsAllUsers() + "\n";
        msg += "============================== 数据源状态报告 ==============================";
        log.warn(msg);
    }
}
