package com.spaceinvaders.controller;

import com.spaceinvaders.Game;
import com.spaceinvaders.gui.GUI;
import com.spaceinvaders.model.menu.Menu;
import com.spaceinvaders.viewer.menu.StartMenuViewer;

import java.io.IOException;

public class MenuController extends Controller<Menu> {
    private final StartMenuViewer viewer;
    private final GUI gui;

    public MenuController(Menu model, GUI gui) {
        super(model);
        this.gui = gui;
        this.viewer = new StartMenuViewer(getModel(), gui);
    }

    @Override
    public void step(Game game, long time) throws IOException {
        viewer.draw();
        GUI.Action action = gui.getAction();
        switch (action){
            case KEYDOWN -> getModel().nextButton();
            case KEYUP -> getModel().previousButton();
            case ENTER -> getModel().getSelectedButton().click();
            case EXIT -> game.setGameState(null);
        }
    }
}
