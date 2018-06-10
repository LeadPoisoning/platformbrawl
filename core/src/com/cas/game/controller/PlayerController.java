package com.cas.game.controller;

import com.cas.game.model.Player;

public class PlayerController {
    public static Player player;

    public static void initializeController() {
        player = new Player(64,80);
    }

    public static void update(float deltaTime) {
        player.update(deltaTime);
    }
}
