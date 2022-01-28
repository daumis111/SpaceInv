public class Mob extends Figure implements hasAppearance{
    private int mobX;
    private int mobY;
    boolean canShoot;



    public Mob(int mobX, int mobY) {
        this.mobX = mobX;
        this.mobY = mobY;

    }

    public int getMobX() {
        return mobX;
    }

    public void setMobX(int mobX) {
        this.mobX = mobX;
    }

    public int getMobY() {
        return mobY;
    }

    public void setMobY(int mobY) {
        this.mobY = mobY;
    }



    public boolean isCanShoot() {return canShoot;}

    public void setCanShoot(boolean canShoot) {this.canShoot = canShoot;}


    @Override
    public int appearAs() {
            return 2;
        }
    }

