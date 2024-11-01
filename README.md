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

## Command to validate the effectiveness:
Create a tuple for Table User:
`curl http://localhost:8080/user/add -d userID=1 -d userName=XX`

Find the user named XX: 
`curl -G http://localhost:8080/user/getByUserName -d userName=XX`
