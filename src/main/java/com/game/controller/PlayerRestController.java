package com.game.controller;

import com.game.entity.Player;
import com.game.entity.Data;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.exceptions.ExceptionsBAD_REQUEST;
import com.game.service.PlayerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/players")
public class PlayerRestController {

    @Autowired
    private final PlayerServices services;

    public PlayerRestController(PlayerServices services) {
        this.services = services;
    }

    @PostMapping
    public Player createPlayer(@RequestBody Data params) {

        // указаны не все параметры из Data Params (кроме banned);
        if (params.getName() == null ||
                params.getTitle() == null ||
                params.getRace() == null ||
                params.getProfession() == null ||
                params.getBirthday() == null ||
                params.getExperience() == null) throw  new ExceptionsBAD_REQUEST();

        //  длина значения параметра “name” или “title” превышает размер соответствующего поля в БД (12 и 30 символов);
        if (params.getName().length() > 12) throw  new ExceptionsBAD_REQUEST();
        if (params.getTitle().length() > 30) throw  new ExceptionsBAD_REQUEST();

        //  значение параметра “name” пустая строка;
        if (params.getName().equals("")) throw  new ExceptionsBAD_REQUEST();

        //  опыт находится вне заданных пределов;
        if (!(params.getExperience() >= 0 && params.getExperience() <= 10_000_000)) throw  new ExceptionsBAD_REQUEST();

        //  “birthday”:[Long] < 0;
        if (params.getBirthday().getTime() < 0) throw  new ExceptionsBAD_REQUEST();

        //  дата регистрации находятся вне заданных пределов.
        Date date0 = new Date(100, 0, 1);
        Date date1 = new Date(1100, 0, 1);
        if (!(params.getBirthday().getYear() >= date0.getYear() && params.getBirthday().getYear() <= date1.getYear())) throw  new ExceptionsBAD_REQUEST();

        // Проверяем рассу.
        Race[] races = Race.values();
        for (int i = 0; i < races.length; i++) {
            if (races[i] == params.getRace()) break;
            if (i == races.length - 1) throw  new ExceptionsBAD_REQUEST();
        }

        // Проверяем профессию.
        Profession[] professions = Profession.values();
        for (int i = 0; i < professions.length; i++) {
            if (professions[i] == params.getProfession()) break;
            if (i == professions.length - 1) throw  new ExceptionsBAD_REQUEST();
        }

        if (params.getBanned() == null) params.setBanned(false);

        return services.createPlayer(params);
    }

    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable Long id) {
        if (id < 1) throw  new ExceptionsBAD_REQUEST();
        return services.getPlayer(id);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {
        if (id < 1) throw  new ExceptionsBAD_REQUEST();
        services.deletePlayer(id);
    }

    @GetMapping
    public List<Player> getAllWithoutFilters(){
        return services.getAllWithoutFilters();
    }

    @PostMapping("/{id}")
    public Player updatePlayer(@RequestBody Data params, @PathVariable Long id) {
        if (id < 1) throw  new ExceptionsBAD_REQUEST();

        //  длина значения параметра “name” или “title” превышает размер соответствующего поля в БД (12 и 30 символов);
        //  значение параметра “name” пустая строка;
        if (params.getName() != null) {
            if ( (params.getName().length() > 12) || params.getName().equals("") ) throw  new ExceptionsBAD_REQUEST();
        }
        if (params.getTitle() != null) {
            if (params.getTitle().length() > 30) throw  new ExceptionsBAD_REQUEST();
        }

        //  “birthday”:[Long] < 0;
        if (params.getBirthday() != null) {
            if (params.getBirthday().getTime() < 0) throw  new ExceptionsBAD_REQUEST();
        }

        //  опыт находится вне заданных пределов;
        if (params.getExperience() != null) {
            if (!(params.getExperience() >= 0 && params.getExperience() <= 10_000_000)) throw  new ExceptionsBAD_REQUEST();
        }

        //  дата регистрации находятся вне заданных пределов.
        if (params.getBirthday() != null) {
            Date date0 = new Date(100, 0, 1);
            Date date1 = new Date(1100, 0, 1);
            if (!(params.getBirthday().getYear() >= date0.getYear() && params.getBirthday().getYear() <= date1.getYear())) throw  new ExceptionsBAD_REQUEST();
        }

        // Проверяем рассу.
        if (params.getRace() != null) {
            Race[] races = Race.values();
            for (int i = 0; i < races.length; i++) {
                if (races[i] == params.getRace()) break;
                if (i == races.length - 1) throw  new ExceptionsBAD_REQUEST();
            }
        }

        // Проверяем профессию.
        if (params.getProfession() != null) {
            Profession[] professions = Profession.values();
            for (int i = 0; i < professions.length; i++) {
                if (professions[i] == params.getProfession()) break;
                if (i == professions.length - 1) throw  new ExceptionsBAD_REQUEST();
            }
        }

        return services.updatePlayer(params, id);
    }

    @GetMapping("/count")
    public Long getCount(String name,
                            String title,
                            Race race,
                            Profession profession,
                            Long after,
                            Long before,
                            Boolean banned,
                            Integer minExperience,
                            Integer maxExperience,
                            Integer minLevel,
                            Integer maxLevel) {
        return services.getCount(name, title, race, profession, after, before, banned, minExperience, maxExperience, minLevel, maxLevel);
    }
}
