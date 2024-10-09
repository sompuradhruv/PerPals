# PetPals - Pet Adoption Platform

PetPals is a software system designed to facilitate the adoption of pets from shelters and rescue organizations. It serves as a digital marketplace where potential adopters can browse and select pets, shelters can list available pets, and donors can contribute to support animal welfare.

## Project Structure

The project follows a well-organized directory structure and implements various OOP principles, exception handling, and database interaction. Below is the structure followed:

```
📁 src/
   ├── 📁 entity/
   │   └── Pet.java
   │   └── Dog.java
   │   └── Cat.java
   ├── 📁 dao/
   │   └── PetShelterDAO.java
   └── 📁 exception/
   │   └── InvalidPetAgeException.java
   │   └── InsufficientFundsException.java
   └── 📁 util/
   │   └── DBPropertyUtil.java
   │   └── DBConnUtil.java
   └── 📁 Main/
       └── MainModule.java
```

### Entity Classes (Model)
- `Pet.java`: Represents a pet with attributes like name, age, and breed.
- `Dog.java` & `Cat.java`: Inherit from Pet and add specific attributes such as dog breed and cat color.

### Data Access Objects (DAO)
- `PetShelterDAO.java`: Manages CRUD operations related to shelters and pets available for adoption.

### Exception Handling
- `InvalidPetAgeException.java`: Custom exception thrown when a pet's age is invalid.
- `InsufficientFundsException.java`: Exception thrown when a donation amount is less than the minimum allowed.

### Utility Classes
- `DBPropertyUtil.java`: Contains methods to retrieve connection strings from property files.
- `DBConnUtil.java`: Establishes a connection to the database.

### Main Module
- `MainModule.java`: Demonstrates the functionalities in a menu-driven application, such as listing pets, adding pets, handling donations, and managing adoption events.

## Features

1. **Object-Oriented Design**
   - Uses OOP principles including inheritance, abstraction, and encapsulation.
   - Pet classes are modeled to represent different animals like dogs and cats.
   
2. **Exception Handling**
   - Handles various exceptions, such as invalid pet age, insufficient donations, and file handling issues.
   - Custom exceptions like `AdoptionException` ensure robust error handling during the adoption process.

3. **Database Interaction**
   - Establishes connection to the database to handle pet listings, donations, and adoption event management.
   - `DBConnUtil.java` and `DBPropertyUtil.java` streamline database connectivity.

4. **Donation Recording**
   - Handles both cash and item donations, with attributes for donation type, amount, and dates.

5. **Adoption Events**
   - Supports management of adoption events, including registering participants and hosting events.

## How to Run

1. **Clone the repository**:
   ```bash
   git clone https://github.com/sompuradhruv/petpals.git
   ```

2. **Database Setup**:
   - Configure the database connection in the `DBPropertyUtil.java` file.
   - Run the SQL scripts included in the `db` folder to create the required tables for pets, donations, and adoption events.

3. **Compile and Run**:
   - Navigate to the project directory and compile the code.
   ```bash
   javac -d bin src/**/*.java
   ```
   - Run the main module to launch the application.
   ```bash
   java -cp bin MainModule
   ```

## Requirements

- Java 8+
- MySQL or any relational database
- JDBC Driver

## Author

- **Dhruv Sompura**

```

