package com.game.controller;

import com.game.service.PlayerServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/players")
public class PlayerRestController {

    private final PlayerServices services;

    public PlayerRestController(PlayerServices services) {
        this.services = services;
    }
}
