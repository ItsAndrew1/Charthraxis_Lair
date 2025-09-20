package me.andrew;

import java.util.ArrayList;
import java.util.Scanner;

public class Rooms{
    int width, height, roomNR;
    int roomX, roomY;
    int chestX, chestY;
    int keyChestX, keyChestY;
    int chestCoins;
    int coinsGathered;
    private boolean chestOpened;
    Tiles wall = new Tiles('#', "wall", false);
    Tiles floor = new Tiles('.', "floor",true);
    Tiles treasureChest;
    Tiles exitKey;
    Tiles Player = new Tiles('P', "player", true);
    Tiles Enemy;
    ArrayList<Enemies> enemyList;
    ArrayList<Items> lootChest;

    Tiles[][] grid;

    public Rooms(int width, int height, int roomNR, Player player){
        this.width = width;
        this.height = height;
        this.roomNR = roomNR;

        player.playerX = 10;
        player.playerY = 1;

        coinsGathered = 0;

        chestOpened = false;
        grid = new Tiles[height][width];

        for(roomY = 0; roomY < height; roomY++){
            for(roomX = 0; roomX < width; roomX++){
                if(roomY == 0 || roomY == height - 1|| roomX == 0 || roomX == width - 1){
                    grid[roomY][roomX] = wall;
                }
                else{
                    grid[roomY][roomX] = floor;
                }
            }
        }
        enemyList = new ArrayList<>();
        addLoot(player);
    }

    public void render(Player player){
        System.out.println("=======ROOM "+roomNR+"=======");
        grid[player.playerY][player.playerX] = Player;
        for(roomY = 0; roomY < height; roomY++){
            for(roomX = 0; roomX < width; roomX++){
                if(roomX == player.playerX && roomY == player.playerY){
                    System.out.print(Player.symbol);
                }
                else if(isEnemyAt(roomX, roomY)){
                    System.out.print(getEnemyAt(roomX, roomY).symbol);
                }
                else if(roomX == 0 && roomY == 0){
                    System.out.print(wall.symbol);
                }
                else if(isTreasureChest(roomX, roomY)){
                    System.out.print(getTreasureChest(roomX, roomY).symbol);
                }
                else if(isKeyChest(roomX, roomY)){
                    System.out.print(getKeyChest(roomX, roomY).symbol);
                }
                else if(((roomX > 0 && roomY == 0) || (roomX > 0 && roomY == 9)) || ((roomX == 0 && roomY >= 1) || (roomX == 19 && roomY >= 1))){
                    System.out.print(wall.symbol);
                }
                else{
                    System.out.print(floor.symbol);
                }
            }
            System.out.println();
        }
    }

    public void addEnemy(Enemies enemy){
        Enemy = new Tiles(enemy.symbol, "enemy", true);
        enemyList.add(enemy);
        grid[enemy.enemyY][enemy.enemyX] = Enemy;
    }

    public void addTreasureChest(int y, int x){
        chestX = x;
        chestY = y;
        treasureChest = new Tiles('C', "chest", true);
        grid[chestY][chestX] = treasureChest;
    }

    public void addKeyChest(int y, int x){
        keyChestX = x;
        keyChestY = y;
        exitKey = new Tiles('E', "key", true);
        grid[keyChestY][keyChestX] = exitKey;
    }

