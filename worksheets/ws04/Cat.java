package ddp2.worksheets.ws04;

public class Cat implements Speakable {
    private String name;
    public Cat(String name) {
        this.name = name;
        System.out.println("I am a cat.");
    }
    public void speak() {
        System.out.println("Meow! Meow!");
    }
    public String toString() {
        return "Cat: " + name;
    }
}