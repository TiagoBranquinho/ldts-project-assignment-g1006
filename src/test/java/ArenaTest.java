import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class ArenaTest {
    private Arena arena;
    private Screen screen;
    private Ammo ammo;
    private Player player;
    private Alien alien;

    @BeforeEach
    public void setup(){
        screen = Mockito.mock(Screen.class);
        arena = new Arena(10,10);
        ammo = Mockito.mock(Ammo.class);
        arena.setProjectiles(List.of(ammo));
        alien = Mockito.mock(Alien.class);
        arena.setAliens(List.of(alien));
        player = Mockito.mock(Player.class);
        arena.setPlayer(player);
    }

    @Test
    public void drawAmmo(){
        arena.draw(screen.newTextGraphics());
        Mockito.verify(ammo, Mockito.times(1)).draw(screen.newTextGraphics());
    }

    @Test
    public void drawPlayer(){
        arena.draw(screen.newTextGraphics());
        Mockito.verify(player, Mockito.times(1)).draw(screen.newTextGraphics());
    }

    @Test
    public void drawAlien(){
        arena.draw(screen.newTextGraphics());
        Mockito.verify(player, Mockito.times(1)).draw(screen.newTextGraphics());
    }
}
