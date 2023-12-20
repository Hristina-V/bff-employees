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
   b. Create a database called `bff-employee` 
3. Create the following tables
   
   * EmployeePairs table - stores the pairs of employees and period of time that employees worked together
   ``` sql
   CREATE TABLE EmployeePairs
   (
   );
   ```


## TODO - To be deleted before submission

Questions for interview

- Checked vs. Unchecked exceptions
- What is the difference between @Component, @service, @RestController, etc.
- Equals & hashcode
- final
- static
- Bean
- Method overload
