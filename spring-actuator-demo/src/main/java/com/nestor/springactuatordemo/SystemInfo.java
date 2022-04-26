package com.nestor.springactuatordemo;

/**
 * 自定义MBean
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2022/4/25
 */
public class SystemInfo implements SystemInfoMBean {
    @Override
    public int cpuCore() {
        return Runtime.getRuntime().availableProcessors();
    }

    @Override
    public long totalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    @Override
    public long maxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    @Override
    public void shutdown() {
        System.exit(0);
    }
}
