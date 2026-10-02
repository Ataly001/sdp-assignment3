package channel;

public class EmailChannel implements Channel {
    @Override
    public String send(String id, String message) {
        return "EMAIL: " + id + " - " + message;
    }
}