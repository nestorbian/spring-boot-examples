package com.nestor.mybatisdemo.config;

import org.springframework.boot.autoconfigure.transaction.PlatformTransactionManagerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

/**
 * 事务管理器配置类
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2022/5/27
 */
@Configuration
public class TransactionManagerConfig {

    /**
     * 配置只有存在事务的时候才初始化ThreadLocal<Set> sychronizations，防止事务传播设置NOT_SUPPORT时以非事务运行
     * 导致mybatis update操作无法清除二级缓存的问题
     *
     * @param
     * @return org.springframework.boot.autoconfigure.transaction.PlatformTransactionManagerCustomizer<org.springframework.jdbc.datasource.DataSourceTransactionManager>
     * @date : 2022/5/27 19:03
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @Bean
    public PlatformTransactionManagerCustomizer<DataSourceTransactionManager> dataSourceTransactionManagerCustomizer() {
        return transactionManager -> transactionManager.setTransactionSynchronization(
                AbstractPlatformTransactionManager.SYNCHRONIZATION_ON_ACTUAL_TRANSACTION);
    }

}
