package me.andrew;

public abstract class Enemies{
    protected int health;
    protected int attackDmg;
    protected int enemyX, enemyY;
    char symbol;
    protected int critDamage;
    protected String attackMsg, critAttackMsg;
    protected String name;

    public Enemies(int enemyX, int enemyY, char symbol){
        this.enemyY = enemyY;
        this.enemyX = enemyX;
        this.symbol = symbol;
    }

    public int getHealth(){
        return health;
    }
    public int  getCritDamage(){
        return critDamage;
    }
    public int getDamage(){
        return attackDmg;
    }
    public String getName(){
        return name;
    }

    public boolean isAlive(){
        return health > 0;
    }

    public abstract void attack(Player player);
}

class Skeletons extends Enemies{
    public Skeletons(int enemyX, int enemyY, int attackDmg, int critDamage, int health){
        super(enemyX, enemyY, 'S');
        this.attackDmg = attackDmg;
        this.critDamage = critDamage;
        this.health = health;
        name = "Skeleton";
    }

    @Override
    public void attack(Player player){
        attackMsg = "The skeleton STRIKES! ";
        critAttackMsg = "The skeleton landed a CRITICAL HIT! ";
        int critChance = (int)(Math.random()*10);
        if(critChance > 6){
            System.out.print(critAttackMsg);
            player.takeDamage(critDamage);
        }
        else{
            System.out.print(attackMsg);
            player.takeDamage(attackDmg);
        }
    }
}

class Zombies extends Enemies{
    public Zombies(int enemyX, int enemyY, int attackDmg, int critDamage, int health){
        super(enemyX, enemyY, 'Z');
        this.attackDmg = attackDmg;
        this.critDamage = critDamage;
        this.health = health;

        name = "Zombie";
    }

    @Override
    public void attack(Player player){
        attackMsg = getName()+" bites you!";
        critAttackMsg = getName()+" landed a CRITICAL HIT! ";
        int critChance = (int)(Math.random() * 10);
        if(critChance > 7){
            System.out.print(critAttackMsg);
            player.takeDamage(critDamage);
        }
        else{
            System.out.print(attackMsg);
            player.takeDamage(attackDmg);
        }
    }
}

class Boss extends Enemies {
    public Boss(int enemyX, int enemyY, int attackDmg, int critDamage, int health) {
        super(enemyX, enemyY, 'B');
        this.attackDmg = attackDmg;
        this.critDamage = critDamage;
        this.health = health;
        name = "Charthraxis";
    }

    @Override
    public void attack(Player player) {
        attackMsg = getName() + "swings it's claws at you!";
        critAttackMsg = getName() + "lashes his tail upon you, getting a CRITICAL HIT!";

        int critChance = (int) (Math.random() * 10);
        if (critChance % 2 == 0 && critChance < 5) {
            System.out.print(critAttackMsg);
            player.takeDamage(critDamage);
        } else {
            System.out.print(attackMsg);
            player.takeDamage(attackDmg);
        }
    }
}