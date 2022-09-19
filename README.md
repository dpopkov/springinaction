# Spring in Action
Code for "Spring in Action", 6th Edition.
It is different from the book.

## 1 - Getting started
* Project: [c01e01tc](c01e01tc)
* To build and test run: `mvn package`
* To start run: `java -jar target/c01e01tc-0.0.1-SNAPSHOT.jar `
* or: `mvn spring-boot:run`
* To test run: `mvn test`

## 2 - Developing web applications
* Project: [c02e01sh](c02e01sh)

### Validating form input
* Add dependency for `spring-boot-starter-validation`
* Declare validation rules: `@NotNull, @NotBlank, @Pattern, @Digits, @CreditCardNumber`
* Specify that validation should be performed in the controller methods: `@Valid`
* Modify the form views to display validation errors

## 3 - Working with data

### Reading and writing data with JDBC
* Project: [c03e01jdbc](c03e01jdbc)
* Add dependency for `spring-boot-starter-jdbc`
* Add dependency for `h2`
* Configure data source properties:
```yaml
spring:
  datasource:
    generate-unique-name: false
    name: test
    username: sa
    password:
```
* Set SQL init platform for schema-h2.sql and data-h2.sql scripts
```yaml
  sql:
    init:
      platform: h2
```
* Define a schema and preload data using schema-h2.sql and data-h2.sql scripts
* Use `JdbcTemplate`:
    * `query(sql, rowMapper)`
    * `queryForObject(sql, rowMapper, args)`
    * `update(sql, args)`
    * `update(preparedStatementCreator, keyHolder)`

### Working with Spring Data JDBC
* Project: [c03e02datajdbc](c03e02datajdbc)
* Add dependency for `spring-boot-starter-data-jdbc`
* Add dependency for `h2`
* Extend `CrudRepository<T, ID>`
* Annotate the domain for persistence: 
    * `@Id` - org.springframework.data.annotation.Id
    * `@Table` - org.springframework.data.relational.core.mapping.Table
    * `@Column` - org.springframework.data.relational.core.mapping.Column
* Log SQL: `logging.level.sql: debug`

### Preloading data with CommandLineRunner or ApplicationRunner
* Remove data.sql script.
* Add beans for runners.

#### Using CommandLineRunner
```java
@Bean
public CommandLineRunner dataLoader(IngredientRepository repo) {
    return args -> {
        // code loading data
    };
}
```

#### Using ApplicationRunner
```java
@Bean
public ApplicationRunner dataLoader(IngredientRepository repo) {
    return args -> {
        // code loading data
    };
}
```
* ApplicationRunner accepts an ApplicationArguments parameter that offers methods for accessing the arguments 
as parsed components of the command line:
```java
public ApplicationRunner dataLoader(IngredientRepository repo) {
    return args -> {
        List<String> version = args.getOptionValues("version");
        // ...
    };
}
```

### Persisting data with Spring Data JPA
* Project: [c03e03datajpa](c03e03datajpa)
* Add dependency for `spring-boot-starter-data-jpa`
* Add dependency for `h2`
* Annotate domain as entities: 
    * `@Entity` - javax.persistence.Entity
    * `@Id` - javax.persistence.Id
    * `@GeneratedValue` - javax.persistence.GeneratedValue
* Declare JPA repositories: `interface IngredientRepository extends CrudRepository<Ingredient, String>`
* Use `@DataJpaTest` for testing

### Customizing repositories by writing custom queries
* Repository methods are composed of a verb, an optional subject, the word `By` and a predicate
* Custom method `findByDeliveryZip`:
    * `find` - verb
    * `DeliveryZip` - predicate
    * subject isn't specified
* Custom method `readOrdersByDeliveryZipAndPlacedAtBetween`:
    * `read` - verb
    * `By` - start of properties to match
    * `DeliveryZipAndPlacedAtBetween` - predicate
        * `DeliveryZip` - match DeliveryZip property
        * And
        * `PlacedAtBetween` - the value of PlacedAt must fall between the given values

#### Spring Data method signatures can include any of the following operations:
* implicit Equals and Between
* IsAfter , After , IsGreaterThan , GreaterThan
* IsGreaterThanEqual , GreaterThanEqual
* IsBefore , Before , IsLessThan , LessThan
* IsLessThanEqual , LessThanEqual
* IsBetween , Between
* IsNull , Null
* IsNotNull , NotNull
* IsIn , In
* IsNotIn , NotIn
* IsStartingWith , StartingWith , StartsWith
* IsEndingWith , EndingWith , EndsWith
* IsContaining , Containing , Contains
* IsLike , Like
* IsNotLike , NotLike
* IsTrue , True
* IsFalse , False
* Is , Equals
* IsNot , Not
* IgnoringCase , IgnoresCase , AllIgnoringCase, AllIgnoresCase
* OrderBy

#### Using Query
```java
@Query("Order o where o.deliveryCity='Seattle'")
List<Order> readOrdersDeliveredInSeattle();
```

