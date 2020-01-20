package service.commands;

public interface Channel {

    void dispatch(String notificationType, Object notification);

}
