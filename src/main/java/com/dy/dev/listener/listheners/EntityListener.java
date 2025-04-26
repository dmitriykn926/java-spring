package com.dy.dev.listener.listheners;

import com.dy.dev.listener.events.EntityEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EntityListener {

    @EventListener(condition = "#root.args[0].accessType.name() == 'READ'")
    public void acceptEvent(EntityEvent event) {
        log.info("EntityListener accepted: " + event);
    }
}
