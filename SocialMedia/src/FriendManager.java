public interface FriendManager {
    //This class should take in a User in the constructor and assign to the User field of the class

    // STORE FRIENDS AND BLOCKED PEOPLE IN SEPARATE FILES
    /* It is up to you if you want to have one file with all friendships or you can make a friendship file
    for every single user. It is easier to implement these methods if you have a file for every single user.
     But it would reduce the number of files if you were able to create these methods with one friendship file*/

    public void add(User user);

    public User[] getAdded();

    public void remove(User user);


    /* It is up to you if you want to have one file with all blocked friends or you can make a blocked friends file
    for every single user. It is easier to implement these methods if you have a file for every single user.
     But it would reduce the number of files if you were able to create these methods with one blocked friends file*/
    public void block(User user);
    public User[] getBlocked();
    public void unblock(User user);

}

