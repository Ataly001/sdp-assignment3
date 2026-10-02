package channel;
public class SmsChannel implements Channel {

    @Override
    public String send(String id, String message) {
        return "SMS: " + id + " - " + message;
    }
}