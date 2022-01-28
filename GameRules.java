import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class GameRules {

    public static String mobStatus = "Right";


    public GameRules(String mobStatus) {
        GameRules.mobStatus = mobStatus;
    }

    public static void MoveRocketLeft(Rocket rocket, int[][] gameMap) {
        if (rocket.getRocketX() > 0) {
            gameMap[rocket.getRocketY()][rocket.getRocketX()] = 0;
            rocket.setRocketX(rocket.getRocketX() - 1);
            GameMap.setRocketsPosition(rocket.getRocketX(), rocket.getRocketY(), gameMap, rocket.appearAs());
        }


    }

    public static void MoveRocketRight(Rocket rocket, int[][] gameMap) {
        if (rocket.getRocketX() < gameMap.length - 1) {
            gameMap[rocket.getRocketY()][rocket.getRocketX()] = 0;
            rocket.setRocketX(rocket.getRocketX() + 1);
            GameMap.setRocketsPosition(rocket.getRocketX(), rocket.getRocketY(), gameMap, rocket.appearAs());
        }


    }

    public static void MoveMobsRight(ArrayList<Mob> Mobs, int[][] gameMap) {
        var maxIndex = new Object() {
            int maxIndex = 0;
        };
        Mobs.forEach(mob -> {
            if (mob.getMobX() > maxIndex.maxIndex) {
                maxIndex.maxIndex = mob.getMobX();
            }
        });
        if (maxIndex.maxIndex < gameMap.length - 1 && Objects.equals(mobStatus, "Right")) {
            for (int i = Mobs.size() - 1; i >= 0; i--) {
                gameMap[Mobs.get(i).getMobY()][Mobs.get(i).getMobX()] = 0;
                Mobs.get(i).setMobX(Mobs.get(i).getMobX() + 1);
                GameMap.setMobsPosition(Mobs, gameMap);

            }

        }
        if (maxIndex.maxIndex == gameMap.length - 1) {
            mobStatus = "Bottom";
        }


    }

    public static void MoveMobsLeft(ArrayList<Mob> Mobs, int[][] gameMap) {
        var minIndex = new Object() {
            int mostLeftMobIndex = gameMap.length - 1;
        };
        Mobs.forEach(mob -> {
            if (mob.getMobX() < minIndex.mostLeftMobIndex) {
                minIndex.mostLeftMobIndex = mob.getMobX();
            }
        });

        if (minIndex.mostLeftMobIndex > 0) {
            for (int i = 0; i < Mobs.size(); i++) {
                gameMap[Mobs.get(i).getMobY()][Mobs.get(i).getMobX()] = 0;
                Mobs.get(i).setMobX(Mobs.get(i).getMobX() - 1);
                GameMap.setMobsPosition(Mobs, gameMap);

            }

        }
        if (minIndex.mostLeftMobIndex == 0) {
            mobStatus = "Bottom";
        }


    }


    public static void MoveMobsBottom(ArrayList<Mob> Mobs, int[][] gameMap) {
        var maxIndex = new Object() {
            int mostRightMobIndex = 0;
        };
        Mobs.forEach(mob -> {
            if (mob.getMobX() > maxIndex.mostRightMobIndex) {
                maxIndex.mostRightMobIndex = mob.getMobX();
            }
        });

        var minIndex = new Object() {
            int minIndex = gameMap.length;
        };
        Mobs.forEach(mob -> {
            if (mob.getMobX() < minIndex.minIndex) {
                minIndex.minIndex = mob.getMobX();
            }
        });

        for (int i = 0; i < Mobs.size(); i++) {

            gameMap[Mobs.get(i).getMobY()][Mobs.get(i).getMobX()] = 0;
            Mobs.get(i).setMobY(Mobs.get(i).getMobY() + 1);
            GameMap.setMobsPosition(Mobs, gameMap);


        }
        if (maxIndex.mostRightMobIndex == gameMap.length - 1 ||maxIndex.mostRightMobIndex == gameMap.length - 2 ) {
            mobStatus = "Left";
        }
        if (minIndex.minIndex == 0||minIndex.minIndex == 1 ) {
            mobStatus = "Right";
        }
    }

    public static void Move(ArrayList<Mob> Mobs, String mobStatus, ArrayList<MobShot> MobShots) {
        if (Objects.equals(mobStatus, "Right")) {
            MoveMobsRight(Mobs, GameMap.getGameMap());
        }
        if (Objects.equals(mobStatus, "Left")) {
            MoveMobsLeft(Mobs, GameMap.getGameMap());
        }
        if (Objects.equals(mobStatus, "Bottom")) {
            MoveMobsBottom(Mobs, GameMap.getGameMap());
        }

        ShotMovement(MobShots);

    }

    private static void ShotMovement(ArrayList<MobShot> MobShots) {
        for(int i=0;i<MobShots.size();i++){
           if( MobShots.get(i).getShotY()<GameMap.getGameMap().length-2 ) {
               GameMap.gameMap[MobShots.get(i).getShotY()][MobShots.get(i).getShotX()]=0;
               MobShots.get(i).setShotY(MobShots.get(i).getShotY()+2);
               GameMap.gameMap[MobShots.get(i).getShotY()][MobShots.get(i).getShotX()]=MobShots.get(i).appearAs();
           }
           else if(MobShots.get(i).getShotY()>=GameMap.getGameMap().length-2){
               GameMap.gameMap[MobShots.get(i).getShotY()][MobShots.get(i).getShotX()]=0;
               MobShots.remove(i);
           }


        }
    }

    public static void ShotColision(ArrayList<MobShot> MobShots, ArrayList<Brick> Bricks) {

            for (int i = 0; i < MobShots.size(); i++) {
                for (int j = 0; j < Bricks.size(); j++) {
                    if ((MobShots.get(i).getShotX() == Bricks.get(j).getBrickX() && MobShots.get(i).getShotY() == Bricks.get(j).getBrickY()) || (
                            MobShots.get(i).getShotX() == Bricks.get(j).getBrickX() && MobShots.get(i).getShotY() == Bricks.get(j).getBrickY() + 1)) {
                        GameMap.gameMap[Bricks.get(j).getBrickY()][Bricks.get(j).getBrickX()] = 0;
                        Bricks.remove(j);
                        GameMap.gameMap[MobShots.get(i).getShotY()][MobShots.get(i).getShotX()] = 0;
                        MobShots.remove(i);
                        break;

                    }
                }
            }

    }

    public static boolean Suicide(ArrayList<MobShot> MobShots, Rocket rocket) {

        for (int i = 0; i < MobShots.size(); i++) {
            if(MobShots.get(i).getShotX()== rocket.getRocketX() && MobShots.get(i).getShotY() ==rocket.getRocketY()-1)
            {   GameMap.gameMap[rocket.getRocketY()][rocket.getRocketX()] = 0;
                GameMap.gameMap[MobShots.get(i).getShotY()][MobShots.get(i).getShotX()]=0;
                    System.out.println("Game over. You died");
                    MobShots.remove(i);
                    return true;

            }

        }
        return false;


    }

    public static boolean BrickProtection(ArrayList<Brick> bricks, Rocket rocket){
        for (Brick brick : bricks) {
            if (rocket.getRocketY() == brick.getBrickY() + 1 && rocket.getRocketX() == brick.getBrickX())
                return true;
        }
        return false;
    }




    public static boolean GameOverRule(ArrayList<Mob> Mobs, Rocket rocket, ArrayList<MobShot> MobShots, int[][] gameMap) {
        var lowIndex = new Object() {
            int mostBottomMobIndex = 0;
        };
        Mobs.forEach(mob -> {
            if (mob.getMobY() > lowIndex.mostBottomMobIndex) {
                lowIndex.mostBottomMobIndex = mob.getMobY();
            }
        });
        for (Mob mob : Mobs) {
            if (gameMap[rocket.getRocketY()][rocket.getRocketX()] == gameMap[mob.getMobY()][mob.getMobX()]) {
                System.out.println("You died, game over");
                return true;
            }

            if (lowIndex.mostBottomMobIndex == gameMap[0].length - 1 && Objects.equals(mobStatus, "Bottom")) {
                System.out.println("You lost, game over");
                return true;

            }
        }
        for (MobShot mobShot : MobShots) {
            if (gameMap[rocket.getRocketY()][rocket.getRocketX()] == gameMap[mobShot.getShotY()][mobShot.getShotX()]) {
                System.out.println("You died, game over");
                return true;
            }
        }


        return false;
    }
  /*  @Test
    public void testIfGameIsNotWon(ArrayList<Mob> Mobs){
        Assert.assertEquals(Mobs.size()!=0,Mobs.size());
    }*/



    public static boolean isGameWon (ArrayList<Mob> Mobs){

        if(Mobs.isEmpty()){System.out.println("Congratulations, you won the game!"); return true;}

        return false;
    }

    public static Boolean RocketShotBrick(Rocket rocket, ArrayList<Brick> Bricks, int[][] gameMap) {
        for (int i = 0; i < Bricks.size(); i++) {
            if (Bricks.get(i).getBrickX() == rocket.getRocketX()) {
                gameMap[Bricks.get(i).getBrickY()][Bricks.get(i).getBrickX()] = 0;
                Bricks.remove(i);
                return true;
            }

        }
        return false;
    }

    public static void RocketShotMob(Rocket rocket, ArrayList<Mob> Mobs, int[][] gameMap) {
        int maxIndex = 0;
        int a = 0;
        for (int i = 0; i < Mobs.size(); i++) {
            if (Mobs.get(i).getMobX() == rocket.getRocketX() && maxIndex <= i) {
                maxIndex = i;
                a++;
            }

        }
        if (a != 0) {
            gameMap[Mobs.get(maxIndex).getMobY()][Mobs.get(maxIndex).getMobX()] = 0;
            Mobs.remove(maxIndex);
        }

    }

    public static void RocketShot(Rocket rocket, ArrayList<Mob> Mobs, ArrayList<Brick> Bricks, int[][] gameMap) {

        if (!RocketShotBrick(rocket, Bricks, GameMap.getGameMap())) {
            RocketShotMob(rocket, Mobs, gameMap);
        } else {
            RocketShotBrick(rocket, Bricks, GameMap.getGameMap());
        }

    }


    public static void CreateMobShot(Rocket rocket, ArrayList<Mob> Mobs, ArrayList<MobShot> Mobshots, int[][] gameMap) {
        Random random=new Random();
        for (Mob value : Mobs) {
            value.setCanShoot(gameMap[value.getMobY() + 1][value.getMobX()] != 2);
        }

        Mobs.forEach(mob -> {if(mob.getMobY()>rocket.getRocketY()-2){mob.setCanShoot(false);}});


        for (Mob mob : Mobs) {
            if (mob.isCanShoot()) {
                int x = random.nextInt(100);
                if (x > 80 && Mobshots.size() == 0) {
                    MobShot Mobshot1 = new MobShot();
                    Mobshots.add(0, Mobshot1);
                    Mobshot1.setShotX(mob.getMobX());
                    Mobshot1.setShotY(mob.getMobY() + 2);
                    GameMap.gameMap[Mobshot1.getShotY()][Mobshot1.getShotX()] = Mobshot1.appearAs();

                }
            }

        }



}





}