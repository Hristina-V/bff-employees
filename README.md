# BFF Employees

## Overview

BFF Employees is an application that is able to present the pair of employees that worked together for the most time.
Its intention is to be used as a final project for Sirma Academy.

## APIs

### Console application
The project  provides a console application which is run through the "BffEmployeesCli".
It reads the CSV file [employees_assignments.csv](./data/csv/employees_assignments.csv) and outputs the pair of employees with most collaborative work. 
It's a single process that's doing the magic.

### REST API

#### Main Flow
The project exposes REST API that consists of three end points which need to be invoked sequentially.

Endpoints:

1. Upload a file  `/employees-assignments/upload` - this endpoint executes the following steps:

  * reads a CSV file and parses it to a list of assignments;
  * maps the assignments to assignment entities;
  * saves the list of entities to the database;

3. Aggregate the data `/employees-collaborations/aggregate` - the purpose of this endpoint is to find all possible collaborations by employee pair based on the uploaded data and save it to the database
4. Get top N collaborations `/employees-collaborations/top/{n}` - this endpoint returns the top number of collaborations by total collaboration days, as specified by the user

#### Additional operations
The project also exposes the following additional end points:

* Find all assignments `/employees-assignments` - returns all assignments
* Find all assignments by Employee ID `/employees-assignments/employees/{employeeId}` - returns all assignments for a specific employee
* Find all assignments by Project ID `/employees-assignments/projects/{projectId}` - returns all assignments for a specific project


## Local application setup

1. Install Java 17
2. Configure DB 
   a. Install PostgreSQL locally or run it in through a docker container using the following command: 

    ``` shell 
    docker run --name local-postgres -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -d postgres
    ```
   b. Create a database called `bff-employees` 
3. Create the following tables
   
   * Assignments table - stores the pairs of employees and period of time that employees worked together
   ``` sql
   CREATE TABLE assignments (
    id SERIAL PRIMARY KEY,
    employee_id BIGINT,
    project_id BIGINT,
    start_date DATE,
    end_date DATE
   );
   ```
   * Collaborations table - stores pairs of employees and the total collaboration days between them.
   ``` sql
   CREATE TABLE collaborations (
    id SERIAL PRIMARY KEY,
    smaller_employee_id BIGINT,
    higher_employee_id BIGINT
   );
   ```
   * Collaborative_work table - stores the separate collaborations that have occurred on each project, including the start date, end date and the pair of employees (foreign key).
   ``` sql
   CREATE TABLE collaborative_work (
    id SERIAL PRIMARY KEY,
    project_id BIGINT,
    start_date DATE,
    end_date DATE,
    collaboration_id BIGINT,
    constraint fk_collaboration foreign key (collaboration_id) references collaborations(id)
   );
   ```
   * Create sequence for the ids of collaborations table
   ```
   CREATE SEQUENCE collaborations_seq START 1;
   ```
   * Create sequence for the ids of collaborations table
   ```
   CREATE SEQUENCE collaborative_work_seq START 1;
   ```
4. Run `BffEmployeesApplication`
5. Install & Open Postman
6. Import collection from [APP Postman directory](./postman/BFF%20Employees.postman_collection.json)
7. Enjoy this app

## Possible future enhancements

* Introduce a unique id that will allow the application to maintain multiple subsequent files.
* Increase test coverage.
* Introduce the possibility for showing the project with most collaborations.
