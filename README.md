## Instructor Training Schedule Management System

### Description
There are 5 instructors in a training organization, whose monthly schedule needs to be visible and
changeable via GUI.
First name, last name and birthday of each instructor shall be visible.
The monthly schedule of each instructor can theoretically contain an unlimited number of appointments
(events). Every event has a start and end date, an event type and a description.
To start with, every instructor has the following events:

● 1 week off
● 2 seminars at 1 week each
● 1 project week

The events are free to choose but should be different for every instructor. Events may overlap.
Steps for the development task:
1. Please create a data / class model for the data needed for this task. Initialize the data with sample
   data for instructors and events.
2. Write a function that calculates the overall duration (days) of events of each instructor.
3. Develop a user interface of your own choice where a user can pick an instructor and see all
   appointments of this instructor. Furthermore, the overall duration of all events should be visible.
4. Please add the option to delete events.
5. Please add the option to insert new events.
6. Please add the option to edit events

### Implementation
The application is implemented following the Domain Driven Design 
as a showcase how a project can be structured in a way to be easily 
maintainable and extendable with layer isolation.
The requirements of the task could have been covered with a simpler implementation, 
but the project would not have been modified easily if the requirements change.

The project consists of the following layers:
* controller - provides an API for all functionality provided by the application
* domain - contains the core domain logic
* infrastructure - contains interfaces to the outer world. If the data source is changed, it will not affect the other layers.

### Setting up the database
Go to the following directory:

```
/{projectDirectory}/docker
```
Then execute the following command:
```
docker-compose up
```

### Running the application with Maven
You can start the application by running:
```
./mvnw spring-boot:run
```
### Running the tests
Have in mind that it is required to have a configured and running database for the load tests. You can run the tests with the following command:
```
./mvnw clean verify
```
### Improvements
The following improvements can be done:
* more tests - unit, integration and load testing
