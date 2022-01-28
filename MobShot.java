public class MobShot extends Figure implements hasAppearance{

    private int shotX;
    private int shotY;

    public MobShot() {
    }

    public int getShotX() {
        return shotX;
    }

    public void setShotX(int shotX) {
        this.shotX = shotX;
    }

    public int getShotY() {
        return shotY;
    }

    public void setShotY(int shotY) {
        this.shotY = shotY;
    }




    @Override
    public int appearAs() {
            return 4;

    }
}
