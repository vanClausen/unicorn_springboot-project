package de.vanclausen.date4u.core.event;

import java.time.OffsetDateTime;

public record NewPhotoEvent(String name, OffsetDateTime dateTime) {}
