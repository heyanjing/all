package com.he.maven.all.ssh.web.controller;

import com.he.maven.all.ssh.base.bean.Holiday2;
import com.he.maven.all.ssh.entity.Holiday;
import com.he.maven.all.ssh.web.service.HolidayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by heyanjing on 2017/12/19 11:17.
 */
@Controller
@RequestMapping("/holiday")
public class HolidayController {
    private static final Logger log = LoggerFactory.getLogger(HolidayController.class);

    private HolidayService holidayService;

    @Autowired
    public void setHolidayService(HolidayService holidayService) {
        this.holidayService = holidayService;
    }


    /**
     * @http /holiday/getByLocalDateStr
     */
    @RequestMapping("/getByLocalDateStr")
    @ResponseBody
    public Holiday getByLocalDateStr(@RequestParam("d") String d) {
        return this.holidayService.getByLocalDateStr(d);
    }

    /**
     * @http /holiday/findByLocalDateStrs
     */
    @RequestMapping("/findByLocalDateStrs")
    @ResponseBody
    public List<Holiday> findByLocalDateStrs(@RequestParam("d") String d) {
        return this.holidayService.findByLocalDateStrs(d);
    }

    /**
     * @http /holiday/findByMonth
     */
    @RequestMapping("/findByMonth")
    @ResponseBody
    public List<Holiday> findByMonth(@RequestParam("m") String m) {
        return this.holidayService.findByMonth(m);
    }

    /**
     * @http /holiday/findBySql
     */
    @RequestMapping("/findBySql")
    @ResponseBody
    public List<Holiday> findBySql() {
        return this.holidayService.findBySql();
    }

    /**
     * @http /holiday/findBeanBySql
     */
    @RequestMapping("/findBeanBySql")
    @ResponseBody
    public List<Holiday2> findBeanBySql() {
        return this.holidayService.findBeanBySql();
    }

    /**
     * @http /holiday/findMapListBySql
     */
    @RequestMapping("/findMapListBySql")
    @ResponseBody
    public List<Map<String, Object>> findMapListBySql() {
        return this.holidayService.findMapListBySql();
    }

    /**
     * @http /holiday/pageBySql
     */
    @RequestMapping("/pageBySql")
    @ResponseBody
    public Page<Holiday> pageBySql(@RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return this.holidayService.pageBySql(pageNumber, pageSize);
    }

    /**
     * @http /holiday/pageMapListBySql
     */
    @RequestMapping("/pageMapListBySql")
    @ResponseBody
    public Page<Map<String, Object>> pageMapListBySql(@RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return this.holidayService.pageMapListBySql(pageNumber, pageSize);
    }

    /**
     * @http /holiday/pageBeanBySql
     */
    @RequestMapping("/pageBeanBySql")
    @ResponseBody
    public Page<Holiday2> pageBeanBySql(@RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return this.holidayService.pageBeanBySql(pageNumber, pageSize);
    }


}
