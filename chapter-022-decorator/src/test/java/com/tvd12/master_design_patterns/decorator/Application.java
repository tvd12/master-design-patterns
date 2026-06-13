package com.tvd12.master_design_patterns.decorator;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

public final class Application {
    @Data
    public static class House {
        private String paintColor;
        private Set<String> furnitureSet = new HashSet<>();

        public void addFurniture(String furniture) {
            furnitureSet.add(furniture);
        }
    }

    public interface HouseComponent {
        void decorate(House house);
    }

    public static class PaintHouseComponent
        implements HouseComponent {
        @Override
        public void decorate(House house) {
            house.setPaintColor(house.paintColor);
        }
    }

    @AllArgsConstructor
    public static class HouseDecorator
        implements HouseComponent {

        protected final HouseComponent component;

        @Override
        public void decorate(House house) {
            component.decorate(house);
        }
    }

    public static class LivingRoomDecorator
        extends HouseDecorator {

        public LivingRoomDecorator(
            HouseComponent decorator
        ) {
            super(decorator);
        }

        @Override
        public void decorate(House house) {
            super.decorate(house);
            house.addFurniture("Sofa");
            house.addFurniture("TV");
        }
    }

    public static class BedRoomDecorator
        extends HouseDecorator {

        public BedRoomDecorator(
            HouseComponent decorator
        ) {
            super(decorator);
        }

        @Override
        public void decorate(House house) {
            super.decorate(house);
            house.addFurniture("Desk");
            house.addFurniture("Bed");
        }
    }

    @AllArgsConstructor
    public static class HouseCreator {

        private final HouseComponent decorator;

        public House createHouse() {
            final House house = new House();
            decorator.decorate(house);
            return house;
        }
    }

    public static void main(String[] args) {
        final HouseCreator creator = new HouseCreator(
            new BedRoomDecorator(
                new LivingRoomDecorator(
                    new PaintHouseComponent()
                )
            )
        );
        final House house = creator.createHouse();
        System.out.println(house);
    }
}

