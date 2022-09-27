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
* Open application on IntelliJ. Remember to change the postgres username and password on application.properties file.
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
```
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
```
{
  "name": "bookmark 01",
  "url": "http://www.bookmark01.com",
  "folder": {"id": 1}
}   
```  

**2.2 create a bookmark does not belongs to any folder**  
- Body:

```
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
**2.2 Update name, url and folder Id for a bookmark**  
- URL:
```
http://localhost:8080/api/v1/bookmarks/1?name=chgbookmark&url=http://www.rch.com&folderId=1
```
**2.3 Update name and url for a bookmark**  

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
- URL:  

```
http://localhost:8080/api/v1/bookmarks/1  
```