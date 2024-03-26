import java.io.*;
import java.util.ArrayList;

public class FriendManagement {
    ArrayList<String> friends = new ArrayList<>(); //list of all the user's current friends

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
                    blocked = true;
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

}
