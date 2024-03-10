package com.nestor.hibernatevalidation.listener;

import org.springframework.context.ApplicationEvent;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2023/8/27
 */
public class CustomApplicationEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public CustomApplicationEvent(Object source) {
        super(source);
    }
}
