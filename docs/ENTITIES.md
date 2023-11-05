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
| createAt   | Date      | Date when account has been created | | 
| E-mail | String | User email for standard sing up  | Unique | 
| password | String | User password Hash | | 
| onlineStatus | Enum | User online status (DEPARTED, ONLINE , WAS_RECENTLY ) | |
| chats    | List < Chat >   | User chat list | @OneToMany | 
| userGroups | List < GroupChat > | Stores all groups created by this user, that is, groups whose owner is this user | @OneToMany | 
| participatesIn | List < ChatUser > | Displays how many groups this user is a member of. These are the groups in which the user is a member | @OneToMany | 
| betaKey  | BetaTestKey | User secret beta key , to use applications in early access | @OneToOne | 
| userAvatar | UserAvatar | User avatar photo | @OneToOne | 
| blackList |List < BlockedUser >  | List of user's blocked contacts | @OneToMany | 
| notifications | List < Notification > | User Notifications | @OneToMany | 
| lang | LangEnum | User language | | 
| subscription | String |  A small block about yourself that can hold 200 characters | | 
| rols | Enum( USER , ADMIN , MODERATOR , DEVELOPER) | defining user roles | | 
| rights | Enum(READ_MSG , SENT_MSG , CREATE_GROUP_CHAT , ACCOUNT_REFACTORING , BLOCK_USERS , DISBLOCK_USERS ,READ_USER_NOTIFICATION , FEEDBACK , CHANGE_MSG , DELETE_MSG , SENT_FILES ... ) | defines user rights | | 




## BlockedUser Entity Description for the Sapphire Project Documentation

### Overview:
The BlockedUser entity is a fundamental component of the Sapphire communication system, representing the mechanism through which users can restrict communications from other users deemed undesirable. This entity serves as a record of such actions, encapsulating the details of the users involved in the blocking relationship.

### Objective:
The BlockedUser entity’s main objective is to maintain a secure and comfortable environment for the users by allowing them to block any unwanted interaction. By creating an instance of BlockedUser, the system can effectively prevent blocked individuals from contacting the blocker via direct messages, calls, or from viewing their online status and activity. It acts as a shield, providing users with control over their interactions on the platform.


| Field Name | Data Type | Description | Relationship | 
|------------|-----------|-------------|--------------|
| id         | Long      | unique id BlockedUser | PK | 
| userId     | User      | Blocked User id | @ManyToOne | 
| dateToBlocked | LocalDateTime |   date when the user was blocked | | 
| subscription | String  | Some information about why the user was blocked  | | 


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
| chatType | Enum ( GROUP_CHAT , ONE_TO_ONE_CHAT , SYSTEM_CHAT ) | Chat type. The default value for this entity will be ONE_TO_ONE_CHAT | | 
| notifications | List< Notification > | notifications in a specific chat, for example new messages | @OneToMany | 
| chatStatus | Enum ( ARHIVED , ACTIVE , DELETED)  | chat status | | 
| messages | List < Message > | message list | @OneToMany | 


## GroupChat Entity Description for the Sapphire Project Documentation


### Overview:
The GroupChat entity is a cornerstone within the Sapphire communication platform, representing a digital space where multiple users can interact simultaneously. It is a virtual room where ideas, messages, and media are exchanged in a collective environment. Each GroupChat instance embodies a specific topic, project, or social interaction, equipped with tools and features that facilitate group dynamics and collaborative efforts.

### Objective:
The principal aim of the GroupChat entity is to provide a structured and secure environment for multi-user discussions and exchanges. It serves as a hub for collaborative work, social interaction, and information sharing, allowing users to engage in real-time communication and file sharing. The entity is designed to maintain the integrity and chronological order of conversations, support various media types, and allow for user roles and permissions to manage the group effectively.

