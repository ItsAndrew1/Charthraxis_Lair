package me.andrew;

public abstract class Items{
    protected String name;
    protected boolean stackable;
    protected int price;

    public Items(String name, boolean stackable, int price){
        this.name = name;
        this.stackable = stackable;
        this.price = price;
    }
    public boolean isStackable() {return stackable;}
    public String getName(){return name;}
    public abstract void use(Player player);
}

class basicHealthPotion extends Items{
    private final int healAmount;

    public basicHealthPotion(int healAmount){
        super("Basic Health Potion (+"+healAmount+" HP)", true, 0);
        this.healAmount = healAmount;
    }

    @Override
    public void use(Player player){
        player.heal(healAmount);
        System.out.println(player.getName() + " drank "+getName()+" and healed for "+healAmount+" HP");
    }
}
class greaterHealthPotion extends Items{
    private int healAmount;

    public greaterHealthPotion(int healAmount){
        super("Greater Potion of Health (+"+healAmount+" HP)", true, 75);
        this.healAmount = healAmount;
    }

    @Override
    public void use(Player player){
        if(player.maxHealth - player.health >= healAmount){
            player.heal(healAmount);
            System.out.println(player.getName() + " drank "+getName()+" and healed for "+healAmount+" HP");
        }
        else if(player.maxHealth - player.health < healAmount){
            healAmount = player.maxHealth - player.health;
            player.heal(healAmount);
            System.out.println(player.getName() + " drank "+getName()+" and healed for "+healAmount+" HP");
        }
    }
}

class basicManaPotion extends Items{
    private int manaRegenAmount;

    public basicManaPotion (int manaRegenAmount){
        super("Basic Mana Potion (+ "+manaRegenAmount+" Mana)", true, 0);
        this.manaRegenAmount = manaRegenAmount;
    }

    @Override
    public void use(Player player){
        if(player.maxMana - player.mana >= manaRegenAmount){
            player.manaRegen(manaRegenAmount);
            System.out.println(player.getName() + " drank "+getName()+" and regained "+manaRegenAmount+" Mana");
        }
        else if(player.maxMana - player.mana < manaRegenAmount){
            manaRegenAmount = player.maxMana - player.mana;
            player.manaRegen(manaRegenAmount);
            System.out.println(player.getName() + " drank "+getName()+" and regained "+manaRegenAmount+" Mana");
        }
    }
}
class greaterManaPotion extends Items{
    private int manaRegenAmount;

    public greaterManaPotion(int manaRegenAmount){
        super("Greater Potion of Mana (+ "+manaRegenAmount+" Mana)", true, 75);
        this.manaRegenAmount = manaRegenAmount;
    }

    @Override
    public void use(Player player){
        if(player.maxMana - player.mana >= manaRegenAmount){
            player.manaRegen(manaRegenAmount);
            System.out.println(player.getName() + " drank "+getName()+" and regained "+manaRegenAmount+" Mana");
        }
        else if(player.maxMana - player.mana < manaRegenAmount){
            manaRegenAmount = player.maxMana - player.mana;
            player.manaRegen(manaRegenAmount);
            System.out.println(player.getName() + " drank "+getName()+" and regained "+manaRegenAmount+" Mana");
        }
    }
}

class basicOrb extends Items{
    private final int attackDmg;
    public basicOrb(int attackDmg){
        super("Basic Orb (35 DMG)", false, 0);
        this.attackDmg = attackDmg;
    }

    @Override
    public void use(Player player){
        System.out.println(player.getName() + " got "+getName());
        player.attackDmg = attackDmg;
    }
}
class lionheartOrb extends Items{
    private final int attackDmg;

    public lionheartOrb(int attackDmg){
        super("Lionheart Orb ("+attackDmg+" DMG)", false, 150);
        this.attackDmg = attackDmg;
    }

    @Override
    public void use(Player player){
        player.attackDmg = attackDmg;
    }
}
class waywatcherOrb extends Items{
    private final int attackDmg;

    public waywatcherOrb(int attackDmg){
        super("Waywatcher's Orb ("+attackDmg+" DMG)", false, 300);
        this.attackDmg = attackDmg;
    }

    @Override
    public void use(Player player){
        player.attackDmg = attackDmg;
    }
}
class blessedBladeOrb extends Items{
    private final int attackDmg;

    public blessedBladeOrb(int attackDmg){
        super("Orb of the Blessed Blade ("+attackDmg+" DMG)", false, 500);
        this.attackDmg = attackDmg;
    }

    @Override
    public void use(Player player){
        player.attackDmg = attackDmg;
    }
}
class dragonFangOrb extends Items{
    private final int attackDmg;

    public dragonFangOrb(int attackDmg){
        super("Dragon Fang Orb ("+attackDmg+" DMG)", false, 650);
        this.attackDmg = attackDmg;
    }

    @Override
    public void use(Player player){
        player.attackDmg = attackDmg;
    }
}

class basicSword extends Items{
    private final int attackDmg;
    public basicSword(int attackDmg){
        super("Basic Sword (30 DMG)", false, 0);
        this.attackDmg = attackDmg;
    }

