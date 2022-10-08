##BarberShop Schedule API

**Description:** A schedule api to barbershops

**Objective:** Manage appointments to barbershop services

**Project:** 
- api : expose api functionalities through endpoint externally
- core : contains bussiness rules
- adapters : link to external services use in the service api

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


**Business Rules:**
- Schedule a barbershop services appointment 
- Update date, hour, services and barber on appointment
- Cancel a appointment
- Only one appointment per date, time and barber
- Make the current and next week available
- Make past days and hours unavailable

**Technologies:**
- Java 11
- Gradle
- PostgreSQL