    public void movement(Player player, Scanner scanner, combatManager combat){
        boolean running = true;
        char direction;

        while(running && player.health > 0){
            render(player);
            int targetX, targetY;

            direction = scanner.next().charAt(0);
            switch(direction){
                case 'w':
                    targetX = player.playerX;
                    targetY = player.playerY - 1;
                    if(targetY < 0 || !grid[targetY][targetX].walkable){
                        System.out.println("You can't go that way!");
                    }
                    else if(isEnemyAt(targetX, targetY)){
                        Enemies enemy = getEnemyAt(targetX, targetY);
                        combat.fight(player,enemy,scanner);
                        enemyList.remove(enemy);
                        coinsGathered += 50;
                    }
                    else if(isTreasureChest(targetX, targetY)){
                        if(enemyList.isEmpty()){
                            player.playerY = targetY;
                            chestLooting(player);
                            chestOpened = true;
                            coinsGathered += chestCoins;
                        }
                        else{
                            System.out.println("You still have activities to do!");
                        }
                    }
                    else if(isKeyChest(targetX, targetY)){
                        if(chestOpened){
                            System.out.println("\n\n\n\nCongrats on beating room "+roomNR+"! Coins gathered: "+coinsGathered);
                            player.coins += coinsGathered;
                            if(player.maxHealth - player.health >= 200){
                                player.heal(200);
                            }
                            else if(player.maxHealth - player.health < 200){
                                player.heal(player.maxHealth - player.health);
                            }
                            running = false;
                        }
                        else{
                            System.out.println("You still have activities to do!");
                        }
                    }
                    else{
                        player.playerY = targetY;
                    }
                    break;

                case 's':
                    targetX = player.playerX;
                    targetY = player.playerY + 1;
                    if(targetY<0 || !grid[targetY][targetX].walkable){
                        System.out.println("You can't go that way!");
                    }
                    else if(isEnemyAt(targetX, targetY)){
                        Enemies enemy = getEnemyAt(targetX, targetY);
                        combat.fight(player,enemy,scanner);
                        enemyList.remove(enemy);
                        coinsGathered += 50;
                    }
                    else if(isTreasureChest(targetX, targetY)){
                        if(enemyList.isEmpty()){
                            player.playerY = targetY;
                            chestLooting(player);
                            chestOpened = true;
                            coinsGathered += chestCoins;
                        }
                        else{
                            System.out.println("You still have activities to do!");
                        }
                    }
                    else if(isKeyChest(targetX, targetY)){
                        if(chestOpened){
                            System.out.println("\n\n\n\nCongrats on beating room "+roomNR+"! Coins gathered: "+coinsGathered);
                            player.coins += coinsGathered;
                            if(player.maxHealth - player.health >= 200){
                                player.heal(200);
                            }
                            else if(player.maxHealth - player.health < 200){
                                player.heal(player.maxHealth - player.health);
                            }
                            running = false;
                        }
                        else{
                            System.out.println("You still have activities to do!");
                        }
                    }
                    else{
                        player.playerY = targetY;
                    }
                    break;

                case 'a':
                    targetX = player.playerX - 1;
                    targetY = player.playerY;
                    if(targetX<0 || !grid[targetY][targetX].walkable){
                        System.out.println("You can't go that way!");
                    }
                    else if(isEnemyAt(targetX, targetY)){
                        Enemies enemy = getEnemyAt(targetX, targetY);
                        combat.fight(player,enemy,scanner);
                        enemyList.remove(enemy);
                        coinsGathered += 50;
                    }
                    else if(isTreasureChest(targetX, targetY)){
                        if(enemyList.isEmpty()){
                            player.playerX = targetX;
                            chestLooting(player);
                            chestOpened = true;
                            coinsGathered += chestCoins;
                        }
                        else{
                            System.out.println("You still have activities to do!");
                        }
                    }
                    else if(isKeyChest(targetX, targetY)){
                        if(chestOpened){
                            System.out.println("\n\n\n\nCongrats on beating room "+roomNR+"! Coins gathered: "+coinsGathered);
                            player.coins += coinsGathered;
                            if(player.maxHealth - player.health >= 200){
                                player.heal(200);
                            }
                            else if(player.maxHealth - player.health < 200){
                                player.heal(player.maxHealth - player.health);
                            }
                            running = false;
                        }
                        else{
                            System.out.println("You still have activities to do!");
                        }
                    }
                    else{
                        player.playerX = targetX;
                    }
                    break;

                case 'd':
                    targetX = player.playerX + 1;
                    targetY = player.playerY;
                    if(targetX<0 || !grid[targetY][targetX].walkable){
                        System.out.println("You can't go that way!");
                    }
                    else if(isEnemyAt(targetX, targetY)){
                        Enemies enemy = getEnemyAt(targetX, targetY);
                        combat.fight(player,enemy,scanner);
                        enemyList.remove(enemy);
                        coinsGathered += 50;
                    }
                    else if(isTreasureChest(targetX, targetY)){
                        if(enemyList.isEmpty()){
                            chestLooting(player);
                            player.playerX = targetX;
                            chestOpened = true;
                            coinsGathered += chestCoins;
                        }
                        else{
                            System.out.println("You still have activities to do!");
                        }
                    }
                    else if(isKeyChest(targetX, targetY)){
                        if(chestOpened){
                            System.out.println("\n\n\n\nCongrats on beating room "+roomNR+"! Coins gathered: "+coinsGathered);
                            player.coins += coinsGathered;
                            if(player.maxHealth - player.health >= 200){
                                player.heal(200);
                            }
                            else if(player.maxHealth - player.health < 200){
                                player.heal(player.maxHealth - player.health);
                            }
                            running = false;
                        }
                        else{
                            System.out.println("You still have activities to do!");
                        }
                    }
                    else{
                        player.playerX = targetX;
                    }
                    break;

                default:
                    System.out.println("Invalid input!");
            }
        }
    }

    private boolean isEnemyAt(int x, int y){
        for(Enemies e : enemyList){
            if(x == e.enemyX && y == e.enemyY){
                return true;
            }
        }
        return false;
    }
    private Enemies getEnemyAt(int x, int y){
        for(Enemies e : enemyList){
            if(x == e.enemyX && y == e.enemyY){
                return e;
            }
        }
        return null;
    }

