package com.tvd12.master_design_patterns.flightweight;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

public final class Application {

    public interface Flyweight {
        void doOperation(Object state);
    }

    @AllArgsConstructor
    public static class ConcreteFlyweight implements Flyweight {
        private final Object intrinsicState;

        @Override
        public void doOperation(Object extrinsicState) {
            System.out.println(
                "Shared State: " + intrinsicState +
                    ", External State: " + extrinsicState
            );
        }
    }

    public static class FlyweightFactory {

        private final Map<String, Flyweight> flyweightByKey;

        public FlyweightFactory() {
            flyweightByKey = new HashMap<>();
            flyweightByKey.put(
                "heavyJob",
                new ConcreteFlyweight("Heavy Task")
            );
        }

        public Flyweight getFlyweight(String flyweightKey) {
            return flyweightByKey.get(flyweightKey);
        }
    }

    @AllArgsConstructor
    public static class Client {
        private final String name;
        private final Flyweight flyweight;

        public void execute(String action) {
            flyweight.doOperation(name +" do: " + action);
        }
    }

    public static void main(String[] args) {
        final FlyweightFactory factory = new FlyweightFactory();
        final Flyweight flyweight = factory.getFlyweight("heavyJob");
        Client client1 = new Client("A", flyweight);
        Client client2 = new Client("B", flyweight);
        client1.execute("start");
        client2.execute("stop");
    }
}
