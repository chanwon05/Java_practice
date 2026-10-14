import java.util.List;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, GameCharacter> characters = new HashMap<>();

        characters.put("전사", new Warrior("전사", 100));
        characters.put("궁수", new Archer("궁수", 80));
        characters.put("기사", new Knight("기사", 150));

        characters.values().forEach(character -> {
            character.attack();
            character.showInfo();
            character.heal(10);     
            character.showInfo();     
        });

        characters.values().stream()
            .filter(character -> character.getHp() >= 100)
            .map(character -> character.getName())
            .forEach(name -> System.out.println(name));         

        List<String> names = characters.values().stream()
            .filter(character -> character.getHp() < 100)
            .map(character -> character.getName())
            .toList();

        System.out.println(names);
        System.out.println("선택된 캐릭터 수: " + names.size());

    }     
}

interface SkillUser {
    void useSkill();
}

class GameCharacter {
    private String name;
    private int hp;

    GameCharacter(String name, int hp) {
        this.name = name;
        this.hp = Math.max(0,hp);
    }

    String getName() {
        return name;
    }

    void attack() {
        System.out.println(name + ": 공격합니다!");
    }

    void takeDamage(int damage){
        if(damage <= 0) {
            System.out.println("피해량은 0보다 커야 합니다.");
            return;
        }

        hp = Math.max(0, hp - damage);
    }

    void heal(int amount){
        if(amount <= 0) {
            System.out.println("회복량이 0보다 커야 합니다.");
            return;
        }
        hp+=amount;
    }

    void showInfo() {
        System.out.println(name + "의 체력: " + hp);
    }

    int getHp(){
        return hp;
    }
}

class Warrior extends GameCharacter {
    Warrior(String name, int hp){
        super(name, hp);
    }

    @Override 
    void attack(){
        System.out.println(getName() + ": 검으로 공격합니다!");
    }

}

class Mage extends GameCharacter implements SkillUser{
    Mage(String name, int hp){
        super(name, hp);
    }

    @Override 
    void attack() {
        System.out.println(getName() + ": 마법으로 공격합니다!");
    }

    @Override
    public void useSkill(){
        System.out.println(getName()+ ": 화염 폭풍!");
    }
}

class Archer extends GameCharacter implements SkillUser{
    Archer(String name, int hp){
        super(name, hp);
    }

    @Override
    void attack() {
        System.out.println(getName() + ": 활로 공격합니다!");
    }

    @Override 
    public void useSkill(){
        System.out.println(getName()+ ": 화살 폭풍!");
    }
}

class Knight extends GameCharacter implements SkillUser{
    Knight(String name, int hp){
        super(name, hp);
    }

    @Override
    void attack() {
        System.out.println(getName() + ": 창으로 공격합니다!");
    }

    @Override 
    public void useSkill(){
        System.out.println(getName()+ ": 창 폭풍!");
    }

}