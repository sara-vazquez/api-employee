# Support App - Helpdesk Software
A startup has requested the construction of an API to be consumed by its web application and its mobile app. The API should be able to register employee requests for technical support from their IT department if needed. User identification through a security module will not be necessary.

## 🛠 Technologies
* Java 21 SE
* Spring & Spring Boot
* Spring Data JPA
* Database: H2

## 🎯 Diagrams 
### Class diagram
```mermaid
---
config:
  theme: 'forest'
---
classDiagram
    %% ========== INTERFACES ==========
    class IGenericService~T, S~ {
        <<interface>>
        +getEntities() List~T~
        +storeEntity(S) T
        +getEntityById(Long) T
        +updateEntity(Long, S) T
    }
    
    class ITopicService~T~ {
        <<interface>>
        +getAllEntities() List~T~
        +findById(Long) T
    }
    
    class ISortableService~T, S~ {
        <<interface>>
        +getEntitiesSortedByDate() List~T~
    }
    
    class IAttendanceService {
        <<interface>>
        +markAsAttended(AttendanceDTORequest) AttendanceDTOResponse
    }
    
    %% ========== ENTITIES ==========
    class RequestEntity {
        <<@Entity>>
        -Long id
        -String name
        -LocalDate date
        -TopicEntity topic
        -String description
        -boolean attended
        -LocalDateTime createdAt
        -LocalDateTime updatedAt
        -AttendanceEntity attendance
    }
    
    class TopicEntity {
        <<@Entity>>
        -Long id
        -String name
    }
    
    class TechnicianEntity {
        <<@Entity>>
        -Long id
        -String technicianName
        -AttendanceEntity attendance
    }
    
    class AttendanceEntity {
        <<@Entity>>
        -Long id
        -LocalDateTime attendedAt
        -RequestEntity request
        -TechnicianEntity technician
    }
    
    %% ========== DTOs ==========
    class RequestDTORequest {
        <<record>>
        +String name
        +LocalDate date
        +Long topicId
        +String description
        +boolean attended
    }
    
    class RequestDTOResponse {
        <<record>>
        +Long id
        +String name
        +LocalDate date
        +Long topicId
        +String description
        +boolean attended
        +LocalDateTime createdAt
        +LocalDateTime updatedAt
    }
    
    class TopicDTORequest {
        <<record>>
        +String name
    }
    
    class TopicDTOResponse {
        <<record>>
        +Long id
        +String name
    }
    
    class TechnicianDTORequest {
        <<record>>
        +String technicianName
    }
    
    class TechnicianDTOResponse {
        <<record>>
        +Long id
        +String technicianName
    }
    
    class AttendanceDTORequest {
        <<record>>
        +LocalDateTime attendedAt
        +Long request
        +Long technician
    }
    
    class AttendanceDTOResponse {
        <<record>>
        +Long id
        +LocalDateTime attendedAt
        +Long request
        +Long technician
    }
    
    %% ========== CONTROLLERS ==========
    class RequestController {
        <<@RestController>>
    }
    
    class TopicController {
        <<@RestController>>
    }
    
    class TechnicianController {
        <<@RestController>>
    }
    
    class AttendanceController {
        <<@RestController>>
    }
    
    %% ========== SERVICES ==========
    class RequestServiceImpl {
        <<@Service>>
    }
    
    class TopicServiceImpl {
        <<@Service>>
    }
    
    class TechnicianServiceImpl {
        <<@Service>>
    }
    
    class AttendanceServiceImpl {
        <<@Service>>
    }
    
    %% ========== REPOSITORIES ==========
    class RequestRepository {
        <<@Repository>><<interface>>
    }
    
    class TopicRepository {
        <<@Repository>><<interface>>
    }
    
    class TechnicianRepository {
        <<@Repository>><<interface>>
    }
    
    class AttendanceRepository {
        <<@Repository>><<interface>>
    }
    
    %% ========== MAPPERS ==========
    class RequestMapper
    class TopicMapper
    class TechnicianMapper
    class AttendanceMapper
    
    %% ========== RELACIONES ==========
    TopicEntity "1" --> "*" RequestEntity : "@ManyToOne"
    RequestEntity "1" --> "1" AttendanceEntity : "@OneToOne"
    TechnicianEntity "1" --> "1" AttendanceEntity : "@OneToOne"
    AttendanceEntity "*" --> "1" RequestEntity : "request"
    AttendanceEntity "*" --> "1" TechnicianEntity : "technician"
    
    ISortableService --|> IGenericService : extends
    
    RequestServiceImpl ..|> ISortableService
    TopicServiceImpl ..|> ITopicService
    TechnicianServiceImpl ..|> IGenericService
    AttendanceServiceImpl ..|> IAttendanceService
    
    RequestController --> ISortableService
    RequestController --> RequestServiceImpl
    TopicController --> ITopicService
    TechnicianController --> IGenericService
    AttendanceController --> IAttendanceService

```

### Database schema
```mermaid
---
config:
  theme: 'forest'
---
erDiagram
    TOPICS {
        LONG id_topic PK "AUTO_INCREMENT"
        STRING name UK "NOT NULL, UNIQUE"
    }
    
    REQUESTS {
        LONG id_request PK "AUTO_INCREMENT"
        STRING name "NOT NULL"
        DATE date "NOT NULL"
        LONG topic_id FK "NOT NULL"
        STRING description "NOT NULL, TEXT"
        BOOLEAN attended "DEFAULT FALSE"
        TIMESTAMP created_at "NOT NULL, UPDATABLE=FALSE"
        TIMESTAMP updated_at "NULLABLE"
    }
    
    TECHNICIANS {
        LONG id_technicians PK "AUTO_INCREMENT"
        STRING technician_name UK "NOT NULL, UNIQUE"
    }
    
    ATTENDANCES {
        LONG id_attendance PK "AUTO_INCREMENT"
        TIMESTAMP attended_at "NOT NULL"
        LONG request_id FK "NOT NULL, UNIQUE"
        LONG technician_id FK "NOT NULL, UNIQUE"
    }
    
    %% Relaciones
    TOPICS ||--o{ REQUESTS : "has_many"
    REQUESTS ||--|| ATTENDANCES : "has_one_attendance"
    TECHNICIANS ||--o{ ATTENDANCES : "can_attend_many"
```

## 📑 Documentation with Swagger

## ⚙️ Execution
Follow these steps to get the project up and running:

1. Clone the Repository: Open your terminal and clone the project using the following command:
   ```
   git clone [https://github.com/sara-vazquez/api-employee]
   ```
2. Compile the Project: Use Maven to compile the source code.
   ```
   mvn clean install
   ```
3. Execute the application
   ```
   java -jar target/support-api-0.0.1-SNAPSHOT.jar
   ```



## 📯 Postman
![postman screenshot showing collection](postman.png)

[Postman collection link](https://lunar-rocket-4378638.postman.co/workspace/API-practice~317c143d-3828-4b04-8929-47a76e03adfa/collection/45994293-eca8c853-f45b-482e-a2b3-6d3d346eec15?action=share&creator=45994293)

## 🧪 Test Coverage
![image of test coverage](coverage-helpdesk.png)
