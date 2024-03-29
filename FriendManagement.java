import java.awt.color.ICC_ColorSpace;
import java.io.*;
import java.util.ArrayList;

public class FriendManagement {

    private ArrayList<String> friends; //friend list
    private ArrayList<String> blocked; //block list
    private String username; //the way i have this rn, this username is the other user; not "us", might distinguish later
    private String friendList; //friend list file name
    private String blockList; //block list file name


    ///use a synchronized obj for all methods?
    //parameters may change, like the file name strings

    public FriendManagement(String username, String friendList, String blockList) { //constructor?
        this.username = username;
        this.friendList = friendList;
        this.blockList = blockList;
        this.friends = new ArrayList<>();
        this.blocked = new ArrayList<>();
    }


    public void readFriends() { //initialize friend array with 'current' friends
        ArrayList<String> temp = new ArrayList<>();
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(friendList)); //reads friend list file
            String line = "";
            while ((line = bfr.readLine()) != null) {
                temp.add(line); //add to temp array
            }
            bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        friends.clear();
        friends.addAll(temp);
    }


    public void readBlocked() { //initialize blocked array
        ArrayList<String> temp = new ArrayList<>();
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(blockList)); //reads blocked list file
            String line = "";
            while ((line = bfr.readLine()) != null) {
                temp.add(line); //add to temp array
            }
            bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        blocked.clear();
        blocked.addAll(temp);
    }


    public boolean friended(String username) { //check if friends
        return friends.contains(username);
    }

    public void addFriend(String username) { //add friend ///maybe - check if the username exists to begin with
        if (!friends.contains(username)) {
            friends.add(username);
        }
    }

    public void removeFriend(String username) { //remove friend
        if (friends.contains(username)) {
            friends.remove(username);
        }
    }

    public boolean isBlocked(String username) { //check if blocked
        return blocked.contains(username);
    }

    public void blocked(String username) { //block user
        if (!blocked.contains(username)) {
            blocked.add(username);
        }
    }

    public void unBlockFriend(String username) { //unblocks user
        if (blocked.contains(username)) {
            blocked.remove(username);
        }
    }


    public void writeFriendList() { //writes changes back to the file
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(friendList)));
            for (String friendedUser : friends) {
                pw.println(friendedUser);
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeBlockList() { //writes changes back to the file
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(blockList)));
            for (String blockedUser : blocked) {
                pw.println(blockedUser);
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
