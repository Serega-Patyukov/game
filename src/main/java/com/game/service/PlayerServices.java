package com.game.service;

import com.game.repository.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerServices {

    private final PlayerRepository repository;

    public PlayerServices(PlayerRepository repository) {
        this.repository = repository;
    }
}
