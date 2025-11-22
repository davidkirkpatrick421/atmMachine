# --- ATMM Banking System - Java Project ---

*Updated 22 Nov 25*

This project evolved over the course of the semester, starting as basic Java logic and rudimentary UI. The previous
iteration was a re-design of OOP and secure encapsulation. It has now been refactored into a Springboot REST API
microservice. The Java program is now hosted on a home Linux server, running on a re-purposed MacBook, and accessed via
a web browser. The program is interacted with via Swagger UI which shows the REST API endpoints and gives a live demo of
the functionality live.

##### Tech stack:

- Java, IntelliJ IDEA
- Linux network server deployment
- Spring Boot, Maven, PostgreSQL (SQL )
- Docker, Jenkins, Gitea
- PiHole DNS, NginX proxy, Cloudflare tunnel, Swagger UI

## **Live Demo:** https://atm.dkirkpatrick.co.uk/swagger-ui/index.html

*Uses a persistent PostgreSQL database so a new account should be used for each demo*


### Instructions for Demo:

1. Follow the demo link above.
2. Go to 'POST /api/atm/accounts' and click 'Try it out'.
3. Copy this JSON into the request body - Choose your own PIN and initial balance or use the defaults:

   {
   "pin": 1234,
   "balance": 100.00
   }
4. Click 'Execute'. **_Copy or remember the ID from response - this is your account ID._**
5. 
5. Go to 'GET /api/atm/accounts/{id}' and click 'Try it out'.
6. Paste your account ID into the path parameter and click 'Execute'. You should see your account details.

7. Go to 'POST /api/atm/accounts/{id}/withdraw' and click 'Try it out'.
8. Paste your account ID into the path parameter and enter a withdrawal amount into the request body.
9. Click 'Execute'. You should see your new balance.

10. Test validation by entering an incorrect PIN or a negative amount.

11. Go to 'POST /api/atm/accounts/{id}/deposit' and click 'Try it out'.
12. Paste your account ID into the path parameter and enter a deposit amount into the request body.
13. Click 'Execute'. You should see your new balance.

### System Architecture:
Replaces a command line interface with a web browser interface with functionality to create, read, update and delete
ATM accounts in persistent database. Refactored structure allows for easy addition of new features and better
maintainability, as well mirroring a more realistic banking system. All logic is @transactional, so no data is lost if
an error occurs and the persisten database is @versioned to allow for concurrency control so that multiple users can
access the same data simultaneously and repeat transactions are prevented.
* API Layer - Handles HTTP requests and validation of input data.
* Service Layer - Handles business logic and transactions.
* Repository Layer - Handles database interactions and persistence. Usses Spring Data JPA to abstract SQL queries.
* Database - Persistent PostgreSQL container on the server that stores accounts and transactions.

<br>
</br>
<details>
<summary>Legacy Project README</summary>


**Video Demo:** https://youtu.be/Y_87BCSaLD4

##### Tech stack:

- Java, IntelliJ IDEA
- Linux network server deployment
- Spring Boot, Gradle _(future implementation)_
- Docker, Jenkins, Gitea
- PiHole DNS, NginX proxy, Cloudflare tunnel _(future implementation)_

*Demo shows terminal ssh into the home server and running the program, showing current functionality*

_**I attempted to host the demo live on the server and use cloudtunnel to allow secure public access, but it didn't
work,
due to java command line programs not being compatible with runtime in browser as I learnt._

*I will be attempting to develop this and implement a REST API microservice in the future so that the java program can
be hosted on a server and accessed via a web browser.**.*

Default PIN is 1234 and account balance is £100.

### **Current Features**

- User PIN authentication - encapsulated in Account class and set in main method constructor
- Security: 3-attempt limit on PIN entry and forced exit if incorrect PIN entered
- Basic menu system with repeating menu loop with updated UI
- Balance checking and display
- Deposit/withdrawal with validation (cannot withdraw more than balance)

#### **Future Implementations**

- [x] Refactoring and setup of better OOP design.
    - [x] Added Account class with better encapsulation and SRP of methods
    - [x] Encapsulation of classes and methods like PIN auth and validation for security.
    - [x] Created separate methods for encapsulated functionality.
    - [x] Validation of input for deposit and withdrawal.
    - [x] Create a separate class for the ATM menu system.
    - [x] Create a separate class for the ATM system — separate main method.
- [ ] Add a back button to the menu system to return to the previous menu.
- [ ] Creating JUnit tests.
    - [x] Unit tests for Account class.
    - [ ] Unit tests for ATM class.
    - [ ] Unit tests for ATMMachine class.
- [ ] More robust error handling for edge cases like negative balance, invalid input, etc.
    - [x] Added error handling for negative balance.
    - [x] Added error fix for null exception in menu options.
    - [ ] Fix the error for letters in pin input.
- [ ] Refactoring and setup of REST API microservice deployment using Docker and Spring Boot.
- [ ] Adding arrayList for transaction history? or other relevant data.
- [ ] Consider ways of implementing a GUI and improving the user experience (arrow keys to navigate, etc.)
- [ ] Consider how to integrate with a database for account data and transaction history.

</details>