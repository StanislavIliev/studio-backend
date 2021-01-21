package studio.demo.contants;

public class GlobalConstants {
    public static final String USERNAME_REGEX = "[a-zA-Z0-9]+";
    public static final String USERNAME_NOT_CORRECT = "Username must be only letter and digits.";

    public static final String FIRST_NAME_REGEX = "[a-zA-Z]+";
    public static final String FIRST_NAME_NOT_CORRECT = "First name must be only letter.";

    public static final String LAST_NAME_REGEX = "[a-zA-Z]+";
    public static final String LAST_NAME_NOT_CORRECT = "Last name must be only letter.";

    public static final String PASSWORD_REGEX = "[a-zA-Z0-9]+";
    public static final String PASSWORD_NOT_CORRECT = "Password must be only digits,letter,&%@_-+.!,.";

    public static final String PHONE_REGEX = "[+]*[0-9]+";
    public static final String PHONE_NOT_CORRECT = "Phone number must be only digits and +.";

    public static final String PROMOTION_NAME_REGEX = "[a-zA-Z]+";
    public static final String PROMOTION_NAME_NOT_CORRECT = "Promotion name must be only letters.";

    public static final String ORDER_NAME_REGEX = "[a-zA-Z]+";
    public static final String ORDER_NAME_NOT_CORRECT = "Order name must be only letters.";

    public static final String PRICE_NOT_NULL = "Price can not be null.";

    public static final String MANICURE_NOT_NULL = " Enter valid manicure name.";

    public static final String COMMENT_REGEX = "[a-zA-Z]+";
    public static final String COMMENT_NOT_NULL = " Enter valid comment only with letters.";
    public static final String COMMENT_DESCRIPTION = "Description must be more than 7 characters.";
}