    private boolean isTreasureChest(int x, int y){
        return !chestOpened && x == chestX && y == chestY;
    }
    private Tiles getTreasureChest(int x, int y){
        if(x == chestX && y == chestY){
            return treasureChest;
        }
        else{
            return null;
        }
    }
    public void chestLooting(Player player){
        for(Items e : lootChest){
            System.out.println(player.getName()+" has got "+e.getName()+" from the loot chest!");
            player.inventory.add(e);
        }
        System.out.println(player.getName()+" has got "+chestCoins+" coins!\n");
    }
    public void addLoot(Player player){
        lootChest = new ArrayList<>();

        switch(roomNR){
            case 1:
                chestCoins = 100;
                if(player.classChoice.equalsIgnoreCase("wizard")){
                    lootChest.add(new basicHealthPotion(20));
                    lootChest.add(new basicManaPotion(25));
                }
                else if(player.classChoice.equalsIgnoreCase("ranger")){
                    lootChest.add(new basicManaPotion(25));
                    lootChest.add(new basicManaPotion(25));
                }
                else if(player.classChoice.equalsIgnoreCase("fighter")){
                    lootChest.add(new basicHealthPotion(20));
                    lootChest.add(new basicHealthPotion(20));
                }
                else if(player.classChoice.equalsIgnoreCase("rogue")){
                    lootChest.add(new basicHealthPotion(20));
                    lootChest.add(new basicHealthPotion(20));
                }
                break;

            case 2:
                chestCoins = 300;
                if(player.classChoice.equalsIgnoreCase("wizard")){
                    lootChest.add(new basicManaPotion(25));
                    lootChest.add(new basicManaPotion(25));
                }
                else if(player.classChoice.equalsIgnoreCase("ranger")){
                    lootChest.add(new basicHealthPotion(20));
                    lootChest.add(new basicHealthPotion(20));
                }
                else if(player.classChoice.equalsIgnoreCase("fighter")){
                    lootChest.add(new basicHealthPotion(20));
                    lootChest.add(new basicManaPotion(25));
                }
                else if(player.classChoice.equalsIgnoreCase("rogue")){
                    lootChest.add(new basicHealthPotion(20));
                    lootChest.add(new basicHealthPotion(20));
                    lootChest.add(new basicManaPotion(25));
                }
                break;

            case 3:
                chestCoins = 450;
                if(player.classChoice.equalsIgnoreCase("wizard")){
                    lootChest.add(new basicHealthPotion(20));
                    lootChest.add(new basicManaPotion(25));
                    lootChest.add(new greaterHealthPotion(40));
                }
                else if(player.classChoice.equalsIgnoreCase("ranger")){
                    lootChest.add(new basicManaPotion(25));
                    lootChest.add(new basicManaPotion(25));
                    lootChest.add(new greaterManaPotion(40));
                }
                else if(player.classChoice.equalsIgnoreCase("fighter")){
                    lootChest.add(new basicHealthPotion(20));
                    lootChest.add(new basicManaPotion(25));
                    lootChest.add(new greaterHealthPotion(40));
                }
                else if(player.classChoice.equalsIgnoreCase("rogue")){
                    lootChest.add(new basicHealthPotion(20));
                    lootChest.add(new greaterHealthPotion(40));
                    lootChest.add(new basicManaPotion(25));
                }
                break;

            case 4:
                chestCoins = 600;
                if(player.classChoice.equalsIgnoreCase("wizard")){
                    lootChest.add(new basicHealthPotion(20));
                    lootChest.add(new basicManaPotion(25));
                    lootChest.add(new greaterManaPotion(40));
                    lootChest.add(new greaterHealthPotion(40));
                }
                else if(player.classChoice.equalsIgnoreCase("ranger")){
                    lootChest.add(new basicManaPotion(25));
                    lootChest.add(new basicHealthPotion(20));
                    lootChest.add(new greaterHealthPotion(40));
                }
                else if(player.classChoice.equalsIgnoreCase("fighter")){
                    lootChest.add(new basicHealthPotion(20));
                    lootChest.add(new basicHealthPotion(20));
                    lootChest.add(new greaterHealthPotion(40));
                    lootChest.add(new basicManaPotion(25));
                }
                else if(player.classChoice.equalsIgnoreCase("rogue")){
                    lootChest.add(new basicHealthPotion(20));
                    lootChest.add(new greaterHealthPotion(40));
                    lootChest.add(new basicManaPotion(25));
                    lootChest.add(new basicManaPotion(25));
                }
                break;

            case 5:
                lootChest.add(new rubyOfTheFallenDragon());
                break;
        }
    }

    private boolean isKeyChest(int x, int y){
        return x == keyChestX && y == keyChestY;
    }
    private Tiles getKeyChest(int x, int y){
        if(x == keyChestX && y == keyChestY){
            return exitKey;
        }
        else{
            return null;
        }
    }
}

class Tiles{
    boolean walkable;
    char symbol;
    String type;

    public Tiles(char symbol, String type, boolean walkable){
        this.symbol = symbol;
        this.type = type;
        this.walkable = walkable;
    }
}