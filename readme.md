# Wallet Service

## Live Version - https://wallet-ms.herokuapp.com

### Installation

- git clone https://github.com/paradigmwit/wallet_microservice.git
- cd wallet_microservice
- mvn spring-boot:run
- profit?

**Note** - the H2 database has been bundled as a file with the code for easy deployment. There will not be synchronization of data across muliple instances of the application. 

---

### Endpoints

The wallet service has two resources - 
1. wallet
2. player

#### Wallet
The wallet endpoint provides the functionality -
- get wallet balance
- credit amount to wallet
- debit amount from wallet
- get wallet transactions history

#### Player
The player endpoint provides the functionality -
- get list of players
- get player information
- create player

---

### API 
The api is available at -
http://localhost:8080/swagger-ui.html 

---

### Data Strategy
A relational database has been employed in this implementation.
The reason is that having a traditional relational database would make it easier to manage relationships using reference keys.

A different strategy would be to have an event driven design relying on topics. This would give us the flexibility of packaging the databse with the microservice and publishing changes to the topics for subscribers to act on

#####  Data model

![ER Diagram](https://github.com/paradigmwit/wallet_microservice/blob/master/er.JPG)

---

### Improvements 

- Use Postgres for better scaling. Currently behaving as Database per Instance without synchronization.
- Logging to file
- Automatic Test coverage
- Exception message in JSON response, messages are getting lost at the moment
- Currency Code to be populated from an enum of supported currencies
- API documentation for models, more detail 
- Use ValidatorService across the application
