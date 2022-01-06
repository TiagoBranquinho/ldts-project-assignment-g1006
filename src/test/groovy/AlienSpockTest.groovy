import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import org.mockito.Mock
import spock.lang.Specification

class AlienSpockTest extends Specification{
    private Screen screen

    def setup(){
        def terminalSize = new TerminalSize(20, 20)
        def terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize)
        def terminal = terminalFactory.createTerminal()
        Screen screen = new TerminalScreen(terminal)
        this.screen = screen
    }

    def "alien movement"(){
        given:
        def alien = new Alien(10,20,'A' as char)

        when:
        alien.move()

        then:
        alien.getPosition() == new Position(11,20);

        when:
        alien.move()

        then:
        alien.getPosition() == new Position(12,20);
    }

    def "change alien movement direction"(){
        given:
        def alien = new Alien(10,10, 'A' as char)

        when:
        alien.changeDirection()

        then:
        alien.getDirection() == -1
    }

    def "alien movement decision"(){
        given:
        def alien = new Alien(10,10, 'A' as char)

        when:
        alien.move()

        then:
        alien.getPosition() == new Position(11,10);

        when:
        alien.changeDirection()
        alien.move()
        alien.move()

        then:
        alien.getPosition() == new Position(9,10);
    }

    def "draw alien character"(){
        given:
        def alien = new Alien(10,10, 'A' as char)

        when:
        alien.draw(screen.newTextGraphics())

        then:
        screen.getBackCharacter(10,10).getCharacter() == alien.getCharacter()
    }

    def "test if there is an alien below"(){
        given:
        def alien1 = new Alien(10,10,'A' as char)
        def alien2 = new Alien(10,11,'A' as char)
        def arena = Mock(Arena.class)
        arena.getAliens() >> [alien1, alien2]

        expect:
        !alien1.freeToShoot(arena)

    }

    def "test if there is an alien below in the same column"(){
        given:
        def alien1 = new Alien(10,10,'A' as char)
        def alien2 = new Alien(10,19,'A' as char)
        def arena = Mock(Arena.class)
        arena.getAliens() >> [alien1, alien2]

        expect:
        !alien1.freeToShoot(arena)

    }

    def "test if there isn't any alien below"(){
        given:
        def alien1 = new Alien(10,10,'A' as char)
        def alien2 = new Alien(11,12,'A' as char)
        def arena = Mock(Arena.class)
        arena.getAliens() >> [alien1, alien2]

        expect:
        alien1.freeToShoot(arena)

    }


    def "test alien shooting above another one"(){
        given:
        def alien1 = new Alien(10,10,'A' as char)
        def alien2 = new Alien(10,12,'A' as char)
        def projectiles = []
        def arena = Mock(Arena.class)
        alien1.freeToShoot(arena) >> false
        arena.setProjectiles([])

        when:
        alien1.shoot(arena)

        then:
        arena.getProjectiles().isEmpty()
    }

    def "test alien shooting below another one"() {
        given:
        def alien1 = new Alien(10, 10, 'A' as char)
        def alien2 = new Alien(10, 12, 'A' as char)
        def arena = Mock(Arena.class)
        alien2.freeToShoot(arena) >> true
        arena.setProjectiles([])

        when:
        alien2.shoot(arena)

        then:
        arena.getProjectiles()[0].getPosition() == new Position(10,13)
    }
}

