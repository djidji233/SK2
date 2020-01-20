package components;

import domain.LoginRequest;
import service.commands.Channel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class LoginComponent extends JPanel implements Channel {

    private List<Channel> subscriptions;

    private JTextField tfEmail;

    private JPasswordField pfPassword;

    private JButton btnLogin;

    public LoginComponent(List<Channel> subscriptions) {
        this.subscriptions = new ArrayList<>();
        this.subscriptions.addAll(subscriptions);

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);

        tfEmail = new JTextField(20);
        tfEmail.setSize(200, 10);
        pfPassword = new JPasswordField(20);
        btnLogin = new JButton("Login");

        add(new JLabel("Email:"));
        add(tfEmail);
        add(new JLabel("Password:"));
        add(pfPassword);
        add(btnLogin);

        btnLogin.addActionListener(actionEvent -> {
            subscriptions.forEach(subscription -> subscription.dispatch("LOGIN", new LoginRequest(tfEmail.getText(), new String(pfPassword.getPassword()))));
        });
    }

    @Override
    public void dispatch(String notificationType, Object notification) {
//        subscriptions.forEach(subscription -> subscription.dispatch("LOGIN", new LoginRequest(tfEmail.getText(), new String(pfPassword.getPassword()))));
    }
}
