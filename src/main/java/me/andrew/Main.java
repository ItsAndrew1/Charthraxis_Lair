package me.andrew;
//PROJECT MADE BY  ItsAndrew

import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Player player = new Player();
        combatManager combat = new combatManager();
        Coinshop coinShop = new Coinshop();
        boolean ok = false;
        boolean gameRunning = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("=========DUNGEON CRAWLER=========");
        System.out.println("1. New Game");
        System.out.println("2. Show Info (recommended for first play-through)");
        System.out.println("Choose an option: ");

        while(!ok){
            int choice = scanner.nextInt();
            scanner.nextLine();

            if(choice == 1){
                newGame(scanner, player);
                ok = true;
            }
            else if(choice == 2){
                System.out.println("Movement - WASD");
                System.out.println("P - Player");
                System.out.println("S - Skeleton");
                System.out.println("Z - Zombie");
                System.out.println("B - Boss");
                System.out.println("C - Treasure Chest");
                System.out.println("E - Exit Key Chest");
            }
        }

        while(gameRunning){
            Rooms room1 = new Rooms(20, 10, 1, player);
            room1.addTreasureChest(6, 15);
            room1.addKeyChest(8, 10);
            room1.addEnemy(new Skeletons(5, 6, 19, 36, 175));
            room1.addEnemy(new Skeletons(4, 2, 19, 36, 175));
            room1.addEnemy(new Zombies(17, 4, 21, 38, 180));
            room1.movement(player, scanner, combat);

            if(!player.isAlive()){
                break;
            }

            accessShop(scanner, coinShop, player);

            System.out.println("Stats: "+player.health+" HP, "+player.attackDmg+" Damage");
            player.mana = player.maxMana;
            Rooms room2 = new Rooms(20, 10, 2, player);
            room2.addTreasureChest(7, 4);
            room2.addKeyChest(8,10);
            room2.addEnemy(new Skeletons(3, 2, 29, 49, 202));
            room2.addEnemy(new Skeletons(16, 5, 29, 49, 202));
            room2.addEnemy(new Zombies(6, 7, 30, 51, 208));
            room2.addEnemy(new Zombies(4, 4, 30, 51, 208));
            room2.movement(player, scanner, combat);

            if(!player.isAlive()){
                break;
            }

            accessShop(scanner, coinShop, player);

            System.out.println("Stats: "+player.health+" HP, "+player.attackDmg+" Damage");
            player.mana = player.maxMana;
            Rooms room3 = new Rooms(20, 10, 3, player);
            room3.addTreasureChest(7, 12);
            room3.addKeyChest(8, 10);
            room3.addEnemy(new Skeletons(4,1, 36, 51, 220));
            room3.addEnemy(new Skeletons(16, 5, 36, 51, 220));
            room3.addEnemy(new Skeletons(1, 4, 36, 51, 220));
            room3.addEnemy(new Zombies(17 , 2, 38, 53, 224));
            room3.addEnemy(new Zombies(3,7, 38, 53, 224));
            room3.movement(player,scanner,combat);

            if(!player.isAlive()){
                break;
            }

            accessShop(scanner, coinShop, player);

            System.out.println("Stats: "+player.health+" HP, "+player.attackDmg+" Damage");
            player.mana = player.maxMana;
            Rooms room4 = new Rooms(20, 10, 4, player);
            room4.addTreasureChest(6, 16);
            room4.addKeyChest(8, 10);
            room4.addEnemy(new Skeletons(3,2, 44, 60, 238));
            room4.addEnemy(new Skeletons(7, 5, 44, 60, 238));
            room4.addEnemy(new Skeletons(17, 6, 44, 60, 238));
            room4.addEnemy(new Zombies(5, 4, 45, 59, 240));
            room4.addEnemy(new Zombies(10, 3, 45, 59, 240));
            room4.addEnemy(new Zombies(2, 5, 45, 59, 240));
            room4.movement(player, scanner, combat);

            if(!player.isAlive()){
                break;
            }

            accessShop(scanner, coinShop, player);
            System.out.println("Stats: "+player.health+" HP, "+player.attackDmg+" Damage");
            player.mana = player.maxMana;
            Rooms room5 = new Rooms(20, 10, 5, player);
            room5.addKeyChest(8, 10);
            room5.addTreasureChest(7, 11);
            room5.addEnemy(new Skeletons(4, 3, 51, 66, 250));
            room5.addEnemy(new Zombies(16, 3, 52, 64, 250));
            room5.addEnemy(new Boss(10, 7, 65, 80, 500));
            room5.movement(player, scanner, combat);

            if (player.isAlive()) {
                System.out.println("Congrats! You defeated Charthraxis and broke the curse!");
            }
            gameRunning = false;
        }
    }

    public static void newGame(Scanner scanner, Player player){
        System.out.println("Enter your characters name: ");
        String inputName = scanner.nextLine();
        player.setName(inputName);
        boolean maybe = false;
        System.out.println("Choose a class: ");

        while (!maybe) {
            System.out.println("Wizard: Different magical abilities!");
            System.out.println("Fighter: Different strong abilities!");
            System.out.println("Ranger: Different nature empowered abilities!");
            System.out.println("Rogue: Squishy but powerful!");
            player.classChoice = scanner.nextLine();
            if (player.classChoice.equalsIgnoreCase("wizard") || player.classChoice.equalsIgnoreCase("fighter") || player.classChoice.equalsIgnoreCase("ranger") || player.classChoice.equalsIgnoreCase("rogue")) {
                switch (player.classChoice){
                    case "wizard":
                        player.health = 765;
                        player.maxHealth = 765;
                        player.mana = 200;
                        player.maxMana = 200;
                        System.out.println("\n\n" + player.getName() + ", welcome to your adventure!");
                        Items basicOrb = new basicOrb(35);
                        player.inventory.add(basicOrb);
                        basicOrb.use(player);
                        player.abilities.add(new Abilities("Arcane Torrent", "A powerful icy whirlwind", 30, 45, 0, true,false, false));
                        player.abilities.add(new Abilities("Chill Strike", "Throws an ice boulder", 50, 40, 0, false, false, true));
                        player.abilities.add(new Abilities("Icy Knife", "A big icy knife", 70, 60, 0, true, false, false));
                        break;

                    case "fighter":
                        player.health = 950;
                        player.maxHealth = 950;
                        player.mana = 75;
                        player.maxMana = 75;
                        Items basicSword = new basicSword(30);
                        player.inventory.add(basicSword);
                        basicSword.use(player);
                        player.abilities.add(new Abilities("Power Strike", "Strikes the enemy with the sword, disabling the enemy", 5, 35, 0, false, false, true));
                        player.abilities.add(new Abilities("Whirlwind Slash", "Swings the sword, creating a powerful whirlwind", 25, 43, 0, true, false, false));
                        player.abilities.add(new Abilities("Anvil of Doom", "Summons a powerful anvil, striking the enemy down", 45, 50, 0, true, false, false));
                        System.out.println("\n\n" + player.getName() + ", welcome to your adventure!");
                        break;

                    case "ranger":
                        player.health = 870;
                        player.maxHealth = 870;
                        player.mana = 120;
                        player.maxMana = 120;
                        Items basicBow = new basicBow(40);
                        player.inventory.add(basicBow);
                        basicBow.use(player);
                        player.abilities.add(new Abilities("Piercing Arrow", "Shoots a powerful arrow", 20, 48, 0, true, false, false));
                        player.abilities.add(new Abilities("Entangling Shot", "Shoots an arrow that entangles the enemy, disabling him", 40, 30, 0, false, false, true));
                        player.abilities.add(new Abilities("Stag's Heart", "Heals you for 8% of your max health", 30, 0, 8.0, false, true, false));
                        System.out.println("\n\n" + player.getName() + ", welcome to your adventure!");
                        break;

                    case "rogue":
                        player.health = 720;
                        player.maxHealth = 720;
                        player.mana = 100;
                        player.maxMana = 100;
                        Items basicKnives = new basicKnives(50);
                        player.inventory.add(basicKnives);
                        basicKnives.use(player);
                        player.abilities.add(new Abilities("Backstab", "Backstabs the enemy, disabling them", 40, 55, 0, false, false, true));
                        player.abilities.add(new Abilities("Vampiric Stab", "Stabs the enemy, dealing damage and healing you for 5% of your Max Health", 35, 45, 5.0, true, true, false));
                        player.abilities.add(new Abilities("Assassination", "Leaps at the enemy and crushes them", 55, 70, 0, true, false, false));
                        System.out.println("\n\n" + player.getName() + ", welcome to your adventure!");
                        break;
                }
                player.inventory.add(new basicHealthPotion(20));
                maybe = true;
            } else {
                System.out.println("Invalid class!");
            }
        }
        System.out.println("Your stats: " + player.health + " HP, " + player.attackDmg + " attack damage, " + player.mana + " Mana" + "\n\n");
    }

    public static void accessShop(Scanner scanner, Coinshop coinshop, Player player){
        String choice;
        boolean ok = false;

        while(!ok){
            if(scanner.hasNextLine()){
                scanner.nextLine();
            }
            System.out.println("Do you want to access the coin shop?(yes/no)");
            choice = scanner.nextLine();

            if(choice.equalsIgnoreCase("yes")){
                coinshop.useShop(player, scanner);
                ok = true;
            }
            else if(choice.equalsIgnoreCase("no")){
                break;
            }
            else{
                System.out.println("Invalid input!");
            }
        }
    }
}