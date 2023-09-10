# Dateapp-backend
Backend of dateapp made in Spring.

API for a Tinder-like dating application.
Documentation available at: "http://localhost:8080/swagger-ui/index.html"
The application allows swiping left to reject a profile and right to like a profile.
If two profiles mutually like each other, a match is created, and a chat is initiated between the two users.
On the homepage, there is a user panel where you can view all likes, matches, and messages.
Additionally, real-time communication is possible in the chat.
In the profile settings, users can change their location for profile visibility, update personal information, change their password, delete their profile, and log out.
Used technologies and frameworks:

* Spring Boot Devtools
* Liquibase
* H2 Database
* Spring JPA
* Spring Security
* Hibernate
* JUnit
* Mockito
* Jakarta Validation
* Json Web Token
* Open API
* Project Lombok

How to start?

Download the repository.
* Install Maven as shown in the instructions at: https://maven.apache.org/install.html.
* Install Java 19 JDK as shown in the instructions at: https://thejavaguy.org/posts/015-how-to-install-jdk-19-on-windows/.
* Open Windows PowerShell or another command line interface.
* Navigate to the root directory of the application.
* Execute the command "mvn spring-boot:run".
* Register, log in, and start using the application.
* Appilcation should be available on http://localhost:8080/
* Default login credentials in src/main/resources/changelogs/data/credentials.txt
* Contact If You have any questions, send me a message at mariuszo19@onet.pl
