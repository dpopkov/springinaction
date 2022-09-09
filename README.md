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
