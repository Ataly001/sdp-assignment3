package notification;
import channel.Channel;

public class Reminder extends Notification {
    public Reminder(String id, String message, Channel channel) {
        super(id, message, channel);
    }
    @Override
    public String execute() {
        String text = "Reminder: " + getMessage();
        return send(text);
    }
}