## PayMyBuddy

### Data Model
<img width="761" height="667" alt="image" src="https://github.com/user-attachments/assets/9182e943-5e32-4650-bd5b-2cbd6f686184" />


### Login UI
<img width="359" height="396" alt="image" src="https://github.com/user-attachments/assets/04342203-23c1-469f-a58a-9a78d7fe066d" />

### Transfer page
<img width="1294" height="540" alt="image" src="https://github.com/user-attachments/assets/030d9a3f-8ce8-4196-b8c6-a1346105970e" />

### application.properties
```
spring.application.name=demo
spring.datasource.url=jdbc:mysql://localhost:3306/paymybuddy
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
server.port=5000
```

### Jacoco test coverage on the service layer:

<img width="1012" height="183" alt="image" src="https://github.com/user-attachments/assets/cc5d52a6-4310-4a40-b578-371add774994" />

