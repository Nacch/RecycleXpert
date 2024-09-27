package backend.project.serviceimpl;

import backend.project.entities.Donation;
import backend.project.entities.Event;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.DonationRepository;
import backend.project.repositories.EventRepository;
import backend.project.services.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationServiceImpl implements DonationService {
    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private EventRepository eventRepository;

    public Donation createDonation(Long eventId, Donation donation) {
        Optional<Event> eventOpt = eventRepository.findById(eventId);
        if (eventOpt.isPresent()) {
            Event event = eventOpt.get();
            donation.setEvent(event);
            donation.setDonationDate(new java.util.Date());
            return donationRepository.save(donation);
        } else {
            throw new ResourceNotFoundException("Event with id " + eventId + " not found");
        }
    }

    public List<Donation> getAllDonationsForEvent(Long eventId) {
        Optional<Event> eventOpt = eventRepository.findById(eventId);
        if (eventOpt.isPresent()) {
            return eventOpt.get().getDonations();
        } else {
            throw new ResourceNotFoundException("Event with id " + eventId + " not found");
        }
    }

    public Donation getDonationById(Long donationId) {
        return donationRepository.findById(donationId)
                .orElseThrow(() -> new ResourceNotFoundException("Donation with id " + donationId + " not found"));
    }
}
