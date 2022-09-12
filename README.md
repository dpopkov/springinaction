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
