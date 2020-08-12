package Lab_2;

public class Main {
    public static void main(String[] args) {
        StringGenerator gen = new StringGenerator();
        for (int i = 0; i < 5; i++) {
            System.out.println("String : " + gen.generateString(20));

        }
    }
}
