

# Assignment 3 - Bridge Pattern

## Topic:Notifications

## Base Commit: a5f6adb1e873c89370c2b3acf8c24f64dfc7bed4

##  Project



This project shows the Bridge design pattern using *notifications*.

The project has two parts:

- Notification type
- Delivery channel

There are two notification types: Reminder and UrgentAlert.

There are three delivery channels: EmailChannel, SmsChannel and PushChannel.

The Notification class uses the Channel interface. This connects the notification side with the channel side.

## Project Structure

| Role | Class | File |
|---|---|---|
| Abstraction | Notification | src/notification/Notification.java |
| A1 | Reminder | src/notification/Reminder.java |
| A2 | UrgentAlert | src/notification/UrgentAlert.java |
| Implementor | Channel | src/channel/Channel.java |
| I1 | EmailChannel | src/channel/EmailChannel.java |
| I2 | SmsChannel | src/channel/SmsChannel.java |
| I3 | PushChannel | src/channel/PushChannel.java |
| Client | Main | src/Main.java |

## Bridge

The bridge is inside the Notification class:

`private Channel channel;`

Notification does not depend on EmailChannel, 
SmsChannel or PushChannel directly. It works with the Channel interface.

The main operation is:

`execute()`

The channel can be changed with:

`setImplementation(Channel channel)`

## Tests

The program has seven tests.

**T1 - Reminder + EmailChannel**

Message: `Go to gym`

Expected result:

`EMAIL: R1 - Reminder: Go to gym`

**T2 - Reminder + SmsChannel**

Message: `Go to gym`

Expected result:

`SMS: R1 - Reminder: Go to gym`

**T3 - UrgentAlert + EmailChannel**

Message: `Memory full`

Expected result:

`EMAIL: U1 - URGENT: Memory full`

**T4 - UrgentAlert + SmsChannel**

Message: `Memory full`

Expected result:

`SMS: U1 - URGENT: Memory full`

**T5 - Runtime Switch**

One Reminder first uses EmailChannel and then changes to SmsChannel.

Before:

`EMAIL: R2 - Reminder: Check your plans`

After:

`SMS: R2 - Reminder: Check your plans`

T5 also checks that it is the same Reminder object and that its ID and message do not change.

**T6 - Reminder + PushChannel**

Expected result:

`PUSH: R3 - Reminder: Football at 7`

**T7 - UrgentAlert + PushChannel**

Expected result:

`PUSH: U2 - URGENT: Battery is low`

The final result should be:

`SUMMARY: 7/7 PASS`

## How to Run

Compile:

```bash
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
```

Run:

```bash
java -cp out Main --demo
```

## Runtime Switch

T5 shows the runtime switch.

First, the Reminder uses EmailChannel.

Then the implementation is changed:

`reminder.setImplementation(new SmsChannel());`

The Reminder object stays the same. The ID and message also stay the same. 
Only the delivery channel changes.

The program uses `==` to check that it is the same object.

## Extension

The first version of the project had EmailChannel and SmsChannel.

After the base commit, PushChannel was added.

PushChannel implements the same Channel interface.

I did not need to change Notification, Reminder, UrgentAlert, EmailChannel or SmsChannel.

This shows that a new implementation can be added without changing the existing hierarchy.

## Bridge vs Adapter

Bridge and Adapter have different purposes.

Bridge separates two parts that can change independently.

In this project, these parts are the notification type and the delivery channel.

Adapter is used to connect classes with incompatible interfaces.


## Conclusion

Bridge makes the project easy to extend.

For example, I can add a new channel without changing Reminder or UrgentAlert.

The disadvantage is that the project needs more classes and interfaces.