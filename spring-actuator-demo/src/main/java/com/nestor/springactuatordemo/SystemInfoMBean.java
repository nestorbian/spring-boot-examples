package com.nestor.springactuatordemo;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2022/4/25
 */
public interface SystemInfoMBean {
    int cpuCore();
    long totalMemory();

    long maxMemory();

    void shutdown();
}