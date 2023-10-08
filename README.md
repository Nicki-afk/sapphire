 ## Sapphire - A Secure and Private Messaging Service Based on IPFS



Sapphire is a modern messaging service, designed to ensure maximum security and user data privacy. Its unique feature is the use of the decentralized storage system, IPFS, to store message content, which ensures unparalleled censorship resilience and data protection.

## Technology Stack

**Java:** Sapphire  is developed in Java for several reasons:

- Scalability: Java offers tools and libraries that allow for efficient scaling of applications, making it ideal for a global messaging service.

- Security: Java has a multitude of security features and is regularly updated to address vulnerabilities.

- Portability: Java code is portable across different platforms and clouds, simplifying deployment and maintenance.

- Community: With a vast and active developer community, arising issues can be swiftly resolved, and access to a plethora of libraries accelerates development.



**IPFS:** [IPFS](https://docs.ipfs.tech/) is used for storing all bulky data, such as avatars, documents, multimedia, and messages. This ensures that user data is not centralized and is protected against any external threats.

## User Data

In our messenger, user data security is paramount. We store the minimum amount of data required for identification and account operation:

- User nickname
- Cryptowallet email address
- Registration date
- User status
- Profile signature
- Nicknames and identifiers of interlocutors and chats
- All other data, including avatars, profile descriptions,  and of course, messages, are stored in IPFS.

## Authorship

The idea and primary development belong to [gybernaty](https://github.com/TheMacroeconomicDao), and this project is a part of the [Gyber Social Platform](https://gyber.org/) community.

## Conclusion

Sapphire - is not just another messaging app. It represents a step forward in the messaging world where a user's privacy and security are paramount. Join us and become a part of this change!



## 🗒️ Version Plan 


In the new version of the server under the extension **0.0.5-alphaS** I plan to add the following elements : 

 - Full HTTP interaction. There will be a strictly defined range of requests that can be addressed. The HTTP interaction itself will also be modified, perhaps some parameters will be moved to the headers or parameters of the HTTP request
 - Full documentation will be compiled in **POSTMAN** for all available server addresses. The documentation will clearly explain how to access the server
- Full authorization will be implemented using a user signature from the **Metamask** network. The server will programmatically calculate and verify the user's signature, and authorize him
- Full data validation will be added during user registration. The user will be able to register correctly in the system
- The models that must be stored and retrieved from the database will be clearly defined. Models will have clearly defined fields that are required
- A complete restructuring of the folders in the project will be carried out. Classes will be placed in their own folders. Unnecessary folders will be deleted
- Reliable message transmission using the **STOMP** protocol will be ensured. As well as basic saving and retrieving messages from the database
- Test scripts will be written
- There will be a lot of code refactoring. The code will be optimized and fast. All previous errors and shortcomings will be corrected


### ❌  Disadvantages of the old versions

This block will provide the result of the analysis of previous versions of the server. Errors, bugs and shortcomings of previous versions will be described here.

- Extra imports. In many application classes, unnecessary imports were found that are not needed or used
- Folder structure in the project. There are a lot of folders in the project that do not correspond to their names. It is not clear what is contained in which folder
- Not advanced enough test script. Test scenarios do not cover all possible scenarios. Also, the test scripts are not optimized from a code point of view, which impairs their readability
- Lack of any code formatting.
- Failure to comply with SOLID principles
- No use of any patterns
- Bad code designs. Some code is terribly readable, not following coding standards



### Warning ⚠️

It is strongly recommended not to use the server version presented in this thread. This branch is constantly subject to changes, and therefore a problem may arise in the performance of this version



