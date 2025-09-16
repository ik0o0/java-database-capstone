This Spring Boot application uses both MVC and REST controllers. Thymeleaf templates are used for the Admin and Doctor dashboards, while REST APIs serve all other modules. The application interacts with two databases—MySQL (for patient, doctor, appointment, and admin data) and MongoDB (for prescriptions). All controllers route requests through a common service layer, which in turn delegates to the appropriate repositories. MySQL uses JPA entities while MongoDB uses document models.

1. Users interact with AdminDashboard / DoctorDashboard (via UI) or Appointments, PatientDashboard, and PatientRecord (via JSON API).
2. Dashboards use Thymeleaf Controllers. REST modules use REST Controllers.
3. Both Thymeleaf Controllers and REST Controllers call into the Service Layer for business logic.
4. The Service Layer uses MySQL Repositories to access relational data.
5. MySQL Repositories interact with the MySQL Database, which stores entities such as patients, doctors, appointments, and admins.
6. The MySQL Database maps its tables to MySQL Models, represented as JPA entities (Patient, Doctor, Appointment, Admin).
7. The Service Layer also uses the MongoDB Repository to query the MongoDB Database, which stores document-based models (e.g., Prescription). These are represented as MongoDB Models (documents).
