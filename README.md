# SocialMedia
This project creates a social media for posting and commenting. Users can sign up and login the social media and get their own accounts. Users can send text posts on the media, and they can follow other users to see their posts.

The link of this GitHub project is `https://github.com/spamathur/SocialMedia`.

Phase 2 submitted in Vocareum workspace by : 

Phase 2 Distribution: 


To run the code, open Main.java and execute it as your Java application's entry point. Inside Main.java, you will find some commented-out code snippets. Uncomment these lines to test various functionalities of the program.

Following contents describe the main functions of the codes and brief explanations for key methods in each class.

## Main Class

This is the main entry point of the application. It demonstrates the usage of the `FileManager`, `UsersManager`, and other classes in the system.

### Key Methods

`main(String[] args)`: The main method that demonstrates the usage of the system.

## Server Class

This class accepts incoming client connections and uses a new ClientHandler thread to handle communication for each client.

### Key Methods

`main(String[] args)`: - This method initializes a ServerSocket to a specific port. It also accepts connections and returns a Socket object that represents the connection. It instantiates a ClientHandler object for each client connected. This method catches an IOException.

## Client Class

This class connects to the server and manages the client side operations. It uses Gson to serialize and deserialize data sent and recieved from the server.

### Constructor
- Establishes a connection to the server.
- Reads and sends data from and to the server.

### Key Methods

`COMMUNICATION METHODS`:
- sendRequest(String request): Checks to see if a client is connected and then sends a formatted string to the server.
- getResponse(): Retrieves a response from the server.
  
`USER INTERACTION METHODS`:
- logIn(String username, String password): Sends login strings to the server.
- signUp(String username, String firstName, String lastName, String password, String profilePic): Registers a new user with the server.
- addFriend(String username), removeFriend(String username), blockFriend(String username), unblockFriend(String username): Manages the users friend by sending the commands to the server.
  
`RETRIEVAL METHODS`:
- getMyFriendsPosts(), getMyPosts(): Retrieves posts from the user or friend and parses the Json into 'User' objects.
- getMyProfile(): Retrieves the user's profile from the server
- searchUsers(String searchString): Search for users matching a search string, parse the returned JSON into User objects.
  
`POST AND COMMENT METHODS`:
- createPost(String content), createComment(String postID, String commentString): Allows the user to post new content and comment on existing posts.
- upvotePost(String postID), downvotePost(String postID), upvoteComment(String commentID), downvoteComment(String commentID): Send requests to vote on posts and comments.
- hidePost(String postID): Sends a request to hide a specific post.
  
`OTHER METHODS`:
- closeConnection(): CLoses the network stream and socket when the client is done.

## ClientHandler Class

This class handles individual client connections. It processes client requests, performs the action and responds accordingly. This class implements Runnable.

### Constructor
- Accepts a Socket object representing the client connection.

### Key Methods

`run()`:
-  Reads from and writes to the client.
-  Listens for login or signup requests until successful before anything.
-  Handles various other commands from the client (more in the Client.java class).
-  Uses Gson to serialize objects(like posts, comments, user lists, etc.) into Json format before sending it back to the client.
-  Ends the session when the appropriate commands comes in.
-  Catches IOException
