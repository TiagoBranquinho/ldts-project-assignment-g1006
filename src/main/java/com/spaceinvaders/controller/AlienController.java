package com.spaceinvaders.controller;

import com.spaceinvaders.model.Alien;
import com.spaceinvaders.model.Arena;
import com.spaceinvaders.model.Position;

import java.util.List;

public class AlienController extends Controller<Arena>{
    public AlienController(Arena model) {
        super(model);
    }

    @Override
    public void step() {
        for (List<Alien> list : getModel().getAliens()){
            for (Alien alien : list){
                move(alien);
            }
        }
    }

    private void move(Alien alien){
        Position newPos = new Position(alien.getPosition().getX() + alien.getDirection(), alien.getPosition().getY());
        if (newPos.getX() >= 0 && newPos.getX() <= getModel().getWidth() - 1)
            alien.setPosition(newPos);
        else
            alien.changeDirection();
    }
}