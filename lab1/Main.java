

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        FractionSet f1 = new FractionSet();
        f1.add(2, -4);
        f1.add(1, 3);
        f1.add(1, 6);
        System.out.println("min: " + f1.min());
        System.out.println("max: " + f1.max());
        Fraction f0 = new Fraction(1,1);
        System.out.println("countLarger: " + f1.countLarger(f0));
        System.out.println("countSmaller: " + f1.countSmaller(f0));

        Polynomial pol1 = new Polynomial(f1);
        System.out.println("Polynomial 1: " + pol1);
        FractionSet f2 = new FractionSet();
        f2.add(1, 4);
        f2.add(2, 3);
        Polynomial pol2 = new Polynomial(f2);
        System.out.println("Polynomial 2: " + pol2);
        System.out.println("Polynomial 2 + Polynomial 1 : " + pol2.sum(pol1));

    }
}

