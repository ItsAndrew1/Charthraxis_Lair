package me.andrew;

import java.util.ArrayList;
import java.util.Scanner;

public class Coinshop {
    ArrayList<Items> shopItems = new ArrayList<>();

    public void useShop(Player player, Scanner scanner){
        int choice;
        shopItems.clear();
        addShopItems(player);
        System.out.println("=======COIN SHOP=======");

        System.out.println("Coins: "+player.coins);

        int i = 1;
        for(Items e : shopItems){
            System.out.println(i+") " +e.getName() +" - "+e.price+" coins");
            i++;
        }
        System.out.println(i + ") Greater Potion of Health (+40 HP) - 75 coins");
        System.out.println((i+1) + ") Greater Potion of Mana (+40 Mana) - 75 coins");
        System.out.println((i+2) + ") Quit shop");
        System.out.println("Choose an option: ");

        boolean buying = true;
        while(buying){
            choice = scanner.nextInt();

            if(choice >= 1 && choice <= shopItems.size()){
                Items itemChosen = shopItems.get(choice - 1);
                if(player.coins >= itemChosen.price){
                    player.inventory.add(itemChosen);
                    itemChosen.use(player);
                    player.coins -= itemChosen.price;
                    System.out.println("Successfully bought "+ itemChosen.getName());
                    System.out.println("Remaining coins: "+player.coins);
                }
                else{
                    System.out.println("Not enough coins");
                }
            }
            else if(choice == shopItems.size()+1){
                if(player.coins >= 75){
                    player.inventory.add(new greaterHealthPotion(40));
                    player.coins -= 75;
                    System.out.println("Successfully bought Greater Potion of Health (+40 HP)");
                    System.out.println("Remaining coins: "+player.coins);
                }
                else{
                    System.out.println("Not enough coins!");
                }
            }
            else if(choice == shopItems.size() + 2){
                if(player.coins >= 75){
                    player.inventory.add(new greaterManaPotion(40));
                    player.coins -= 75;
                    System.out.println("Successfully bought Greater Potion of Mana (+40 Mana)");
                    System.out.println("Remaining coins: "+player.coins);
                }
                else{
                    System.out.println("Not enough coins!");
                }
            }
            else if(choice == shopItems.size() + 3){
                buying = false;
            }
            else{
                System.out.println("Invalid input");
            }
        }
    }

    public void addShopItems(Player player){
        if(player.classChoice.equalsIgnoreCase("wizard")){
            shopItems.add(new lionheartOrb(44));
            shopItems.add(new waywatcherOrb(60));
            shopItems.add(new blessedBladeOrb(74));
            shopItems.add(new dragonFangOrb(90));
        }
        else if(player.classChoice.equalsIgnoreCase("ranger")){
            shopItems.add(new lionheartBow(49));
            shopItems.add(new waywatcherBow(61));
            shopItems.add(new blessedBladeBow(76));
            shopItems.add(new dragonFangBow(93));
        }
        else if(player.classChoice.equalsIgnoreCase("fighter")){
            shopItems.add(new lionheartSword(41));
            shopItems.add(new waywatcherSword(50));
            shopItems.add(new blessedBladeSword(62));
            shopItems.add(new dragonFangSword(74));
        }
        else if(player.classChoice.equalsIgnoreCase("rogue")){
            shopItems.add(new lionheartKnives(61));
            shopItems.add(new waywatcherKnives(70));
            shopItems.add(new blessedBladeKnives(82));
            shopItems.add(new dragonFangKnives(94));
        }
    }
}
