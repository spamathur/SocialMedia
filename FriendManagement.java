import java.io.*;
import java.util.ArrayList;

public class FriendManagement {
    ArrayList<String> friends = new ArrayList<>();

    //  public boolean blocked = false;
    //  public boolean friended = false;
    //  might have boolean methods - to quickly check if user is blocked/friends or not

    public ArrayList<String> searchFriends(String friendList) { //displays friend list
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(friendList));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(friendList)));
            String line = "";
            while ((line = bfr.readLine()) != null) {
                friends.add(line);
            }
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return friends; //returns all current friends, can potentially write results to a file, depends on what we want
    }


    public void addFriend(String username, String friendList) {
        boolean friendExists = false;
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(friendList));
            String line = "";
            while ((line = bfr.readLine()) != null) {
                if (line.equals(username)) { //check to see if already friends
                    friendExists = true;
                    break;
                }
            }
            bfr.close();
            if (!friendExists) { //if not friends
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(friendList, true)));
                pw.println(username); //add friend to list
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeFriend(String username, String friendList) {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(friendList));
            String line = "";
            while ((line = bfr.readLine()) != null) {
                friends.add(line); //get all the friends
            }
            bfr.close();
            if (friends.contains(username)) {
                friends.remove(username); //if username is present in friends, remove them
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(friendList)));
                for (int i = 0; i < friends.size(); i++) {
                    pw.println(friends.get(i)); //rewrite friends to the file after one is removed
                }
                pw.flush();
                pw.close();

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void blockFriend(String username, String friendList, String blockList) {
        boolean blocked = false;
        try {

            BufferedReader bfr = new BufferedReader(new FileReader(blockList));
            String line = "";
            while ((line = bfr.readLine()) != null) {
                if (line.equals(username)) {
                    blocked = true; //check to see if user is blocked
                    break;
                }
            }
            bfr.close();
            if (!blocked) { //if the user is not on the blocklist
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(blockList, true)));
                pw.println(username); //block them
                pw.flush();
                pw.close();

            }
            removeFriend(username, friendList); //remove them from the friend list if applicable
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //only unblocks, still not friends
    public void unblockFriend(String username, String blockList) {
        try {
            boolean blocked = false;
            ArrayList<String> blockedFriends = new ArrayList<>();
            BufferedReader bfr = new BufferedReader(new FileReader(blockList));
            String line = "";
            while ((line = bfr.readLine()) != null) {
                blockedFriends.add(line); //list keeps track of blocked users
                if (line.equals(username)) { //see if user is blocked
                    blocked = true;
                    break;
                }
            }
            bfr.close();
            if (blocked) {
                blockedFriends.remove(username); //if user is blocked, remove them from blocklist array
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(blockList)));
                for (int i = 0; i < blockedFriends.size(); i++) {
                    pw.println(blockedFriends.get(i)); //rewrite all the blocked users to the blocked list file
                }
                pw.flush();
                pw.close();


            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
