package channel;

public class PushChannel implements Channel {
    @Override
    public String send(String id, String message) {
        return "PUSH: " + id + " - " + message;
    }
}