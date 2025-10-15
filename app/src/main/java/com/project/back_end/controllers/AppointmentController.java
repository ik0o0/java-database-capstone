package com.project.back_end.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.back_end.models.Appointment;
import com.project.back_end.services.AppointmentService;
import com.project.back_end.services.Service;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private Service service;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/{date}/{patientName}/{token}")
    public ResponseEntity<Map<String, Object>> getAppointments(
        @RequestParam LocalDate date,
        @RequestParam String patientName,
        @RequestParam String token
    ) {
        Map<String, Object> response = new HashMap<>();

        ResponseEntity<Map<String, String>> validateToken = this.service.validateToken(token, "patient");
        if (validateToken.getStatusCode() != HttpStatusCode.valueOf(200)) {
            response.put("message", "The token is not valid.");
            return ResponseEntity.badRequest().body(response);
        }

        return this.appointmentService.getAppointment(patientName, date, token);
    }

    @PostMapping("/{token}")
    public ResponseEntity<Map<String, String>> bookAppointment(
        @RequestParam String token,
        @RequestBody Appointment appointment
    ) {
        Map<String, String> response = new HashMap<>();

        ResponseEntity<Map<String, String>> validateToken = this.service.validateToken(token, "patient");
        if (validateToken.getStatusCode() != HttpStatusCode.valueOf(200)) {
            return validateToken;
        }

        if (this.service.validateAppointment(appointment) < 1) {
            response.put("message", "The appointment is not valid.");
            return ResponseEntity.badRequest().body(response);
        }

        if (this.appointmentService.bookAppointment(appointment) < 1) {
            response.put("message", "The appointment is not save.");
            return ResponseEntity.badRequest().body(response);
        }

        response.put("message", "Appointment created.");
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{token}")
    public ResponseEntity<Map<String, String>> updateAppointment(
        @PathVariable String token,
        @RequestBody Appointment appointment
    ) {
        ResponseEntity<Map<String, String>> validateToken = this.service.validateToken(token, "patient");
        if (validateToken.getStatusCode() != HttpStatusCode.valueOf(200)) {
            return validateToken;
        }
        return this.appointmentService.updateAppointment(appointment);
    }

    @DeleteMapping("/{id}/{token}")
    public ResponseEntity<Map<String, String>> cancelAppointment(
        @PathVariable Long id,
        @PathVariable String token
    ) {
        ResponseEntity<Map<String, String>> validateToken = this.service.validateToken(token, "patient");
        if (validateToken.getStatusCode() != HttpStatusCode.valueOf(200)) {
            return validateToken;
        }

        return this.appointmentService.cancelAppointment(id, token);
    }

// 1. Set Up the Controller Class:
//    - Annotate the class with `@RestController` to define it as a REST API controller.
//    - Use `@RequestMapping("/appointments")` to set a base path for all appointment-related endpoints.
//    - This centralizes all routes that deal with booking, updating, retrieving, and canceling appointments.


// 2. Autowire Dependencies:
//    - Inject `AppointmentService` for handling the business logic specific to appointments.
//    - Inject the general `Service` class, which provides shared functionality like token validation and appointment checks.


// 3. Define the `getAppointments` Method:
//    - Handles HTTP GET requests to fetch appointments based on date and patient name.
//    - Takes the appointment date, patient name, and token as path variables.
//    - First validates the token for role `"doctor"` using the `Service`.
//    - If the token is valid, returns appointments for the given patient on the specified date.
//    - If the token is invalid or expired, responds with the appropriate message and status code.


// 4. Define the `bookAppointment` Method:
//    - Handles HTTP POST requests to create a new appointment.
//    - Accepts a validated `Appointment` object in the request body and a token as a path variable.
//    - Validates the token for the `"patient"` role.
//    - Uses service logic to validate the appointment data (e.g., check for doctor availability and time conflicts).
//    - Returns success if booked, or appropriate error messages if the doctor ID is invalid or the slot is already taken.


// 5. Define the `updateAppointment` Method:
//    - Handles HTTP PUT requests to modify an existing appointment.
//    - Accepts a validated `Appointment` object and a token as input.
//    - Validates the token for `"patient"` role.
//    - Delegates the update logic to the `AppointmentService`.
//    - Returns an appropriate success or failure response based on the update result.


// 6. Define the `cancelAppointment` Method:
//    - Handles HTTP DELETE requests to cancel a specific appointment.
//    - Accepts the appointment ID and a token as path variables.
//    - Validates the token for `"patient"` role to ensure the user is authorized to cancel the appointment.
//    - Calls `AppointmentService` to handle the cancellation process and returns the result.


}
