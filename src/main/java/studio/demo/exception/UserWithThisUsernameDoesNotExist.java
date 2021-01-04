package studio.demo.exception;

public class UserWithThisUsernameDoesNotExist extends Exception {
    public UserWithThisUsernameDoesNotExist(String msg) {
        super(msg);
    }
}
