package com.game.entity;

import java.util.Date;

public class Data {
    private String name;//______________// Имя персонажа (до 12 знаков включительно)
    private String title;//_____________// Титул персонажа (до 30 знаков включительно)
    private Race race;//________________// Расса персонажа
    private Profession profession;//____// Профессия персонажа
    private Date birthday;//____________// Дата регистрации. Диапазон значений года 2000..3000 включительно
    private Boolean banned;//___________// Забанен / не забанен
    private Integer experience;//_______// Опыт персонажа. Диапазон значений 0..10,000,000



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }
}