    @Override
    public void use(Player player){
        System.out.println(player.getName() + " got "+getName());
        player.attackDmg = attackDmg;
    }
}
class lionheartSword extends Items{
    private final int attackDmg;

    public lionheartSword(int attackDmg){
        super("Lionheart Sword ("+attackDmg+" DMG)", false, 150);
        this.attackDmg = attackDmg;
    }

    @Override
    public void use(Player player){
        player.attackDmg = attackDmg;
    }
}
class waywatcherSword extends Items{
    private final int attackDmg;

    public waywatcherSword(int attackDmg){
        super("Waywatcher's Sword ("+attackDmg+" DMG)", false, 300);
        this.attackDmg = attackDmg;
    }

    @Override
    public void use(Player player){
        player.attackDmg = attackDmg;
    }
}
class blessedBladeSword extends Items{
    private final int attackDmg;

    public blessedBladeSword(int attackDmg){
        super("Sword of the Blessed Blade ("+attackDmg+" DMG)", false, 500);
        this.attackDmg = attackDmg;
    }

    @Override
    public void use(Player player){
        player.attackDmg = attackDmg;
    }
}
class dragonFangSword extends Items{
    private final int attackDmg;

    public dragonFangSword(int attackDmg){
        super("Dragon Fang Sword ("+attackDmg+" DMG)", false, 650);
        this.attackDmg = attackDmg;
    }

    @Override
    public void use(Player player){
        player.attackDmg = attackDmg;
    }
}

class basicBow extends Items{
    private final int attackDmg;
    public basicBow(int attackDmg){
        super("Basic Bow (40 DMG)", false, 0);
        this.attackDmg = attackDmg;
    }

    @Override
    public void use(Player player){
        player.attackDmg = attackDmg;
        System.out.println(player.getName() + " got "+getName());
    }
}
class lionheartBow extends Items{
    private final int attackDmg;

    public lionheartBow(int attackDmg){
        super("Lionheart Longbow ("+attackDmg+" DMG)", false, 150);
        this.attackDmg = attackDmg;
    }

    @Override
    public void use(Player player){
        player.attackDmg = attackDmg;
    }
}
class waywatcherBow extends Items{
    private final int attackDmg;

    public waywatcherBow(int attackDmg){
        super("Waywatcher's Longbow ("+attackDmg+" DMG)", false, 300);
        this.attackDmg = attackDmg;
    }

    @Override
    public void use(Player player){
        player.attackDmg = attackDmg;
    }
}
class blessedBladeBow extends Items{
    private final int attackDmg;

    public blessedBladeBow(int attackDmg){
        super("Longbow of the Blessed Blade ("+attackDmg+" DMG)", false, 500);
        this.attackDmg = attackDmg;
    }

    @Override
    public void use(Player player){
        player.attackDmg = attackDmg;
    }
}
class dragonFangBow extends Items{
    private final int attackDmg;

    public dragonFangBow(int attackDmg){
        super("Dragon Fang Longbow ("+attackDmg+" DMG)", false, 650);
        this.attackDmg = attackDmg;
    }

    @Override
    public void use(Player player){
        player.attackDmg = attackDmg;
    }
}

class basicKnives extends Items{
    private final int attackDmg;
    public basicKnives(int attackDmg){
        super("Basic Knives (50 DMG)", false, 0);
        this.attackDmg = attackDmg;
    }

    @Override
    public void use(Player player){
        player.attackDmg = attackDmg;
        System.out.println(player.getName() + " got "+getName());
    }
}
class lionheartKnives extends Items{
    private final int attackDmg;

    public lionheartKnives(int attackDmg){
        super("Lionheart Knives ("+attackDmg+" DMG)", false, 150);
        this.attackDmg = attackDmg;
    }

    @Override
    public void use(Player player){
        player.attackDmg = attackDmg;
    }
}
class waywatcherKnives extends Items{
    private final int attackDmg;

    public waywatcherKnives(int attackDmg){
        super("Waywatcher's Knives", false, 300);
        this.attackDmg = attackDmg;
    }

    @Override
    public void use(Player player){
        player.attackDmg = attackDmg;
    }
}
class blessedBladeKnives extends Items{
    private final int attackDmg;

    public blessedBladeKnives(int attackDmg){
        super("Knives of the Blessed Blade ("+attackDmg+" DMG)", false, 500);
        this.attackDmg = attackDmg;
    }

    @Override
    public void use(Player player){
        player.attackDmg = attackDmg;
    }
}
class dragonFangKnives extends Items{
    private final int attackDmg;

    public dragonFangKnives(int attackDmg){
        super("Dragon Fang Knives ("+attackDmg+" DMG)", false, 650);
        this.attackDmg = attackDmg;
    }

    @Override
    public void use(Player player){
        player.attackDmg = attackDmg;
    }
}

class rubyOfTheFallenDragon extends Items{
    public rubyOfTheFallenDragon(){
        super("Ruby of The Fallen Dragon", false, 0);
    }

    @Override
    public void use(Player player){
        System.out.println("The curse has been BROKEN!");
    }
}