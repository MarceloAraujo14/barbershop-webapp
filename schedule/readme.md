##BarberShop Schedule API

**Description:** A schedule api for barbershops

**Objective:** Manage appointments to barbershop services

**Architecture**

The project is built as microsservices, with independent databases and each service
communicate syncronous using http REST requests and assyncronous through messaging
system such as kafka.

The system is composed of one web app frontend to interact with the user and
make http calls to the backend api's.

To keep the context well delimited and focus on the business, the api's use concepts of ddd and
clean architecture, each service will be composed of three layers:
- core: the domain of the application, with the entities and business rules
- api: the entrypoint that expose endpoints to access the system functionalities
- interface adapters: connect the core to external dependencies such as drivers and frameworks

**Entities:**
- **Appointment**
  - id
  - date
  - startAt
  - duration
  - customerId
  - barberId
  - serviceIds
  - status


- **Diary**
  - date
  - busyTime

**API features**:
- Schedule a barbershop services appointment
- Can Update date, hour, services and barber on appointment
- Cancel a appointment

**Business Rules:**
- Only one appointment per date and time.
- Make only the current and next week available at time.
- Not allow scheduling on pass days and hours.

**Technologies:**
- Java 11
- Gradle
- Spring Web
- Spring Data JPA
- Spring Validation
- FlyWay Migration
- Lombok
- Mockito
- JUnit
- PostgreSQL
- Docker