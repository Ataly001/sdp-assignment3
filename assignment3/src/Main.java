import channel.*;
import notification.*;

public class Main {

    static int passed = 0;
    static int total = 0;

    public static void main(String[] args) {

        if (args.length > 0 && args[0].equals("--demo")) {
            runDemo();
        } else {
            System.out.println("Use --demo");
        }
    }

    public static void runDemo() {

        test1();
        test2();
        test3();
        test4();
        test5();

        System.out.println();
        System.out.println("SUMMARY: " + passed + "/" + total + " PASS");
    }

    public static void test1() {

        Reminder reminder = new Reminder(
                "R1",
                "Go to gym",
                new EmailChannel()
        );

        String actual = reminder.execute();
        String expected = "EMAIL: R1 - Reminder: Go to gym";

        showTest(
                "T1",
                "Reminder + EmailChannel",
                actual,
                expected
        );
    }

    public static void test2() {

        Reminder reminder = new Reminder(
                "R1",
                "Go to gym",
                new SmsChannel()
        );

        String actual = reminder.execute();
        String expected = "SMS: R1 - Reminder: Go to gym";

        showTest(
                "T2",
                "Reminder + SmsChannel",
                actual,
                expected
        );
    }

    public static void test3() {

        UrgentAlert alert = new UrgentAlert(
                "U1",
                "Memory full",
                new EmailChannel()
        );

        String actual = alert.execute();
        String expected = "EMAIL: U1 - URGENT: Memory full";

        showTest(
                "T3",
                "UrgentAlert + EmailChannel",
                actual,
                expected
        );
    }

    public static void test4() {

        UrgentAlert alert = new UrgentAlert(
                "U1",
                "Memory full",
                new SmsChannel()
        );

        String actual = alert.execute();
        String expected = "SMS: U1 - URGENT: Memory full";

        showTest(
                "T4",
                "UrgentAlert + SmsChannel",
                actual,
                expected
        );
    }

    public static void test5() {

        total++;

        Reminder reminder = new Reminder(
                "R2",
                "Check your plans",
                new EmailChannel()
        );

        Notification firstReference = reminder;

        String oldId = reminder.getId();
        String oldMessage = reminder.getMessage();

        String before = reminder.execute();

        reminder.setImplementation(new SmsChannel());

        Notification secondReference = reminder;

        String after = reminder.execute();

        boolean sameObject = firstReference == secondReference;

        boolean sameData =
                oldId.equals(reminder.getId())
                        && oldMessage.equals(reminder.getMessage());

        boolean resultChanged =
                before.equals("EMAIL: R2 - Reminder: Check your plans")
                        && after.equals("SMS: R2 - Reminder: Check your plans");

        boolean success =
                sameObject && sameData && resultChanged;

        if (success) {
            passed++;
        }

        System.out.println();
        System.out.println("T5");

        if (success) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }

        System.out.println("Classes: Reminder, EmailChannel, SmsChannel");
        System.out.println("Same object: " + sameObject);
        System.out.println("Same data: " + sameData);
        System.out.println("Before: " + before);
        System.out.println("After: " + after);
    }

    public static void showTest(
            String name,
            String classes,
            String actual,
            String expected
    ) {

        total++;

        boolean success = actual.equals(expected);

        if (success) {
            passed++;
        }

        System.out.println();
        System.out.println(name);

        if (success) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }

        System.out.println("Classes: " + classes);
        System.out.println("Result: " + actual);

        if (!success) {
            System.out.println("Expected: " + expected);
        }
    }
}