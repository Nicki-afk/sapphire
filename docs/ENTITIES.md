## Final Review of Entities in the Sapphire Project: Integration, Optimization and Final Implementation



### Description:
This document is intended for a comprehensive analysis and final representation of all key entities involved in Project Sapphire. It covers the phases of integration, optimization, and final implementation of each entity, providing detailed data and conclusions that serve as a foundation for the project's subsequent development or completion.

### Purpose:
The primary aim of this document is to offer a complete and exhaustive overview of all entities to ensure a clear understanding of their roles, interrelationships, and contributions to the overall success of Project Sapphire. The document seeks to identify opportunities for further optimization, highlight areas of risk, and serve as a basis for strategic planning.



## User Entity Description for the Sapphire Project Documentation

### Overview:

The User entity serves as the cornerstone of the Sapphire Project, embodying each individual who interacts with the messaging platform. This entity contains vital personal and authentication information necessary for account creation, sign-in procedures, and user identification within the system.

### Objective:

The primary goal of maintaining a robust User entity is to facilitate seamless user management, including but not limited to user authentication, personalization, status tracking, and interfacing with other entities like Messages, Chats, Contacts, and Notifications.


| Field Name | Data Type | Description | Relationship | 
|------------|-----------|-------------|--------------|
| id         | Long      | Unique identifier for each user | PK |
| username   | String    |  Username chosen by the user | Unique | 
| wallet     | String    | User cryptowallet address  | Unique |
| hashUserFile | String  | User other data which saved in IPFS | Unique | 
| E-mail | String | User email for standard sing up  | Unique | 
| password | String | User password Hash | | 
| onlineStatus | Enum | User online status (DEPARTED, ONLINE , WAS_RECENTLY ) | |
| chats    | Chat  | User chat list | @OneToMany | 
| betaKey  | BetaTestKey | User secret beta key , to use applications in early access | @OneToOne | 
| userAvatar | UserAvatar | User avatar photo | @OneToOne | 
| blackList | UserBlackList | List of user's blocked contacts | @OneToMany | 
| notifications | Notification | User Notifications | @OneToMany | 
| lang | LangEnum | User language | | 
| subscription | String |  A small block about yourself that can hold 200 characters | | 










