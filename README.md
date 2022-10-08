###BarberShop WebApp

**Description:** 

A web app that allow clients to schedule barbershop appointments based on the 
available time, anywhere.

**Objective:**
- Save customers time and effort, preventing spend hours waiting his time to cut hair or shave.
- Permit the barbers organize their day based on the previous schedules.
- Allow the barbershop owner controll the business based on the data.
- Be an easy-to-use, mobile first app for customers and owners.

**Architecture**

The project is built as microsservices, with independent databases and each service 
communicate syncronous using http REST requests and assyncronous through messaging 
system such as kafka. 

The system is composed of one web app interface to interact with the frontend user and 
with the backend api's.

To keep the context well delimited and focus on the business, the api's use concepts of ddd and
clean architecture, each service will be composed of three layers: 
- core: the domain of the application, with the entities and business rules
- api: the entrypoint that expose endpoints to access the system functionalities
- interface adapters: connect the core to external dependencies such as drivers and frameworks

**Projects:**
- barbershop-schedule
- barbershop-services
- barbershop-customer
- barbershop-web-app

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

