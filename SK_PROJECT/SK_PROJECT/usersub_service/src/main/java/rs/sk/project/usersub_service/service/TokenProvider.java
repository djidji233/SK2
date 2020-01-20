package rs.sk.project.usersub_service.service;

public interface TokenProvider {

    String encrypt(String email);

    String decrypt(String token);

}
