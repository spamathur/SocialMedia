# SocialMedia
This project creates a social media for posting and commenting. Users can sign up and login the social media and get their own accounts. Users can send text posts on the media, and they can follow other users to see their posts.

The link of this GitHub project is `https://github.com/spamathur/SocialMedia`.

To run the code, open Main.java and execute it as your Java application's entry point. Inside Main.java, you will find some commented-out code snippets. Uncomment these lines to test various functionalities of the program.

Following contents describe the main functions of the codes and brief explanations for key methods in each class.

## Main Class

This is the main entry point of the application. It demonstrates the usage of the `FileManager`, `UsersManager`, and other classes in the system.

### Key Methods

- `main(String[] args)`: The main method that demonstrates the usage of the system.

## Server Class

This class accepts incoming client connections and uses a new ClientHandler thread to handle communication for each client.

### Key Methods

- `main(String[] args)`: - This method initializes a ServerSocket to a specific port. It also accepts connections and returns a Socket object that represents the connection. It instantiates a ClientHandler object for each client connected. This method catches an IOException.

## Client Class

This class connects to the server and manages the client side operations. It uses Gson to serialize and deserialize data sent and recieved from the server.

### Constructor
- Establishes a connection to the server.
- Reads and sends data from and to the server.

### Key Methods

- `COMMUNICATION METHODS`:
- sendRequest(String request): Checks to see if a client is connected and then sends a formatted string to the server.
- getResponse(): Retrieves a response from the server.
- `USER INTERACTION METHODS`:
- logIn(String username, String password): Sends login strings to the server.
- signUp(String username, String firstName, String lastName, String password, String profilePic): Registers a new user with the server.
- addFriend(String username), removeFriend(String username), blockFriend(String username), unblockFriend(String username): Manages the users friend by sending the commands to the server. 
- `RETRIEVAL METHODS`:
- getMyFriendsPosts(), getMyPosts(): Retrieves posts from the user or friend and parses the Json into 'User' objects.
- getMyProfile(): Retrieves the user's profile from the server
- searchUsers(String searchString): Search for users matching a search string, parse the returned JSON into User objects.
- `POST AND COMMENT METHODS`:
- createPost(String content), createComment(String postID, String commentString): Allows the user to post new content and comment on existing posts.
- upvotePost(String postID), downvotePost(String postID), upvoteComment(String commentID), downvoteComment(String commentID): Send requests to vote on posts and comments.
- hidePost(String postID): Sends a request to hide a specific post.
- `OTHER METHODS`:
- closeConnection(): CLoses the network stream and socket when the client is done.

## FileManager Class

This class provides functionality to read and write all data (users, posts, comments) to and from files.

### Key Methods

- `readAll()`: Reads all data from files.
- `writeAll()`: Writes all data to files.

## User Class

This class represents a user in the system. Each user has a unique username, a first name, a last name, a password, and a profile picture. Users can also have a list of friends, a list of blocked users, and a list of their own posts.

### Key Methods

- `addFriend(String username)`: Adds a user to the current user's friends list.
- `removeFriend(String username)`: Removes a user from the current user's friends list.
- `blockFriend(String username)`: Blocks a user, removing them from the current user's friends list.
- `unblockFriend(String username)`: Unblocks a user.
- `searchUsers(String searchString)`: Searches for users based on a search string.
- `createPost(String content)`: Creates a new post with the given content.
- `hidePost(String postID)`: Hides a post from the user's feed.
- `upvotePost(String postID)`: Upvotes a post.
- `downvotePost(String postID)`: Downvotes a post.

## UsersManager Class

This class manages all users in the system. It provides functionality for user registration, login, and searching for users.

### Key Methods

- `signUp(User newUser)`: Registers a new user in the system.
- `logIn(String userName, String password)`: Authenticates a user based on their username and password.
- `searchUsers(String myUserName, String searchString)`: Searches for users based on a search string, excluding users blocked by the searcher.
- `readUsers()`: Reads user data from a file.
- `writeUsers()`: Writes user data to a file.

## LogInFailedException Class

This class represents an exception that is thrown when a user fails to log in, typically due to incorrect credentials.

### Constructor

- `LogInFailedException()`: Constructs a new LogInFailedException with a default error message.

## UserNameTakenException Class

This class represents an exception that is thrown when a user tries to sign up with a username that is already taken.

### Constructor

- `UserNameTakenException()`: Constructs a new UserNameTakenException with a default error message.

## UserNotFoundException Class

This class represents an exception that is thrown when a user is not found in the system, typically during search operations.

### Constructor

- `UserNotFoundException()`: Constructs a new UserNotFoundException with a default error message.

## Post Class

This class represents a user's post in the system. It includes properties such as the post's uniquely generated ID (`postID`), the post creator's username (`creator`), the content of the post (`content`), likes (`upvotes`), dislikes (`downvotes`), and a list of comments (`comments`). This class provides methods that allow users to create a post, comment on it, and like or dislike it.

### Key Methods

- `addComment(Comment comment)`: Adds a comment to the post.
- `upvote()`: Increments the upvote count.
- `downvote()`: Increments the downvote count.

## PostNotFoundException Class

This class is a custom exception that extends the `Exception` class. It is thrown when a post is not found in the system.

### Constructor

- `PostNotFoundException()`: Constructs a new PostNotFoundException with a default error message.

## PostsManager Class

This class acts as a management layer for posts. It extends the `PostsManagerInterface` interface and provides static methods to manage posts, such as finding posts by their unique ID, creating new posts, reading posts from a file, and writing posts to a file. This class is used to maintain and retrieve post data.

### Key Methods

- `findPost(String postID)`: Finds a post by its unique ID.
- `createPost(String creator, String content)`: Creates a new post with the specified creator and content.
- `readPosts()`: Reads post data from a file.
- `writePosts()`: Writes post data to a file.
