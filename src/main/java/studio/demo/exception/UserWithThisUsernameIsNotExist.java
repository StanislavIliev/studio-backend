package studio.demo.exception;

public class UserWithThisUsernameIsNotExist extends Exception {
    public UserWithThisUsernameIsNotExist(String msg) {
        super(msg);
    }
}
