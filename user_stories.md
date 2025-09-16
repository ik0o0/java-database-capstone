# User stories

## Admin User Stories

**Title:**
_As an afmin, I want to log into the portal with my username and password, so that i can manage the platform securely._

**Acceptance Criteria:**
1. Admin can enter a valid username and password.
2. Admin is granted access if credentials are correct.
3. Admin sees an error message if credentials are invalid.

**Priority:** High
**Story Points:** 3
**Notes:**
- Think abt security practices

**Title:**
_As an admin, I want to log out of the portal, so that i can protect system access._

**Acceptance Criteria:**
1. Admin can click a logout button.
2. Admin session is terminated immediately.
3. Admin is redirected to the login screen after logout.

**Priority:** High
**Story Points:** 2
**Notes:**
- Ensure session tokens are invalidated on logout.

**Title:**
_As an admin, I want to add doctors to the portal, so that i can register new practitioners._

**Acceptance Criteria:**
1. Admin can input doctor’s personal and professional information.
2. Doctor’s profile is saved and visible in the portal.
3. Confirmation message is displayed after successful creation.

**Priority:** High
**Story Points:** 5
**Notes:**
- Validate mandatory fields (name, specialty, contact).

**Title:**
_As an admin, I want to delete a doctor’s profile from the portal, so that i can remove inactive or incorrect records._

**Acceptance Criteria:**
1. Admin can select a doctor profile to delete.
2. A confirmation prompt appears before deletion.
3. Doctor’s profile is permanently removed from the database.

**Priority:** Medium
**Story Points:** 3
**Notes:**
- Ensure deletion does not affect existing appointment records.

**Title:**
_As an admin, I want to run a stored procedure in MySQL CLI to get the number of appointments per month, so that i can track usage statistics._

**Acceptance Criteria:**
1. Admin can access the MySQL CLI with the necessary permissions.
2. Admin can execute a stored procedure that returns the count of appointments per month.
3. Results are displayed in a readable and structured table format.

**Priority:** Medium
**Story Points:** 8
**Notes:**
- Stored procedure should handle large datasets efficiently.
- Include error handling for failed execution or permission issues.
- Consider adding a date range filter for flexibility.

## Patient User Stories

**Title:**
_As a patient, I want to view a list of doctors without logging in, so that i can explore options before registering._

**Acceptance Criteria:**
1. Patient can access the list of available doctors from the homepage without authentication.
2. Doctor’s basic details (name, specialty, location) are displayed.
3. Patients are prompted to register or log in to book an appointment.

**Priority:** High
**Story Points:** 3
**Notes:**
- Limit the information shown before login (hide contact info until registration).

**Title:**
_As a patient, I want to sign up using my email and password, so that i can book appointments._

**Acceptance Criteria:**
1. Patient can provide an email and password during signup.
2. System validates email format and password strength.
3. Successful signup redirects the patient to their dashboard.

**Priority:** High
**Story Points:** 5
**Notes:**
- Consider email verification step for account activation.

**Title:**
_As a patient, I want to log into the portal, so that i can manage my bookings._

**Acceptance Criteria:**
1. Patient can log in using email and password.
2. Valid credentials grant access to the patient dashboard.
3. Invalid credentials show an error message.

**Priority:** High
**Story Points:** 3
**Notes:**
- Use secure session management practices.

**Title:**
_As a patient, I want to log out of the portal, so that i can secure my account._

**Acceptance Criteria:**
1. Patient can click a logout button.
2. Session ends immediately after logout.
3. Patient is redirected to the homepage or login screen.

**Priority:** High
**Story Points:** 2
**Notes:**
- Ensure session tokens are invalidated after logout.

**Title:**
_As a patient, I want to log in and book an hour-long appointment with a doctor, so that i can consult with them._

**Acceptance Criteria:**
1. Patient can select a doctor and choose an available one-hour slot.
2. System prevents double-booking of the same slot.
3. Confirmation message and appointment details are displayed after booking.

**Priority:** High
**Story Points:** 8
**Notes:**
- Include validation to ensure appointments align with doctors’ availability.

**Title:**
_As a patient, I want to view my upcoming appointments, so that i can prepare accordingly._

**Acceptance Criteria:**
1. Patient can see a list of upcoming appointments on their dashboard.
2. Each appointment shows date, time, and doctor’s details.
3. Appointments are sorted in chronological order.

**Priority:** Medium
**Story Points:** 4
**Notes:**
- Allow filtering past vs upcoming appointments.

## Doctor User Stories

**Title:**
_As a doctor, I want to log into the portal, so that i can manage my appointments._

**Acceptance Criteria:**
1. Doctor can log in using their credentials (email/username + password).
2. Successful login redirects to the doctor dashboard.
3. Invalid credentials display an error message.

**Priority:** High
**Story Points:** 3
**Notes:**
- Use secure authentication practices with encrypted passwords.

**Title:**
_As a doctor, I want to log out of the portal, so that i can protect my data._

**Acceptance Criteria:**
1. Doctor can log out by clicking a logout button.
2. Session ends immediately and access tokens are invalidated.
3. Doctor is redirected to the login page.

**Priority:** High
**Story Points:** 2
**Notes:**
- Ensure no cached data remains accessible after logout.

**Title:**
_As a doctor, I want to view my appointment calendar, so that i can stay organized._

**Acceptance Criteria:**
1. Doctor can see all upcoming appointments in a calendar view.
2. Each appointment shows patient name, date, and time.
3. Calendar allows switching between daily, weekly, and monthly views.

**Priority:** High
**Story Points:** 5
**Notes:**
- Consider mobile-friendly calendar view for quick access.

**Title:**
_As a doctor, I want to mark my unavailability, so that patients only see available slots._

**Acceptance Criteria:**
1. Doctor can select dates/times of unavailability.
2. System prevents patients from booking during unavailable slots.
3. Updated availability is reflected in real-time on the patient portal.

**Priority**: High
**Story Points:** 8
**Notes:**
- Handle recurring unavailability (e.g., every Friday afternoon).

**Title:**
_As a doctor, I want to update my profile with specialization and contact information, so that patients have up-to-date information._

**Acceptance Criteria:**
1. Doctor can edit specialization, phone number, and clinic details.
2. Changes are saved and displayed on the patient portal.
3. A confirmation message appears after a successful update.

**Priority:** Medium
**Story Points:** 4
**Notes:**
- Validate mandatory fields before saving updates.

**Title:**
_As a doctor, I want to view patient details for upcoming appointments, so that i can be prepared._

**Acceptance Criteria:**
1. Doctor can see patient name, age, and reason for consultation.
2. Patient details are accessible only for confirmed appointments.
3. Information is displayed securely and complies with privacy standards.

**Priority:** High
**Story Points:** 6
**Notes:**
- Ensure compliance with healthcare data regulations (e.g., GDPR, HIPAA).
