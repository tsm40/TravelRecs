# Data Preparation

We collected our data using the [Google Places API](https://developers.google.com/maps/documentation/places/web-service/overview). The dataset we created can be found at [hawaii_places.csv](hawaii_places.csv). The data was collected after creating an API key through Google Developers and running the script [gplaces.py](gplaces.py). In this script, requests are made to the Google Places API to retrieve 60 results for each of three categories (tourist attractions, restaurants, and lodging) in a 50,000 meter radius around the center of the state of Hawaii. The results are preprocessed in the same script through data cleaning and sectioning to retrieve solely the data necessary for our relational schema, and saved in a CSV format to easily populate our database. This script can be directly run to replicate our results and can also be modified to retrieve additional results with different parameters.


# Spring Boot
Document for JpaRepository: https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html

Guides for Spring Boot + Mysql: https://spring.io/guides/gs/accessing-data-mysql

## Details for Spring Boot
HTTP port is 8080 and Mysql API port is 3306 (assigned in src/main/resource/application.properties).

## Mysql init:
```
CREATE DATABASE travel_recs;
USE travel_recs;

CREATE USER 'group5'@'127.0.0.1' IDENTIFIED BY 'abc123';

GRANT ALL PRIVILEGES ON travel_recs.* TO 'group5'@'127.0.0.1';
FLUSH PRIVILEGES;
```

### Table creation
```
CREATE TABLE Days (
    dayID INT PRIMARY KEY,
    tripID INT,
    placeID INT,
    index INT,
    FOREIGN KEY (tripID) REFERENCES Trips(tripID)
);

CREATE TABLE Trips (
    tripID INT PRIMARY KEY,
    startDate DATE,
    endDate DATE,
    totalBudget FLOAT,
    curBudget FLOAT,
    userID INT,
    FOREIGN KEY (userID) REFERENCES Users(userID)
);


CREATE TABLE User (
    userID INT PRIMARY KEY,
    userName VARCHAR(255) NOT NULL
);
```

## Command to validate the effectiveness:
Create a tuple for Table User:
`curl http://localhost:8080/user/add -d userID=1 -d userName=XX`

Find a user named XX: 
`curl -G http://localhost:8080/user/getByUserName -d userName=XX`


## (optional) In memory database for deployment
in `application.properties`, comment out the following code:
```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```
and click run in the IntelliJ, then the service will be started
