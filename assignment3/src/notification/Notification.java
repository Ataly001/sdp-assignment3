package notification;
import channel.Channel;

public abstract class Notification {

    private final String id;
    private final String message;
    private Channel channel;

    public Notification(String id, String message, Channel channel) {
        this.id = id;
        this.message = message;
        this.channel = channel;
    }
    public String getId() {
        return id;
    }
    public String getMessage() {
        return message;
    }
    public void setImplementation(Channel channel) {
        this.channel = channel;
    }
    protected String send(String text) {
        return channel.send(id, text);
    }
    public abstract String execute();
}