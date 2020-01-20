package components;

import domain.User;
import service.commands.Channel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class HomeComponent extends JPanel implements Channel {

    private List<Channel> subscriptions;

    private User user;

    private JButton btAction;

    private JButton btLogout;

    private TableComponent tableComponent;

    public HomeComponent(User user, AbstractTableModel tableModel, List<Channel> subscriptions) {

        this.user = user;
        this.subscriptions = new ArrayList<>(subscriptions);

        JLabel labelName = new JLabel(String.format("Hello, %s", user.getFullName()));
        btAction = new JButton("Change Subscription");
        btLogout = new JButton("Logout");
        tableComponent = new TableComponent(tableModel);

        JPanel panel = new JPanel();
        BoxLayout bl1 = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(bl1);
        panel.add(btAction);
        panel.add(btLogout);

        BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(bl);

        add(labelName);
        add(panel);
        add(tableComponent);

        btAction.addActionListener(actionEvent -> subscriptions.forEach(subscription -> subscription.dispatch("CHANGE_NOTIFICATION", tableComponent.getSelectedRow())));

        btLogout.addActionListener(actionEvent -> subscriptions.forEach(subscription -> subscription.dispatch("LOGOUT", null)));

    }


    @Override
    public void dispatch(String notificationType, Object notification) {

    }
}
