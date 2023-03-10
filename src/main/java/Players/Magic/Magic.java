package Players.Magic;


import Players.Character;
import Players.behaviours.iAttack;
import Players.behaviours.iDefend;
import WeaponInventory.Spells;
import WeaponInventory.Weapon;


import java.util.ArrayList;

public abstract class Magic extends Character implements iAttack, iDefend {

    private Spells currentSpell;
    private ArrayList<Spells> spellBook;
    private ArrayList<Weapon> petList;
    private ArrayList<Weapon> allowed_pets;
    private Weapon currentWeapon;

    private int SP;


    public Magic(String name, int life, int strength, int defense, int SP) {
        super(name, life, strength, defense);
        this.currentSpell = Spells.LIGHTNINGBOLT;
        this.spellBook = new ArrayList<Spells>();
        this.petList = new ArrayList<Weapon>();
        this.allowed_pets = new ArrayList<Weapon>();
        this.currentWeapon = Weapon.LIZARD;
        this.SP = SP;
    }

    public int getSP() {
        return SP;
    }

    public void setSP(int newSP){
        this.SP = newSP;
    }

    public int reduceSP( int usedSP) {
        return this.SP -= usedSP;
    }

    public int getSPNeededForSpell(Spells spellToUse){
        return spellToUse.getSpellCost();
    }

    public Spells getCurrentSpell() {
        return currentSpell;
    }

    public void setCurrentSpell(Spells currentSpell) {
        this.currentSpell = currentSpell;
    }

    public ArrayList<Spells> getSpellBook() {
        return spellBook;
    }

    public void setSpellBook(ArrayList<Spells> spellBook) {
        this.spellBook = spellBook;
    }

    public void setCurrentWeapon(Weapon set_to_this){
        this.currentWeapon = set_to_this;
    }
    public Weapon getCurrentWeapon(){
        return this.currentWeapon;
    }


    public void setAllowedPet(ArrayList<Weapon> input_allowedPet) {

        ArrayList<Weapon> returnList = new ArrayList<>();

        for (Weapon weapon : input_allowedPet) {
            if (weapon.getType().equals("Pet")) {
                returnList.add(weapon);
            }
        }
        this.allowed_pets = returnList;

    }

    @Override
    public int getAttackValue(){
        return this.getStrength() + this.getCurrentWeapon().getDamage() + this.getCurrentSpell().getDamage();
    }

    @Override
    public void attack(iDefend enemy) {

        // get the attack
        int attack = this.getAttackValue();

        // get the defence
        int defense = enemy.getDefense();

        // calculate damage
        int damage = attack - (defense / 2);

        // reduce life
        enemy.receiveDamage(damage);

        //available SP
        int availableSP = this.getSP();

        //get SP from chosen spell
        int spFromSpell = this.getCurrentSpell().getSpellCost();

        //set reduced SP as new SP
        this.setSP(availableSP - spFromSpell);
    }

    public void receiveDamage(int damage){};

    public int getDefense() {
        return this.getDefense();

    }
}
