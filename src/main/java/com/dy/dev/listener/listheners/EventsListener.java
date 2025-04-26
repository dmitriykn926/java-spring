package com.dy.dev.listener.listheners;

import com.dy.dev.listener.events.EntityEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventsListener {

    @EventListener
    public void onEvent(EntityEvent event) {
        log.info("Entity Event: " + event);
    }
}
