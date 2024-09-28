package backend.project.controllers;

import backend.project.entities.Donation;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.serviceimpl.DonationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DonationController {
    @Autowired
    private DonationServiceImpl donationService;

    // Crear una donación para un evento específico
    @PostMapping("/event/{eventId}")
    public ResponseEntity<Donation> createDonation(@PathVariable Long eventId, @RequestBody Donation donation) {
        try {
            Donation newDonation = donationService.createDonation(eventId, donation);
            return new ResponseEntity<>(newDonation, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todas las donaciones para un evento específico
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Donation>> getAllDonationsForEvent(@PathVariable Long eventId) {
        try {
            List<Donation> donations = donationService.getAllDonationsForEvent(eventId);
            return new ResponseEntity<>(donations, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener una donación por su ID
    @GetMapping("/{donationId}")
    public ResponseEntity<Donation> getDonationById(@PathVariable Long donationId) {
        try {
            Donation donation = donationService.getDonationById(donationId);
            return new ResponseEntity<>(donation, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