| Field name | Data Type | Description | Relationship | 
|------------|-----------|-------------|--------------|
| chatID     | Long      | Unique identifier for each chat | PK |
| createdAt | Date      | Time the chat was created | |
| participantsChat | List< User >(1..10000) | The default value will be 2 which will mean one on one chat | @OneToMany | 
| groupName | String | Name group | | 
| groupPhoto | String  | Photo Group . IPFS link | | 
| subscription | String | a short description of what and who this group is for | |  
| owner | User | One group chat can only have one owner. This owner is indicated in this field | @ManyToOne | 
| moders | List < ChatUser > | is a list of moderators who can manage some group functions | @OneToMany |
| chatType | Enum ( GROUP_CHAT , ONE_TO_ONE_CHAT , SYSTEM_CHAT ) | Chat type. The default value for this entity will be GROUP_CHAT | | 
| notifications | List< Notification > | notifications in a specific chat, for example new messages | @OneToMany | 
| chatStatus | Enum ( ARHIVED , ACTIVE , DELETED)  | chat status | | 
| messages | List < Message > | message list | @OneToMany | 

>[!NOTE]
>This class will inherit from the Chat base class, and will use the  *[SINGLE_TABLE](https://www.baeldung.com/hibernate-inheritance#single-table)* inheritance strategy found in Hibernate. Thus, we can put both objects of the **Chat** type and objects of other types for which Chat is a superclass into the chats collection in the User object


## ChatUser Entity Description for the Sapphire Project Documentation


### Overview:
The ChatUser entity is a critical component in the Sapphire project's chat management architecture, defining the role and participation level of users within a chat context. This entity is the manifestation of a user within the chat ecosystem, imbuing them with the capacity to engage, contribute, and influence the flow of conversation. Each ChatUser instance delineates not just membership in a chat but also the individual's roles, permissions, and their functional scope within the group dynamics.

### Objective:
The primary function of the ChatUser entity is to enable effective management and hierarchical structuring within chat environments. It exists to assign roles, delineate permissions, and differentiate between general participants and those with elevated privileges, such as moderators or admins. This structure is essential for maintaining order, promoting constructive dialogue, and ensuring the smooth operation of the chat platform. It provides a clear governance model where responsibilities and capabilities are proportionate to the user’s role



| Field name | Data Type | Description | Relationship | 
|------------|-----------|-------------|--------------|
| id         | Long      | unque id  ChatUser | PK |
| userID     | User      | user ID to which this entity belongs  | @ManyToOne |
| chat       | GroupChat | ID of the chat this entity belongs to | @ManyToOne  |    
| joinDate  | LocalDateTime | join date | | 
| role      | Enum (USER , ADMIN , MODERATOR , DEVELOPER) | defining user roles | | 
| permissions | Enum(READ_MSG , SENT_MSG  , ADD_USERS , DEL_USERS   , CHANGE_MSG , DELETE_MSG , SENT_FILES , DEL_FILES , CHANGE_CHAT_STYLE )  | | 







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


## UserAvatar Entity Description for the Sapphire Project Documentation


### Overview:
Within the intricate ecosystem of the Sapphire platform, the UserAvatar entity stands out as a visual representation of a user's identity and presence. This digital icon, whether custom-uploaded or selected from a predefined set, serves as the first point of interaction and recognition among peers on the platform. More than just a static image, the UserAvatar encapsulates the user's chosen persona, offering a hint of their style, preferences, and sometimes even their mood.

### Objective:
The primary purpose of the UserAvatar entity is to personalize and enrich the user experience on the Sapphire platform. It provides users with a means to express themselves, setting them apart in a vast sea of digital identities. The avatar becomes especially crucial in forums, chatrooms, and any other interactive space within Sapphire, where visual cues can greatly enhance communication dynamics.




| Field name | Data Type | Description | Relationship | 
|------------|-----------|-------------|--------------|
| id         | Long      | Unique Image id | PK |
| userId     | User      | The user who owns the avatar | @OneToOne |
| hash       | String    |  link to image on IPFS network | | 
| defaultAvatar  | String  |  link to image on IPFS network | | 
| imgSize    | Integer   | Size of the image in bytes. Useful for storage management. | | 
| ImgFormat | String | File extension | | 
| lastModified | LocalDateTime | Photo update date | | 


























