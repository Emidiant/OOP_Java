import java.util.ArrayList;
import java.util.Collections;

public class FractionSet {
    private ArrayList<Fraction> set;
    private Fraction MaxF;
    private Fraction MinF;

    FractionSet() {
        set = new ArrayList<Fraction>();
        MaxF = null;
        MinF = null;
    }

    public int size() {
        return this.set.size();
    }

    public Fraction get(int i) {
        return (Fraction)this.set.get(i);
    }

    public void add(int num, int den) {
        Fraction frac = new Fraction(num, den);
        this.MaxF = null;
        this.MinF = null;
        this.set.add(frac);

        if (this.MaxF == null) {
            this.MaxF = Collections.max(this.set);
        }
        if (this.MinF == null) {
            this.MinF = Collections.min(this.set);
        }
    }

    public void add(Fraction frac) {
        this.set.add(frac);
    }

    public Fraction max() {
        return this.MaxF;
    }

    public Fraction min() {
        return this.MinF;
    }

    public int countLarger(Fraction frac) {
        int i = 0;
        int count = 0;
        while (i < set.size())
        {
            if (set.get(i).compareTo(frac) > 0)
                count++;
            i++;
        }
        return count;
    }

    public int countSmaller(Fraction frac) {
        int i = 0;
        int count = 0;
        while (i < set.size())
        {
            if (set.get(i).compareTo(frac) < 0)
                count++;
            i++;
        }
        return count;
    }
}
