package com.game.service;

import com.game.entity.Data;
import com.game.entity.Player;
import com.game.exceptions.ExceptionsNOT_FOUND;
import com.game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServices {

    @Autowired
    private final PlayerRepository repository;

    public PlayerServices(PlayerRepository repository) {
        this.repository = repository;
    }

    public Player createPlayer(Data params) {

        // Вычисляем уровень персонажа.
        Integer L = (int) (Math.sqrt(2500 + 200 * params.getExperience()) - 50)/100;
        // Вычисляем остаток опыта до следующего уровня
        Integer N = 50 * (L + 1) * (L + 2) - params.getExperience();

        Player player = new Player();

        player.setName(params.getName());
        player.setTitle(params.getTitle());
        player.setRace(params.getRace());
        player.setProfession(params.getProfession());
        player.setExperience(params.getExperience());
        player.setLevel(L);
        player.setUntilNextLevel(N);
        player.setBirthday(params.getBirthday());
        player.setBanned(params.getBanned());

        return repository.save(player);
    }

    public Player getPlayer(Long id) {
        return repository.findById(id).orElseThrow(() -> new ExceptionsNOT_FOUND());
    }

    public void deletePlayer(Long id) {
        if (repository.existsById(id)) repository.deleteById(id);
        else throw new ExceptionsNOT_FOUND();
    }

    public List<Player> getAllWithoutFilters() {
        List<Player> players = (List<Player>) repository.findAll();
        return players;
    }


    public Player updatePlayer(Data params, Long id) {
        Player player = repository.findById(id).orElseThrow(() -> new ExceptionsNOT_FOUND());

        if (params.getName() != null) player.setName(params.getName());
        if (params.getTitle() != null) player.setTitle(params.getTitle());
        if (params.getRace() != null) player.setRace(params.getRace());
        if (params.getProfession() != null) player.setProfession(params.getProfession());
        if (params.getExperience() != null) {

            Integer L = (int) (Math.sqrt(2500 + 200 * params.getExperience()) - 50)/100;
            Integer N = 50 * (L + 1) * (L + 2) - params.getExperience();

            player.setExperience(params.getExperience());
            player.setLevel(L);
            player.setUntilNextLevel(N);
        }

        if (params.getBirthday() != null) player.setBirthday(params.getBirthday());
        if (params.getBanned() != null) player.setBanned(params.getBanned());

        return repository.save(player);
    }
}
