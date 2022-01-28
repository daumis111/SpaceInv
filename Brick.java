public class Brick extends Figure implements hasAppearance{

    private final int brickX;
    private final int brickY;


    public int getBrickX() {
        return brickX;
    }

    public int getBrickY() {
        return brickY;
    }




    public Brick(int brickX, int brickY) {
        this.brickX = brickX;
        this.brickY = brickY;
    }


    @Override
    public int appearAs() {
            return 3;

    }
}


