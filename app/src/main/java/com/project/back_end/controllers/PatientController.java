package com.project.back_end.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.back_end.DTO.Login;
import com.project.back_end.models.Patient;
import com.project.back_end.services.PatientService;
import com.project.back_end.services.Service;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private Service service;

    @GetMapping("/{token}")
    public ResponseEntity<Map<String, Object>> getPatient(@RequestParam String token) {
        Map<String, Object> response = new HashMap<>();

        ResponseEntity<Map<String, String>> validateToken = this.service.validateToken(token, "patient");
        if (validateToken.getStatusCode() != HttpStatusCode.valueOf(200)) {
            response.put("message", validateToken.getBody().get("message"));
            return ResponseEntity.status(401).body(response);
        }

        return this.patientService.getPatientDetails(token);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createPatient(@RequestBody Patient patient) {
        Map<String, Object> response = new HashMap<>();

        if (!this.service.validatePatient(patient)) {
            response.put("message", "Patient with email id or phone no already exist");
            return ResponseEntity.status(404).body(response);
        }

        int createPatientResult = this.patientService.createPatient(patient);
        if (createPatientResult < 1) {
            response.put("message", "Internal server error.");
            return ResponseEntity.status(500).body(response);
        }

        response.put("message", "Signup successfully.");
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Login login) {
        return this.service.validatePatientLogin(login);
    }

    @GetMapping("/{id}/{token}")
    public ResponseEntity<Map<String, Object>> getPatientAppointment(
        @RequestParam Long id,
        @RequestParam String token
    ) {
        Map<String, Object> response = new HashMap<>();

        ResponseEntity<Map<String, String>> validateToken = this.service.validateToken(token, "patient");
        if (validateToken.getStatusCode() != HttpStatusCode.valueOf(200)) {
            response.put("message", validateToken.getBody().get("message"));
            return ResponseEntity.status(401).body(response);
        }

        return this.patientService.getPatientAppointment(id, token);
    }

    @GetMapping("/filter/{condition}/{name}/{token}")
    public ResponseEntity<Map<String, Object>> filterPatientAppointment(
        @RequestParam String condition,
        @RequestParam String name,
        @RequestParam String token
    ) {
        Map<String, Object> response = new HashMap<>();

        ResponseEntity<Map<String, String>> validateToken = this.service.validateToken(token, "patient");
        if (validateToken.getStatusCode() != HttpStatusCode.valueOf(200)) {
            response.put("message", validateToken.getBody().get("message"));
            return ResponseEntity.status(401).body(response);
        }

        return this.service.filterPatient(condition, name, token);
    }

// 1. Set Up the Controller Class:
//    - Annotate the class with `@RestController` to define it as a REST API controller for patient-related operations.
//    - Use `@RequestMapping("/patient")` to prefix all endpoints with `/patient`, grouping all patient functionalities under a common route.


// 2. Autowire Dependencies:
//    - Inject `PatientService` to handle patient-specific logic such as creation, retrieval, and appointments.
//    - Inject the shared `Service` class for tasks like token validation and login authentication.


// 3. Define the `getPatient` Method:
//    - Handles HTTP GET requests to retrieve patient details using a token.
//    - Validates the token for the `"patient"` role using the shared service.
//    - If the token is valid, returns patient information; otherwise, returns an appropriate error message.


// 4. Define the `createPatient` Method:
//    - Handles HTTP POST requests for patient registration.
//    - Accepts a validated `Patient` object in the request body.
//    - First checks if the patient already exists using the shared service.
//    - If validation passes, attempts to create the patient and returns success or error messages based on the outcome.


// 5. Define the `login` Method:
//    - Handles HTTP POST requests for patient login.
//    - Accepts a `Login` DTO containing email/username and password.
//    - Delegates authentication to the `validatePatientLogin` method in the shared service.
//    - Returns a response with a token or an error message depending on login success.


// 6. Define the `getPatientAppointment` Method:
//    - Handles HTTP GET requests to fetch appointment details for a specific patient.
//    - Requires the patient ID, token, and user role as path variables.
//    - Validates the token using the shared service.
//    - If valid, retrieves the patient's appointment data from `PatientService`; otherwise, returns a validation error.


// 7. Define the `filterPatientAppointment` Method:
//    - Handles HTTP GET requests to filter a patient's appointments based on specific conditions.
//    - Accepts filtering parameters: `condition`, `name`, and a token.
//    - Token must be valid for a `"patient"` role.
//    - If valid, delegates filtering logic to the shared service and returns the filtered result.



}


