package com.tvd12.master_design_patterns.storage;

import lombok.AllArgsConstructor;

public final class Application {

    public interface MessageChannel {

        void send(String message);
    }

    public static class SMSChannel implements MessageChannel {
        @Override
        public void send(String message) {
            System.out.println("Send SMS: " + message);
        }
    }

    public static class EmailChannel implements MessageChannel {
        @Override
        public void send(String message) {
            System.out.println("Send email: " + message);
        }
    }

    @AllArgsConstructor
    public static abstract class NotificationSender {
        protected final MessageChannel channel;

        public abstract void send(String message);
    }

    public static class NewMessageSender extends NotificationSender {

        public NewMessageSender(MessageChannel channel) {
            super(channel);
        }

        @Override
        public void send(String message) {
            System.out.println("check new message");
            channel.send(message);
            System.out.println("save sent new message");
        }
    }

    public static class NewOrderSender extends NotificationSender {

        public NewOrderSender(MessageChannel channel) {
            super(channel);
        }

        @Override
        public void send(String message) {
            System.out.println("check new order");
            channel.send(message);
            System.out.println("save sent new order message");
        }
    }

    public static class NewPostSender extends NotificationSender {

        public NewPostSender(MessageChannel channel) {
            super(channel);
        }

        @Override
        public void send(String message) {
            System.out.println("check new post");
            channel.send(message);
            System.out.println("save sent new post message");
        }
    }

    public static void main(String[] args) {
        MessageChannel smsChannel = new SMSChannel();
        EmailChannel emailChannel = new EmailChannel();
        NewMessageSender smsNewMessageSender =
            new NewMessageSender(smsChannel);
        NewMessageSender emailNewMessageSender =
            new NewMessageSender(emailChannel);
        NewOrderSender smsNewOrderSender =
            new NewOrderSender(smsChannel);
        NewOrderSender emailNewOrderSender =
            new NewOrderSender(emailChannel);
        NewPostSender smsNewPostSender =
            new NewPostSender(smsChannel);
        NewPostSender emailNewPostSender =
            new NewPostSender(emailChannel);
        smsNewMessageSender.send("new message");
        emailNewMessageSender.send("new message");
        smsNewOrderSender.send("new order");
        emailNewOrderSender.send("new order");
        smsNewPostSender.send("new post");
        emailNewPostSender.send("new post");
    }
}
