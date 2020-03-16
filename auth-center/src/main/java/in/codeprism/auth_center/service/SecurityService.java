package in.codeprism.auth_center.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}

