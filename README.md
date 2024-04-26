# Social Media News Feed
This project creates a social media for posting and commenting. Users can sign up and login the social media and get their own accounts. Users can send text posts on the media, and they can follow other users to see their posts.

Authors: Abhinav Padmanabhuni, Chenbo Sun, Sparsh Mathur, Amrit Multani, Christina Melnic

The link of this GitHub project is [https://github.com/spamathur/SocialMedia](https://github.com/spamathur/SocialMedia).

Phase 1 submitted on Vocareum workspace by : `Abhinav Padmanabhuni`

Phase 1 Distribution: 

`Abhinav Padmanabhuni:`
Worked on methods of Comment and Post classes.

`Chenbo Sun:`
Worked on UserManager class and Readme.

`Sparsh Mathur:`
Worked on creating test cases and debugging and formatting code.

`Amrit Multani:`
Worked on User and UserManager classes.

`Christina Melnic:`
Wrote interfaces and comments.


Phase 2 submitted on Vocareum workspace by : `Abhinav Padmanabhuni`

Phase 2 Distribution: 

`Abhinav Padmanabhuni:`
Worked on implementing a client-server connection, and making it thread safe.

`Chenbo Sun:`
Worked on the client-server classes.

`Sparsh Mathur:`
Worked on creating test cases and debugging and formatting code.

`Amrit Multani:`
Wrote the ReadMe and helped with test cases.

`Christina Melnic:`
Wrote interfaces and helped with the ReadMe.


Phase 3 codes submitted on Vocareum workspace by : `Abhinav Padmanabhuni`

Phase 3 report submitted on Brightspace by: `Sparsh Mathur`

Phase 3 presentation submmitted by: `Abhinav Padmanabhuni`

Phase 3 Distribution:

`Abhinav Padmanabhuni:`
Worked on designing and constructing GUI. Updated some files.

`Chenbo Sun:`
Worked on the documentation of Readme.

`Sparsh Mathur:`
Wrote report Part One and collected Part Two. Fixed previous test cases and interfaces to reflect changes from this phase.

`Amrit Multani:`
Edited profile codes and updated the program

`Christina Melnic:`
Worked on the presentation slides.

## Run the code

Before executing the code, ensure the GSON library is imported. Download the `gson-2.10.1.jar` file from [https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/](https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/) and add the downloaded file as a library in the IDE.

To run the code, execute `Server.java` first to start the server. Then, run `MainGUIController.java` to start a client. Multiple clients could be handled at the same time.

### Make sure the server is started before executing the client.


Following contents describe the main functions of the codes and brief explanations for key methods in each class.

## IO Functionality Tests:

### It is important to note that Server.java must be running before ClientTest and ClientHandlerTest are executed.

I/O functionality is thoroughly tested within the `ClientTest` and `ClientHandlerTest` classes. Descriptions and testing methodologies are documented in the comments at the beginning of each test case, explaining what specific functionalities are tested.

## Main Class

This is the main entry point of the application. It demonstrates the usage of the `FileManager`, `UsersManager`, and other classes in the system.

### Key Methods

`main(String[] args)`: The main method that demonstrates the usage of the system.

## Test Cases:

All functionalities are tested using the following test classes: `UserTest`, `UserManagerTest`, `PostManagerTest`, `PostTest`, `FileManagerTest`, `ClientTest`, `ClientHandlerTest`, `RunLocalTestCommentManager`, and `RunLocalTestComments`. These test cases utilize JUnit 4 for execution.

### Make sure to import a GSON library before executing `ClientTest` and `ClientHandlerTest`.


# Phase 3 Classes:

## MyProfileComponent.java

The component that manages the user profile view, allowing users to view and edit their profile details.

### Key Methods
- `editProfile()`: Edits the user's profile details.
- `viewProfile()`: Displays the user's profile information.

## BlankComment.java

Defines a placeholder for comments, used in the UI when there are no comments to display.

### Key Methods
- `displayEmptyComment()`: Shows an empty comment template.

## BlankPost.java

Serves as a default layout for posts when there are no posts to display.

### Key Methods
- `displayEmptyPost()`: Displays a blank post template.

## CommentComponent.java

Manages the display and interaction of individual comments within the application.

### Key Methods
- `postComment()`: Allows users to post new comments.
- `editComment()`: Enables editing of existing comments.

## EditProfilePane.java

Provides a user interface for editing the details of a user's profile.

### Key Methods
- `saveChanges()`: Saves the updated profile details.
- `cancelChanges()`: Discards any changes made to the profile.

## FullPostPane.java

Displays the full details of a post, including comments and reactions.

### Key Methods
- `displayFullPost()`: Shows complete post information.
- `updateReactions()`: Updates the reaction counts on a post.

## HomePane.java

The main dashboard view for the user after logging in, showing the news feed.

### Key Methods
- `refreshFeed()`: Refreshes the news feed to show the latest posts and comments.
- `searchUser()`: Allows the user to search for other users by name.

## LoginPane.java

Interface for user login.

### Key Methods
- `login()`: Authenticates the user credentials.
- `resetPassword()`: Provides the option to reset a forgotten password.

## MainGUIController.java

Central controller for managing the user interface and interactions across the application.

### Key Methods
- `initialize()`: Sets up the initial state of the GUI.
- `handleEvent()`: General method to handle various UI events.

## MyPostsPane.java

Displays a list of posts made by the logged-in user.

### Key Methods
- `displayMyPosts()`: Shows posts created by the user.
- `deletePost()`: Allows the user to delete one of their posts.

## UserComponent.java

Manages the display and functionality related to individual user profiles visible to others.

### Key Methods
- `displayUserProfile()`: Shows the profile details of a specific user.
- `sendFriendRequest()`: Allows sending friend requests to the user.

## NavigationPane.java

Provides navigation between different parts of the application like Home, My Profile, Settings, etc.

### Key Methods
- `navigateToHome()`: Transitions to the home screen.
- `navigateToProfile()`: Goes to the user's profile page.

## PostCommentComponent.java

Manages the display of comments on a particular post, allowing users to interact through comments.

### Key Methods
- `postComment()`: Posts a new comment to the current viewing post.
- `editComment()`: Allows editing an existing comment.

## PostComponent.java

Handles the individual post block in the user interface, including interactions like liking, sharing, and commenting.

### Key Methods
- `likePost()`: Increments the like count for the post.
- `sharePost()`: Shares the post to other users or platforms.

## PostPane.java

Manages the main area where posts are displayed in the application, potentially aggregating multiple PostComponents.

### Key Methods
- `refreshPosts()`: Reloads the post feed with the latest posts.
- `filterPosts()`: Filters posts based on certain criteria like date or popularity.

## ProfileComponent.java

Represents the section of the application where a user's own profile is displayed and can be edited.

### Key Methods
- `editProfile()`: Allows users to edit their own profile information.
- `viewMyActivities()`: Displays the user's activities like posts, comments, and likes.

## ProfilePane.java

A specialized pane that provides an interface for user profile viewing and editing.

### Key Methods
- `updateProfileDetails()`: Updates and saves the user's profile details to the system.
- `cancelEdits()`: Cancels any ongoing edits and reverts to the original profile data.

## SearchPane.java

Facilitates searching for posts, comments, or users within the application.

### Key Methods
- `searchContent()`: Performs a search based on the entered query and displays results.
- `clearSearch()`: Clears the current search results and resets the search pane.

## SideBySideButtons.java

Provides a generic component that houses two buttons side by side, typically used for actions like 'Accept' and 'Reject', 'Yes' and 'No', etc.

### Key Methods
- `setLeftButtonAction()`: Assigns an action to the left button.
- `setRightButtonAction()`: Assigns an action to the right button.

## SignUpPane.java

The interface for new users to create an account within the application.

### Key Methods
- `registerUser()`: Registers a new user with the provided details.
- `validateInput()`: Checks the input fields for correctness and completeness before submission.


# Phase 2 Classes:

## Server Class

This class accepts incoming client connections and uses a new ClientHandler thread to handle communication for each client.

### Note that Server does not have an interface or test cases in our program as it does not have any methods besides the main method

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


# Phase 1 Classes:

## Comment Class

This class represents a comment on a post in the system. Each comment has a unique ID, a creator, content, and upvote/downvote counts.

### Key Methods

- `upvote()`: Increments the upvote count of the comment.
- `downvote()`: Increments the downvote count of the comment.

## CommentsManager Class

This class manages all comments in the system. It provides functionality for creating, deleting, and searching for comments.

### Key Methods

- `createComment(String postID, String creator, String content)`: Creates a new comment on a post.
- `deleteComment(String commentID)`: Deletes a comment from the system.
- `findComment(String commentID)`: Finds a comment by its ID.
- `readComments()`: Reads comment data from a file.
- `writeComments()`: Writes comment data to a file.

## FileManager Class

This class provides functionality to read and write all data (users, posts, comments) to and from files.

### Key Methods

- `readAll()`: Reads all data from files.
- `writeAll()`: Writes all data to files.
- 
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
