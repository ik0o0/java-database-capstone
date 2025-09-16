## MySQL Database Design

### Table: patients
- id: INT, Primary Key, Auto Increment
- first_name: VARCHAR(50), Not Null
- last_name: VARCHAR(50), Not Null
- date_of_birth: DATE, Not Null
- email: VARCHAR(100), Unique, Not Null
- phone: VARCHAR(15), Not Null
- address: VARCHAR(255)

### Table: doctors
- id: INT, Prumary Key, Auto Increment
- first_name: VARCHAR(50), Not Null
- last_name: VARCHAR(50), Not Null
- specialization: VARCHAR(100), Not Null
- email: VARCHAR(100), Unique, Not Null
- phone: VARCHAR(15), Not Null
- clinic_location_id: INT, Foreign Key -> clinic_locations(id)

### Table: appointments
- id: INT, Primary Key, Auto Increment
- doctor_id: INT, Foreign Key → doctors(id)
- patient_id: INT, Foreign Key → patients(id)
- appointment_time: DATETIME, Not Null
- status: INT (0 = Scheduled, 1 = Completed, 2 = Cancelled)
- notes: TEXT

### Table: admin
- id: INT, Primary Key, Auto Increment
- username: VARCHAR(50), Unique, Not Null
- password_hash: VARCHAR(255), Not Null
- email: VARCHAR(100), Unique, Not Null

### Table: clinic_locations
- id: INT, Primary Key, Auto Increment
- name: VARCHAR(100), Not Null
- address: VARCHAR(255), Not Null
- phone: VARCHAR(15)

## MongoDB Collection Design

### Collection: prescription

```json
{
  "_id": "ObjectId('650f987654abc')",
  "appointmentId": 102,
  "patientId": 55,
  "doctorId": 12,
  "medications": [
    {
      "name": "Amoxicillin",
      "dosage": "500mg",
      "frequency": "3 times a day",
      "duration": "7 days",
      "instructions": "Take after meals",
      "refillAllowed": true
    },
    {
      "name": "Paracetamol",
      "dosage": "500mg",
      "frequency": "as needed",
      "duration": "5 days",
      "instructions": "Max 4 tablets per day",
      "refillAllowed": false
    }
  ]
  "doctorNotes": [
    {
      "timestamp": "2025-09-16T09:30:00Z",
      "note": "Monitor patient for allergic reactions."
    }
  ],
  "tags": ["urgent", "infection"],
  "status": "active",
  "metadata": {
    "createdAt": "2025-09-16T09:25:00Z",
    "updatedAt": "2025-09-16T09:35:00Z",
    "createdBy": "Dr. Alice Johnson"
  }
}
```
