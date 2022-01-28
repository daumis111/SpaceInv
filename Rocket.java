public class Rocket implements hasAppearance {

    private int rocketX;
    private final int rocketY;

    public Rocket(int rocketX, int rocketY) {
        this.rocketX = rocketX;
        this.rocketY = rocketY;

    }

    public int getRocketX() {
        return rocketX;
    }

    public void setRocketX(int rocketX) {
        this.rocketX = rocketX;
    }

    public int getRocketY() {
        return rocketY;
    }


    @Override
    public int appearAs() {
        return 1;

    }
}
