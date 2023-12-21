# BFF Employees

## Overview

BFF Employees is an application that is able to present the pair of employees that worked together for the most time.
It's intention is to be used as a final project for Sirma Academy.

## APIs

### Console application

### REST API

Endpoints:

* Load a file  

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

## Future enhancements

* Introduce a correlation id that will application to maintain a broad set of different files

## TODO - To be deleted before submission


