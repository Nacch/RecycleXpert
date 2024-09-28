package backend.project.services;

import backend.project.entities.RegisterEvent;

public interface RegisterEventService {
    public RegisterEvent registerVolunteerToEvent(Long eventId, Long volunteerId);
    public void unregisterVolunteerFromEvent(Long eventId, Long volunteerId);
}
