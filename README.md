## Saphhire Messanger ( Server )

Sapphire Messenger is a partially decentralized messaging service. The fact is that Sapphire does not store your correspondence or all your data on servers. Sapphire emphasizes decentralization, so the server stores hashed links of your data and messages that are in IPFS. The server only manipulates a small part of your data for fast database mapping


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



### Warning ⚠️

It is strongly recommended not to use the server version presented in this thread. This branch is constantly subject to changes, and therefore a problem may arise in the performance of this version



