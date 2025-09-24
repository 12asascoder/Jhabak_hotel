# Jhabak Hotel Management System

A modern Java Spring Boot hotel management system with authentication, rooms, customers, and bookings. Dark-themed, animated Thymeleaf UI. Supports H2 (dev) and MySQL (prod-like).

## Features
- Admin and Customer roles
  - Admin: manage rooms/customers/bookings
  - Customer: view and create bookings
- Entities: Room, Customer, Booking (Spring Data JPA)
- Animated dark UI (Thymeleaf)
- Profiles: `default` (H2), `mysql` (MySQL)
- SQL seed (MySQL) with ~100 rooms (idempotent)

## Prerequisites
- Java 17+
- Maven 3.9+
- MySQL 8+ (for `mysql` profile)

## Run (H2/dev)
```bash
mvn -q spring-boot:run
# http://localhost:8080/login
```

## Run (MySQL)
1. Ensure database exists (or `createDatabaseIfNotExist=true` will create it):
```sql
CREATE DATABASE IF NOT EXISTS jhabakhms;
```
2. Check `src/main/resources/application-mysql.properties` for URL/user/password.
3. Start:
```bash
mvn -q -Dspring-boot.run.profiles=mysql spring-boot:run
# http://localhost:8080/login
```

## Logins
- Admin: `admin` / `admin`
- Customer: `customer` / `customer`

## Structure
- `src/main/java/com/jhabak/hotel/` — core app
- `src/main/resources/templates/` — Thymeleaf views
- `src/main/resources/static/` — CSS
- `src/main/resources/db/mysql/` — schema & seed for MySQL

## Notes
- Seeds use `ON DUPLICATE KEY UPDATE` for idempotency
- H2 uses `spring.jpa.hibernate.ddl-auto=update`

License: For academic and general use.
