

public class Fraction implements Comparable<Fraction> {
    private int num;
    private int den;

    static int nod(int a, int b) {
        while(a != 0 && b != 0) {
            if (a > b) {
                a %= b;
            } else {
                b %= a;
            }
        }
        return a + b;
    }

    Fraction(int num, int den){
        this.num = num;
        this.den = den;
        int sign = 1;
        if (num < 0){
            sign = -sign;
            this.num = -num;
        }
        if (den < 0){
            sign = -sign;
            this.den= -den;
        }
        int x = nod(this.num, this.den);
        this.num /= x * sign;
        this.den /= x;
    }


    public Fraction sum(Fraction x) {
        Fraction frac = new Fraction(this.num * x.den + x.num * this.den, this.den * x.den);
        return frac;
    }

    public int compareTo(Fraction other) {
        if (this.num * other.den > other.num * this.den) {
            return 1;
        } else if (this.num * other.den < other.num * this.den)
            return -1;
        else{
            return  0;
        }
    }

    public int getNum(){
        return this.num;
    }

    public String toString() {
        return this.num + "/" + this.den;
    }
}



