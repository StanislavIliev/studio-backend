package studio.demo.contants;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 432_000_000; // 5 days expressed in milliseconds
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String GET_ARRAYS_LLC = "Get Arrays, LLC";
    public static final String GET_ARRAYS_ADMINISTRATION = "User Management Portal";
    public static final String AUTHORITIES = "authorities";
    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    //public static final String[] PUBLIC_URLS
      //      = { "/users/login", "/users/register",
           // "/comments/add", "/api/comments/details","/api/comments/delete/**",
            //"/orders/add","/api/orders/details","/api/orders/delete/**",
            //"/api/promotions/add","/api/promotions/details","/api/promotions/delete/**",
    //        "/v2/api-docs/**", "/swagger-ui/**"};
    public static final String[] PUBLIC_URLS = { "**" };
}
