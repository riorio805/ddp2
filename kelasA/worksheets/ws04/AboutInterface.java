package ddp2.kelasA.worksheets.ws04;

public class AboutInterface {
    public static void main(String[] args) {
        Speakable obj;
        obj = new Dog("Cute");          //print: I am a dog.
        System.out.println(obj);              //print: Dog: Cute
        obj.speak();                          //print: Woof! Woof!
        obj = new Cat("Lazy");          //print: I am a cat.
        System.out.println(obj);              //print: Cat: Lazy
        obj.speak();                          //print: Meow! Meow!
        Speakable[] arr = new Speakable[3];
        arr[0] = obj;
        arr[1] = obj;
        arr[2] = new Dog("Adorable");   //print: I am a dog.
        for (Speakable s: arr) s.speak();
        /* Print:
        Meow! Meow!
        Meow! Meow!
        Woof! Woof!
         */
    }
}

