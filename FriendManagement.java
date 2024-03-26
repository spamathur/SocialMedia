import java.io.*;
import java.util.ArrayList;

public class FriendManagement {
    //parameters may change
    
    public ArrayList<String> searchFriends(String friendList) { //displays friend list
        ArrayList<String> friends = new ArrayList<>();
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(friendList));
            String line = "";
            while ((line = bfr.readLine()) != null) {
                friends.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return friends; //returns all current friends, can potentially write results to a file, depends on what we want
    }


    public boolean friended(String username, String friendList) { //check if friends
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(friendList));
            String line = "";
            while ((line = bfr.readLine()) != null) {
                if (line.equals(username)) {
                    return true;
                }
            }
            bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean blocked(String username, String blockList) { //check if blocked
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(blockList));
            String line = "";
            while ((line = bfr.readLine()) != null) {
                if (line.equals(username)) {
                    return true;
                }
            }
            bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void addFriend(String username, String friendList) {
        try {
            if (!friended(username, friendList)) { //if not friends
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(friendList, true)));
                pw.println(username); //add friend to list
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeFriend(String username, String friendList) {
        ArrayList<String> friend = new ArrayList<>();
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(friendList));
            String line = "";
            while ((line = bfr.readLine()) != null) {
                friend.add(line); //get all the friends
            }
            bfr.close();
            if (friend.contains(username)) {
                friend.remove(username); //if username is present in friends, remove them
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(friendList)));
                for (int i = 0; i < friend.size(); i++) {
                    pw.println(friend.get(i)); //rewrite friends to the file after one is removed
                }
                pw.flush();
                pw.close();

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void blockFriend(String username, String friendList, String blockList) {
        try {
            if (!blocked(username, blockList)) { //if the user is not on the blocklist
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(blockList, true)));
                pw.println(username); //block them
                pw.flush();
                pw.close();
            }
            if (friended(username, friendList)) { // if friended
                removeFriend(username, friendList); //remove them from the friend list
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unblockFriend(String username, String blockList) {
        try {
            boolean blocked = false;
            ArrayList<String> blockedFriends = new ArrayList<>();
            BufferedReader bfr = new BufferedReader(new FileReader(blockList));
            String line = "";
            while ((line = bfr.readLine()) != null) {
                blockedFriends.add(line); //list keeps track of all blocked users
                if (line.equals(username)) { //see if user is blocked
                    blocked = true;
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
