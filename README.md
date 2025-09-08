## PayMyBuddy

### Data Model
<img width="761" height="667" alt="image" src="https://github.com/user-attachments/assets/9182e943-5e32-4650-bd5b-2cbd6f686184" />


### 🔑 Signup & Login
To access the application, users first need to register.

Signup:
* A new user can register by filling in their username, email, and password.
* The password is encrypted with BCrypt before being stored in the database.
* During signup, an account is automatically created for the user with an initial balance of 0.00.

Login:
* Registered users can log in with their email and password.
* Authentication is handled by Spring Security with a custom UserDetailsService.
If the credentials are valid, the user is redirected to their dashboard.
<img width="196" height="229" alt="SignUp" src="https://github.com/user-attachments/assets/dd743fbc-cea3-4c82-8ebf-09c6460539f2" />
<img width="208" height="227" alt="LogIn" src="https://github.com/user-attachments/assets/7e0a89a4-66ea-4afc-8a62-478f37fcd7ed" />


### 💸 Transfer page
After logging in, users can send money to their friends.
* The sender’s balance is verified before the transfer.
* If successful, balances are updated and the transaction is saved.
* All transactions can be viewed in the “My Transactions” section.
<img width="650" height="398" alt="Transfer" src="https://github.com/user-attachments/assets/15606398-8571-421a-a540-6eafcf62d757" />

### 👤 Profile
On the profile page, users can:
* View their registered information,
* Update their password
<img width="653" height="350" alt="Profile" src="https://github.com/user-attachments/assets/d135f737-e962-4eb0-8eed-6b9311bc11c1" />

### 📞 Contact (Friends Management)
The contact page is used to manage friends inside the application.

Add a Friend:
* Users can add another user as a friend by entering their email address.
* The system checks in the database if this user exists and has an account.
* If the email does not exist → the user sees the message:
  * “This user does not exist or has no account yet.”
* If the user tries to add themselves → the system displays:
  * “You cannot add yourself as a friend.”

Remove a Friend:
* Users can also delete an existing friend from their list.
* The relationship is removed from the database, and the friend no longer appears in the contact and send money list.
<img width="650" height="188" alt="Contact" src="https://github.com/user-attachments/assets/2f243f52-45c4-4495-bdb9-d1e0dfe82f5b" />

### application.properties
In our application.properties file, we don’t write the database credentials directly.
Instead, we use environment variables like this:
```
spring.application.name=demo
spring.datasource.url={DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
server.port=5000
```
* Security: We don’t want to expose sensitive data (like database username and password) inside the code or in GitHub.
* Flexibility: Each developer or server can have their own values without changing the code. For example:
  * On your local machine, DB_USERNAME=root
  * On production, DB_USERNAME=prod_user

We simply define these variables in the environment (for example in IntelliJ → Edit Configurations → Environment Variables), and Spring Boot automatically replaces ${DB_USERNAME} with the real value.
This way, the application is portable, secure, and easy to configure in different environments (local, test, production).


### Unit Tests
This project includes unit tests to verify the behaviour of service classes in isolation.
For example, we have a unit test for the sendMoney functionality to ensure that money transfers work correctly between accounts. This test checks:

* The sender's balance decreases by the transfer amount.
* The receiver's balance increases by the transfer amount.
* A transaction record is saved with the correct amount and description.
* Both sender and receiver accounts are updated in the database.
* 
This ensures that the core logic of transferring funds behaves as expected without affecting the database directly (using mocked repositories).

```
 @Test
    void test_sendMoney_success(){
        BigDecimal amount = BigDecimal.valueOf(30);
        String description = "Payment";

        when(accountRepository.findByUserId(sender.getId())).thenReturn(senderAccount);
        when(accountRepository.findByUserId(receiver.getId())).thenReturn(receiverAccount);

        transactionServiceImpl.sendMoney(sender, receiver, amount, description, "Euro");

        assertEquals(new BigDecimal("70.00"), senderAccount.getBalance());
        assertEquals(new BigDecimal("80.00"), receiverAccount.getBalance());

        verify(transactionRepository).save(any(Transaction.class));

        verify(accountRepository).save(senderAccount);
        verify(accountRepository).save(receiverAccount);
    }
```

###  Integration Tests
This integration test ensures that the sendMoney feature works correctly with the actual database. It verifies:
* The sender's account balance decreases by the transfer amount.
* The receiver's account balance increases by the same amount.
* A transaction record is created and saved in the database.
* The transaction status is correctly set to SUCCESS.

By testing with the real repositories, we make sure that all parts of the money transfer process, updating accounts and storing transactions work together as expected.
   
```
    @Test
    public void createAPersonShouldReturnTrue() throws Exception {
        Person person = new Person("Thomas", "Anderson", "15 Street John Kennedy",
                "New York", "28000", "11-555-9999", "t.anderson@gmail.com");
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                        .post("/person")
                        .content(objectMapper.writeValueAsString(person))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();
        assertEquals("true", response.getContentAsString(StandardCharsets.UTF_8));
    }
```
   
### Jacoco test coverage on the service layer:

<img width="1012" height="183" alt="image" src="https://github.com/user-attachments/assets/cc5d52a6-4310-4a40-b578-371add774994" />

### Surefire Report
<img width="813" height="229" alt="sureifre" src="https://github.com/user-attachments/assets/cddbca1e-7984-4960-9752-9a7b802f7600" />
