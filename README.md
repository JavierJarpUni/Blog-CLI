# Blog-CLI

A project for managing blog content. This project uses Java with Maven and includes basic configurations to support future enhancements and multiple build tools.

## Features

- Write, update, and delete blog posts through CLI commands.
- Maven-based project structure with proper dependency management.
- Preconfigured for common IDEs and build systems (Maven/Gradle).
- Example templates and starter code for future extensions.

## Getting Started

### Prerequisites

- Java 8 or newer installed
- Maven installed (or Gradle if you choose to switch)

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/blog-cli.git
   cd blog-cli
   ```

2. Build the project with Maven:

   ```bash
   mvn clean install
   ```

### Running the Application

Run the application using Maven:

```bash
mvn exec:java -Dexec.mainClass="com.blog.Main"
```

(Ensure that the main class is correctly set in your `pom.xml` or update the command above to use the appropriate main class.)

## Development

### Project Structure

- `src/main/java`: Java source code and application logic.
- `src/test/java`: Unit tests.
- `pom.xml`: Maven build configuration.
- `.gitignore`: Git ignore file for common build artifacts and IDE files.

### IDE Setup

The project includes configuration files for popular IDEs. Open the project in your favorite IDE:

- **IntelliJ IDEA** – open the project folder directly.
- **VS Code** – use the included `.vscode` directory for workspace settings.
- **Eclipse/NetBeans** – import as an existing Maven project.

## Contributing

Contributions are welcome! Feel free to fork the repository and submit a pull request with your improvements.

## License

This project is licensed under the MIT License – see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Thanks to the Maven community for the robust build tools.
- Inspiration from various open-source CLI projects.

Happy coding!
