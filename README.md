# --- ATMMachine Java Project ---

*Updated Nov 25*

This is a simulated ATM bank machine, developed in Java, as part of my M.Sc. Software Development degree course.

It was previously created in class, but I'm now using it to test my CI/CD linux server
pipeline. I will be adding further revisions to the program using git control and developing the ATM as I learn more in
the Java programming module.

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