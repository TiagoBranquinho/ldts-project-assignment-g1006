import com.googlecode.lanterna.graphics.TextGraphics;

public class Player extends Element{

    public Player(int x, int y, char character){
        super(x,y,character);
    }

    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
    }

    @Override
    public Position getPosition() {
        return super.getPosition();
    }

    public void moveLeft(){
        setPosition(new Position(getPosition().getX() - 1, getPosition().getY()));
    }

    public void moveRight() {
        setPosition(new Position(getPosition().getX() + 1, getPosition().getY()));
    }

    public Ammo shoot(){
        return new Ammo(getPosition().getX(), getPosition().getY() - 1, 'B', -1, 1);
    }

    @Override
    public void draw(TextGraphics screen) {
        screen.setCharacter(getPosition().getX(), getPosition().getY(), getCharacter());
    }
}