package ddp2.kelasA.worksheets.ws04;

public class Dog implements Speakable {
    private String name;
    public Dog(String name) {
        this.name = name;
        System.out.println("I am a dog.");
    }
    public void speak() {
        System.out.println("Woof! Woof!");
    }
    public String toString(){
        return "Dog: " + name;
    }
}
