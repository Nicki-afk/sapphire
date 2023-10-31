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


## Chat Entity Description for the Sapphire Project Documentation

### Overview:
The Chat entity represents a communication channel within the Sapphire messaging platform. It encapsulates the collective interactions between two or more users in a cohesive unit, providing the foundational structure for message exchanges. This entity is designed to accommodate various types of chats, such as one-on-one conversations , and group chats 

### Objective:
The primary aim of the Chat entity is to manage and facilitate real-time interactions among users. It serves as a repository for ongoing conversations, allowing for the grouping of messages, member management, and other communication-related functionalities.


| Field name | Data Type | Description | Relationship | 
|------------|-----------|-------------|--------------|
| chatID     | Long      | Unique identifier for each chat | PK |
| createdAt | Date      | Time the chat was created | |
| participantsChat | List< User >(2) | The default value will be 2 which will mean one on one chat | @OneToMany | 
| chatType | Enum ( GROUP_CHAT , ONE_TO_ONE_CHAT , SYSTEM_CHAT ) | Chat type | | 
| notifications | List< Notification > | notifications in a specific chat, for example new messages | @OneToMany | 
| chatStatus | Enum ( ARHIVED , ACTIVE , DELETED)  | chat status | | 
| messages | Message | message list | @OneToMany | 


## Notification Entity Description for the Sapphire Project Documentation

### Overview:
The Notification entity acts as an alert mechanism within the Sapphire messaging platform. It notifies users about various events and activities related to their account, conversations, or the system in general. This can range from informing a user about a new message to system updates or friend requests.

### Objective:
The main purpose of the Notification entity is to ensure that users stay informed and updated about pertinent events on the platform. By providing timely alerts, the Notification entity aids users in staying engaged, responsive, and up-to-date with their communications and other relevant happenings within the Sapphire messaging ecosystem.

| Field name | Data Type | Description | Relationship | 
|------------|-----------|-------------|--------------|
| notId      | Long      | Unique notification ID | PK | 
| typeNoti   | Enum (SYSTEM , MESSAGE , SECURITY ) | Notification type | |
| content | String | content notification | | 
| associatedChat | Chat | Refers to the chat where the event occurred, if applicable. | @ManyToOne | 
| associatedUser | User | Refers to the user related to the event, if applicable. | @ManyToOne | 
| createdAt | Date |  Time when the notification was generated | | 
| sentAt    | Date | Time when the notification was sednt |  | 
| receivedAt | Date | Time when the notification was recive  |  |
| read      | boolean | Was the notification read |  | 
| imageNotification | URL | if the notification has an image, it will be set to this field; if not, the default image will be used | | 

## Message Entity Description for the Sapphire Project Documentation

### Overview:
The Message entity plays a pivotal role in the Sapphire messaging platform, symbolizing individual pieces of communication exchanged between users. Each Message instance captures the essence of a single communicative intent, whether it's text, media, or any other form of digital content. This entity not only stores the actual content but also contextual details surrounding the exchange, offering a comprehensive view of the conversation's progression.

### Objective:
The primary function of the Message entity is to chronicle the flow of communication within chats. It acts as a vessel carrying information from one user to another, ensuring that every piece of data, whether it's a simple greeting or a multimedia file, is accurately logged and delivered. The entity provides the structural basis for representing user interactions, supporting features like message ordering, timestamping, and content type differentiation.



| Field name | Data Type | Description | Relationship | 
|------------|-----------|-------------|--------------|
| msgId      | Long      | unique message id | PK | 
| sentAt     | Date      | Time when the notification was sent | | 
| receivedAt | Date      | Time when the notification recived  | |
| senderId   | User      | The user who sent the message | @ManyToOne | 
| recipientId| User      | The user who received the message | @ManyToOne |
| content  | String | Message content | | 
| files    | List< URL >  | file addresses, file address that is saved on the server or that is saved in IPFS | | 
| isDelete | boolean | This flag indicates whether the message has been deleted. A message can only be deleted for everyone | | 
| isRead | boolean | indicates whether the message has been read | | 
| isDelivered | boolean | displays whether the message was delivered  | | 
| chat  | Chat | Indicates which chat the message belongs to | @ManyToOne | 


## BetaTestKey Entity Description for the Sapphire Project Documentation


### Overview:
In the intricate framework of the Sapphire project, the BetaTestKey entity emerges as a crucial element for both quality assurance and user engagement. This cryptographic string serves as an exclusive gateway, offering a selected subset of users the privilege of exploring the beta version of the platform. More than just a digital "golden ticket," the BetaTestKey is imbued with meta-information related to usage statistics, feature access, and the beta-testing timeline.

### Objective:
The multifaceted objective of the BetaTestKey is clear. Its primary purpose is to regulate and manage entry into the beta stage of the Sapphire project, ensuring that only qualified testers gain the ability to engage with nascent features. By creating this environment of exclusivity, the team can capture valuable user data in a more controlled setting, refining functionalities based on actual user experiences while mitigating the risks associated with exposing a broader user base to potential flaws or incomplete capabilities.

The BetaTestKey entity also contributes to the segmentation of different types of users—such as enthusiasts, newcomers, or experts—providing the development team with insights into how various user categories interact with the Sapphire environment. This nuanced, data-driven approach serves as an invaluable tool for optimizing the user experience before a full-scale public launch.



| Field name | Data Type | Description | Relationship | 
|------------|-----------|-------------|--------------|
| createdAt    | LocalDateTime | Key creation time | | 
| expirAt    | LocalDateTime | Key expiration time | |
| isUsed     | boolean | Shows whether the key has been used by another user. If yes then the user must use a different key | | 
| activatedAt | LocalDateTime | Time to activate key | |    
| key  | String | Directly cham key 128 characters long | | 
| user | User | User who uses this key | @OneToOne  |
























