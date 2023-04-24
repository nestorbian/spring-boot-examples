package com.nestor.hibernatevalidation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象日志类
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2022/5/12
 */
public interface AbstractLogService {

    public void log(String str);

    // public void log(String str) {
    //     Logger logger = LoggerFactory.getLogger(AbstractLogService.class);
    //     System.err.println("AbstractLogService log info:" + str);
    //     logger.debug("AbstractLogService debug log info:" + str);
    // }

}
