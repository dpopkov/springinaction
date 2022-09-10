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
