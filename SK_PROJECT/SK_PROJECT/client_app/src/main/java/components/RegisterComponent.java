package components;

import domain.RegisterRequest;
import service.commands.Channel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class RegisterComponent extends JPanel implements Channel {

    private List<Channel> subscriptions;

    private JLabel labelMessage;

    private JTextField tfEmail, tfFullName;

    private JPasswordField pfPassword;

    private JButton btnRegister;

    public RegisterComponent(List<Channel> subscriptions) {
        this.subscriptions = new ArrayList<>(subscriptions);

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);

        tfEmail = new JTextField(20);
        tfFullName = new JTextField(20);
        pfPassword = new JPasswordField(20);
        btnRegister = new JButton("Register");
        labelMessage = new JLabel();

        add(new JLabel("FullName:"));
        add(tfFullName);
        add(new JLabel("Email:"));
        add(tfEmail);
        add(new JLabel("Password:"));
        add(pfPassword);
        add(labelMessage);
        add(btnRegister);

        btnRegister.addActionListener(actionEvent -> {
            var registerRequest = new RegisterRequest(tfFullName.getText(), tfEmail.getText(), new String(pfPassword.getPassword()));
            subscriptions.forEach(subscription -> subscription.dispatch("REGISTER", registerRequest));
        });
    }

    @Override
    public void dispatch(String notificationType, Object notification) {
        switch (notificationType){
            case "REGISTER_RESULT":
                this.labelMessage.setText(notification.toString());
                break;
        }
    }
}
