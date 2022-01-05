import com.googlecode.lanterna.graphics.TextGraphics;

public class Alien extends Element{
    private char direction = 'R';

    public Alien(int x, int y, char character){
        super(x, y, character);
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

    public char getDirection(){
        return direction;
    }

    public void changeDirection() {
        if(direction == 'R')
            direction = 'L';
        else if(direction == 'L')
            direction = 'R';
    }

    public void move(){
        if(direction == 'R')
            moveRight();
        if(direction == 'L')
            moveLeft();
    }

    @Override
    public void draw(TextGraphics screen) {
        screen.setCharacter(getPosition().getX(), getPosition().getY(), getCharacter());
    }
}