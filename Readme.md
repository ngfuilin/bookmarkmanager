# Spring Boot Project - Bookmark Manager System Backend

This is a Java / Maven / Spring Boot (version 2.7.4) project built with IntelliJ IDEA 2022.1.4 (Community Edition) for creating REST APIs.

## Description

The Bookmark manager is a system that’s similar to how the bookmarking is done in a browser. You can bookmark URLs and organize them in different folders. A bookmark doesn’t have to always belong to a folder (e.g, when creating/updating a bookmark, the folder id can optionally be omitted).

It uses PostgreSQL to store the data. The data models are as follow:

**Bookmark:**
* Id: Unique identifier
* Name: Name of the bookmark
* URL: Link of the bookmark
* Folder Id (optional): Id of the folder where the bookmark is kept
* Created at: Timestamp when the bookmark is created
* Updated at: Timestamp when the bookmark is updated

**Folder:**
* Id: Unique identifier
* Name: Name of the folder
* Description (optional): Some description
* Created at: Timestamp when the folder is created
* Updated at: Timestamp when the folder is updated

## How to install and Run the Project

* Clone this repository
* Make sure you are using Java 11 or above
* Install IntelliJ
* Install Postgresql and create an empty database name “bookmarkmanager” (you may use different database name).
* Open application on IntelliJ. Remember to change the postgres username, password and url according to your settings on application.properties file.
```
spring.datasource.url=jdbc:postgresql://localhost:5432/bookmarkmanager
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQL94Dialect
spring.jpa.properties.hibernate.format_sql=true
server.error.include-message=always
```
* Run the application on IntelliJ. Make sure no error thrown. Once the application runs you should see something like this:
```
2022-09-26 20:57:54.477  INFO 18304 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-09-26 20:57:54.490  INFO 18304 --- [           main] c.e.b.BookmarkmanagerApplication         : Started BookmarkmanagerApplication in 6.73 seconds (JVM running for 7.345)
```
* Now, you can start calling some of the operational endpoints.

Here are some endpoints you can call (Assume Tomcat webserver started on port 8080):

### Get information about bookmark and folder
**1.  Get a list of bookmarks**  
Method: **GET**     /api/v1/bookmarks
```
http://localhost:8080/api/v1/bookmarks
```

**2.  Get a list of bookmarks for a folder**  
Method: **GET**     /api/v1/bookmarks/folders/:id
```
http://localhost:8080/api/v1/bookmarks/folders/1
```

**3.  Get a list of folders**  
Method: **GET**     /api/v1/bookmarks/folders
```
http://localhost:8080/api/v1/folders
```
### Create a Bookmark and Folder
**1.  Create a folder**  

- Method: **POST**    
- Endpoint: /api/v1/folders  
- Content-Type: application/json  
- URL:        
```     
http://localhost:8080/api/v1/folders  
```
- Body:  
```json
{
  "name": "folder 01",
  "description": "folder 01 description"
}
```  

**2.  Create a bookmark**    

- Method: **POST**    
- Endpoint: /api/v1/bookmarks  
- Content-Type: application/json
```
http://localhost:8080/api/v1/bookmarks  
```        
**2.1 create a bookmark belongs to a folder**  
- Body:
```json
{
  "name": "bookmark 01",
  "url": "http://www.bookmark01.com",
  "folder": {"id": 1}
}   
```  

**2.2 create a bookmark does not belongs to any folder**  
- Body:

```json
{
  "name": "bookmark 02",
  "url": "https://www.bookmark02.com"
}  
```
### Update a Bookmark and Folder

**1.  Update a folder**   
- Method: **PUT**     
- Endpoint: /api/v1/folders/:id
- URL:
```
http://localhost:8080/api/v1/folders/1?name=folder01 test&description=folder01 description
```
**2.  Update a bookmark**  
- Method: **PUT**     
- Endpoint: /api/v1/bookmarks/:id  

**2.1 Update folder Id for a bookmark**  
- URL:  

```
http://localhost:8080/api/v1/bookmarks/1?folderId=1 
```
**2.2 Update name, url and folder Id for a bookmark with folder Id = 1**  
- URL:
```
http://localhost:8080/api/v1/bookmarks/1?name=chgbookmark&url=http://www.rch.com&folderId=1
```
**2.3 Update name and url for a bookmark with folder Id = 1**  

