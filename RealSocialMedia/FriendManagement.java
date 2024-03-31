import java.awt.color.ICC_ColorSpace;
import java.io.*;
import java.util.ArrayList;

public class FriendManagement {

    private ArrayList<String> friends; //friend list
    private ArrayList<String> blocked; //block list
    private String username;

    public FriendManagement(String username) {
        this.username = username;
        this.friends = new ArrayList<>();
        this.blocked = new ArrayList<>();
        initializeFriendListFile(); //creates these files
        initializeBlockListFile();
        readFriends(); //reads and populates the file, based on what is in the arraylist
        readBlocked();
    }


    // Initialize friend list file
    private synchronized void initializeFriendListFile() {
        File friendListFile = new File(username + "_friendlist.txt");
        if (!friendListFile.exists()) {
            try {
                friendListFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Initialize block list file
    private synchronized void initializeBlockListFile() {
        File blockListFile = new File(username + "_blocklist.txt");
        if (!blockListFile.exists()) {
            try {
                blockListFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void readFriends() { //initialize friend array with 'current' friends
        ArrayList<String> temp = new ArrayList<>();
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(username + "_friendlist.txt")); //reads friend list file
            String line = "";
            while ((line = bfr.readLine()) != null) {
                temp.add(line); //add to temp array, not og array as it may be changed
            }
            bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        friends.clear();
        friends.addAll(temp);
    }

    public synchronized ArrayList<String> returnFriends() { //returns friend list
        return friends;
    }

    public synchronized ArrayList<String> returnBlocked() { //returns block list
        return blocked;
    }

    public synchronized void readBlocked() { //initialize blocked array
        ArrayList<String> temp = new ArrayList<>();
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(username + "_blocklist.txt")); //reads blocked list file
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


    public synchronized boolean isFriended(String username) { //check if friends
        return friends.contains(username);
    }

    public synchronized void addFriend(String username) { //add friend ///maybe - check if the username exists to begin with
        if (!friends.contains(username)) {
            friends.add(username);
            writeFriendList();
        }
    }

    public synchronized void removeFriend(String username) { //remove friend
        if (friends.contains(username)) {
            friends.remove(username);
            writeFriendList();
        }
    }

    public synchronized boolean isBlocked(String username) { //check if blocked
        return blocked.contains(username);
    }

    public synchronized void blockUser(String username) { //block user
        if (friends.contains(username)) {
            friends.remove(username);
            writeFriendList();
        }
        if (!blocked.contains(username)) {
            blocked.add(username);
            writeBlockList();
        }
    }

    public synchronized void unBlockUser(String username) { //unblocks user
        if (blocked.contains(username)) {
            blocked.remove(username);
            writeBlockList();
        }
    }


    public synchronized void writeFriendList() { //writes changes back to the file
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(username + "_friendlist.txt")));
            for (String friendedUser : friends) {
                pw.println(friendedUser);
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void writeBlockList() { //writes changes back to the file
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(username + "_blocklist.txt")));
            for (String blockedUser : blocked) {
                pw.println(blockedUser);
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
