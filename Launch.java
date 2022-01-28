import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

public class Launch {
    public static void main(String[] args) {

        boolean quit = false;
        Rocket Rocket = new Rocket(9, 19);
        ArrayList<Mob> Mobs = createMobs();
        ArrayList<Brick> Bricks = createBricks();
        ArrayList<MobShot> MobShots= new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        String inserted;

        GameMap.setRocketsPosition(Rocket.getRocketX(), Rocket.getRocketY(), GameMap.getGameMap(), Rocket.appearAs());
        GameMap.setMobsPosition(Mobs, GameMap.getGameMap());
        GameMap.setBricksPosition(Bricks, GameMap.getGameMap());

        GameMap.Output(GameMap.getMap());
        while (!quit) {
            System.out.println("-----------------------------------------");
            System.out.println("CONTROLS w-SHOT a-LEFT d-RIGHT q-QUIT");
        inserted = sc.next();

        switch (inserted) {
            case "a" -> GameRules.MoveRocketLeft(Rocket, GameMap.getGameMap());
            case "d" -> GameRules.MoveRocketRight(Rocket, GameMap.getGameMap());
            case "w" -> GameRules.RocketShot(Rocket,Mobs,Bricks,GameMap.getGameMap());
            case "q" -> quit = true;
            default -> System.out.println("Wrong value");

        }
            if((!inserted.equals("a") && !inserted.equals("d"))&& GameRules.Suicide(MobShots, Rocket) && !GameRules.BrickProtection(Bricks, Rocket)){quit=true;}
            GameRules.Move(Mobs, GameRules.mobStatus, MobShots);
            GameRules.CreateMobShot(Rocket,Mobs,MobShots, GameMap.getGameMap());
            GameRules.ShotColision(MobShots,Bricks);
            GameMap.setRocketsPosition(Rocket.getRocketX(), Rocket.getRocketY(), GameMap.getGameMap(), Rocket.appearAs());
            if(GameRules.isGameWon(Mobs)){quit=true;}
            if(GameRules.GameOverRule(Mobs, Rocket, MobShots, GameMap.getGameMap())){quit=true;}
            GameMap.Output(GameMap.getMap());
    }


}

    private static ArrayList<Brick> createBricks() {
        Brick Brick1=new Brick(1,18);
        Brick Brick2=new Brick(7,18);
        Brick Brick3=new Brick(13,18);
        Brick Brick4=new Brick(19,18);
        Brick Brick5=new Brick(2,18);
        Brick Brick6=new Brick(8,18);
        Brick Brick7=new Brick(14,18);
        Brick Brick8=new Brick(18,18);

        ArrayList<Brick> Bricks= new ArrayList<>();
        Bricks.add(0,Brick1);
        Bricks.add(1,Brick2);
        Bricks.add(2,Brick3);
        Bricks.add(3,Brick4);
        Bricks.add(4,Brick5);
        Bricks.add(5,Brick6);
        Bricks.add(6,Brick7);
        Bricks.add(7,Brick8);
        return Bricks;
    }

    private static ArrayList<Mob> createMobs() {
        Mob Mob1 = new Mob(0, 0);
        Mob Mob2 = new Mob(1, 0);
        Mob Mob3 = new Mob(2, 0);
        Mob Mob4 = new Mob(3, 0);
        Mob Mob5 = new Mob(0, 1);
        Mob Mob6 = new Mob(1, 1);
        Mob Mob7 = new Mob(2, 1);
        Mob Mob8 = new Mob(3, 1);
        Mob Mob9 = new Mob(0, 2);
        Mob Mob10 = new Mob(1, 2);
        Mob Mob11 = new Mob(2, 2);
        Mob Mob12= new Mob(3, 2);
        Mob Mob13= new Mob(0, 3);
        Mob Mob14= new Mob(1, 3);
        Mob Mob15= new Mob(2, 3);
        Mob Mob16= new Mob(3, 3);


        ArrayList<Mob> Mobs = new ArrayList<>();
        Mobs.add(0, Mob1);
        Mobs.add(1, Mob2);
        Mobs.add(2, Mob3);
        Mobs.add(3, Mob4);
        Mobs.add(4, Mob5);
        Mobs.add(5, Mob6);
        Mobs.add(6, Mob7);
        Mobs.add(7, Mob8);
        Mobs.add(8, Mob9);
        Mobs.add(9, Mob10);
        Mobs.add(10, Mob11);
        Mobs.add(11, Mob12);
        Mobs.add(12, Mob13);
        Mobs.add(13, Mob14);
        Mobs.add(14, Mob15);
        Mobs.add(15, Mob16);
        return Mobs;
    }

   /* @Test
    public void testIfMobsCreated1(ArrayList<Mob> Mobs){
        int x=Constants.MOBS;
        Assert.assertEquals(x,Mobs.size());
    }*/
  /*  @Test
    public void testIfBricksCreated1(ArrayList<Brick> Bricks){
        int y=Constants.BRICKS;
        Assert.assertEquals(y,Bricks.size());
    }
*/


}



