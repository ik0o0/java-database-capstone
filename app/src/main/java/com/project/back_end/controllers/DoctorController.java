package com.project.back_end.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.back_end.DTO.Login;
import com.project.back_end.models.Doctor;
import com.project.back_end.services.DoctorService;
import com.project.back_end.services.Service;

@RestController
@RequestMapping("${api.path}" + "doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private Service service;

    @GetMapping("/availability/{user}/{doctorId}/{date}/{token}")
    public ResponseEntity<Map<String, Object>> getDoctorAvailability(
        @RequestParam String user,
        @RequestParam Long doctorId,
        @RequestParam LocalDate date,
        @RequestParam String token
    ) {
        Map<String, Object> response = new HashMap<>();

        ResponseEntity<Map<String, String>> validateToken = this.service.validateToken(token, user);
        if (validateToken.getStatusCode() != HttpStatusCode.valueOf(200)) {
            response.put("message", validateToken.getBody().get("message"));
            return ResponseEntity.status(401).body(response);
        }
        
        List<String> availabilities = this.doctorService.getDoctorAvailability(doctorId, date);
        if (availabilities.size() < 1) {
            response.put("message", "There is no availibities.");
            return ResponseEntity.status(404).body(response);
        }

        response.put("availabilities", availabilities);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getDoctor() {
        Map<String, Object> response = new HashMap<>();
        
        List<Doctor> doctors = this.doctorService.getDoctors();
        if (doctors.size() < 1) {
            response.put("message", "Doctors not found.");
            return ResponseEntity.status(404).body(response);
        }

        response.put("doctors", doctors);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/{token}")
    public ResponseEntity<Map<String, Object>> addNewDoctor(
        @RequestParam String token,
        @RequestBody Doctor doctor
    ) {
        Map<String, Object> response = new HashMap<>();

        ResponseEntity<Map<String, String>> validateToken = this.service.validateToken(token, "doctor");
        if (validateToken.getStatusCode() != HttpStatusCode.valueOf(200)) {
            response.put("message", validateToken.getBody().get("message"));
            return ResponseEntity.status(401).body(response);
        }

        int doctorResult = this.doctorService.saveDoctor(doctor);
        if (doctorResult == -1) {
            response.put("message", "Doctor already exists.");
            return ResponseEntity.badRequest().body(response);
        }

        if (doctorResult == 0) {
            response.put("message", "Some internal error occurred.");
            return ResponseEntity.status(500).body(response);
        }

        response.put("message", "Doctor added to the db.");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> doctorLogin(@RequestBody Login login) {
        return this.doctorService.validateDoctor(login);
    }

    @PutMapping("/{token}")
    public ResponseEntity<Map<String, Object>> updateDoctorDetails(
        @RequestParam String token,
        @RequestBody Doctor doctor
    ) {
        Map<String, Object> response = new HashMap<>();

        ResponseEntity<Map<String, String>> validateToken = this.service.validateToken(token, "doctor");
        if (validateToken.getStatusCode() != HttpStatusCode.valueOf(200)) {
            response.put("message", validateToken.getBody().get("message"));
            return ResponseEntity.status(401).body(response);
        }

        int updateDoctorResult = this.doctorService.updateDoctor(doctor);
        if (updateDoctorResult == -1) {
            response.put("message", "Doctor not found.");
            return ResponseEntity.status(404).body(response);
        }

        if (updateDoctorResult == 0) {
            response.put("message", "Some internal error occurred.");
            return ResponseEntity.status(500).body(response);
        }

        response.put("message", "Doctor updated.");
        return ResponseEntity.status(201).body(response);
    }

    @DeleteMapping("/{id}/{token}")
    public ResponseEntity<Map<String, Object>> deleteDoctor(
        @RequestParam Long id,
        @RequestParam String token
    ) {
        Map<String, Object> response = new HashMap<>();

        ResponseEntity<Map<String, String>> validateToken = this.service.validateToken(token, "doctor");
        if (validateToken.getStatusCode() != HttpStatusCode.valueOf(200)) {
            response.put("message", validateToken.getBody().get("message"));
            return ResponseEntity.status(401).body(response);
        }

        int deleteDoctorResult = this.doctorService.deleteDoctor(id);
        if (deleteDoctorResult == -1) {
            response.put("message", "Doctor not found with id.");
            return ResponseEntity.status(404).body(response);
        }

        if (deleteDoctorResult == 0) {
            response.put("message", "Some internal error occurred.");
            return ResponseEntity.status(500).body(response);
        }

        response.put("message", "Doctor deleted successfully.");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/filter/{name}/{time}/{specialty}")
    public ResponseEntity<Map<String, Object>> filterDoctor(
        @RequestParam String name,
        @RequestParam String time,
        @RequestParam String specialty
    ) {
       return ResponseEntity.ok().body(this.service.filterDoctor(name, specialty, time));
    }

// 1. Set Up the Controller Class:
//    - Annotate the class with `@RestController` to define it as a REST controller that serves JSON responses.
//    - Use `@RequestMapping("${api.path}doctor")` to prefix all endpoints with a configurable API path followed by "doctor".
//    - This class manages doctor-related functionalities such as registration, login, updates, and availability.


// 2. Autowire Dependencies:
//    - Inject `DoctorService` for handling the core logic related to doctors (e.g., CRUD operations, authentication).
//    - Inject the shared `Service` class for general-purpose features like token validation and filtering.


// 3. Define the `getDoctorAvailability` Method:
//    - Handles HTTP GET requests to check a specific doctor’s availability on a given date.
//    - Requires `user` type, `doctorId`, `date`, and `token` as path variables.
//    - First validates the token against the user type.
//    - If the token is invalid, returns an error response; otherwise, returns the availability status for the doctor.


// 4. Define the `getDoctor` Method:
//    - Handles HTTP GET requests to retrieve a list of all doctors.
//    - Returns the list within a response map under the key `"doctors"` with HTTP 200 OK status.


// 5. Define the `saveDoctor` Method:
//    - Handles HTTP POST requests to register a new doctor.
//    - Accepts a validated `Doctor` object in the request body and a token for authorization.
//    - Validates the token for the `"admin"` role before proceeding.
//    - If the doctor already exists, returns a conflict response; otherwise, adds the doctor and returns a success message.


// 6. Define the `doctorLogin` Method:
//    - Handles HTTP POST requests for doctor login.
//    - Accepts a validated `Login` DTO containing credentials.
//    - Delegates authentication to the `DoctorService` and returns login status and token information.


// 7. Define the `updateDoctor` Method:
//    - Handles HTTP PUT requests to update an existing doctor's information.
//    - Accepts a validated `Doctor` object and a token for authorization.
//    - Token must belong to an `"admin"`.
//    - If the doctor exists, updates the record and returns success; otherwise, returns not found or error messages.


// 8. Define the `deleteDoctor` Method:
//    - Handles HTTP DELETE requests to remove a doctor by ID.
//    - Requires both doctor ID and an admin token as path variables.
//    - If the doctor exists, deletes the record and returns a success message; otherwise, responds with a not found or error message.


// 9. Define the `filter` Method:
//    - Handles HTTP GET requests to filter doctors based on name, time, and specialty.
//    - Accepts `name`, `time`, and `speciality` as path variables.
//    - Calls the shared `Service` to perform filtering logic and returns matching doctors in the response.


}
