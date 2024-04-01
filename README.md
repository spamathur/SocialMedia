# SocialMedia
This projects create a social media for posting and commenting. Uesrs can sign up and login the social media and get their own accounts. Users can send posts on the media, and they can follow other users to see their posts.

Following contents describe the main functions of the codes and brief explanation for key methods in each class.

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

