# Calculator Project

This project provides a simple calculator functionality that evaluates mathematical expressions with a single operator and two operands. The supported operations are addition, subtraction, multiplication, division, and exponentiation.

## Table of Contents

- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Building](#building)
- [Usage](#usage)
- [Testing](#testing)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 8 or higher
- Maven

### Decompressing the Project

- Locate the `calculator-project.zip` file.
- Decompress the ZIP file to a location of your choice on your local machine using your preferred decompression tool.
- Navigate to the root directory of the decompressed project files in a terminal or command prompt.

```bash
cd path/to/calculator-project
```
 
### Building

- Build the project using Maven.

```bash
mvn clean package
```
This will compile the code, run the tests, and produce a JAR file in the `target` directory.


## Usage

- Run the JAR file using the following command.

```bash
java -jar target/calculator-project-1.0-SNAPSHOT.jar
```

- Enter a mathematical expression with a single operator and two operands. The supported operations are addition, subtraction, multiplication, division, and exponentiation.

```bash
Enter a mathematical expression: 2 + 2
```

- The result of the expression will be displayed.

```bash
Result: 4.0
```

- Enter `exit` to exit the program.

```bash
Enter a mathematical expression: exit
```

## Testing

- Run the tests using Maven.

```bash
mvn test
```
