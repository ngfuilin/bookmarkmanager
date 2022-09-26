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
* Install Postgresql and create an empty database name “bookmarkmanager”.
* Open application on IntelliJ. Remember to change the postgres username and password on application.properties file.
```
spring.datasource.url=jdbc:postgresql://localhost:5432/bookmarkmanager
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
server.error.include-message=always
```
* Run the application on IntelliJ. Make sure no error thrown. Once the application runs you should see something like this:
```
2022-09-25 22:15:20.037  INFO 9712 --- [main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2022-09-25 22:15:20.057  INFO 9712 --- [main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2022-09-25 22:15:20.058  INFO 9712 --- [main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.65]
```
* Now, you can start calling some of the operational endpoints.

Here are some endpoints you can call (Assume Tomcat webserver started on port 8080):

### Get information about bookmark and folder
**1.  Get a list of bookmarks**  
Method: GET   /api/v1/bookmarks
```
http://localhost:8080/api/v1/bookmarks
```

**2.  Get a list of bookmarks for a folder**  
Method: GET  /api/v1/bookmarks/folders/:id
```
http://localhost:8080/api/v1/bookmarks/folders/1
```

**3.  Get a list of folders**  
Method: GET  /api/v1/bookmarks/folders
```
http://localhost:8080/api/v1/folders
```
### Create a Bookmark and Folder
**1.  Create a folder**  
Method: POST /api/v1/folders  
Content-Type: application/json
```     
http://localhost:8080/api/v1/folders  
```
```
{
  "name": "folder 01",
  "description": "folder 01 description"
}
```  

**2.  Create a bookmark**  
Method: POST /api/v1/bookmarks  
Content-Type: application/json
```
http://localhost:8080/api/v1/bookmarks  
```        
* create a bookmark belongs to a folder
```
{
  "name": "bookmark 01",
  "url": "http://www.bookmark01.com",
  "folder": {"id": 1}
}   
```  

* create a bookmark does not belongs to any folder
```
{
  "name": "bookmark 02",
  "url": "https://www.bookmark02.com"
}  
```
### Update a Bookmark and Folder

**1.  Update a folder**   
PUT /api/v1/folders/:id
```
http://localhost:8080/api/v1/folders/1?name=folder01 test&description=folder01 description
```
**2.  Update a bookmark**  
PUT /api/v1/bookmarks/:id
* Update folder Id for a bookmark
```
http://localhost:8080/api/v1/bookmarks/1?folderId=1 
```
* Update name, url and folder Id for a bookmark
```
http://localhost:8080/api/v1/bookmarks/1?name=chgbookmark&url=http://www.rch.com&folderId=1
```
* Update name and url for a bookmark
```
http://localhost:8080/api/v1/bookmarks/1?name=abc&url=http://abc.com
```  

### Delete a Bookmark and Folder

**1.  Delete a folder(bookmarks under the folder will be deleted)**  
DELETE /api/v1/folders/:id
```
http://localhost:8080/api/v1/folders/1
```  
**2.  Delete a bookmark**   
DELETE /api/v1/bookmarks/:id
```
http://localhost:8080/api/v1/bookmarks/1  
```