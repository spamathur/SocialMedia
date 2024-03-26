public class UserManagement {
    private User user;

    public UserManagement(User user) {
        this.user = user;
    }

    public UserManagement() {
        this.user = null;
    }

    public User getUser() {
        return user;
    }
}
