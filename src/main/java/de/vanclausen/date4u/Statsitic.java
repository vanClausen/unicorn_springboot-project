package de.vanclausen.date4u;

import de.vanclausen.date4u.event.NewPhotoEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class Statsitic {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @EventListener
    public void onNewPhotoEvent(NewPhotoEvent event) {
        log.info("New Photo: {}", event);
    }
}
