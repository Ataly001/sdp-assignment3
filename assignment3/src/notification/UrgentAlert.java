package notification;
import channel.Channel;

public class UrgentAlert extends Notification {
    public UrgentAlert(String id, String message, Channel channel) {
        super(id, message, channel);
    }
    @Override
    public String execute() {
        String text = "URGENT: " + getMessage();
        return send(text);
    }
}