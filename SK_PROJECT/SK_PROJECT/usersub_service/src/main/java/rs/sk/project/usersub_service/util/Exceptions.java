package rs.sk.project.usersub_service.util;

public class Exceptions {

    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) { super(message); }
    }

    public static class IllegalStatException extends RuntimeException {
        public IllegalStatException(String message) { super(message); }
    }

}
