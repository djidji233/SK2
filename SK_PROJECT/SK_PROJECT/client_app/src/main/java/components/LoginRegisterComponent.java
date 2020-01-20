package components;

import domain.LoginRequest;
import domain.LoginResponse;
import domain.RegisterRequest;
import service.UserService;
import service.commands.Channel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class LoginRegisterComponent extends JPanel implements Channel {

    private List<Channel> subscriptions;

    private UserService userService;

    public LoginRegisterComponent(UserService userService, List<Channel> subscriptions) {
        this.userService = userService;
        this.subscriptions = new ArrayList<>();
        this.subscriptions.addAll(subscriptions);

        JTabbedPane tabbedPane = new JTabbedPane();

        var loginComponent = new LoginComponent(List.of(this));
        tabbedPane.addTab("Login", loginComponent);
        this.subscriptions.add(loginComponent);

        var registerComponent = new RegisterComponent(List.of(this));
        tabbedPane.addTab("Register", registerComponent);
        this.subscriptions.add(registerComponent);

        add(tabbedPane);
    }

    @Override
    public void dispatch(String notificationType, Object notification) {
        switch (notificationType) {
            case "LOGIN":
                LoginRequest loginRequest = (LoginRequest) notification;
                LoginResponse loginResponse = userService.login(loginRequest);
                subscriptions.forEach(subscription -> subscription.dispatch("SUCC_LOGIN", loginResponse));
                break;
            case "REGISTER":
                RegisterRequest registerRequest = (RegisterRequest) notification;
                LoginResponse registerResponse = userService.register(registerRequest);
                subscriptions.forEach(subscription -> subscription.dispatch("SUCC_LOGIN", registerResponse));
                break;
        }
    }
}