## 4 - Working with non-relational data

### Working with Cassandra repositories
* Project: [c04e01cassandra](c04e01cassandra)
* Add dependency for `spring-boot-starter-data-cassandra`
* Download Docker image: `docker image pull cassandra:3.11.11`
* Start a single-node cluster for development purposes using Docker:
```shell script
docker network create cassandra-net
docker run --name my-cassandra --network cassandra-net -p 9042:9042 -d cassandra:3.11.11

# Run a command in the 'my-cassandra' container
docker run -it --network cassandra-net --rm cassandra:3.11.11 cqlsh my-cassandra
# Create keyspace manually:
cqlsh> create keyspace shaurmacloud
   ... with replication={'class':'SimpleStrategy', 'replication_factor':1}
   ... and durable_writes=true;
```
* Configure the `spring.data.cassandra.keyspace-name` property
```yaml
spring:
  data:
    cassandra:
      keyspace-name: shaurmacloud
      schema-action: recreate
      local-datacenter: datacenter1
```
* Map domain types for Cassandra using annotations
    * `@Table` - org.springframework.data.cassandra.core.mapping.Table
    * `@PrimaryKey` - org.springframework.data.cassandra.core.mapping.PrimaryKey
    * `@Colum` - org.springframework.data.cassandra.core.mapping.Column
    * `@PrimaryKeyColumn` - org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
* Create User Defined Types
* Write repositories by extending Spring Data interfaces (CrudRepository)

### Working with MongoDB repositories
* Project: [c04e02mongodb](c04e02mongodb)
* Add dependency for `spring-boot-starter-data-mongodb`
* Get Mongo server running using Docker: `docker run -p 27017:27017 -d mongo:4.4.9` (14 oct 2021)
* Or use embedded Mongo:
```xml
<dependency>
    <groupId>de.flapdoodle.embed</groupId>
    <artifactId>de.flapdoodle.embed.mongo</artifactId>
    <!-- <scope>test</scope> -->
</dependency>
```
* Annotate domain objects for persistence as documents in MongoDB.
    * `@Document` - org.springframework.data.mongodb.core.mapping.Document
    * `@Id` - org.springframework.data.annotation.Id
* Change the type of id properties to String for automatically assigning by MongoDB 
* Write repository interfaces by extending Spring Data interfaces (CrudRepository)

## 5 - Securing Spring
* Project: [c05e01security](c05e01security)
* Enable Spring Security: `spring-boot-starter-security`

### Configuring Authentication
* Declare a PasswordEncoder, which will be used when creating users and authenticating at login:
```java
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```
* Declare a UserDetailsService bean

#### In-memory user details service for a handful of users, none of which are likely to change
```java
@Bean
public UserDetailsService userDetailsService(PasswordEncoder encoder) {
    List<UserDetails> users = new ArrayList<>();
    users.add(new User("james", encoder.encode("password"), List.of(new SimpleGrantedAuthority(ROLE_USER))));
    users.add(new User("alice", encoder.encode("password"), List.of(new SimpleGrantedAuthority(ROLE_USER))));
    return new InMemoryUserDetailsManager(users);
}
```

#### Customizing user authentication
* Create domain object and repository for user.
* Create a custom user details service bean:
```java
@Bean
public UserDetailsService userDetailsService(AppUserRepository appUserRepository) {
    return username -> {
        Optional<AppUser> byUsername = appUserRepository.findByUsername(username);
        if (byUsername.isEmpty()) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }
        return byUsername.get();
    };
}
```
* Add registration controller and view.
* Secure web requests:
    * Secure requests:
    ```java
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeRequests()
                .antMatchers("/design", "/orders").hasRole("USER")
                .antMatchers("/", "/**").permitAll()
            .and()
                .build();
    }
    ```
    * Create custom login page.
    * Spring Security has built-in CSRF protection:
        * __Do not forget__ to include `th:action=@{context-relative-path}` into form element.
        * then the hidden `_csrf` field will be rendered automatically.

### Applying method level security
* Apply `@EnableGlobalMethodSecurity` annotation:
```java
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    // ...
}
```
* Apply `@PreAuthorize` annotation on method:
```java
@PreAuthorize("hasRole('ADMIN')")
public void deleteAllOrders() {
    orderRepository.deleteAll();
}
```

### Determine who the logged in user is
* Associate specific Entities with application user Entity.
* Identify who the associated user is using one of these ways:
    * Inject a `java.security.Principal` object into the controller method.
    ```java
    @PostMapping
    public String processOrder(@Valid Order order, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        order.setUser(user);
        orderRepository.save(order);  
    }
    ```
    * Inject an `org.springframework.security.core.Authentication` object into the controller method.
    ```java
    @PostMapping
    public String processOrder(@Valid Order order, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        order.setUser(user);
        orderRepository.save(order);
    }
    ```
    * Use `org.springframework.security.core.context.SecurityContextHolder` to get at the security context.
        ```java
        @PostMapping
        public String processOrder(@Valid ShaurmaOrder order) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            order.setUser(user);
            orderRepository.save(order);
        }
        ```
    * Inject an `@AuthenticationPrincipal` annotated method parameter (`org.springframework.security.core.annotation`)
    ```java
    @PostMapping
    public String processOrder(@Valid ShaurmaOrder order, @AuthenticationPrincipal AppUser user) {
        order.setUser(user);
        orderRepository.save(order);
    }
    ```

