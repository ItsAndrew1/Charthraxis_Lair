package me.andrew;

import java.util.*;

public class Player{
    int health, maxHealth;
    int attackDmg;
    int mana, maxMana;
    int critChance;
    private String name;
    double coins = 0;
    String classChoice = "";

    ArrayList<Items> inventory = new ArrayList<>();
    ArrayList<Abilities> abilities = new ArrayList<>();
    HashMap<String, Integer> stackableItems = new HashMap<>();
    List<Items> uniqueItems = new ArrayList<>();

    int playerX;
    int playerY;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public boolean isAlive(){
        return health > 0;
    }
    public void heal(int healAmount){
        health += healAmount;
    }
    public void manaRegen(int manaAmount){mana += manaAmount;}

    public void autoAttack(Enemies enemy){
        critChance = (int)(Math.random()*10);
        if(critChance > 8){
            attackDmg += 10;
            System.out.println("CRITICAL HIT! You've dealt "+attackDmg+" damage!");
            enemy.health -= attackDmg;
            attackDmg -= 10;
        }
        else{
            System.out.println("Damage dealt: "+attackDmg);
            enemy.health -= attackDmg;
        }
    }

    public void takeDamage(int dmg){
        health -= dmg;
        System.out.println(getName() + " takes " + dmg +" damage!");
    }

    public void useInventory(Scanner scanner, Player player){
        System.out.println("=======INVENTORY=======");

        stackableItems.clear();
        uniqueItems.clear();

        addItems(this);
        char choice;
        int i = 1;
        for(Map.Entry<String, Integer> e : stackableItems.entrySet()){
            System.out.println(i +") " + e.getKey() + " - " + e.getValue());
            i++;
        }
        for(Items e : uniqueItems){
            System.out.println(i +") " + e.getName());
            i++;
        }
        System.out.print("Use Z - Basic Health Potion; X - Basic Mana Potion; C - Greater Health Potion; V - Greater Mana Potion");
        System.out.println();
        choice = scanner.next().charAt(0);

        switch(choice){
            case 'z':
                if(hasItem(basicHealthPotion.class)){
                    useItem(basicHealthPotion.class, player);
                }
                else{
                    System.out.println("No Basic Potions of Health left!");
                }
                break;

            case 'x':
                if(hasItem(basicManaPotion.class)){
                    useItem(basicManaPotion.class, player);
                }
                else{
                    System.out.println("No Basic Potions of Mana left!");
                }
                break;

            case 'c':
                if(hasItem(greaterHealthPotion.class)){
                    useItem(greaterHealthPotion.class, player);
                }
                else{
                    System.out.println("No Greater Potions of Health left!");
                }
                break;

            case 'v':
                if(hasItem(greaterManaPotion.class)){
                    useItem(greaterManaPotion.class, player);
                }
                else{
                    System.out.println("No Greater Potions of Mana left!");
                }
                break;

            default:
                System.out.println("Invalid input!");
        }
    }

    public void addItems(Player player){
        for(Items e : player.inventory){
            if(e.isStackable()){
                stackableItems.put(e.getName(), stackableItems.getOrDefault(e.getName(), 0) + 1);
            }
            else{
                uniqueItems.add(e);
            }
        }
    }

    public boolean hasItem(Class<? extends Items> itemClass){
        for(Items item:inventory){
            if(item.getClass() == itemClass){
                return true;
            }
        }
        return false;
    }

    public void useItem(Class<? extends Items> itemClass, Player player){
        for (Items item : inventory) {
            if(item.getClass() == itemClass){
                item.use(player);
                inventory.remove(item);
                return;
            }
        }
        System.out.println("You don't have any "+itemClass.getSimpleName()+" on  you!");
    }
}

class Abilities{
    String name;
    String description;
    int manaCost;
    private final int baseAbilityDmg;
    double healingPercent;
    boolean isAttackable, isHealable, isDisabling;

    public Abilities(String name, String description, int manaCost, int baseAbilityDmg, double healingPercent, boolean isAttackable, boolean isHealable, boolean isDisabling){
        this.name = name;
        this.description = description;
        this.manaCost = manaCost;
        this.baseAbilityDmg = baseAbilityDmg;
        this.isAttackable = isAttackable;
        this.isDisabling = isDisabling;
        this.isHealable = isHealable;
        this.healingPercent = healingPercent;
    }

    public int getAbilityDmg(Player player){
        if(isHealable && isAttackable){
            return (int) (baseAbilityDmg + 0.5 * player.attackDmg);
        }
        else if(isDisabling){
            return (int)(baseAbilityDmg +  0.4 * player.attackDmg);
        }
        else if(isAttackable){
            return (int)(baseAbilityDmg + 0.6 * player.attackDmg);
        }
        return 0;
    }

    public void use(Player caster, Enemies target){
        int healAmount = (int)((healingPercent/100)*caster.maxHealth);
        if(isAttackable && isHealable){
            target.health -= getAbilityDmg(caster);
            if(caster.maxHealth - caster.health >= healAmount){
                caster.heal(healAmount);
                System.out.println(caster.getName() + " uses "+name+"! ");
                caster.mana -= manaCost;
            }
            else if(caster.maxHealth - caster.health < healAmount){
                caster.heal(caster.maxHealth-caster.health);
                System.out.println(caster.getName() + " uses "+name+"! ");
                caster.mana -= manaCost;
            }
        }
        else if(isHealable){
            target.attackDmg = 0;
            target.critDamage = 0;
            if(caster.maxHealth - caster.health >= healAmount){
                caster.heal(healAmount);
                System.out.println(caster.getName() + " uses "+name+"! ");
                caster.mana -= manaCost;
            }
            else if(caster.maxHealth - caster.health < healAmount){
                caster.heal(caster.maxHealth-caster.health);
                System.out.println(caster.getName() + " uses "+name+"! ");
                caster.mana -= manaCost;
            }
        }
        else if(isDisabling){
            target.health -= getAbilityDmg(caster);
            target.attackDmg = 0;
            target.critDamage = 0;
            System.out.println(caster.getName() + " uses "+name+"! ");
            caster.mana -= manaCost;
        }
        else if(isAttackable){
            target.health -= getAbilityDmg(caster);
            System.out.println(caster.getName() + " uses "+name+"! ");
            caster.mana -= manaCost;
        }
    }
}