package com.tvd12.master_design_patterns.flightweight.game;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public final class Application {

    public interface Texture {
        void draw(float x, float y);
    }

    @AllArgsConstructor
    public static class Texture2D implements Texture {
        private final byte[] image;

        @Override
        public void draw(float x, float y) {
            System.out.println(
                "draw image: " + image.length +
                    " at " + x + ", " + y
            );
        }
    }

    @Getter
    @AllArgsConstructor
    public static class GameObject {
        private final String id;
        private final Texture texture;
        private float x;
        private float y;

        public void move(float x, float y) {
            this.x = x;
            this.y = y;
            texture.draw(x, y);
            System.out.println(
                "move " + id + " to: " + x + ", " + y
            );
        }
    }

    public static class GameObjectManager {
        private final Map<String, Texture> textureByName =
            new HashMap<>();
        private final Map<String, GameObject> objectById =
            new HashMap<>();

        public GameObjectManager() {
            this.textureByName.put(
                "player",
                new Texture2D(loadPlayerImage())
            );
            this.textureByName.put(
                "bot",
                new Texture2D(loadBotImage())
            );
        }

        public GameObject getObject(
            String id,
            String textureName
        ) {
            return objectById.computeIfAbsent(
                id,
                k -> new GameObject(
                    id,
                    textureByName.get(textureName),
                    0,
                    0
                )
            );
        }

        public void removeObject(String id) {
            objectById.remove(id);
        }

        private byte[] loadPlayerImage() {
            // mockup
            return new byte[0];
        }

        private byte[] loadBotImage() {
            // mockup
            return new byte[0];
        }
    }

    public static void main(String[] args) {
        final GameObjectManager manager = new GameObjectManager();
        for (int i = 0; i < 1000; i++) {
            manager
                .getObject("player_" + i, "player")
                .move(i, i);
        }
        for (int i = 0; i < 1000; i++) {
            manager
                .getObject("bot_" + i, "bot")
                .move(i, i);
        }
        Texture t1 = manager
            .getObject("player_0", "player")
            .getTexture();
        Texture t2 = manager
            .getObject("player_1", "player")
            .getTexture();
        System.out.println(t1 == t2); // true
    }
}
