package me.andrew;

import java.util.Scanner;

public class combatManager{
    Abilities chosen;
    public void fight(Player player, Enemies enemy, Scanner scanner){
        int getEnemyDamage;
        int getCritDamage;
        int manaRegenAmount;
        while(player.isAlive() && enemy.isAlive()){
            getEnemyDamage = enemy.getDamage();
            getCritDamage = enemy.getCritDamage();
            System.out.println("Your HP: "+player.health+"       "+enemy.getName()+"'s HP: "+enemy.getHealth());
            System.out.print("Mana: "+player.mana+"\n");
            System.out.println("Choose an ability: ");
            int i = 1;
            for(Abilities abilities : player.abilities){
                System.out.println(i + ") " + abilities.name +" ("+abilities.description +") - "+ abilities.getAbilityDmg(player) +" Damage, "+ abilities.manaCost+" mana");
                i++;
            }
            System.out.println("4) Auto Attack - 0 mana");
            System.out.println("5) Open inventory");
            int choice = scanner.nextInt();

            if(choice >= 1 && choice <= player.abilities.size()){
                chosen = player.abilities.get(choice-1);
                if(player.mana >= chosen.manaCost){
                    chosen.use(player, enemy);
                }
                else{
                    enemy.attackDmg = 0;
                    enemy.critDamage = 0;
                    System.out.println("Insufficient mana!");
                }
            }
            else if(choice == 4){
                player.autoAttack(enemy);
            }
            else if(choice == 5){
                enemy.attackDmg = 0;
                enemy.critDamage = 0;
                player.useInventory(scanner, player);
            }
            enemy.attack(player);
            if(player.health <= 0){
                System.out.println("DEFEATED! GAME OVER");
                break;
            }
            else if(enemy.health <= 0){
                manaRegenAmount = 20;
                System.out.println(enemy.getName() +" defeated! +50 coins");
                if(player.mana != player.maxMana){
                    if(player.maxMana - player.mana >= manaRegenAmount){
                        player.manaRegen(manaRegenAmount);
                    }
                    else if(player.maxMana - player.mana < manaRegenAmount){
                        manaRegenAmount = player.maxMana - player.mana;
                        player.manaRegen(manaRegenAmount);
                    }
                }
            }
            if(player.mana != player.maxMana){
                player.mana+=5;
            }
            enemy.attackDmg = getEnemyDamage;
            enemy.critDamage = getCritDamage;
        }
    }
}