```
http://localhost:8080/api/v1/bookmarks/1?name=abc&url=http://abc.com
```  

### Delete a Bookmark and Folder

**1.  Delete a folder(bookmarks under the folder will be deleted)**  
- Method: **DELETE**      
- Endpoint: /api/v1/folders/:id
- URL:  

```
http://localhost:8080/api/v1/folders/1
```  
**2.  Delete a bookmark**   
- Method: **DELETE**
- Endpoint: /api/v1/bookmarks/:id
- URL:  (to delete bookmark with id = 1)

```
http://localhost:8080/api/v1/bookmarks/1  
```

**Restful API Response Code**  

| HTTP Status Code          |                                                                                                                              Description |
|---------------------------|-----------------------------------------------------------------------------------------------------------------------------------------|
| 200 OK                    |                                                                                                                               Successful |
| 400 Bad Request           |                                                                    Bad input parameter. Error message should indicate which one and why. |
| 404 Not Found             |                                                                                                                      Resource not found. |
| 500 Internal Server Error | Server encountered an unexpected condition that prevented it from fulfilling the request. Error message should indicate possible causes. |

**Annotation Used**  

| Annotation            | Description                                                                                                                                                                               |
|-----------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| @Entity               | Specifies that the class is an entity and is mapped to a database table.                                                                                                                  |
| @Table                | Specifies the name of the database table to be used for mapping                                                                                                                           |
| @JsonInclude          | to indicate when value of the annotated property (when used for a field, method or constructor parameter), or all properties of the annotated class, is to be serialized.                 |
| @JsonIgnoreProperties | used at class level to mark a property or list of properties to be ignored.                                                                                                               |
| @SequenceGenerator    | Defines a primary key generator that may be referenced by name when a generator element is specified for the GeneratedValue annotation.                                                   |
| @GeneratedValue       | Provides the specification of generation strategies for the primary keys values.                                                                                                          |
| @Column               | It is an optional annotation that enables you to customize the mapping between the entity attribute and the database column.                                                              |
| @Id                   | Specifies the primary key of an entity                                                                                                                                                    |
| @CreationTimeStamp    | Marks a property as the creation timestamp of the containing entity. The property value will be set to the current VM date exactly once when saving the owning entity for the first time. |
| @UpdateTimestamp      | To track the timestamp of the last update of an entity. Attribute value changed for every update.                                                                                         |
| @ManyToOne            | Defines a many-to-one relationship between two entities                                                                                                                                   |
| @JoinColumn           | Specifies a column for joining an entity association or element collection.                                                                                                               |
| @OneToMany            | Define a many-to-one relationship between two entities.                                                                                                                                   |
| @Repository           | Used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects.                                                            |
| @Query                | Declares finder queries directly on repository methods.                                                                                                                                   |
| @Service              | Used with classes that provide some business functionalities.                                                                                                                             |
| @Autowired            | Enables you to inject the object dependency implicitly.                                                                                                                                   |
| @Transactional        | Metadata that specifies the semantics of the transactions on a method. Use at service layer to define the transaction boundaries.                                                         |
| @RestController       | Used to create RESTful web services.                                                                                                                                                      |
| @RequestMapping       | Used to map web requests onto specific handler classes and/or handler methods.                                                                                                            |
| @GetMapping           | Mapping HTTP GET requests onto specific handler methods. Use GET requests to retrieve data from your server.                                                                              |
| @PathVariable         | Used to extract the value from the URI.                                                                                                                                                   |
| @DeleteMapping        | Maps the HTTP DELETE requests onto specific handler methods of a Spring controller. Use DELETE request to delete resources from your system.                                              |
| @PostMapping          | Mapping HTTP POST requests onto specific handler methods. Used to create new resources to your system.                                                                                    |
| @PutMapping           | Mapping HTTP PUT  requests onto specific handler methods. Used to update  resources to your system.                                                                                       |
| @RequestBody          | Indicates that Spring should deserialize a request body into an object.                                                                                                                   |
| @RequestParam         | Used to bind a web request parameter to a method parameter.                                                                                                                               |