## 6 - Working with configuration properties

### Fine-tuning auto-configuration

#### Understanding Spring's environment abstraction
* application.properties
    * server.port=9090
* application.yml
    * server:
        * port: 9090
* command line arguments
    * `java -jar app.jar --server.port=9090`
* operating system environment variable
    * `export SERVER_PORT=9090`
        * Spring interprets SERVER_PORT as server.port

#### Configuring a data source
* application.yml
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost/databasename
    username: user
    password: 123
    driver-class-name: com.mysql.jdbc.Driver  # if there is a problem with auto-configuring
```
* Database initialization scripts
```yaml
spring:
  datasource:
    schema:
    - order-schema.sql
    - ingredient-schema.sql
    - shaurma-schema.sql
    - user-schema.sql
  data:
    - ingredients.sql
```
* Configure data source in the Java Naming and Directory Interface (JNDI)
```yaml
spring:
  datasource:
    jndi-name: java:/comp/env/jdbc/shaurmaDS
``` 

#### Configuring the embedded server
* Start on a randomly chosen available port
```yaml
server:
  port: 0
```
* Enable HTTPS on the embedded server
    * create a keystore using `keytool` command line utility:
        * `keytool -keystore mykeys.jks -genkey -alias tomcat -keyalg RSA`
    * add to application.yml
    ```yaml
    server:
      port: 8443
      ssl:
        key-store: file:///path/to/mykeys.jks
        key-store-password: letmeplease
        key-password: letmeplease
    ```

#### Configuring logging
* By default, Spring Boot configures logging via Logback to write to the console at an INFO level.
* For full control over the logging configuration, you can create a logback.xml file (in src/main/resources):
```xml
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>
                %d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n
            </pattern>
        </encoder>
    </appender>
    <logger name="root" level="INFO"/>
    <root level="INFO">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```
* Set logging levels:
```yaml
logging:
  level:
    root: WARN
    org:
      springframework:
        security: DEBUG
```
* or
```yaml
logging:
  level:
    root: WARN
    org.springframework.security: DEBUG
```
* to write the log entries to a file
```yaml
logging:
  file:
    path: /var/logs/
    file: shaurma.log
  level:
    root: WARN
    org.springframework.security: DEBUG
```

#### Using special property values
* Derive values from other configuration properties:
```yaml
greeting:
  welcome: ${spring.application.name}
```
* Embed the placeholder into other text:
```yaml
greeting:
  welcome: You are using ${spring.application.name}
```

### Creating your own configuration properties
* Project: [c06e01properties](c06e01properties)

Configuration properties are nothing more than properties of beans that have been designated
to accept configurations from Spring's environment abstraction.

To support property injection of configuration properties, Spring Boot provides 
the `@ConfigurationProperties` annotation. When placed on any Spring bean, it specifies that
the properties of that bean can be injected from properties in the Spring environment.

* Use `@ConfigurationProperties` annotation:
```java
@ConfigurationProperties(prefix = "shaurma.orders")
public class OrderController {
    private int pageSize = 20;
}
```
* Set this property in application.yml:
```yaml
shaurma:
  orders:
    pageSize: 10
```
* Or set this property using standard methods:
    * Environment variable: `export SHAURMA_ORDERS_PAGESIZE=30`
    * Command line argument: `--shaurma.orders.pageSize=15`

#### Defining configuration property holders
`@ConfigurationProperties` are often placed on beans whose sole purpose in the application 
is to be holders of configuration data. This keeps configuration specific details out 
of the controllers and other application classes. 
It also makes it easy to share common configuration properties among several
beans that may make use of that information.

* Create a separate class for holding configuration data.
* Inject his holder into other beans that use this configuration data.
```java
@Data
@Component
@ConfigurationProperties(prefix = "shaurma.orders")
public class OrderProps {
    private int pageSize = 20;
}
```
* This allows you to reuse the properties in the holder in any other bean that may need them.
* For testing purposes, it’s easy to set configuration properties directly on a test-specific holder
  and give it to the controller prior to the test.

#### Adding metadata for custom configuration properties
* Use quick fix of IDE or create file manually in resources/META-INF folder:
    * additional-spring-configuration-metadata.json:
    ```json
    {
      "properties": [
        {
          "name": "shaurma.orders.pageSize",
          "type": "java.lang.Integer",
          "description": "Number of orders in the list of recent orders of the current user."
      }
    ] }
    ```
* To use the Annotation Processor, include a dependency on `spring-boot-configuration-processor`:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```
* After recompiling of the project you get hover-documentation and auto-completion help.
