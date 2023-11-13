## Technology Stack and Architecture of Sapphire Messenger


>[!NOTE]
> Focus on Key Technologies: The primary objective of this document is to provide a high-level understanding of the core technologies and frameworks that form the backbone of Sapphire Messenger. It focuses on the most critical components that define the architecture and functionality of the application.In summary, this document serves as a general guide to the technology stack of Sapphire Messenger, outlining the key technologies and frameworks. It is not an exhaustive list of all the components or a detailed guide to their implementation within the project. The document is designed to provide a clear and concise understanding of the technological underpinnings of the application, while acknowledging the dynamic and potentially confidential aspects of software development.



![Alt text](https://github.com/devicons/devicon/blob/master/icons/java/java-plain.svg)



This document provides a comprehensive overview of the technology stack and architectural framework utilized in the development of Sapphire Messenger, a secure and private messaging application. The document highlights the strategic choice of technologies and frameworks that align with the core principles of security, efficiency, and user privacy.



Key components of the technology stack include:

- **Java**: Chosen for its portability, security features, high performance, and maturity, Java forms the backbone of the application, ensuring cross-platform compatibility and robustness.

- **Spring Framework**: Utilized for its powerful suite of tools, including Spring Boot for easy application setup, Spring Data for efficient data handling, Spring Security for robust authentication and authorization, and Spring Cloud for building scalable distributed systems. Additionally, Spring Native is employed to enhance performance through native compilations.

- **IPFS**: Incorporated for its decentralized file storage capabilities, ensuring data reliability and privacy. It plays a pivotal role in managing data storage in a secure and distributed manner.

- **Data & Reddis & PostgreSQL**: IPFS: Incorporated for its decentralized file storage capabilities, ensuring data reliability and privacy. It plays a pivotal role in managing data storage in a secure and distributed manner.

- **Additional Technologies**: The document also covers the use of WebSocket for real-time communication, Docker and Kubernetes for efficient orchestration and scalability, and Prometheus and Grafana for comprehensive monitoring and maintaining high service availability.




Overall, this document outlines the technological foundation of Sapphire Messenger, emphasizing the choices made to ensure a secure, efficient, and user-focused messaging experience.


>[!IMPORTANT]
> This document may be changed and rewritten depending on the functionality and rationality of using technology. Also, not all technologies, libraries, modules presented here are used in the messenger now, but they will be integrated into the consequences


### Development in Java

Java is an optimal choice for developing Sapphire Messenger for several key reasons:

- **Portability and Platform Independence**: Java applications can run on any operating system, providing easy portability and availability of the messenger across various devices.
- **Security**: Java has built-in security features, which is especially important for a messenger focused on confidentiality.
-**High Performance**: Combined with JVM optimizations, Java offers high execution speed, critical for efficient operation of the messenger.
- **Maturity and Reliability**: Java is a mature language with a robust ecosystem, extensive knowledge base, and community support, contributing to the project's stability.


### Using Spring Framework

Spring Framework provides a powerful set of tools for developing robust and scalable applications:

**Core Spring Stack:**
1. **Spring Boot**: Simplifies the creation of stand-alone, production-grade applications on Java.
2. **Spring Data**: Facilitates convenient access to data through repository abstractions.
3. **Spring Security**: Offers comprehensive solutions for authentication and authorization, crucial for protecting confidential messages.
4. **Spring Cloud**: For building distributed and easily scalable systems.
5. **Spring Messaging** : The Spring framework has many ways of communicating and exchanging messages. We will use the best way to achieve our goals. This could be like **STOMP** or **Spring AMQP**.
5. **Spring Native for Performance**: Using Spring Native will enhance application performance, allowing the app to be compiled into a native image, reducing startup time and memory consumption.



### IPFS (InterPlanetary File System)

IPFS is a distributed file storage system designed to create a permanent and decentralized method of storing and sharing data. In the context of Sapphire Messenger:

1. **Decentralization**: Eliminates a single point of failure and centralized control over data.
2. **Reliability**: Data is replicated across the network, increasing availability and resilience to failures.
3. **Privacy**: Combined with encryption, IPFS ensures that data is accessible only to intended recipients.


### Data & PostgreSQL

The Data & PostgreSQL section of this document outlines the rationale behind choosing PostgreSQL as the primary database management system for Sapphire Messenger and how it aligns with the application's data handling requirements. This section underscores PostgreSQL's relevance and advantages in managing the complex and sensitive data inherent in a secure messaging application.

#### Why PostgreSQL is the Optimal Choice for Sapphire Messenger

1. **Advanced Data Security Features**: PostgreSQL offers robust security features such as strong access controls, role-based access management, and support for SSL encrypted connections. These features are crucial for protecting sensitive user data and messages in a privacy-focused messaging app like Sapphire Messenger.

2. **High Performance and Scalability**: Known for its high performance, PostgreSQL can efficiently handle large volumes of data with quick response times, which is essential for a messaging application that needs to manage a significant amount of concurrent user interactions and data exchanges.

3. **Reliability and Data Integrity**: PostgreSQL has a strong reputation for reliability and data integrity. It supports ACID (Atomicity, Consistency, Isolation, Durability) properties, ensuring that the database transactions are processed reliably, which is paramount for maintaining the integrity of user messages and data.

4. **Support for JSON and Full-Text Search**: As a modern messaging application, Sapphire Messenger can benefit from PostgreSQL’s support for JSON data types and powerful full-text search capabilities, allowing for efficient storage and querying of diverse message formats and content.

5. **Extensibility and Customization**: PostgreSQL is highly extensible, meaning that it can be customized and extended with new functions, data types, and more to fit the specific needs of Sapphire Messenger, such as implementing custom encryption functions or data partitioning strategies.

6. **Community and Ecosystem**: PostgreSQL comes with a strong, active community and a wide range of tools and extensions. This ecosystem can provide valuable support and resources for ongoing development and maintenance of the database aspects of the application.

7. **Open Source and Cost-Effectiveness**: Being open-source, PostgreSQL offers cost advantages and avoids vendor lock-in, which is particularly beneficial for an independent project like Sapphire Messenger that values flexibility and adaptability.


### Data & Reddis


Redis, known for its high performance and flexibility, serves as an in-memory data store and plays a crucial role in several aspects of the application.

#### Key Uses of Redis in Sapphire Messenger

1. **Caching**: Redis is primarily used for caching frequently accessed data, reducing the load on the primary database and improving response times. This is particularly useful for data that does not change often but is read frequently, such as user profiles or frequently accessed messages.

2. **Session Management**: Redis can efficiently handle user sessions, especially in a distributed environment. Its fast read and write capabilities make it ideal for managing session information, ensuring quick access and a smooth user experience.

3. **Real-Time Communication**: Given its high performance, Redis is well-suited for real-time operations. It can be used to facilitate real-time message delivery and notifications in the messenger, ensuring that users receive immediate updates.

4. **Storing Authorization Tokens**: Redis is an excellent choice for storing authorization tokens. Its fast access speeds make it ideal for quickly validating tokens during user authentication and authorization processes. This is critical for maintaining a secure and efficient authentication flow.

5. **Pub/Sub Messaging**: Redis supports Pub/Sub messaging patterns, which can be used for broadcasting messages to multiple subscribers in real-time. This feature can be leveraged for implementing features like group chats or event notifications within the messenger.

6. **Scalability and Performance**: Redis, being an in-memory database, offers exceptional performance and scalability. This is vital for Sapphire Messenger as it needs to handle a large volume of simultaneous connections and data transactions.

7. **Persistence Options**: While Redis is an in-memory database, it also offers persistence options. This means that critical data can be stored in a non-volatile manner, ensuring data durability and preventing loss in case of a system failure.

**Conclusion**

The integration of Redis into the application's architecture significantly contributes to its performance, scalability, and overall user experience, all while maintaining robust security standards, especially in authentication processes. Redis’s versatile capabilities align well with the technical and operational demands of a modern, secure messaging platform like Sapphire Messenger.


### Testing & H2DB & JUnit 5/4

This section of the document delves into the testing strategy for Sapphire Messenger, highlighting the use of H2DB, JUnit 4/5, Mockito, and other testing tools to ensure the quality, reliability, and performance of the application.

JUnit 4 and JUnit 5: Benefits of Using Both Versions

**JUnit 4:**

1. Stability and Familiarity: JUnit 4 is a well-established and widely used testing framework, offering stability and a familiar environment for developers who have been using it for years.
Broad Compatibility: It is compatible with a wide range of tools and libraries, ensuring smooth integration with existing testing environments.

#### JUnit 5:

2. Advanced Features: JUnit 5 provides more advanced features like nested tests, parameterized tests, and more expressive assertions, enhancing the efficiency and clarity of tests.
Modularity: Its modular architecture allows for more flexibility and better separation of concerns in test design.
3. Parallel Execution: JUnit 5 supports parallel execution of tests, which can significantly improve the speed of the testing process.

Using both JUnit 4 and JUnit 5 allows developers to leverage the strengths of both versions, ensuring comprehensive and efficient testing.

#### Mockito for Mocking and Stubbing

1. Behavior Testing: Mockito is used for creating mock objects and stubbing, allowing the testing of interactions between classes without relying on their external dependencies.
2. Isolation of Tests: By mocking external dependencies, Mockito ensures that tests are isolated, making them more reliable and easier to understand.

#### H2 Database for Testing

3. In-Memory Database: H2DB, an in-memory database, is utilized for testing, providing a lightweight and fast environment for running database-related tests without the need for a physical database.
4. Ease of Configuration and Cleanup: H2DB can be easily set up and torn down, making it ideal for integration tests where database state needs to be controlled and reset between tests.
5. SQL Compliance and Compatibility: H2DB's SQL compliance ensures that the SQL queries used in tests are compatible with the main PostgreSQL database, reducing the risk of discrepancies between test and production environments.

#### Additional Testing Tools

Integration with Spring Test: This integration provides useful annotations and utilities for testing Spring applications, simplifying the setup and execution of tests involving Spring components.
Code Coverage Tools: Tools like JaCoCo are used to measure code coverage, ensuring that a significant portion of the codebase is covered by tests, which is critical for identifying untested or potentially problematic areas.

### Security 

The Security section of this document focuses on detailing the encryption algorithms and security frameworks employed in Sapphire Messenger to ensure the highest levels of data privacy and protection against unauthorized access. This part of the document outlines the strategic approach to security, emphasizing the selection of reliable and efficient encryption algorithms and the use of Spring Security for robust authentication and authorization.

#### Encryption Algorithms

- **AES** (Advanced Encryption Standard): AES is a widely used symmetric encryption algorithm known for its speed and security. It's suitable for encrypting user messages and data at rest. We plan to use AES-256, which provides a high level of security without compromising performance.

- **RSA** (Rivest–Shamir–Adleman): As an asymmetric algorithm, RSA is used for securely exchanging encryption keys over the network. Its ability to provide secure key exchange is vital in a messaging application where data confidentiality is paramount.

- **ECDH** (Elliptic Curve Diffie-Hellman): For establishing secure client-to-client communication, ECDH will be utilized for its efficiency in generating shared secret keys between users. This method is known for its strong security with relatively shorter key lengths, enhancing both performance and security.

- **TLS** (Transport Layer Security): To secure data in transit, TLS will be implemented. This ensures that all communication between the client and server is encrypted and secure from eavesdropping and man-in-the-middle attacks.

#### Spring Security for Comprehensive Protection

In addition to these encryption algorithms, Spring Security plays a crucial role in the overall security strategy:

1. **Authentication and Authorization**: Spring Security provides a robust and customizable framework for managing user authentication and authorization, ensuring that only authorized users can access their messages and personal data.

2. **Protection Against Common Vulnerabilities**: Spring Security helps safeguard the application against common security vulnerabilities such as cross-site request forgery (CSRF), session fixation, and more.

3. **OAuth 2.0 and JWT (JSON Web Tokens)**: For a stateless and scalable authentication mechanism, OAuth 2.0 in combination with JWT will be used. This approach is particularly effective for RESTful APIs and mobile applications, where managing user sessions and tokens securely is crucial.



