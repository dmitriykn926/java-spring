package com.dy.dev.listener.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class EntityEvent extends ApplicationEvent {

    @Getter
    private final AccessType accessType;
    private final Object entity;

    public EntityEvent(Object source, AccessType accessType) {
        super(source);
        this.accessType = accessType;
        this.entity = source;
    }
}
