package components;

import domain.LoginResponse;
import service.commands.Channel;
import service.impl.UserServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WindowComponent extends JFrame implements Channel {

    public WindowComponent() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LoginRegisterComponent loginComponent = new LoginRegisterComponent(new UserServiceImpl(), List.of(this));
        loginComponent.setOpaque(true);
        setContentPane(loginComponent);

        setMinimumSize(new Dimension(200, 200));
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    @Override
    public void dispatch(String notificationType, Object notification) {
        switch (notificationType) {
            case "SUCC_LOGIN":
                setVisible(false);
                new HomeWindowComp((LoginResponse) notification);
                break;
        }
    }
}
