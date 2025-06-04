How do you connect Spring Data JPA to a database in Spring Boot?
1. Add Required Dependencies
If you're using Maven, include the following in pom.xml:

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
</dependency>
---------------------------------------------------------------------------------
 2. Configure Database Properties
Add the connection details in application.properties or application.yml:

properties
Copy
Edit
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
ddl-auto=update automatically creates/updates tables

show-sql=true logs SQL in the console

--------------------------------------------------------------------------------
3. Create the Entity Class

import jakarta.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // getters and setters
}

--------------------------------------------------------------------------------

4. Create the Repository Interface

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // You can add custom query methods like findByName
}
----------------------------------------------------------------------------------
5. Use in Service or Controller

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return repository.save(employee);
    }

    @GetMapping
    public List<Employee> getAll() {
        return repository.findAll();
    }
}
----------------------------------------------------------------------------------