package components;

import components.component_ctrl.TableCompModel;
import domain.LoginResponse;
import domain.User;
import domain.WeatherInfo;
import service.UserService;
import service.WeatherService;
import service.commands.Channel;
import service.impl.UserServiceImpl;
import service.impl.WeatherServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomeWindowComp extends JFrame implements Channel {

    private List<Channel> subscriptions;

    private User user;

    private UserService userService;

    private WeatherService weatherService;

    private List<WeatherInfo> infos;

    private TableCompModel tableModel;

    public HomeWindowComp(LoginResponse loginResponse) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.weatherService = new WeatherServiceImpl();
        this.subscriptions = new ArrayList<>();

        this.userService = new UserServiceImpl();
        userService.setAuthorization(loginResponse.getToken());
        this.user = loginResponse.getUser();

        infos = weatherService.findCurrentWeatherData();
        List<Object[]> data = infos.stream()
                .map(item -> new Object[]{item.getCity(), item.getDateTime().toString(), item.getTemp(), user.getSubscriptions().contains(item.getId())})
                .collect(Collectors.toList());
        Object[][] objects = new Object[data.size()][];
        for(int i=0; i<data.size(); i++) {
            objects[i] = data.get(i);
        }

        tableModel = new TableCompModel(objects);
        HomeComponent homeComponent = new HomeComponent(this.user, tableModel, List.of(this));
        homeComponent.setOpaque(true);
        setContentPane(homeComponent);
        subscriptions.add(homeComponent);

        setMinimumSize(new Dimension(700, 200));
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    @Override
    public void dispatch(String notificationType, Object notification) {
        switch (notificationType) {
            case "CHANGE_NOTIFICATION":
                Integer index = (Integer) notification;
                WeatherInfo weatherInfo = infos.get(index);
                System.out.println(weatherInfo);
                boolean result = user.getSubscriptions().contains(weatherInfo.getId());
                if(result) {
                    user.getSubscriptions().remove(weatherInfo.getId());
                    userService.unsubscribe(weatherInfo.getId());
                } else {
                    user.getSubscriptions().add(weatherInfo.getId());
                    userService.subscribe(weatherInfo.getId());
                }
                tableModel.setValueAt(!result, index, 3);
                break;
            case "LOGOUT":
                setVisible(false);
                new WindowComponent();
                break;
        }
    }
}
